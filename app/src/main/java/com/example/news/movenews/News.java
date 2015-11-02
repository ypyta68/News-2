package com.example.news.movenews;

import android.app.Application;
import android.net.NetworkInfo;

/**
 * Created by Yoe on 2015/10/20.
 */
public class News extends Application {

    private static News mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static News getInstance(){
        return mContext;
    }
}
