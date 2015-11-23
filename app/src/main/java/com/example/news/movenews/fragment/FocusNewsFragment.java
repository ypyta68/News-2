package com.example.news.movenews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news.movenews.R;
import com.example.news.movenews.adapter.RecyclerviewAdapter;
import com.example.news.movenews.adapter.fRecyclerviewAdapter;
import com.example.news.movenews.http.OkHttpClientManager;
import com.example.news.movenews.model.FocusNewList;
import com.example.news.movenews.model.FocusNewList.FocusNewListResult;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.squareup.okhttp.Request;

import java.util.List;

/**
 * Created by Yoe on 2015/10/11.
 */
public class FocusNewsFragment extends Fragment {

    private RecyclerView fRecyclerView;
    private List<FocusNewListResult> newsResultLists;
    private LinearLayoutManager mLayoutManager;
    private fRecyclerviewAdapter adapter;
    private SwipyRefreshLayout FocusSwipyRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list, container, false);
        fRecyclerView = (RecyclerView) view.findViewById(R.id.focusRecyclerView);
        FocusSwipyRefresh = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        getData();
        mLayoutManager = new LinearLayoutManager(getActivity());
        fRecyclerView.setLayoutManager(mLayoutManager);

        //实现下拉刷新和上拉加载更多事件
        FocusSwipyRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {

                //刷新5秒
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FocusSwipyRefresh.setRefreshing(false);
                    }
                },5000);

                switch (direction){
                    case TOP: //下拉
                        adapter.notifyDataSetChanged(); //刷新数据
                        break;
                    case BOTTOM: //上拉
                        Toast.makeText(getContext(),"抱歉，没有更多数据了！",Toast.LENGTH_SHORT).show();
                        FocusSwipyRefresh.setRefreshing(false);
                }
            }
        });
        return view;
    }

    //使用okhttp获取网络新闻数据
    private void getData(){
        OkHttpClientManager.getAsyn("http://www.tngou.net/api/top/list/",
                new OkHttpClientManager.ResultCallback<FocusNewList>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(FocusNewList newsList) {
                        newsResultLists = newsList.getTngou();
                        adapter = new fRecyclerviewAdapter(getActivity(), newsResultLists);
                        fRecyclerView.setAdapter(adapter);
                    }
                });
    }
}
