package com.iamdj.countrycomparison;

import android.app.Application;

public class DemoClass extends Application {

    public static long executionTime;


    public DemoClass() {
    }

    public static long getExecutionTime() {
        return executionTime;
    }

    public static void setExecutionTime(long executionTime) {
        DemoClass.executionTime = executionTime;
    }
}
