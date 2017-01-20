package com.neworin.pulltorefreshdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * <b>Create Date:</b> 01/18/2017<br>
 * <b>Author:</b> NewOrin <br>
 * <b>Description:</b>
 * <br>
 */

public class MainActivity extends AppCompatActivity {

    private ListView list_view;
    private RefreshableView refreshable_view;
    private ArrayAdapter<String> adapter;
    String[] items = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initView();
    }

    private void initView() {
        list_view = (ListView) findViewById(R.id.list_view);
        refreshable_view = (RefreshableView) findViewById(R.id.refreshable_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        list_view.setAdapter(adapter);
        refreshable_view.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshable_view.finishRefreshing();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Refresh Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, 0);
    }
}
