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
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> 75061d38a2d102768add38d10a2a7a023aece9bf

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
 * Created by Yoe on 2015/10/11.
 */
public class SocialNewsFragment extends Fragment {

    private RecyclerView sRecyclerView;
    private List<NewsResultList> newsResultLists;
    private SwipyRefreshLayout socialSwipyRefresh;
    private LinearLayoutManager mLinearLayoutManager;
    int pageNum = 1;
    private RecyclerviewAdapter  mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.news_list,container,false);
        sRecyclerView = (RecyclerView) view.findViewById(R.id.focusRecyclerView);
        socialSwipyRefresh = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        socialSwipyRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final SwipyRefreshLayoutDirection direction) {
<<<<<<< HEAD
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        socialSwipyRefresh.setRefreshing(false);
                    }
                },5000);
=======
>>>>>>> 75061d38a2d102768add38d10a2a7a023aece9bf
                switch (direction) {
                    case TOP:
                        getData(pageNum,2);
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
                            socialSwipyRefresh.setRefreshing(false);
                    }
=======
                        if (pageNum == 1){
                            pageNum = 2;
                            getData(pageNum,3);
                        }
                        getData(pageNum++,3);
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
        getData(1,1);
    }

    private void getData(int pageNum, final int what){
        if (what == 2) {
            newsResultLists.clear();
        }

        OkHttpClientManager.getAsyn("http://api.huceo.com/social/other/" +
                        "?key=0c59cea54324c7daa1bf10fc38958b48&num=10&page="+pageNum+"",
                new OkHttpClientManager.ResultCallback<NewsList>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(NewsList newsList) {
                        mLinearLayoutManager = new LinearLayoutManager(getActivity());
                        sRecyclerView.setLayoutManager(mLinearLayoutManager);
                        newsResultLists.addAll(newsList.getNewslist());
                        mHandler.sendEmptyMessage(what);
                        socialSwipyRefresh.setRefreshing(false);
                    }
                });
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://初始化数据
                    mAdapter = new RecyclerviewAdapter(getActivity(),newsResultLists);
                    sRecyclerView.setAdapter(mAdapter);
                    break;
                case 2://刷新数据
                case 3://添加数据
                    mAdapter.notifyDataSetChanged();
                    int positionItem = pageNum * 10 - 10;
                    sRecyclerView.scrollToPosition(positionItem);
                    break;
                case 4://空数据
                    break;
            }
        }
    };

}
