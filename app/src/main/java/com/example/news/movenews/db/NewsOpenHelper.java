package com.example.news.movenews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * Created by Yoe on 2015/11/17.
 */
public class NewsOpenHelper extends SQLiteOpenHelper {

    //数据库表内容
    public static final String CREATE_NEWS = "create table News ("
       //     + "id integer primary key autoincrement,"
            + "title text,"
            + "image text,"
            + "content text,"
            + "url text)";

    public NewsOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS);  //创建数据库表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
