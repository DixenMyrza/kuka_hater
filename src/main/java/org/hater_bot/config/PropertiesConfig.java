package org.hater_bot.config;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesConfig {

    private static String token;
    private static String userName;
    private static long objectId;
    private static long creatorId;
    private static final String propertiesFilePath = "src/main/resources/properties/bot_config.properties";



    public PropertiesConfig(){

        Properties properties = readPropertiesFile();

        assert properties != null;

        token = properties.getProperty("bot.token");
        userName = properties.getProperty("bot.userName");

        objectId = Long.parseLong(properties.getProperty("users.objectId"));
        creatorId = Long.parseLong(properties.getProperty("users.creatorId"));
    }



    private static Properties readPropertiesFile(){

        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(propertiesFilePath)) {

            properties.load(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  properties;
    }



    public static String getToken(){
        return token;
    }


    public static String getUserName(){
        return userName;
    }


    public static long getObjectId(){
        return objectId;
    }


    public static long getCreatorId(){
        return creatorId;
    }
}
