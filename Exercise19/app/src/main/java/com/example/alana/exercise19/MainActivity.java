package com.example.alana.exercise19;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonWrite=(Button)findViewById(R.id.buttonWrite);
        Button buttonRead=(Button)findViewById(R.id.buttonRead);
        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out=null;
                try{
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        File file=Environment.getExternalStorageDirectory();
                        File myfile=new File(file.getCanonicalPath()+"/"+"MyFile");
                        FileOutputStream fileOutputStream=new FileOutputStream(myfile);
                        out=new BufferedOutputStream(fileOutputStream);
                        String content="2014011303tian";
                        try{
                            out.write(content.getBytes(StandardCharsets.UTF_8));
                        }finally {
                            if(out!=null)
                                out.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in = null;
                try{
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        File file=Environment.getExternalStorageDirectory();
                        File myfile=new File((file.getCanonicalPath()+"/"+"MyFile"));
                        FileInputStream fileInputStream=new FileInputStream(myfile);
                        in=new BufferedInputStream(fileInputStream);
                        int c;
                        StringBuilder stringBuilder=new StringBuilder("");
                        try{
                            while ((c=in.read())!=-1){
                                stringBuilder.append((char)c);
                            }
                            Toast.makeText(MainActivity.this,stringBuilder.toString(),
                                    Toast.LENGTH_LONG).show();
                        }finally {
                            if(in!=null)
                                in.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
