package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class configReader {

	static Properties prop;

	// This method will read any propertiy file
	public static Properties readProperties(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

//This method retrieves single value based on a specific key
	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

}
