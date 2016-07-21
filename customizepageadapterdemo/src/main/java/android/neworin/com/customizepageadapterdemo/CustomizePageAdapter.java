package android.neworin.com.customizepageadapterdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 公共自定义的页面适配器 -- 抽象类
 * Created by NewOrin Zhang on 2016/7/21.
 * E-mail: NewOrin@163.com
 */
public abstract class CustomizePageAdapter<T> extends PagerAdapter {

    private List<T> mData;//装载的数据结合

    public CustomizePageAdapter() {
    }

    public CustomizePageAdapter(List<T> mData) {
        this.mData = mData;
    }

    //抽象方法
    protected abstract View createItem(ViewGroup viewGroup, int paramInt);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public Context getContext() {
        return MyApplication.getContext();
    }

    @Override
    public int getCount() {
        if (this.mData == null) {
            return 0;
        }
        return mData.size();
    }

    /**
     * mData的getter setter方法
     *
     * @return
     */
    public List<T> getData() {
        return this.mData;
    }

    public void setData(List<T> mData) {
        this.mData = mData;
    }

    public T getItem(int paramInt) {
        if (this.mData == null) {
            return null;
        }
        T localObject = this.mData.get(paramInt);
        return localObject;
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(MyApplication.getContext());
    }

    /**
     * 初始化Item
     *
     * @param viewGroup
     * @param paramInt
     * @return
     */
    public Object instantiateItem(ViewGroup viewGroup, int paramInt) {
        View localView = createItem(viewGroup, paramInt);
        viewGroup.addView(localView);
        return localView;
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
