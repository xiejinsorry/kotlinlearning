package com.example.xiejin.kotlinlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter<String, BaseViewHolder> adapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rl_list);
        mRecyclerView.setLayoutManager(mManager = new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_simple, getList()) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                boolean flag = item.startsWith("https");
                helper.setVisible(R.id.iv_scroll, flag);
                helper.setVisible(R.id.tv_name, !flag);
                if (flag) {
                    Glide.with(getApplicationContext()).load(item).diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.iv_scroll));
                } else {
                    helper.setText(R.id.tv_name, item);
                }
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fp = mManager.findFirstVisibleItemPosition();
                int lp = mManager.findLastCompletelyVisibleItemPosition();
                for (int i = fp; i <= lp; i++) {
                    View v = mManager.findViewByPosition(i);
                    MySrollImageView imageView = v.findViewById(R.id.iv_scroll);
                    if (imageView != null) {
                        if (imageView.getVisibility() == View.VISIBLE) {
                            imageView.setDy(mManager.getHeight() - v.getTop());
                        }
                    }
                }
            }
        });
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i == 10) {
                list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3001897710,2599592361&fm=27&gp=0.jpg");
            } else {
                list.add("fuckyou" + i);
            }
        }
        return list;
    }
}
