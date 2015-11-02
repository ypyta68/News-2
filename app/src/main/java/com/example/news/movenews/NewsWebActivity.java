package com.example.news.movenews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Yoe on 2015/10/18.
 */
public class NewsWebActivity extends AppCompatActivity{

    final Activity context = this;
    private WebView newsWeb;
    private Toolbar Webtoolbar;
    private ProgressBar WebProgressBar;
    private String NewsUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_web);
        newsWeb = (WebView) findViewById(R.id.NewsWeb);
        Webtoolbar = (Toolbar) findViewById(R.id.webToolbar);
        WebProgressBar = (ProgressBar) findViewById(R.id.webProgressBar);
        newsWeb.getSettings().setJavaScriptEnabled(true);
        setSupportActionBar(Webtoolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_action_back);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("新闻内容");


        newsWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
             //   WebProgressBar.setProgress(0);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

        });

        newsWeb.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                WebProgressBar.setProgress(newProgress);
             if (newProgress == 100){
                    WebProgressBar.setVisibility(View.GONE);
                }
            }
        });
        getUrl();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
                return super.onOptionsItemSelected(item);
    }

    public void getUrl(){
        Intent  intent = getIntent();
        NewsUrl=intent.getStringExtra("NewsUrl");
        newsWeb.loadUrl(NewsUrl);
    }
}
