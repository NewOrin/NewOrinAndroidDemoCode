package android.neworin.com.asynctaskdemo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_download;
    private ImageView image;
    private ProgressDialog progressDialog;
    private String photoUrl = "http://tupian.enterdesk.com/2013/mxy/12/10/15/3.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_download = (Button) findViewById(R.id.btn_download);
        image = (ImageView) findViewById(R.id.image);
        btn_download.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在下载图片");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        new DownloadAysncTask().execute(photoUrl);
    }

    /**
     * 下载图片
     */
    class DownloadAysncTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressDialog.dismiss();
            progressDialog = null;
            image.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            // 在后台执行异步任务，另外的线程，不能做更新UI的任何操作
            // 发送请求，下载图片
            // 1. 创建客户端对象
            HttpURLConnection connection = null;
            URL url = null;
            InputStream is = null;//输入流
            Bitmap bitmap = null;//图片资源
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//输入流对象
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    is = connection.getInputStream();
                    //获取文件长度
                    long fileLegth = is.available();
                    //改变进度条实时进度
                    long curLength = 0;
                    int len = 0;
                    byte[] data = new byte[2048];
                    while ((len = is.read(data)) != -1) {
                        curLength += len;
                        // 更新进度条
                        // 不能直接调用onProgressUpdate()方法
                        publishProgress((int) (curLength / fileLegth) * 100);
                        //写入数据
                        baos.write(data, 0, len);
                    }
                    //转存图片
                    byte[] imgRes = baos.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(imgRes, 0, imgRes.length);
                    //
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                        connection.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }
    }

}
