package com.neworin.scrollsticklayoutdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * <b>Project:</b> NewOrinAndroidDemoCode<br>
 * <b>Create Date:</b> 2016/9/27<br>
 * <b>Author:</b> NewOrin<br>
 * <b>Description:</b>
 */

public class ScrollStickView extends ScrollView {

    private IStickScrollListener iStickScrollListener;

    public interface IStickScrollListener {
        void onScroll(int scrollY);
    }

    public ScrollStickView(Context context) {
        this(context, null);
    }

    public ScrollStickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollStickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setiStickScrollListener(IStickScrollListener iStickScrollListener) {
        this.iStickScrollListener = iStickScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (iStickScrollListener != null) {
            iStickScrollListener.onScroll(t);
        }
    }
}
