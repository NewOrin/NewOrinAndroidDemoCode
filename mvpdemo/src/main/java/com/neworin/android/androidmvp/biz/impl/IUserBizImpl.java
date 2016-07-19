package com.neworin.android.androidmvp.biz.impl;

import android.util.Log;

import com.neworin.android.androidmvp.bean.User;
import com.neworin.android.androidmvp.biz.IUserBiz;
import com.neworin.android.androidmvp.biz.OnLoginListener;

/**
 * Created by NewOrin Zhang on 2016/7/18.
 * E-Mail : NewOrinZhang@Gmail.com
 */
public class IUserBizImpl implements IUserBiz {

    String TAG = "IUserBizImpl";

    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        Log.d(TAG, "登录用户名:" + username + ",登录密码:" + password);
        //模拟子线程操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("neworin".equals(username) && "123".equals(password)) {
                    Log.d(TAG, "匹配");
                    User user = new User(username, password);
                    loginListener.loginSuccess(user);
                } else {
                    Log.d(TAG, "不匹配");
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
