package com.example.news.movenews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> 75061d38a2d102768add38d10a2a7a023aece9bf

import com.example.news.movenews.R;
import com.example.news.movenews.adapter.RecyclerviewAdapter;
import com.example.news.movenews.http.GsonRequest;
import com.example.news.movenews.http.NewsAPI;
import com.example.news.movenews.http.OkHttpClientManager;
import com.example.news.movenews.http.RequestManager;
import com.example.news.movenews.model.NewsList;
import com.example.news.movenews.model.NewsList.NewsResultList;
import com.google.gson.Gson;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.android.volley.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yoe on 2015/10/12.
 */
public class EntertainmentNewsFragment extends Fragment {

    private RecyclerView eRecyclerView;
    private List<NewsResultList> newsResultLists;
    private SwipyRefreshLayout  EntertainmentSwipyRefresh;
    private int pageNum = 1;
    private RecyclerviewAdapter mAdapter;
    private int positionItem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.news_list,container,false);
        eRecyclerView = (RecyclerView) view.findViewById(R.id.focusRecyclerView);
        EntertainmentSwipyRefresh = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        eRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
<<<<<<< HEAD

        EntertainmentSwipyRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EntertainmentSwipyRefresh.setRefreshing(false);
                    }
                },5000);

=======
        EntertainmentSwipyRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
>>>>>>> 75061d38a2d102768add38d10a2a7a023aece9bf
                switch (direction) {
                    case TOP:
                        getData(pageNum, 2);
                        break;
                    case BOTTOM:
<<<<<<< HEAD
                        if (pageNum <= 5) {
                                if (pageNum < 2) {
                                    pageNum = 2;
                                    getData(pageNum, 3);
                                }
                                getData(pageNum++, 3);
                            } else {
                                Toast.makeText(getContext(), "抱歉，没有更多数据了！", Toast.LENGTH_SHORT).show();
                                EntertainmentSwipyRefresh.setRefreshing(false);
                    }
=======
                        if (pageNum < 2) {
                            pageNum = 2;
                            getData(pageNum, 3);
                        }
                        getData(pageNum++, 3);
>>>>>>> 75061d38a2d102768add38d10a2a7a023aece9bf
                        break;
                }
            }
        });
        newsResultLists = new ArrayList<>();
        initData();
        return view;
    }

    private void initData(){
        getData(1, 1);
    }

    public void getData(int pageNum,final int what){
        if (what == 2){
            newsResultLists.clear();
        }
        OkHttpClientManager.getAsyn("http://api.huceo.com/huabian/other/" +
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
                        EntertainmentSwipyRefresh.setRefreshing(false);
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
                    eRecyclerView.setAdapter(mAdapter);
                    break;
                case 2:
                case 3:
                    mAdapter.notifyDataSetChanged();
                    positionItem = pageNum * 10 - 10;
<<<<<<< HEAD
                    eRecyclerView.scrollToPosition(positionItem); //定位到指定的行数
=======
                    eRecyclerView.scrollToPosition(positionItem);
>>>>>>> 75061d38a2d102768add38d10a2a7a023aece9bf
                    break;
                case 4:
                    break;
            }
        }
    };
}
