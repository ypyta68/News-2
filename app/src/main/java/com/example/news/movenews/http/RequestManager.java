package com.example.news.movenews.http;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.news.movenews.News;


/**
 * Created by Yone on 2015/7/3.
 */
public final class RequestManager {

    private static final RequestQueue mRequestQueue = Volley.newRequestQueue(News.getInstance());

    private RequestManager(){

    }

    public static void addRequest(Request<?> request,Object tag){
        if (tag != null){
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag){
        mRequestQueue.cancelAll(tag);
    }

    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
