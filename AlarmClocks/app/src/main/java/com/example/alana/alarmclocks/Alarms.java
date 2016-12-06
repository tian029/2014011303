package com.example.alana.alarmclocks;

import android.provider.BaseColumns;

/**
 * Created by Alana on 2016/10/25.
 */

public class Alarms {
    public Alarms(){

    }
    public static abstract class Alarm implements BaseColumns {
        public static final String TABLE_NAME="alarms";
        //public static final String COLUMN_NAME_ID="_id";
        public static final String COLUMN_NAME_WEEK="week";
        public static final String COLUMN_NAME_STATE="state";//是否关闭闹钟的标识
        public static final String COLUMN_NAME_MUSIC="music";
        public static final String COLUMN_NAME_LASTTIME="continue";//闹钟重复的时间
        public static final String COLUMN_NAME_HOUR="hour";
        public static final String COLUMN_NAME_MINUTE="minute";
    }
    public static class AlarmDescription {
        public String id;
        public int hour;
        public int minute;
        public int lasttime;
        public String week;
        public int state;
        public String music;

        public AlarmDescription(String id, int hour,int minute,
                                int lasttime,String week,int state,String music) {
            this.id = id;
            this.hour=hour;
            this.minute=minute;
            this.lasttime=lasttime;
            this.week=week;
            this.state=state;
            this.music=music;
        }
    }
}
