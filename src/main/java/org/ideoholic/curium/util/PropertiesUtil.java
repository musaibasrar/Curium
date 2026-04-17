package org.ideoholic.curium.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PropertiesUtil {
	private final Properties properties = new Properties();

	@PostConstruct
	public void init() throws IOException {
		try (InputStream in = getClass().getClassLoader().getResourceAsStream("Util.properties")) {
			if (in != null) {
				properties.load(in);
			} else {
				throw new FileNotFoundException("Util.properties not found");
			}
		}
	}

	public String getPropertiesValue(String key) {
		String value = properties.getProperty(key);
		log.debug("The value of key:{} is:{}", key, value);
		return value;
	}

	public String getPropertiesValue(String key, String defaultValue) {
		String value = properties.getProperty(key, defaultValue);
		log.debug("The value of key:{} is:{}", key, value);
		return value;
	}

	public int getIntPropertiesValue(String key, int defaultValue) {
		String value = getPropertiesValue(key);
		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				// Log warning about invalid number format
				log.error("Invalid integer for key '{}': {}", key, value, e);
			}
		}
		return defaultValue;
	}
}
