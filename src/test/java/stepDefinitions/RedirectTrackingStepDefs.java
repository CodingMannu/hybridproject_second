package stepDefinitions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExcelUtils;
import utils.RedirectTrackerUtils;
import utils.RedirectTrackerUtils.RedirectResult;

public class RedirectTrackingStepDefs {

    private List<RedirectResult> linkResults;
    private final Properties prop = new ConfigReader().init_prop();
    
    private String currentPage;

    @Given("I open the URL {string}")
    public void i_open_the_url(String startUrl) throws Exception {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(startUrl);
        
        currentPage = startUrl; 

        boolean useParallel = Boolean.parseBoolean(prop.getProperty("parallel.links", "true"));
        int threads = Integer.parseInt(prop.getProperty("parallel.thread.count", "10"));

        if (useParallel) {
            linkResults = RedirectTrackerUtils.fetchLinksWithThreadPool(driver, threads, prop);
        } else {
            linkResults = RedirectTrackerUtils.fetchAllAnchorLinksWithResponseTime(driver, prop);
        }

        if (linkResults == null || linkResults.isEmpty()) {
            System.out.println("⚠️ Skipping: No anchor links found on page -> " + currentPage);
            return;
        }
    }

    @Then("I should log all redirected URLs and their response times")
    public void log_all_redirects_to_excel() throws IOException {
        int row = 0;
        int total = linkResults.size();
        int passed = 0;
        int failed = 0;
        
        String timestamp = new SimpleDateFormat("dd-MMM-yy_hh-mm-ss-a").format(new Date());
        String threadId = String.valueOf(Thread.currentThread().getId());
        String excelPath = "test-output-result/redirectTracking/anchorTagOnPage/redirect_report_" + timestamp + "_T" + threadId + ".xlsx";
        ExcelUtils excel = new ExcelUtils(excelPath);
        String sheetName = "AnchorLinks";
        System.out.println("📄 Excel written to: " + excelPath);

        // Add header row
        excel.setCellData(sheetName, row, 0, "Anchor Link(URL)");
        excel.setCellData(sheetName, row, 1, "Response Time (ms)");
        excel.setCellData(sheetName, row, 2, "HTTP Status");
        excel.setCellData(sheetName, row, 3, "Repeat Count(URL)");
        excel.setCellData(sheetName, row, 4, "Broken Link");
        excel.setCellData(sheetName, row, 5, "Remarks");
        
        for (int col = 0; col <= 5; col++) {
            excel.fillBlueColor(sheetName, row, col);
        }

        
        System.out.println("\n================================= Live Redirect Result ====================================="); // ✅ Update 6: Console header
        System.out.printf("%-80s %-15s %-12s %-10s %-10s%n", "URL", "Response(ms)", "Status", "Count", "Broken");
        
        // Iterate and write each result
        for (RedirectResult result : linkResults) {
            row++;
            excel.setCellData(sheetName, row, 0, result.url);
            excel.setCellData(sheetName, row, 1, String.valueOf(result.responseTime));
            excel.setCellData(sheetName, row, 2, String.valueOf(result.statusCode));
            excel.setCellData(sheetName, row, 3, String.valueOf(result.repeatCount));
            excel.setCellData(sheetName, row, 4, result.isBroken ? "Yes" : "No");
            excel.setCellData(sheetName, row, 5, getRemarks(result));

            if (result.isBroken) {
                excel.fillRedColor(sheetName, row, 4);
                failed++;
            } else {
                passed++;
            }
            
            //Fill orange if response time > 800ms
            if (result.responseTime > 2500) {
                excel.fillOrangeColor(sheetName, row, 1);
            }
            
            System.out.printf("%-80s %-15d %-12d %-10d %-10s%n", result.url, result.responseTime, result.statusCode, result.repeatCount, result.isBroken ? "Yes" : "No");
        }
        System.out.println("==========================================================================================\n"); // ✅ Update 8: Console footer


        // Add summary
        int summaryRow = row + 2;
        excel.setCellData(sheetName, summaryRow + 1, 0, "Summary");
        
        for (int col = 1; col <= 5; col++) {
            excel.setCellData(sheetName, summaryRow + 1, col, "");
        }
        
        for (int col = 0; col <= 5; col++) {
            excel.fillBlueColor(sheetName, summaryRow + 1, col);
        }
        
        excel.setCellData(sheetName, summaryRow + 2, 0, "Page URL");
        excel.setCellData(sheetName, summaryRow + 2, 1, currentPage);
        excel.setCellData(sheetName, summaryRow + 3, 0, "Total URLs");
        excel.setCellData(sheetName, summaryRow + 3, 1, String.valueOf(linkResults.size()));
        excel.setCellData(sheetName, summaryRow + 4, 0, "Passed");
        excel.setCellData(sheetName, summaryRow + 4, 1, String.valueOf(passed));
        excel.setCellData(sheetName, summaryRow + 5, 0, "Failed");
        excel.setCellData(sheetName, summaryRow + 5, 1, String.valueOf(failed));
        

                
        // ================= Runtime summary log (added) =================
        System.out.println("\n========= Redirect Report Summary =========");
        System.out.println("📄 Page URL     : " + currentPage);
        System.out.println("🔗 Total URLs   : " + total);
        System.out.println("✅ Passed       : " + passed);
        System.out.println("❌ Failed       : " + failed);
        System.out.println("============================================\n");
    }
    
    
    private String getRemarks(RedirectResult result) {
        if ((result.responseTime == 0 || result.responseTime == -1) && result.statusCode == -1) {
            return "No response / Timeout or blocked domain";
        } else if (result.statusCode == 403) {
            return "Forbidden - Might require auth or IP blocked";
        } else if (result.statusCode == 405) {
            return "Method Not Allowed - HEAD not supported";
        } else if (result.responseTime > 4000) {
            return "Slow response (>4000ms)";
        }else if (result.responseTime > 3000) {
            return "Slow response (>3000ms)";        
        } else if (result.statusCode >= 400) {
            return "HTTP Error " + result.statusCode;
        }
        return "";
    }
    
    
    @Then("I should log response time of the provided URLs")
    public void log_response_of_direct_urls(io.cucumber.datatable.DataTable table) throws Exception {
        List<String> urls = table.asList();
        int threadCount = Integer.parseInt(prop.getProperty("parallel.thread.count", "10"));

        linkResults = RedirectTrackerUtils.fetchGivenUrlsWithThreadPool(urls, threadCount);
        currentPage = "Direct Input URLs";

        //Reuse existing Excel + console logging
        log_all_redirects_to_excel();
    }
    
    
    @Then("I read and validate all URLs from Excel")
    public void logResultsFromInputSheet() throws IOException {
        String inputFile = "src/test/resources/testdata/inputUrls.xlsx";  // ✅ Correct path
        String sheetName = "Sheet1";

        //Unique filename with timestamp
        String timestamp = new SimpleDateFormat("dd-MMM-yy_hh-mm-ss-a").format(new Date());
        String outputFile = "test-output-result/redirectTracking/urlsFromExcel//urls_result_" + timestamp + ".xlsx";
        
        
        List<RedirectResult> results = RedirectTrackerUtils.checkUrlsFromExcel(inputFile, sheetName);

        ExcelUtils excel = new ExcelUtils(outputFile);

        // Header
        excel.setCellData(sheetName, 0, 0, "URL");
        excel.setCellData(sheetName, 0, 1, "Response Time (ms)");
        excel.setCellData(sheetName, 0, 2, "Status Code");
        excel.setCellData(sheetName, 0, 3, "Broken");
        excel.setCellData(sheetName, 0, 4, "Remarks");

        for (int col = 0; col <= 4; col++) {
            excel.fillBlueColor(sheetName, 0, col); // Header row color
        }

        int row = 1;
        int failed = 0;
        int passed = 0;
        for (RedirectResult result : results) {
            excel.setCellData(sheetName, row, 0, result.url);
            excel.setCellData(sheetName, row, 1, String.valueOf(result.responseTime));
            excel.setCellData(sheetName, row, 2, String.valueOf(result.statusCode));
            excel.setCellData(sheetName, row, 3, result.isBroken ? "Yes" : "No");
            excel.setCellData(sheetName, row, 4, getRemarks(result));

            if (result.responseTime > 2500) {
                excel.fillOrangeColor(sheetName, row, 1); // Slow response
            }

            if (result.isBroken) {
                excel.fillRedColor(sheetName, row, 3); // Broken link
                failed++;
            } else {
                passed++;
            }

            row++;
        }

        // ✅ Summary block
        int summaryRow = row + 2;
        excel.setCellData(sheetName, summaryRow, 0, "Summary");
        excel.setCellData(sheetName, summaryRow + 1, 0, "Total URLs");
        excel.setCellData(sheetName, summaryRow + 1, 1, String.valueOf(results.size()));

        excel.setCellData(sheetName, summaryRow + 2, 0, "Passed");
        excel.setCellData(sheetName, summaryRow + 2, 1, String.valueOf(passed));

        excel.setCellData(sheetName, summaryRow + 3, 0, "Failed");
        excel.setCellData(sheetName, summaryRow + 3, 1, String.valueOf(failed));

        System.out.println("=========================================");
        System.out.println("🔗 Total URLs      : " + results.size());
        System.out.println("✅ Passed URLs     : " + passed);
        System.out.println("❌ Failed URLs     : " + failed);
        System.out.println("📄 Excel Generated : " + outputFile);
        System.out.println("=========================================");
    }

}
