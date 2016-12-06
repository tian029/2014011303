package com.example.alana.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigInteger;

public class jzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jz);

        final EditText et10=(EditText)findViewById(R.id.decimal);
        final EditText et2=(EditText)findViewById(R.id.byteArray);
        final EditText et8=(EditText)findViewById(R.id.octal);
        final EditText et16=(EditText)findViewById(R.id.hexadecimal);

        Button btn10=(Button)findViewById(R.id.button10);
        Button btn2=(Button)findViewById(R.id.button2);
        Button btn8=(Button)findViewById(R.id.button8);
        Button btn16=(Button)findViewById(R.id.button16);

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number=Integer.valueOf(et10.getText().toString());
                et2.setText(Integer.toBinaryString(number));
                et8.setText(Integer.toOctalString(number));
                et16.setText(Integer.toHexString(number));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number=Integer.valueOf(et2.getText().toString(),2);
                et10.setText(Integer.toString(number));
                et8.setText(Integer.toOctalString(number));
                et16.setText(Integer.toHexString(number));
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number=Integer.valueOf(et8.getText().toString(),8);
                et10.setText(Integer.toString(number));
                et2.setText(Integer.toBinaryString(number));
                et16.setText(Integer.toHexString(number));
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number=Integer.valueOf(et16.getText().toString(),16);
                et10.setText(Integer.toString(number));
                et8.setText(Integer.toOctalString(number));
                et2.setText(Integer.toBinaryString(number));
            }
        });
    }

}
