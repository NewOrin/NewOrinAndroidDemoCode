package com.neworin.android.androidmvp.biz;

/**
 * Created by NewOrin Zhang on 2016/7/18.
 * E-Mail : NewOrinZhang@Gmail.com
 */
public interface IUserBiz {
    void login(String username, String password, OnLoginListener onLoginListener);
}
