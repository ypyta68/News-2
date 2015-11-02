package com.example.news.movenews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.movenews.NewsWebActivity;
import com.example.news.movenews.R;
import com.example.news.movenews.http.OkHttpClientManager;
import com.example.news.movenews.model.NewsList.NewsResultList;

import java.util.List;

/**
 * Created by Yoe on 2015/10/13.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private Context mContext;
    private List<NewsResultList> newsResultLists;

    public RecyclerviewAdapter(Context context, List<NewsResultList> newsResultLists){
        this.mContext = context;
        this.newsResultLists = newsResultLists;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_newscard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.newsTitle.setText(newsResultLists.get(position).getTitle());
        holder.newsDescription.setText(newsResultLists.get(position).getDescription());
        holder.newsTime.setText(newsResultLists.get(position).getCtime());
        if (newsResultLists.get(position).getPicUrl() != ""){
            OkHttpClientManager.displayImage(holder.newsImage, newsResultLists.get(position).getPicUrl());
        }else {
            holder.newsImage.setImageResource(R.mipmap.ic_background_drawer_header);
        }

        holder.newsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                intent.putExtra("NewsUrl",newsResultLists.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsResultLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView newsCardView;
        public TextView newsTitle,newsTime,newsDescription;
        public ImageView newsImage;
        public ViewHolder(final View view){
            super(view);
            newsCardView = (CardView) view.findViewById(R.id.NewsCard);
            newsTitle = (TextView) view.findViewById(R.id.NewsTitle);
            newsTime = (TextView) view.findViewById(R.id.NewsTime);
            newsDescription = (TextView) view.findViewById(R.id.NewsDescription);
            newsImage = (ImageView) view.findViewById(R.id.NewsImage);
        }
    }
}
