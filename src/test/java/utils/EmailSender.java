package utils;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailSender {

    private static final Logger logger = LogManager.getLogger(EmailSender.class);

    public static String getLatestReportFilePath() {
        String reportsDir = System.getProperty("user.dir") + "/test-output-result/reports";
        File folder = new File(reportsDir);

        if (!folder.exists() || folder.listFiles() == null) {
            logger.error("Reports directory not found: " + reportsDir);
            return null;
        }

        return Arrays.stream(folder.listFiles((dir, name) -> name.endsWith(".html")))
                .max(Comparator.comparingLong(File::lastModified))
                .map(File::getAbsolutePath)
                .orElse(null);
    }

    
    public static void sendExtentReportByEmail(String reportPath, Properties prop) {
        try {
            File reportFile = new File(reportPath);
            if (!reportFile.exists()) {
                logger.error("Report file not found: " + reportPath);
                return;
            }

            URL reportUrl = reportFile.toURI().toURL();

            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setHostName(prop.getProperty("email.smtp.host"));
            email.setSmtpPort(Integer.parseInt(prop.getProperty("email.smtp.port")));
            email.setAuthenticator(new DefaultAuthenticator(
                    prop.getProperty("email.sender"),
                    prop.getProperty("email.sender.password")
            ));
            email.setSSLOnConnect(true);
            email.setFrom(prop.getProperty("email.sender"), prop.getProperty("email.sender.name"));
            email.setSubject("Automation Test Report");
            email.setMsg("Hi,\n\nPlease find the attached automation test execution report.\n\nRegards,\nQA Team");

            // Handle multiple emails
            String[] receivers = prop.getProperty("email.receiver").split(",");
            for (String to : receivers) {
                email.addTo(to.trim());
            }

            email.attach(reportUrl, reportFile.getName(), "Extent Report");
            email.send();

            logger.info("📧 Report emailed successfully to: " + Arrays.toString(receivers));

        } catch (Exception e) {
            logger.error("❌ Failed to send email.", e);
        }
    }


}
