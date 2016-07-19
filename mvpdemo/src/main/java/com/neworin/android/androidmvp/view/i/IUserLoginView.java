package com.neworin.android.androidmvp.view.i;


import com.neworin.android.androidmvp.bean.User;

/**
 * Created by NewOrin Zhang on 2016/7/19.
 * E-mail: NewOrin@163.com
 */
public interface IUserLoginView {

    /**
     * 该操作需要的数据
     *
     * @return
     */

    String getUsername();

    String getPassword();

    /**
     * 显示TextView
     */
    void showTextView(User user);

    /**
     * 操作过程中ProgressBar的交互
     */

    void showLoading();

    void hideLoading();

    /**
     * 该操作的结果，对应的反馈
     *
     * @param user
     */

    void toMainActivity(User user);

    void showFailedError();

}
