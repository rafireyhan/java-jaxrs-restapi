package my.restapi.jaxrsbasic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static final String CONFIG_FILE_PATH = "config.properties";
	
	public static Properties getConfig() {
		Properties properties = new Properties();
		try(InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)){
			properties.load(input);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
}
