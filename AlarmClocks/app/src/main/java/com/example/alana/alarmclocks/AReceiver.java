package com.example.alana.alarmclocks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Alana on 2016/11/24.
 */

public class AReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String music =intent.getStringExtra("music");
        String id=intent.getStringExtra("id");

        Log.v("aa",music);
        Intent fireAlarm = new Intent(context, AlarmRemindActivity.class);
        //fireAlarm.putExtra(Alarms.ID, id);
        fireAlarm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 启动一个新的UI对象来提醒
        fireAlarm.putExtra("ring",music);
        context.startActivity(fireAlarm);
    }
}
