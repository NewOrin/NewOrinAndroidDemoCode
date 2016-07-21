package android.neworin.com.customizepageadapterdemo;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NewOrin Zhang on 2016/7/21.
 * E-mail: NewOrin@163.com
 */
public class MyPageAdapter extends CustomizePageAdapter {
    @Override
    protected View createItem(ViewGroup viewGroup, int paramInt) {
        return (View) getData().get(paramInt);
    }
}
