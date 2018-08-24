package com.dany.androidtest;

import android.app.Application;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

/**
 * Created by dan.y on 2018/08/15.
 */

public class MyApp extends Application {
    
    private static MyApp instance;
    
    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null){
            instance = this;
        }
        
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE)
                .build();
        XLog.init(config);
    }

    public static MyApp getInstance() {
        return instance;
    }

}
