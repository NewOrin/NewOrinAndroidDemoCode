package com.neworin.android.androidmvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.neworin.android.androidmvp.R;
import com.neworin.android.androidmvp.bean.User;
import com.neworin.android.androidmvp.presenter.UserLoginPresenter;
import com.neworin.android.androidmvp.view.i.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView {

    private EditText et_name, et_pwd;
    private Button btn_login;
    private ProgressBar mProgressBar;
    private TextView tv_show;

    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        Log.d(TAG, "当前线程:" + Thread.currentThread().getId());
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_show = (TextView) findViewById(R.id.tv_show);
    }

    private void initEvent() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.login();
            }
        });
    }

    @Override
    public String getUsername() {
        return et_name.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_pwd.getText().toString();
    }

    @Override
    public void showTextView(User user) {
        tv_show.setText(user + "");
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "登录失败,用户名或密码不正确!", Toast.LENGTH_SHORT).show();
    }
}
