package com.example.alana.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigInteger;

public class unitActivity extends AppCompatActivity {
    private String [] lengthUnit={"米", "分米", "厘米", "毫米"};
    private String [] weightUnit={"克","千克","吨"};
    private String [] temUnit={"摄氏度","华氏度"};
    private double num1,num2,num3;
    int position1,position2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        final EditText unitLM=(EditText)findViewById(R.id.unitLM);
        final EditText unitLDM=(EditText)findViewById(R.id.unitLDM);
        final EditText unitLCM=(EditText)findViewById(R.id.unitLCM);
        final EditText unitLMM=(EditText)findViewById(R.id.unitLMM);

        final EditText unitWG=(EditText)findViewById(R.id.unitWG);
        final EditText unitWKG=(EditText)findViewById(R.id.unitWKG);
        final EditText unitWT=(EditText)findViewById(R.id.unitWT);

        final EditText unitTC=(EditText)findViewById(R.id.unitTC);
        final EditText unitTF=(EditText)findViewById(R.id.unitTF);

        Button btnL=(Button)findViewById(R.id.btnL);
        Button btnW=(Button)findViewById(R.id.btnW);
        Button btnT=(Button)findViewById(R.id.btnT);

        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!unitLM.getText().equals("")){
                    unitLDM.setText(String.valueOf(Double.parseDouble(unitLM.getText().toString())*10));
                    unitLCM.setText(String.valueOf(Double.parseDouble(unitLM.getText().toString())*100));
                    unitLMM.setText(String.valueOf(Double.parseDouble(unitLM.getText().toString())*1000));
                }if(!unitLDM.getText().equals("")){
                    unitLM.setText(String.valueOf(Double.parseDouble(unitLDM.getText().toString())*0.1));
                    unitLCM.setText(String.valueOf(Double.parseDouble(unitLDM.getText().toString())*10));
                    unitLMM.setText(String.valueOf(Double.parseDouble(unitLDM.getText().toString())*100));
                }if(!unitLCM.getText().equals("")){
                    unitLM.setText(String.valueOf(Double.parseDouble(unitLCM.getText().toString())*0.01));
                    unitLDM.setText(String.valueOf(Double.parseDouble(unitLCM.getText().toString())*0.1));
                    unitLMM.setText(String.valueOf(Double.parseDouble(unitLCM.getText().toString())*10));
                }if(!unitLMM.getText().toString().equals("")) {
                    unitLM.setText(String.valueOf(Double.parseDouble(unitLMM.getText().toString()) * 0.001));
                    unitLDM.setText(String.valueOf(Double.parseDouble(unitLMM.getText().toString()) * 0.01));
                    unitLCM.setText(String.valueOf(Double.parseDouble(unitLMM.getText().toString()) * 0.1));
                }
            }
        });
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!unitWG.getText().toString().equals("")){
                    unitWKG.setText(String.valueOf(Double.parseDouble(unitWG.getText().toString())*0.001));
                    unitWT.setText(String.valueOf(Double.parseDouble(unitWG.getText().toString())*0.001*0.001));
                } if(!unitWKG.getText().toString().equals("")){
                    unitWG.setText(String.valueOf(Double.parseDouble(unitWKG.getText().toString())*1000));
                    unitWT.setText(String.valueOf(Double.parseDouble(unitWKG.getText().toString())*0.001));
                }if(!unitWT.getText().toString().equals("")){
                    unitWKG.setText(String.valueOf(Double.parseDouble(unitWT.getText().toString())*1000));
                    unitWG.setText(String.valueOf(Double.parseDouble(unitWT.getText().toString())*1000*1000));
                }
            }
        });
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!unitTC.getText().toString().equals("")){
                    unitTF.setText(String.valueOf(Double.parseDouble(unitTC.getText().toString())*1.8+32));
                }else if(!unitTF.getText().toString().equals("")){
                    unitTC.setText(String.valueOf((Double.parseDouble(unitTF.getText().toString())-32)/1.8));
                }
            }
        });
        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitLCM.setText("");
                unitLDM.setText("");
                unitLM.setText("");
                unitLMM.setText("");
                unitTC.setText("");
                unitTF.setText("");
                unitWG.setText("");
                unitWKG.setText("");
                unitWT.setText("");

            }
        });


        final Spinner spinner1=(Spinner)findViewById(R.id.spinner1);
        final ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.unlength
            ,android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position1=position;
                Log.v("111",adapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final Spinner spinner2=(Spinner)findViewById(R.id.spinner2);

        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position2=position;
                Log.v("222",adapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final EditText txtNum1=(EditText) findViewById(R.id.txtNum1);
        final TextView txtNum2=(TextView)findViewById(R.id.txtNum2);
        Button btnChange=(Button)findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1=Double.valueOf(txtNum1.getText().toString());
                double num2 = 0;
                Log.v("aaa",position1+"   "+position2);
                switch (position1-position2){
                    case 1:
                        num2=num1/10;
                        break;
                    case 2:
                        num2=num1/100;
                        break;
                    case 3:
                        num2=num1/1000;
                        break;
                    case -1:
                        num2=num1*10;
                        break;
                    case -2:
                        num2=num1*100;
                        break;
                    case -3:
                        num2=num1*1000;
                        break;
                    case 0:
                        num2=num1;
                        break;
                }
                txtNum2.setText(Double.toString(num2));
            }
        });

    }

}
