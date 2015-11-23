package com.example.news.movenews.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.example.news.movenews.model.NewsCollection;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yoe on 2015/11/17.
 */
public class NewsDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "NewsDataBase";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static NewsDB newsDB;

    private SQLiteDatabase db;

    private Context context;
    private NewsDB(Context context){
        NewsOpenHelper dbHelper = new NewsOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取NewsDB的实例
     */
    public synchronized static NewsDB getInstance(Context context){
        if (newsDB == null){
            newsDB = new NewsDB(context);
        }
        return newsDB;
    }

    /**
     * 将新闻信存储到数据库
     */
    public void saveNews(String NewsTitle,String NewsImage,String NewsContent,String NewsUrl){
        ContentValues values = new ContentValues();
        values.put("title",NewsTitle);
        values.put("image",NewsImage);
        values.put("content",NewsContent);
        values.put("url",NewsUrl);
        db.insert("News",null,values);
    }

    /**
     * 读取数据库下的所有新闻信息
     */
   public List<NewsCollection> loadNews(){
        List<NewsCollection> list = new ArrayList<NewsCollection>();
        Cursor cursor = db.query("News",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                NewsCollection newsCollection = new NewsCollection();
                newsCollection.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                newsCollection.setImage(cursor.getString(cursor.getColumnIndex("image")));
                newsCollection.setContent(cursor.getString(cursor.getColumnIndex("content")));
                newsCollection.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                list.add(newsCollection);
            }while (cursor.moveToNext());
        }
       if (cursor != null){
           cursor.close();
       }
        return list;
    }

    //根据标题判断新闻是否已存在
    public boolean HaveNewsInfo(String title){
        NewsCollection newsCollection = new NewsCollection();
        Boolean b = false;
        Cursor cursor = db.query("News",null,"title = ?",new String[]{String.valueOf(title)},null,null,null);
        b = cursor.moveToFirst();
        cursor.close();
        return b;
    }

    public void DelNewsInfo(){
        db.delete("News",null,null);
    }
}
