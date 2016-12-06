package com.example.alana.wordbook;

import android.app.Application;
import android.content.Context;

/**
 * Created by Alana on 2016/10/27.
 */

public class WordsApplication extends Application {
    private static Context context;
    public static Context getContext(){
        return WordsApplication.context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        WordsApplication.context=getApplicationContext();
    }
}
