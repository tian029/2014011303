package com.example.alana.alarmclocks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alana on 2016/10/25.
 */

public class AlarmDBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "AlarmDataBase";//数据库名字
    private final static int DATABASE_VERSION = 1;//数据库版本

    private final static String SQL_CREATE_DATABASE="CREATE TABLE "
            + Alarms.Alarm.TABLE_NAME+"("+ Alarms.Alarm._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Alarms.Alarm.COLUMN_NAME_HOUR+" INTEGER,"
            + Alarms.Alarm.COLUMN_NAME_MINUTE+" INTEGER,"
            + Alarms.Alarm.COLUMN_NAME_LASTTIME+" INTEGER,"
            + Alarms.Alarm.COLUMN_NAME_WEEK+" TEXT,"
            + Alarms.Alarm.COLUMN_NAME_STATE+" INTEGER,"
            + Alarms.Alarm.COLUMN_NAME_MUSIC+" TEXT"+")";
    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS "
            + Alarms.Alarm.TABLE_NAME;

    public AlarmDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_DATABASE);
        onCreate(sqLiteDatabase);
    }
}
