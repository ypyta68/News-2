package com.example.news.movenews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.news.movenews.R;
import com.example.news.movenews.adapter.RecyclerviewAdapter;
import com.example.news.movenews.db.NewsDB;
import com.example.news.movenews.db.NewsOpenHelper;

/**
 * Created by Yoe on 2015/10/18.
 */
public class NewsWebActivity extends AppCompatActivity{

    private WebView newsWeb;
    private Toolbar Webtoolbar;
    private ProgressBar WebProgressBar;
    private String NewsUrl,NewsTitle,NewsImage,NewsContent;
    private NewsDB newsDB;
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
        newsDB = NewsDB.getInstance(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newsweb,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_collection:
                if (newsDB.HaveNewsInfo(NewsTitle) == false){
                    newsDB.DelNewsInfo();
                    //把数据存入数据库
                    newsDB.saveNews(NewsTitle,NewsImage,NewsContent,NewsUrl);
                    Toast.makeText(this,"收藏成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"你已收藏过此新闻！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,NewsTitle + NewsUrl + NewsImage);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
        }
                return super.onOptionsItemSelected(item);
    }

    //获取新闻信息
    public void getUrl(){
        Intent intent = getIntent();
        NewsUrl=intent.getStringExtra("NewsUrl");
        NewsTitle=intent.getStringExtra("NewsTitle");
        NewsImage=intent.getStringExtra("NewsImage");
        NewsContent=intent.getStringExtra("NewsContent");
        newsWeb.loadUrl(NewsUrl);
    }
}
