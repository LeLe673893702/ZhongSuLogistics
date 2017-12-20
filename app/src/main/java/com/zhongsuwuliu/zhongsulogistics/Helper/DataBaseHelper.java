package com.zhongsuwuliu.zhongsulogistics.Helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 刺雒 on 2016/11/26.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    final static int VERSION = 3;
    final String CREATE_TABLE_SQL_CLASS_Container =
            "create table Container(_id varchar(20)),"+
                    "KindID varchar(20),WarehouseID varchar(20))";
    public DataBaseHelper(Context context, String name) {
        super(context, name, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL_CLASS_Container);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Container");
        db.execSQL(CREATE_TABLE_SQL_CLASS_Container);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
