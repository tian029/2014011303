package com.example.alana.exercise17;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final static String Key_UserName="UserName";
    private final static String Key_LoginDate = "LoginDate";
    private final static String Key_UserType = "UserType";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = preferences.edit();
        Button btnWrite = (Button)findViewById(R.id.buttonWrite);
        Button btnRead = (Button)findViewById(R.id.buttonRead);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate = simpleDateFormat.format(new Date());

                editor.putString(Key_UserName,"Zhang San");
                editor.putString(Key_LoginDate,strLoginDate);
                editor.putInt(Key_UserType,1);

                editor.apply();
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName=preferences.getString(Key_UserName,null);
                String strLoginDate=preferences.getString(Key_LoginDate,null);
                int nUserType=preferences.getInt(Key_UserType,0);
                if(strUserName!=null&&strLoginDate!=null)
                    Toast.makeText(MainActivity.this,"用户名："+strUserName+
                    "登录时间："+strLoginDate+"用户类型："+nUserType,Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"无数据",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
