package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RedirectTrackerUtils {

	public static class RedirectResult {
		public final String url;
		public final long responseTime;
		public final int statusCode;
		public final int repeatCount;
		public final boolean isBroken;

		public RedirectResult(String url, long responseTime, int statusCode, int repeatCount, boolean isBroken) {
			this.url = url;
			this.responseTime = responseTime;
			this.statusCode = statusCode;
			this.repeatCount = repeatCount;
			this.isBroken = isBroken;
		}
	}

	private static HttpURLConnection createConnection(String url, String method) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod(method);
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		conn.setInstanceFollowRedirects(false); // manual redirection
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/114 Safari/537.36");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
		conn.setRequestProperty("Accept", "*/*");
		return conn;
	}
	
	private static boolean isTrustedSocialLink(String url) {
        return url.contains("facebook.com") || url.contains("instagram.com");
    }


	public static List<RedirectResult> fetchAllAnchorLinksWithResponseTime(WebDriver driver, Properties prop) {
		boolean found = WaitUtils.waitForAnchorTags(10);
		if (!found) {
			System.out.println("⚠️ No anchor tags found after waiting 10 seconds.");
			return Collections.emptyList();
		}

		List<WebElement> anchors = driver.findElements(By.tagName("a"));
		if (anchors == null || anchors.isEmpty()) {
			System.out.println("⚠️ No anchor <a> tags found on the current page (non-threaded method).");
			return Collections.emptyList();
		}

		Map<String, Integer> urlCountMap = new HashMap<>();
		List<RedirectResult> resultList = new ArrayList<>();

		int maxLinks = Integer.parseInt(prop.getProperty("max.links", "100"));
		int maxRedirects = Integer.parseInt(prop.getProperty("max.redirects", "5"));

		int processed = 0;
		for (WebElement anchor : anchors) {
			if (processed >= maxLinks)
				break;

			String url = anchor.getAttribute("href");
			if (url != null && url.startsWith("http")) {
				int count = urlCountMap.getOrDefault(url, 0) + 1;
				urlCountMap.put(url, count);

				long responseTime = -1;
				int statusCode = -1;
				boolean isBroken = false;
				String finalUrl = url;

				try {
					long startTime = System.currentTimeMillis();
					int redirectCount = 0;

					while (redirectCount < maxRedirects) {
						HttpURLConnection connection = createConnection(finalUrl, "HEAD");
						connection.connect();
						statusCode = connection.getResponseCode();

						if (statusCode >= 300 && statusCode < 400) {
							String redirectLocation = connection.getHeaderField("Location");
							if (redirectLocation == null)
								break;
							finalUrl = new URL(new URL(finalUrl), redirectLocation).toString();
							redirectCount++;
						} else {
							break;
						}
					}

					responseTime = System.currentTimeMillis() - startTime;

                    // Fallback to GET if HEAD fails or status is error
					if (statusCode == -1 || statusCode >= 400 || statusCode == 405) {
						HttpURLConnection fallbackConn = createConnection(finalUrl, "GET");
						fallbackConn.connect();
						statusCode = fallbackConn.getResponseCode();
						responseTime = System.currentTimeMillis() - startTime; // recalculate response time
						isBroken = statusCode >= 400;
					}

				} catch (IOException e) {
                    isBroken = !isTrustedSocialLink(url);
                }

				resultList.add(new RedirectResult(url, responseTime, statusCode, count, isBroken));
				processed++;
			}
		}

		return resultList;
	}

	public static List<RedirectResult> fetchLinksWithThreadPool(WebDriver driver, int threadCount, Properties prop)
			throws InterruptedException, ExecutionException {

		boolean found = WaitUtils.waitForAnchorTags(10);
		if (!found) {
			System.out.println("⚠️ No anchor tags found after waiting 10 seconds (threaded method).");
			return Collections.emptyList();
		}

		List<WebElement> anchors = driver.findElements(By.tagName("a"));
		if (anchors == null || anchors.isEmpty()) {
			System.out.println("⚠️ No anchor <a> tags found on the current page (threaded method).");
			return Collections.emptyList();
		}

		Map<String, Integer> urlCountMap = new HashMap<>();
		List<Callable<RedirectResult>> tasks = new ArrayList<>();

		int maxLinks = Integer.parseInt(prop.getProperty("max.links", "100"));
		int processed = 0;

		for (WebElement anchor : anchors) {
			if (processed >= maxLinks)
				break;

			String url = anchor.getAttribute("href");
			if (url != null && url.startsWith("http")) {
				int count = urlCountMap.getOrDefault(url, 0) + 1;
				urlCountMap.put(url, count);
				int repeatCount = count;

				tasks.add(() -> {
					long responseTime = -1;
					int statusCode = -1;
					boolean isBroken = false;

					try {
						long startTime = System.currentTimeMillis();

						HttpURLConnection conn = createConnection(url, "HEAD");
						conn.connect();
						statusCode = conn.getResponseCode();
					    responseTime = System.currentTimeMillis() - startTime;


                        // Retry with GET if blocked or error
						if (statusCode >= 400 || statusCode == 405) {
					        startTime = System.currentTimeMillis(); // Restart time tracking
							HttpURLConnection retryConn = createConnection(url, "GET");
							retryConn.connect();
							statusCode = retryConn.getResponseCode();
							responseTime = System.currentTimeMillis() - startTime;
						}

						isBroken = statusCode >= 400;

					} catch (IOException e) {
                        isBroken = !isTrustedSocialLink(url);
                        responseTime = 0; // Set explicit fallback if error
					}

					return new RedirectResult(url, responseTime, statusCode, repeatCount, isBroken);
				});
				processed++;
			}
		}

		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		List<Future<RedirectResult>> futures = executor.invokeAll(tasks);
		List<RedirectResult> results = new ArrayList<>();
		for (Future<RedirectResult> future : futures) {
			results.add(future.get());
		}
		executor.shutdown();
		return results;
	}
	
	// Same structure as fetchLinksWithThreadPool, but for direct input URLs
	public static List<RedirectResult> fetchGivenUrlsWithThreadPool(List<String> urls, int threadCount) throws InterruptedException, ExecutionException {
	    List<Callable<RedirectResult>> tasks = new ArrayList<>();
	    Map<String, Integer> urlCountMap = new HashMap<>();

	    for (String url : urls) {
	        int count = urlCountMap.getOrDefault(url, 0) + 1;
	        urlCountMap.put(url, count);
	        int repeatCount = count;

	        tasks.add(() -> {
	            long responseTime = -1;
	            int statusCode = -1;
	            boolean isBroken = false;

	            try {
	                long startTime = System.currentTimeMillis();
	                HttpURLConnection conn = createConnection(url, "HEAD");
	                conn.connect();
	                statusCode = conn.getResponseCode();
	                responseTime = System.currentTimeMillis() - startTime;

	                // Retry with GET if needed
	                if (statusCode >= 400 || statusCode == 405) {
	                    startTime = System.currentTimeMillis();
	                    HttpURLConnection retryConn = createConnection(url, "GET");
	                    retryConn.connect();
	                    statusCode = retryConn.getResponseCode();
	                    responseTime = System.currentTimeMillis() - startTime;
	                }

	                isBroken = statusCode >= 400;
	            } catch (IOException e) {
	                isBroken = !isTrustedSocialLink(url);
	                responseTime = 0;
	            }

	            return new RedirectResult(url, responseTime, statusCode, repeatCount, isBroken);
	        });
	    }

	    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
	    List<Future<RedirectResult>> futures = executor.invokeAll(tasks);
	    List<RedirectResult> results = new ArrayList<>();
	    for (Future<RedirectResult> future : futures) {
	        results.add(future.get());
	    }
	    executor.shutdown();
	    return results;
	}
	
	
	public static List<RedirectResult> checkUrlsFromExcel(String inputExcelPath, String sheetName) throws IOException {
	    ExcelUtils excel = new ExcelUtils(inputExcelPath);
	    List<RedirectResult> resultList = new ArrayList<>();

	    int totalRows = excel.getRowCount(sheetName);

	    for (int row = 1; row <= totalRows; row++) {
	        String url = excel.getCellData(sheetName, row, 0); // URL in 1st column

	        if (url == null || !url.startsWith("http"))
	            continue;

	        long responseTime = -1;
	        int statusCode = -1;
	        boolean isBroken = false;

	        try {
	            long startTime = System.currentTimeMillis();

	            HttpURLConnection conn = createConnection(url, "HEAD");
	            conn.connect();
	            statusCode = conn.getResponseCode();
	            responseTime = System.currentTimeMillis() - startTime;

	            // Fallback if HEAD fails
	            if (statusCode == -1 || statusCode >= 400 || statusCode == 405) {
	                startTime = System.currentTimeMillis();
	                HttpURLConnection retryConn = createConnection(url, "GET");
	                retryConn.connect();
	                statusCode = retryConn.getResponseCode();
	                responseTime = System.currentTimeMillis() - startTime;
	            }

	            isBroken = statusCode >= 400;

	        } catch (IOException e) {
	            isBroken = true;
	        }

	        resultList.add(new RedirectResult(url, responseTime, statusCode, 1, isBroken));
	    }

	    return resultList;
	}


}
