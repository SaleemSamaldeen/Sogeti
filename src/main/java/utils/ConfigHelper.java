package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    private Properties properties;

    private FileInputStream fis;

    public String browser;

    public String baseURL;

    public ConfigHelper() {
        try {
            readProps();
            this.browser = properties.getProperty("browser");
            this.baseURL = properties.getProperty("baseURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readProps() throws IOException {
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

}
