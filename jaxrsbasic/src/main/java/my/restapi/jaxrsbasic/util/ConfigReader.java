package my.restapi.jaxrsbasic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class ConfigReader {
	//Uncomment this for local db
	//private static final String CONFIG_FILE_PATH = "config.properties";
	
	public static Properties getConfig() {
		Properties properties = new Properties();
		
		//Docker
		try(FileInputStream input = new FileInputStream("/var/lib/jetty/config.properties")){
			properties.load(input);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//Local
//		try(InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)){
//			properties.load(input);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		
		return properties;
	}
}
