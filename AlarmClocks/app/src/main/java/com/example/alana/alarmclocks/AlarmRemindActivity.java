package com.example.alana.alarmclocks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.Calendar;

import static android.media.AudioManager.ADJUST_RAISE;
import static android.media.AudioManager.FLAG_PLAY_SOUND;

public class AlarmRemindActivity extends AppCompatActivity {
    private LinearLayout layout;
    private MediaPlayer mp;
    private AudioManager audioManager;
    Vibrator vibrator;
    Uri ring;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent=getIntent();
        Log.v("aa","m1");
        Log.v("aa",intent.getStringExtra("id"));
        Log.v("aa",Uri.parse(intent.getStringExtra("ring")).toString());
         ring=Uri.parse(intent.getStringExtra("ring"));
        Log.v("aa","m2");
        id=Integer.parseInt(intent.getStringExtra("id"));
        Log.v("aa","m3");
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        Log.v("aa","m4");
        winParams.flags |= (WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        Log.v("aa","m");
        try {
            startMedia();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startVibrator();
        createDialog();


    }
    private void startMedia() throws IOException {
        mp = MediaPlayer.create(this, ring);
        mp.setLooping(true);
        try {
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer player) {
                    player.start();
                }
            });
            mp.prepareAsync();


            //volunmeChange();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    private Uri getSystemDefultRingtoneUri() {
          return RingtoneManager.getActualDefaultRingtoneUri(this,
                             RingtoneManager.TYPE_RINGTONE);
         }

    /**
     * 震动
     */
    private void startVibrator() {
        /**
         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
         *
         */
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = { 500, 1000, 500, 1000 }; // 停止 开启 停止 开启
        vibrator.vibrate(pattern, 0);
    }
    private void volunmeChange() throws IOException {
        audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

        if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
            audioManager.setStreamVolume(AudioManager.STREAM_RING, 1, FLAG_PLAY_SOUND);
            mp.setAudioStreamType(AudioManager.STREAM_ALARM);
            mp.setLooping(true);
            try {
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer player) {
                        player.start();
                    }
                });
                mp.prepareAsync();



            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

            if (mp.isPlaying()) {
                final int volume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                new Thread() {

                    @Override
                    public void run() {
                        super.run();
                        for (int i = 1; i <= volume; i++) {
                            audioManager.setStreamVolume(AudioManager.STREAM_ALARM, i , FLAG_PLAY_SOUND );
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }.start();
            }
        }
    }

    private void createDialog() {
        new AlertDialog.Builder(this)
                .setTitle("闹钟提醒时间到了!!")
                .setMessage("闹钟时间到了!!!")
                .setPositiveButton("再响", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //tenMRemind();
                        String tel="15176915109";
                        String message="我又贪睡了，请在5分钟后叫我起床O(∩_∩)O";
                        sendMsg(tel,message);
                        mp.stop();
                        vibrator.cancel();
                        AlarmManager am=(AlarmManager) getSystemService(ALARM_SERVICE);  ;
                        Intent intent=new Intent(AlarmRemindActivity.this,AlarmReceiver.class);
                        intent.putExtra("music",ring.toString());
                        intent.putExtra("id","0");
                        intent.setAction("ALARM_SNOOZE_ACTION");
                        PendingIntent pendingIntent=PendingIntent.getBroadcast(AlarmRemindActivity.this,0,intent,0);
                        am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+3*60*1000,pendingIntent);
                        finish();
                    }
                })
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mp.stop();
                        vibrator.cancel();
                        Intent intent = new Intent(AlarmRemindActivity.this,AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmRemindActivity.this, id, intent, 0);
                        //获取闹钟管理器
                        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        alarmManager.cancel(pendingIntent);
                        finish();
                    }
                }).show();
    }
    private void tenMRemind(){
        //设置时间
        Calendar calendar_now =Calendar.getInstance();


        calendar_now.setTimeInMillis(System.currentTimeMillis());
        calendar_now.set(Calendar.HOUR_OF_DAY, calendar_now.get(Calendar.HOUR_OF_DAY));
        calendar_now.set(Calendar.MINUTE, calendar_now.get(Calendar.MINUTE)+10);
        calendar_now.set(Calendar.SECOND, 0);
        calendar_now.set(Calendar.MILLISECOND, 0);

        //时间选择好了
        Intent intent = new Intent(this, AlarmReceiver.class);
        //注册闹钟广播
        PendingIntent sender = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am;
        am = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar_now.getTimeInMillis(), sender);
    }
    private void sendMsg(String number, String message) {
          SmsManager smsManager =SmsManager.getDefault();
          smsManager.sendTextMessage(number, null,message, null, null);
         }

}
