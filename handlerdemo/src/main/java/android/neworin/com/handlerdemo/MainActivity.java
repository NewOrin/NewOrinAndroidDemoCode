package android.neworin.com.handlerdemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String TAG = "MainActivity";
    private int i = 0;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("请稍后");
    }
    public void onClick(View v){
        progressDialog.show();
        ++i;
        //创建新的线程
        new Thread(){
            @Override
            public void run() {
                super.run();
                doSendMsg();
            }
        }.start();
    }

    /**
     * 在子线程中做耗时操作，完成之后，通知Handler更新UI
     */
    private void doSendMsg(){
        try {
            Thread.sleep(1000);//模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = Message.obtain();
        message.arg1 = i;
        message.what = 1;
        mHandler.sendMessage(message);
    }

    Handler mHandler = new Handler(){
        /**
         * handleMessage接收消息后进行相应的处理
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                progressDialog.dismiss();
                textView.setText(msg.arg1+"");
            }
        }
    };
}
