/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seker.common.BaseActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * 主入口Activity
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class TrainingMainActivity extends BaseActivity implements OnItemClickListener {
    
    private static final String KEY_SPLIT = "/";

    private static final String KEY_TITLE = "title";

    private static final String KEY_INTENT = "intent";

    public static final String KEY_PATH = "com.example.android.apis.Path";
    
    private ListView mListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mListView = new ListView(this);
        setContentView(mListView);
        
        Intent intent = getIntent();
        String path = intent.getStringExtra(KEY_PATH);
        
        if (path == null) {
            path = "";
        }

        mListView.setAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[] { KEY_TITLE },
                new int[] { android.R.id.text1 }));
        mListView.setTextFilterEnabled(true);
        mListView.setOnItemClickListener(this);
    }
    
    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list) {
            return myData;
        }

        String[] prefixPath;
        String prefixWithSlash = prefix;
        
        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split(KEY_SPLIT);
            prefixWithSlash = prefix + KEY_SPLIT;
        }
        
        int len = list.size();
        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;
            
            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {
                
                String[] labelPath = label.split(KEY_SPLIT);

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        Intent intent = browseIntent(prefix.equals("") ? nextLabel : prefix + KEY_SPLIT + nextLabel);
                        addItem(myData, nextLabel, intent);
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator);
        
        return myData;
    }
    

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
        new Comparator<Map<String, Object>>() {
        private final Collator   collator = Collator.getInstance();

        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
            return collator.compare(map1.get(KEY_TITLE), map2.get(KEY_TITLE));
        }
    };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }
    
    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, TrainingMainActivity.class);
        result.putExtra(KEY_PATH, path);
        return result;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put(KEY_TITLE, name);
        temp.put(KEY_INTENT, intent);
        data.add(temp);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>)av.getItemAtPosition(position);

        Intent intent = (Intent) map.get(KEY_INTENT);
        startActivity(intent);
    }
}
