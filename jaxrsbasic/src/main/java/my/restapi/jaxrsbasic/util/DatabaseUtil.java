package my.restapi.jaxrsbasic.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseUtil {
	private static final Properties config = ConfigReader.getConfig();
	private static final String DATABASE = config.getProperty("simpletask.jdbc.database");
	private static final String MARIA_DB_URL = config.getProperty("simpletask.jdbc.url");
	private static final String MARIA_DB_USER = config.getProperty("simpletask.jdbc.user");
	private static final String MARIA_DB_PASSWORD = config.getProperty("simpletask.jdbc.password");
	
	public static EntityManagerFactory getEntityManagerFactory() {
		Map<String, String> persistenceMap = new HashMap<>();
		persistenceMap.put("javax.persistence.jdbc.url", DATABASE.equals("H2") ? "jdbc:h2:~/test" : MARIA_DB_URL);
		persistenceMap.put("javax.persistence.jdbc.user", DATABASE.equals("H2") ? "sa" : MARIA_DB_USER);
		persistenceMap.put("javax.persistence.jdbc.password", DATABASE.equals("H2") ? "" : MARIA_DB_PASSWORD);
        persistenceMap.put("javax.persistence.jdbc.driver", DATABASE.equals("H2") ? "org.h2.Driver" : "org.mariadb.jdbc.Driver");
        persistenceMap.put("eclipselink.logging.level", "INFO");
        persistenceMap.put("eclipselink.logging.parameters", "true");
        
        return Persistence.createEntityManagerFactory("my-persistence-unit", persistenceMap);
    }
}

