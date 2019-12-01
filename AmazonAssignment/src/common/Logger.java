package common;

import org.testng.Reporter;

public class Logger {

    public void info(String logInfo) {
	Reporter.log(logInfo);
	System.out.println(logInfo);
    }
}
