package com.test.measurelistviewofsliding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private List<Integer> list = new ArrayList<>();
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 屏蔽actionbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        initData();
    }


    private void initData() {
        for (int i = 0; i < 500; i++) {
            list.add(i);
        }
        mAdapter = new MyAdapter(this,list);
        lv.setAdapter(mAdapter);
    }
}
