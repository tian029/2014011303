package com.example.alana.exercise2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
private static final String TAG="MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG,"Verbose Message");
        Log.d(TAG,"Debug Message");
        Log.i(TAG,"Info Message");
        Log.w(TAG,"Warning Message");
        Log.e(TAG,"Error Message");
    }


}
