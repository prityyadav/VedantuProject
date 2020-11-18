package com.vendantu.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataManager {

	private static DataManager dataManager;
	private Properties prop;

	private DataManager() {

		prop = new Properties();

		String fileName = "config.properties";

		InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			is = new FileInputStream(fileName);

		} catch (FileNotFoundException ex) {

		}
		try {
			prop.load(is);
		} catch (IOException ex) {

		}
	}

	// singleton class
	public static DataManager getInstance() {
		if (dataManager == null) {
			dataManager = new DataManager();
		}
		return dataManager;
	}

	public String getBaseUrl() {
		String baseUrl = prop.getProperty("base_Url");
		if (baseUrl != null)
			return baseUrl;
		else
			throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
	}

}
