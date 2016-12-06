package com.example.alana.alarmclocks;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class AlarmSettingActivity extends AppCompatActivity {

    AlarmDBHelper alarmDBHelper;
    private final static int DIALOG=1;
    private int hour;
    private int minute;
    //用于选择铃声后作相应的判断标记
    private static final int REQUEST_CODE_PICK_RINGTONE = 1;
    //保存铃声的Uri的字符串形式
    private String mRingtoneUri = String.valueOf(RingtoneManager
            .getDefaultUri(RingtoneManager.TYPE_RINGTONE));;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_adding);
        alarmDBHelper=new AlarmDBHelper(this);

        final char[] setWeek={'0','0','0','0','0','0','0'};


        String time;
        final TimePicker timePicker=(TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int min) {
                hour=hourOfDay;
                minute=min;
            }
        });


        /**/final ToggleButton tbSun=(ToggleButton)findViewById(R.id.tbSun);
        final ToggleButton tbMon=(ToggleButton)findViewById(R.id.tbMon);
        final ToggleButton tbTue=(ToggleButton)findViewById(R.id.tbTue);
        final ToggleButton tbWen=(ToggleButton)findViewById(R.id.tbWen);
        final ToggleButton tbThu=(ToggleButton)findViewById(R.id.tbThu);
        final ToggleButton tbFri=(ToggleButton)findViewById(R.id.tbFri);
        final ToggleButton tbSat=(ToggleButton)findViewById(R.id.tbSat);

        tbSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbSun.isChecked()){
                    setWeek[0]='1';
                }
                else
                    setWeek[0]='0';
            }
        });
        tbMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbMon.isChecked()){
                    setWeek[1]='1';
                }
                else
                    setWeek[1]='0';
            }
        });
        tbTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbTue.isChecked()){
                    setWeek[2]='1';
                }
                else
                    setWeek[2]='0';
            }
        });
        tbWen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbWen.isChecked()){
                    setWeek[3]='1';
                }
                else
                    setWeek[3]='0';
            }
        });
        tbThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbThu.isChecked()){
                    setWeek[4]='1';
                }
                else
                    setWeek[4]='0';
            }
        });
        tbFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbFri.isChecked()){
                    setWeek[5]='1';
                }
                else
                    setWeek[5]='0';
            }
        });
        tbSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbSat.isChecked()){
                    setWeek[6]='1';
                }
                else
                    setWeek[6]='0';
            }
        });


        final TextView txtlasttime=(TextView)findViewById(R.id.textTbc);
        TextView txtContinue=(TextView)findViewById(R.id.tvContinue);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AlarmSettingActivity.this);
                LayoutInflater inflater=getLayoutInflater();
                final String[] con={"1","2","3","5","10"};
                final View dialodView=inflater.inflate(R.layout.alarm_sleep,null);
                builder.setTitle("重复时间")
                        .setSingleChoiceItems(con,-1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtlasttime.setText(con[which]);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtlasttime.setText("");
                            }
                        });
                builder.show();
            }
            TextView tvTbc=(TextView)findViewById(R.id.textTbc);

        });
        TextView tvMusic=(TextView)findViewById(R.id.ring) ;
        tvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPickRingtone();
            }
        });
        Button btnSet=(Button)findViewById(R.id.btnSave);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String week=new String(setWeek);
                int state=1;
                int lasttime=Integer.valueOf(txtlasttime.getText().toString()).intValue();
                String week=new String(setWeek);
                Log.d("aaa",hour+"  " + minute +"  "+ lasttime +"  "+ week +"  "  + state + "aa");

                Intent intent1 = getIntent();
                Intent intent=new Intent(AlarmSettingActivity.this,MainActivity.class);

                String id = intent1.getStringExtra("id");
                Log.v("id",id);
                update(id,hour,minute,lasttime,week,state,mRingtoneUri);
                startActivity(intent);
            }
        });


    }

    private void update(String strId,int hour,int minute,
                        int lasttime,String week,int state,String music){
        SQLiteDatabase db=alarmDBHelper.getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put(Alarms.Alarm.COLUMN_NAME_HOUR,hour);
        values.put(Alarms.Alarm.COLUMN_NAME_MINUTE,minute);
        values.put(Alarms.Alarm.COLUMN_NAME_LASTTIME,lasttime);
        values.put(Alarms.Alarm.COLUMN_NAME_WEEK,week);
        values.put(Alarms.Alarm.COLUMN_NAME_STATE,state);
        values.put(Alarms.Alarm.COLUMN_NAME_MUSIC,music);

        String selection= Alarms.Alarm._ID+" =?";
        String[] selectionArgs={strId};
        int count=db.update(Alarms.Alarm.TABLE_NAME,
                values,selection,selectionArgs);
    }

    private void doPickRingtone() {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        // Allow user to pick 'Default'
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        // Show only ringtones
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
                RingtoneManager.TYPE_RINGTONE);
        // Don't show 'Silent'
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);

        Uri ringtoneUri;
        if (mRingtoneUri != null) {
            ringtoneUri = Uri.parse(mRingtoneUri);
        } else {
            // Otherwise pick default ringtone Uri so that something is
            // selected.
            ringtoneUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }

        // Put checkmark next to the current ringtone for this contact
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
                ringtoneUri);

        // Launch!
        // startActivityForResult(intent, REQUEST_CODE_PICK_RINGTONE);
        startActivityForResult(intent, REQUEST_CODE_PICK_RINGTONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_PICK_RINGTONE: {
                Uri pickedUri = data
                        .getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                handleRingtonePicked(pickedUri);
                break;
            }
        }
    }

    private void handleRingtonePicked(Uri pickedUri) {
        if (pickedUri == null || RingtoneManager.isDefault(pickedUri)) {
            mRingtoneUri = null;
        } else {
            mRingtoneUri = pickedUri.toString();
        }
        // get ringtone name and you can save mRingtoneUri for database.
        /*if (mRingtoneUri != null) {
            music=RingtoneManager
                    .getRingtone(this, pickedUri).getTitle(this);
        } else {
            music=getString(R.string.default_ringtone);
        }*/
        // ContentValues values = new ContentValues();
        // values.put(Contacts.CUSTOM_RINGTONE, mRingtoneUri);
        // //mContactId mean contacts id
        // getContentResolver().update(Contacts.CONTENT_URI, values,
        // Contacts._ID + " = " + mContactId, null);
    }
}
