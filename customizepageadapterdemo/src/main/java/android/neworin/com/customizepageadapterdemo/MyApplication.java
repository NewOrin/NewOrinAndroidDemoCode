package android.neworin.com.customizepageadapterdemo;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by NewOrin Zhang on 2016/7/21.
 * E-mail: NewOrin@163.com
 */
public class MyApplication extends Application {
    private static Context context = null;

    @Override
    public void onCreate() {
        //初始化
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static ContentResolver getExContentResolver() {
        return context.getContentResolver();
    }

    public static Resources getExResources() {
        return context.getResources();
    }
}
