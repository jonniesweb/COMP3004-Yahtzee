package ca.jonsimpson.comp3004.yahtzee.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Load game settings from "YahtzeeConfig.properties" file from the classpath.
 * Additionally, any properties specified as JVM parameters override the values
 * set in the "YahtzeeConfig.properties" file.
 */
public class YahtzeeConfig extends Properties {
	private static final String YAHTZEE_CONFIG_FILENAME = "YahtzeeConfig.properties";

	private static final Log log = LogFactory.getLog(YahtzeeConfig.class);
	
	private static final YahtzeeConfig singleton = new YahtzeeConfig();
	
	private YahtzeeConfig() {
		loadConfigFromFile();
	}

	private void loadConfigFromFile() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(YAHTZEE_CONFIG_FILENAME);
		
		try {
			load(inputStream);
			log.info("Successfully loaded settings from " + YAHTZEE_CONFIG_FILENAME);
			
		} catch (IOException e) {
			log.error("No \"" + YAHTZEE_CONFIG_FILENAME + "\" file found on classpath. Using defaults", e);
		}
		
	}
	
	public static YahtzeeConfig getInstance() {
		return singleton;
	}
	
	@Override
	public String getProperty(String key) {
		
		/*
		 * return the property from the system properties. If null, return the
		 * result of getting it from this property file.
		 */
		String systemProperty = System.getProperty(key);
		if (systemProperty != null)
			return systemProperty;
		else
			return super.getProperty(key);
	}
	
	public int getInteger(String key, int defaultValue) {
		String result = getProperty(key);
		if (result != null) {
			return Integer.parseInt(result);
		} else
			return defaultValue;
	}
	
	public boolean getBoolean(String key, boolean defaultValue) {
		String result = getProperty(key);
		if (result != null) {
			return Boolean.parseBoolean(result);
		} else
			return defaultValue;
	}
	
}
