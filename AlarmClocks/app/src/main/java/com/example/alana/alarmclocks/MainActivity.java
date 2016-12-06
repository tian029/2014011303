package com.example.alana.alarmclocks;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.zip.Inflater;

import static android.content.Context.ALARM_SERVICE;
import static com.example.alana.alarmclocks.R.id.textId;

public class MainActivity extends AppCompatActivity {
private AlarmDBHelper alarmDBHelper;
    private LayoutInflater mInflater;
    private String[] enableDaysName =new String[]{"周日","周一", "周二", "周三", "周四", "周五", "周六"};
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.lstAlarm);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView textId =(TextView)view.findViewById(R.id.textId);
                String strId=textId.getText().toString();
                Log.v("ccc",strId);
                Intent intent=new Intent(MainActivity.this,AlarmSettingActivity.class);
                intent.putExtra("id",strId);
                startActivity(intent);
                //update(String strId,int hour,int minute,int lasttime,String week,int state,String music);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //View itemView=findViewById(R.layout.item);
                TextView textId=(TextView)view.findViewById(R.id.textId);
                if(textId!=null){
                    String strId=textId.getText().toString();
                    deleteDialog(strId);
                }
                return true;
            }
        });

        alarmDBHelper=new AlarmDBHelper(this);

        ArrayList<Map<String,String>> items=getAll();
        setAlarmListView(items);

        Button btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AlarmAddingActivity.class);
                startActivity(intent);
            }
        });
        //registerForContextMenu(listView);
    }

    protected void onDestroy(){
        super.onDestroy();
        alarmDBHelper.close();
    }

    private void setAlarmListView(ArrayList<Map<String,String>> items){
        SimpleAdapter adapter=new SimpleAdapter(this,items,R.layout.item,
                new String[]{"_id","time","repeat","state"},
                new int[]{textId,R.id.textViewTime,R.id.textViewWeek,R.id.textState});
        ListView list=(ListView)findViewById(R.id.lstAlarm);
        list.setAdapter(adapter);
    }
    private ArrayList<Map<String,String>> getAll(){
        SQLiteDatabase db=alarmDBHelper.getReadableDatabase();
        ArrayList<Map<String,String>> list=new ArrayList<>();

        String[] projection={
                Alarms.Alarm._ID,
                Alarms.Alarm.COLUMN_NAME_HOUR,
                Alarms.Alarm.COLUMN_NAME_MINUTE,
                Alarms.Alarm.COLUMN_NAME_LASTTIME,
                Alarms.Alarm.COLUMN_NAME_WEEK,
                Alarms.Alarm.COLUMN_NAME_STATE,
                Alarms.Alarm.COLUMN_NAME_MUSIC
        };
        String sortOrder= Alarms.Alarm.COLUMN_NAME_HOUR+" DESC";
        Cursor cursor=db.query(
                Alarms.Alarm.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while(cursor.moveToNext()){
            Map<String,String> map=new HashMap<>();
            String timeStr;
            if(cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_MINUTE))>=10) {
                timeStr = String.valueOf(cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_HOUR)))
                        + ":" + String.valueOf(cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_MINUTE)));
            }
            else{
                timeStr = String.valueOf(cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_HOUR)))
                    + ":0" + String.valueOf(cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_MINUTE)));
            }

            String repearStr=cursor.getString(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_WEEK));
            String dateStr="";
            char [] date=repearStr.toCharArray();
            for(int i=0;i<date.length;i++){
                if(date[i]=='1'){
                    dateStr=dateStr+enableDaysName[i];
                    //Log.v("aaa",enableDaysName[i]);
                }
                else {
                    dateStr = dateStr + "";
                    Log.v("aaa", enableDaysName[i]);
                }
               /// Log.v("aaa",date[i]);
            }
            map.put("_id",String.valueOf(cursor.getInt(0)));
            map.put("time",timeStr);
            map.put("repeat",dateStr);
            map.put("state",String.valueOf(cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_STATE))));
            list.add(map);
            Log.v("state",String.valueOf(cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_STATE))));

            //Calendar calendar = Calendar.getInstance(Locale.CHINESE);;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MINUTE, cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_MINUTE)));
            calendar.set(Calendar.HOUR_OF_DAY, cursor.getInt(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_HOUR)));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Calendar currentTime = Calendar.getInstance();
            if(calendar.getTimeInMillis() <= currentTime.getTimeInMillis()){
                calendar.setTimeInMillis(calendar.getTimeInMillis() + 24*60*60*1000);
            }

// 选择的定时时间
            long selectTime = calendar.getTimeInMillis();
            AlarmManager am=(AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);  ;
           Intent intent=new Intent(MainActivity.this,AlarmReceiver.class);
            intent.putExtra("music",cursor.getString(cursor.getColumnIndex(Alarms.Alarm.COLUMN_NAME_MUSIC)));
            intent.putExtra("id",String.valueOf(cursor.getInt(0)));
            intent.setAction("ALARM_ACTION");
            PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,cursor.getInt(0),intent,0);
            am.set(AlarmManager.RTC_WAKEUP,selectTime,pendingIntent);

            //sendBroadcast(intent);
        }

        return list;
    }

    private void deleteUseSql(String strId){
        String sql="delete from alarms where _id='"+strId+"'";
        SQLiteDatabase db=alarmDBHelper.getReadableDatabase();
        db.execSQL(sql);
    }
    private void delete(String  strId){
        SQLiteDatabase db=alarmDBHelper.getReadableDatabase();
        String  selection = Alarms.Alarm._ID+" = ?";
        String[] selectionArgs={strId};
        db.delete(Alarms.Alarm.TABLE_NAME,selection,selectionArgs);
    }
    private void deleteDialog(final String strId){
        new AlertDialog.Builder(this).setTitle("删除闹钟")
                .setMessage("是否真的删除闹钟").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
                Log.v("aaa",strId);
                PendingIntent sender = PendingIntent.getBroadcast(
                        MainActivity.this, Integer.parseInt(strId), intent, 0);

                Log.v("aaa",strId+"aaa");
                // And cancel the alarm.
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                deleteUseSql(strId);
                setAlarmListView(getAll());
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }
  /* private class AlarmTimeAdapter extends BaseAdapter {


       @Override
       public int getCount() {
           return 0;
       }

       @Override
       public Object getItem(int position) {
           return null;
       }

       @Override
       public long getItemId(int position) {
           return 0;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           convertView=mInflater.inflate(R.layout.item,null);

       }
   }*/

}
