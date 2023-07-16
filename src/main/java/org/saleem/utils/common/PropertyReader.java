package org.saleem.utils.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER = LogManager.getLogger(PropertyReader.class);

    public static String getPropertyForGivenKey(String key){
        FileInputStream fis;
        Properties properties;
        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
            LOGGER.info("fetching value for key : {} from config file",key);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String value = properties.getProperty(key);
        if(!Objects.isNull(value)){
            return value ;
        }else{
            throw new RuntimeException("The given key does not match with any keys in config file. KEY : "+key);
        }
    }

    public static void main(String[] args) {
        System.out.println(getPropertyForGivenKey("prod.google.baseUrl"));
    }
}
