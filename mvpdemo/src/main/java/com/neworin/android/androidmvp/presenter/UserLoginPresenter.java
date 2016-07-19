package com.neworin.android.androidmvp.presenter;

import android.os.Handler;
import android.util.Log;

import com.neworin.android.androidmvp.bean.User;
import com.neworin.android.androidmvp.biz.IUserBiz;
import com.neworin.android.androidmvp.biz.OnLoginListener;
import com.neworin.android.androidmvp.biz.impl.IUserBizImpl;
import com.neworin.android.androidmvp.view.i.IUserLoginView;

/**
 * Created by NewOrin Zhang on 2016/7/19.
 * E-mail: NewOrin@163.com
 */
public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private String TAG = "UserLoginPresenter";
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userBiz = new IUserBizImpl();
        this.userLoginView = userLoginView;
    }

    /**
     * 用户登录
     */
    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUsername(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                Log.d(TAG, "当前线程:" + Thread.currentThread().getId());
                //在UI线程中执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.showTextView(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }
}
