package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
// Class for working with configuration keys and values.
public class ConfigPropertiesLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigPropertiesLoader.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("Configuration file not found");
            }
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}


