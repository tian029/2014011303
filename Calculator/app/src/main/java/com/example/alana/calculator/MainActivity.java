package com.example.alana.calculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;

    private Button btnPer;//百分号
    private Button btnSqr;//开方
    private Button btnSqu;//平方
    private Button btnPart;//取倒数
    private Button btnCE;//清除显示的数字
    private Button btnAC;//归零
    private Button btnBack;//回退一位
    private Button btnDivision;//除法
    private Button btnMultiply;//乘法
    private Button btnSub;//减法
    private Button btnAdd;//加法
    private Button btnEqual;//等号
    private Button btnNeg;//取相反数
    private Button btnPoint;//小数点
    private Button btnSin;
    private Button btnCos;
    private Button btnLog;
    private Button btnTan;
    private Button btnLn;
    private Button btnPi;

    private TextView textView;

    double result=0;
    double num=0;
    boolean clickOp =false;//判断是否按运算符号
    int op;

    String text;//获取TextView中显示的数字


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPer=(Button)findViewById(R.id.btnPer);
        btnSqr=(Button)findViewById(R.id.btnSqr);
        btnSqu=(Button)findViewById(R.id.btnSqu);
        btnPart=(Button)findViewById(R.id.btnPart);
        btnCE=(Button)findViewById(R.id.btnCE);
        btnAC=(Button)findViewById(R.id.btnAC);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnDivision=(Button)findViewById(R.id.btnDivision);
        btnMultiply=(Button)findViewById(R.id.btnMultiply);
        btnSub=(Button)findViewById(R.id.btnSub);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnEqual=(Button)findViewById(R.id.btnEqual);
        btnNeg=(Button)findViewById(R.id.btnNeg);
        btnPoint=(Button)findViewById(R.id.btnPoint);
        btn0=(Button)findViewById(R.id.btn0);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
        btnSin=(Button)findViewById(R.id.btnSin);
        btnCos=(Button)findViewById(R.id.btnCos);
        btnLog=(Button)findViewById(R.id.btnLog);
        btnTan=(Button)findViewById(R.id.btnTan);
        btnLn=(Button)findViewById(R.id.btnLn);
        btnPi=(Button)findViewById(R.id.btnPi);

        textView=(TextView)findViewById(R.id.textView);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("0");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-0");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"0";
                    textView.setText(text);
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("1");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-1");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"1";
                    textView.setText(text);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("2");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-2");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"2";
                    textView.setText(text);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("3");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-3");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"3";
                    textView.setText(text);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("4");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-4");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"4";
                    textView.setText(text);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("5");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-5");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"5";
                    textView.setText(text);
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("6");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-6");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"6";
                    textView.setText(text);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("7");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-7");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"7";
                    textView.setText(text);
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("8");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-8");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"8";
                    textView.setText(text);
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("9");
                    clickOp =false;
                }
                else if(textView.getText().equals("-0")){
                    textView.setText("-9");
                }
                else{
                    text=textView.getText().toString();
                    text=text+"9";
                    textView.setText(text);
                }
            }
        });
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("0");
                clickOp=false;
                result=0;
            }
        });
        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("0");
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOp||textView.getText().equals("0")){
                    textView.setText("0.");
                    clickOp =false;
                }
                else{
                    text=textView.getText().toString();
                    text=text+".";
                    textView.setText(text);
                }
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                count();
                op=0;
                clickOp=true;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                count();
                op=1;
                clickOp=true;
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                count();
                op=2;
                clickOp=true;
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                count();
                op=3;
                clickOp=true;
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                count();
                op=4;
                clickOp=true;
            }
        });
        btnPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=num*0.01;
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnSqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=Math.sqrt(num);
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnSqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=Math.pow(num,2);
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=1/num;
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                text="-"+text;
                textView.setText(text);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                if(text.length()<=1)
                    textView.setText("0");
                else
                    textView.setText(text.substring(0,text.length()-1));
            }
        });
        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=Math.sin(num);
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=Math.cos(num);
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=Math.log10(num);
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=Math.tan(num);
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=Math.log(num);
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });
        btnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=textView.getText().toString();
                num=Double.valueOf(text);
                num=3.14;
                textView.setText(String.valueOf(num));
                clickOp=true;
            }
        });

    }
    public void count(){
        switch(op){
            case 0:
                result=num;
                break;
            case 1:
                result=result+num;
                break;
            case 2:
                result=result-num;
                break;
            case 3:
                result=result*num;
                break;
            case 4:
                result=result/num;
                break;
        }
        check();
    }
    public void check(){
        int i;
        text=String.valueOf(result);
        if(text.length()-1-text.indexOf(".")>5){
            text=text.substring(0,text.indexOf(".")+6);

        }
        for(i=text.length()-1;i>=0;i--){
            if(text.charAt(i)!='0'){
                if(text.charAt(i)!='.'){
                    i++;
                }
                else{
                    text=text.replace(".","");
                }
                break;
            }
            text=text.substring(0,i);
        }
        textView.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_settings:
                Toast.makeText(this,"setting",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_another:
                Toast.makeText(this,"another",Toast.LENGTH_SHORT).show();
                break;
        }*/
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("这是帮助").setTitle("帮助");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("Tag","ok");
                    }
                });
                builder.show();
                break;
            case R.id.action_jz:
                Intent intent1=new Intent(MainActivity.this,jzActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_unit:
                Intent intent2=new Intent(MainActivity.this,unitActivity.class);
                startActivity(intent2);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
