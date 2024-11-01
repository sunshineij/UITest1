package com.example.uitest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private int[] picture = new int[]{R.drawable.cat, R.drawable.dog, R.drawable.elephant,
            R.drawable.lion, R.drawable.monkey, R.drawable.tiger};
    private String[] text = new String[]{
            "Cat", "Dog", "Elephant", "Lion", "Monkey", "Tiger"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", text[i]);
            map.put("image", picture[i]);
            mapList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, mapList, R.layout.cell, new String[]{"image", "name"}, new int[]{R.id.image, R.id.name});
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 先将所有行的背景设为默认颜色
                for (int i = 0; i < parent.getChildCount(); i++) {
                    parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                // 将点击的行背景设为红色
                view.setBackgroundColor(Color.RED);
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, map.get("name").toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
