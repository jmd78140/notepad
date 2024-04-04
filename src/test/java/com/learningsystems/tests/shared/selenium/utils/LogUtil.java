package com.learningsystems.tests.shared.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.springframework.util.Assert;

import com.learningsystems.tests.shared.selenium.annotations.LazyComponent;

@LazyComponent
public class LogUtil {
	
	
    public static LogEntries getLogs(WebDriver driver) {
        return driver
            .manage()
            .logs()
            .get(LogType.BROWSER);
    }

    
    //@FIXME must be moved (login page ?) 
    public void isLoginErrorLog(WebDriver driver) {
        
    	//Check logs (works only Chrome and Edge)
        LogEntries logEntries = driver
            .manage()
            .logs()
            .get(LogType.BROWSER);
        Assert.isTrue(logEntries
            .getAll()
            .stream()
            .anyMatch(logEntry -> logEntry
                .getMessage()
                .contains("An invalid email address was specified")), "Login error is correctly logged !");
    }

}
