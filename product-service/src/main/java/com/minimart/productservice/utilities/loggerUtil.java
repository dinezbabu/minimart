package com.minimart.productservice.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


/**
 * Created by dbabu on 5/22/19.
 */

public class loggerUtil {

    public static loggerUtil LOGGERUTIL_INSTANCE;

    private static Logger LOGGER = LogManager.getLogger();

    public void loggerInfo(String className, String methodName, String message) {
        LOGGER.info(message,className,methodName);
    }
    public void loggerError(String className, String methodName, String message,Throwable e) {
        LOGGER.error(message,e.getStackTrace(),className,methodName);
        LOGGER.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile"));
    }
    public static synchronized loggerUtil getInstance(){
        return Optional.ofNullable(LOGGERUTIL_INSTANCE).orElseGet(()->{
            LOGGERUTIL_INSTANCE = new loggerUtil();
            return LOGGERUTIL_INSTANCE;
        });
    }
}
