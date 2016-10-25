package com.neworin.scrollsticklayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements ScrollStickView.IStickScrollListener, ViewTreeObserver.OnGlobalLayoutListener {

    private ScrollStickView mStickscrollview;
    private LinearLayout mLayout01;
    private LinearLayout mLayout02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        mStickscrollview.setiStickScrollListener(this);
        //根布局状态下,监听布局改变
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private void initView() {
        mStickscrollview = (ScrollStickView) findViewById(R.id.stickscrollview);
        mLayout01 = (LinearLayout) findViewById(R.id.stick);
        mLayout02 = (LinearLayout) findViewById(R.id.normal);
    }

    @Override
    public void onScroll(int scrollY) {
        //获取正常布局的位置来重新设置粘滞布局的位置
        int layoutTop = Math.max(scrollY, mLayout01.getTop());
        mLayout02.layout(0, layoutTop, mLayout02.getWidth(), layoutTop + mLayout02.getHeight());
    }

    @Override
    public void onGlobalLayout() {
        onScroll(mStickscrollview.getScrollY());
    }
}
