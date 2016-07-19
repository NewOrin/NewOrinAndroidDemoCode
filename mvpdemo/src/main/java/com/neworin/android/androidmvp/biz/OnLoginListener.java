package com.neworin.android.androidmvp.biz;


import com.neworin.android.androidmvp.bean.User;

/**
 * Created by NewOrin Zhang on 2016/7/18.
 * E-Mail : NewOrinZhang@Gmail.com
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();

}
