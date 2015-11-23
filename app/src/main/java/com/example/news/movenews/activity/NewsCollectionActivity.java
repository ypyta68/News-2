package com.example.news.movenews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.news.movenews.R;
import com.example.news.movenews.adapter.CollectionNewsAdapter;
import com.example.news.movenews.db.NewsDB;
import com.example.news.movenews.db.NewsOpenHelper;
import com.example.news.movenews.model.NewsCollection;

import java.util.List;

/**
 * Created by Yoe on 2015/11/19.
 */
public class NewsCollectionActivity extends AppCompatActivity{

    private Toolbar CollectToobar;
    private ListView NewsCollectionList;
    private NewsDB newsDB;
    private List<NewsCollection> newsCollectionData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_collection_list);
        CollectToobar = (Toolbar) findViewById(R.id.collectionToolbar);
        NewsCollectionList = (ListView) findViewById(R.id.CollectionList);
        setSupportActionBar(CollectToobar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_action_back);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("收藏");
        newsDB = NewsDB.getInstance(this);
        newsCollectionData=newsDB.loadNews();
        NewsCollectionList.setAdapter(new CollectionNewsAdapter(NewsCollectionActivity.this,
               R.layout.news_collection_info,newsCollectionData));
        NewsCollectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(NewsCollectionActivity.this,NewsWebActivity.class);
                intent.putExtra("NewsUrl",newsCollectionData.get(position).getUrl());
                intent.putExtra("NewsTitle",newsCollectionData.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
