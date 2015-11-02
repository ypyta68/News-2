package com.example.news.movenews.http;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.movenews.News;
import com.example.news.movenews.model.NewsList;

/**
 * Created by Yoe on 2015/10/20.
 */
public class NewsAPI {

    public static GsonRequest<NewsList> getNewsRequest(String Url){
        return new GsonRequest<NewsList>(Url,buildDefaultErrorListener());
    }

    private static Response.ErrorListener buildDefaultErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(News.getInstance(), "您的网络不给力", Toast.LENGTH_LONG).show();
            }
        };
    }
}
