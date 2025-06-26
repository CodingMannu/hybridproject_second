package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtils {

	private static Connection connection;
	private static Properties properties;
	private static final Logger logger = LogManager.getLogger(DBUtils.class);

	static {
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load DB config from properties file", e);
		}
	}

	public static void connectToDB() throws SQLException {
		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.user");
		String password = properties.getProperty("db.pass");

		connection = DriverManager.getConnection(url, user, password);
	}

	public static ResultSet executeQuery(String query) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeQuery(query);
	}

	public static void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}

	public static String getOTP(String mobileNumber) {
		String otp = "";
		int maxRetries = Integer.parseInt(properties.getProperty("otp.max.retries", "3")); // default is 3 if not found
		int delaySeconds = Integer.parseInt(properties.getProperty("otp.retry.delay.seconds", "1")); // default is 2

		for (int attempt = 1; attempt <= maxRetries; attempt++) {
			try {

				if (attempt == 1 && delaySeconds > 0) {
					logger.info("Waiting " + delaySeconds + " seconds before first DB attempt...");
					WaitUtils.executionDelay(delaySeconds);
				}

				connectToDB();
				String query = "SELECT TOP 1 OtpNumber FROM [Demographics].MessageOTP WHERE MobileNumber = ? ORDER BY ModifiedDate DESC";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, mobileNumber);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					otp = rs.getString("OtpNumber");
				}

				closeConnection();

				if (otp != null && !otp.isEmpty()) {
					logger.info("OTP fetched successfully on attempt " + attempt + ": " + otp);
					return otp; // Return immediately if OTP found
				} else {
					logger.warn("Attempt " + attempt + ": OTP not found for mobile number: " + mobileNumber);
				}

			} catch (SQLException e) {
				logger.error("DB error while fetching OTP (Attempt " + attempt + ") for mobile: " + mobileNumber, e);
			}

			// Wait before next attempt using reusable delay method
			WaitUtils.executionDelay(delaySeconds);
		}

		// Final failure after retries
		throw new RuntimeException(
				"OTP not found after " + maxRetries + " attempts for mobile number: " + mobileNumber);
	}

	
	
	public static void updateWalletAmountByPhone(String mobileNumber, String amount) {
	    try {
	        connectToDB();

	        String phoneQuery = "SELECT PhoneNumberId FROM [Demographics].[PhoneNumber] WHERE Number = ?";
	        PreparedStatement phoneStmt = connection.prepareStatement(phoneQuery);
	        phoneStmt.setString(1, mobileNumber);
	        ResultSet phoneRs = phoneStmt.executeQuery();

	        if (!phoneRs.next()) {
	            throw new RuntimeException("PhoneNumberId not found for number: " + mobileNumber);
	        }
	        System.out.println("	-Phone Number: " + mobileNumber);
	        logger.info("Phone Number: " + mobileNumber);
	        
	        int phoneNumberId = phoneRs.getInt("PhoneNumberId");
	        String userQuery = "SELECT UserLoginId FROM [User].[UserLogin] WHERE PhoneNumberId = ?";
	        PreparedStatement userStmt = connection.prepareStatement(userQuery);
	        userStmt.setInt(1, phoneNumberId);
	        ResultSet userRs = userStmt.executeQuery();

	        if (!userRs.next()) {
	            throw new RuntimeException("UserLoginId not found for PhoneNumberId: " + phoneNumberId);
	        }
	        System.out.println("	-PhoneNumberId: " + phoneNumberId);
	        logger.info("PhoneNumberId: " + phoneNumberId);
	        
	        int userLoginId = userRs.getInt("UserLoginId");

	        // Step 3: Update wallet balance
	        String updateQuery = "UPDATE [Transaction].[UserCalculatedWallet] SET Amount = ? WHERE Currency = 'INR' AND UserLoginId = ?";
	        PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
	        updateStmt.setString(1, amount);
	        updateStmt.setInt(2, userLoginId);
	        
	        System.out.println("	-UserLoginId: " + userLoginId);
	        logger.info("UserLoginId: " + userLoginId);
	        
	        int updatedRows = updateStmt.executeUpdate();
	        if (updatedRows > 0) {
	            logger.info("Wallet updated successfully for UserLoginId: " + userLoginId + " with amount: " + amount);
	        } else {
	            logger.warn("Wallet update failed. No matching record for UserLoginId: " + userLoginId);
	        }

	    } catch (Exception e) {
	        logger.error("Error updating wallet for mobile: " + mobileNumber);
	        throw new RuntimeException(e);
	    } finally {
	        try {
	            closeConnection();
	        } catch (SQLException e) {
	            logger.error("Error closing DB connection after wallet update");
	        }
	    }
	}


}
