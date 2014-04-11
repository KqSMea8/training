/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.ui.adapterview;

import java.util.ArrayList;
import java.util.List;

import seker.common.BaseActivity;
import seker.common.Utility;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author xinjian.lxj
 */
public class ListViewActivity extends BaseActivity {
    ListView mListView;
    TestAdapter mAdapter;
    List<String> mDataList = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = new ListView(this);
        mDataList.add("Hello");
        mDataList.add("ListView");
        mDataList.add("Test");
        mDataList.add("Activity");
        mAdapter = new TestAdapter(mDataList);
        mListView.setAdapter(mAdapter);
        setContentView(mListView);
        
        new Thread() {
            @Override
            public void run() {
                for (int i = 0, N = 10; i < N; i++) {
                    try {
                        Thread.sleep(DateUtils.SECOND_IN_MILLIS * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mDataList.add(Utility.createFileName(i + ".txt"));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }.start();
    }
}
class TestAdapter extends BaseAdapter {
    
    List<String> mDataList;
    
    public TestAdapter(List<String> dataList) {
        mDataList = dataList;
    }
    
    @Override
    public int getCount() {
        return null == mDataList ? 0 : mDataList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView itemView;
        if (null == convertView) {
            itemView = new TextView(parent.getContext());
            itemView.setTextSize(24);
        } else {
            itemView = (TextView) convertView;
        }
        itemView.setText(mDataList.get(position));
        return itemView;
    }
}
