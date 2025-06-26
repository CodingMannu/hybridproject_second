package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties propertiesFile;

	/**
	 * This method is used to load the properties from config.properties file
	 * 
	 * @return it returns Properties prop object
	 */

	public Properties init_prop() {

		propertiesFile = new Properties();
		try {
			FileInputStream fileInput = new FileInputStream("./src/test/resources/config.properties");
			propertiesFile.load(fileInput);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Failed to find properties file", e);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load properties file", e);
		}

		return propertiesFile;

	}
	
	public static String getProperty(String key) {
	    if (propertiesFile == null) {
	        new ConfigReader().init_prop(); // load if not loaded
	    }
	    return propertiesFile.getProperty(key);
	}
	
	public static boolean isOtpFromDb() {
	    return Boolean.parseBoolean(getProperty("otp.from.db"));
	}

	
	

}
