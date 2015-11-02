package com.example.news.movenews.adapter;

/**
 * Created by Yoe on 2015/10/17.
 */
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
import com.example.news.movenews.model.FocusNewList;

import java.util.List;

/**
 * Created by Yoe on 2015/10/13.
 */
public class fRecyclerviewAdapter extends RecyclerView.Adapter<fRecyclerviewAdapter.ViewHolder> {

    private Context mContext;
    private List<FocusNewList.FocusNewListResult> newsResultLists;

    public fRecyclerviewAdapter(Context context, List<FocusNewList.FocusNewListResult> newsResultLists){
        this.mContext = context;
        this.newsResultLists = newsResultLists;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_focusnews,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.newsTitle.setText(newsResultLists.get(position).getKeywords());
        holder.newsDescription.setText(newsResultLists.get(position).getDescription());

        if((newsResultLists.get(position).getImg()).equals("/top/default.jpg")){
            holder.newsImage.setImageResource(R.mipmap.ic_background_drawer_header);
        }else {
            OkHttpClientManager.displayImage(holder.newsImage,
                    "http://tnfs.tngou.net/image" + newsResultLists.get(position).getImg());
        }

        holder.newsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                intent.putExtra("NewsUrl",newsResultLists.get(position).getFromurl());
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
            newsCardView = (CardView) view.findViewById(R.id.focusNewsCard);
            newsTitle = (TextView) view.findViewById(R.id.focusNewsTitle);
          //  newsTime = (TextView) view.findViewById(R.id.NewsTime);
            newsDescription = (TextView) view.findViewById(R.id.focusNewsDescription);
            newsImage = (ImageView) view.findViewById(R.id.focusNewsImage);
        }
    }
}
