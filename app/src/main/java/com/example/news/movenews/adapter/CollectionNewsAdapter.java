package com.example.news.movenews.adapter;

import android.content.Context;

import com.example.news.movenews.R;
import com.example.news.movenews.model.NewsCollection;

import java.util.List;

/**
 * Created by Yoe on 2015/11/19.
 */
public class CollectionNewsAdapter extends SimpleBaseAdapter<NewsCollection> {

    private List<NewsCollection> newsCollections;

    public CollectionNewsAdapter(Context context,int layoutId,List<NewsCollection> newsCollections){
        super(context,layoutId,newsCollections);
        this.newsCollections = newsCollections;
    }

    @Override
    public void getItemView(ViewHolder holder, NewsCollection newsCollection) {
        holder.setText(R.id.CollectionNewsTitle,newsCollection.getTitle())
                .setText(R.id.CollectionNewsDescription,newsCollection.getContent())
                .setImageURL(R.id.CollectionNewsImage,newsCollection.getImage());
    }
}
