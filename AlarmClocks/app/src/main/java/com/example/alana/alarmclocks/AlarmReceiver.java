package com.example.alana.alarmclocks;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static android.content.Intent.getIntent;

/**
 * Created by Alana on 2016/11/9.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "闹钟时间到了！", Toast.LENGTH_SHORT).show();
        //Intent intent=new Intent(AlarmReceiver.this,AlarmRemindActivity.class);

        long now = System.currentTimeMillis();
        //int id = intent.getIntExtra(Alarms.ID, 0 );
        //long setFor = intent.getLongExtra(Alarms.TIME, 0 );

        String music =intent.getStringExtra("music");
        String id=intent.getStringExtra("id");

        //Log.v("aa",music);
        Intent fireAlarm = new Intent(context, AlarmRemindActivity.class);
        //fireAlarm.putExtra(Alarms.ID, id);
        fireAlarm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 启动一个新的UI对象来提醒
        fireAlarm.putExtra("ring",music);
        fireAlarm.putExtra("id",id);
        context.startActivity(fireAlarm);


    }
}
