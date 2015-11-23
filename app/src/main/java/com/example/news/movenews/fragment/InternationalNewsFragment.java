package com.example.news.movenews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news.movenews.R;
import com.example.news.movenews.adapter.RecyclerviewAdapter;
import com.example.news.movenews.http.OkHttpClientManager;
import com.example.news.movenews.model.NewsList;
import com.example.news.movenews.model.NewsList.NewsResultList;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yoe on 2015/10/12.
 */
public class InternationalNewsFragment extends Fragment {

    private RecyclerView iRecyclerView;
    private List<NewsResultList> newsResultLists;
    private SwipyRefreshLayout InternationalSwipyRefresh;
    private int pageNum = 1;
    private RecyclerviewAdapter mAdapter;
    private int positionItem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.news_list,container,false);
        iRecyclerView = (RecyclerView) view.findViewById(R.id.focusRecyclerView);
        InternationalSwipyRefresh = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        InternationalSwipyRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InternationalSwipyRefresh.setRefreshing(false);
                    }
                },5000);
                switch (direction) {
                    case TOP:
                        getData(pageNum, 2);
                        break;
                    case BOTTOM:
                        if (pageNum <= 5) {
                            if (pageNum < 2) {
                                pageNum = 2;
                                getData(pageNum, 3);
                            }
                            getData(pageNum++, 3);
                        } else {
                            Toast.makeText(getContext(), "抱歉，没有更多数据了！", Toast.LENGTH_SHORT).show();
                            InternationalSwipyRefresh.setRefreshing(false);
                        }
                        break;
                }
            }
        });
        newsResultLists = new ArrayList<>();
        initData();
        return view;
    }

    private void initData(){
        getData(1,1);
    }

    public void getData(int pageNum,final int what){
        if (what == 2){
            newsResultLists.clear();
        }

        OkHttpClientManager.getAsyn("http://api.huceo.com/world/other/" +
                        "?key=0c59cea54324c7daa1bf10fc38958b48&num=10&page="+pageNum+"",
                new OkHttpClientManager.ResultCallback<NewsList>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(NewsList newsList) {
                        newsResultLists.addAll(newsList.getNewslist());
                        mHander.sendEmptyMessage(what);
                        InternationalSwipyRefresh.setRefreshing(false);
                    }
                });
    }

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    mAdapter = new RecyclerviewAdapter(getActivity(),newsResultLists);
                    iRecyclerView.setAdapter(mAdapter);
                    break;
                case 2:
                case 3:
                    mAdapter.notifyDataSetChanged();
                    positionItem = pageNum * 10 - 10;
                    iRecyclerView.scrollToPosition(positionItem);
                    break;
                case 4:
                    break;
            }
        }
    };
}
