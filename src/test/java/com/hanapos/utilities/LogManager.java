package com.hanapos.utilities;

import com.hanapos.seleniumProjectBase.TestBaseClass;

public class LogManager extends TestBaseClass {

    private static ThreadLocal<String> logFileName = new ThreadLocal<>();

    public static String getLogFileName() {
        return logFileName.get();
    }

    public static void setLogFileName(String testName) {
        logFileName.set("logs/" + testName + "_" + Thread.currentThread().getId() + ".log");
    }


}
