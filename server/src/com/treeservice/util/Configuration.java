package com.treeservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static Properties props;

	private static Configuration instance = null;

	public static Configuration getInstance() {
		if (instance == null)
			instance = new Configuration();
		return instance;
	}

	private Configuration() {
		System.out.println("Configuration :: _constructor");
		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/../app.properties");
			props = new Properties();
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Configuration :: _constructor END");
	}

	public String get(String key) {
		return props.getProperty(key);
	}
}

