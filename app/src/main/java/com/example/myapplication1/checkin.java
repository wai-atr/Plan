package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myapplication1.listview.MyListAdapter;
import com.example.myapplication1.listview.MyListAdapter_checkin;

import java.util.ArrayList;
import java.util.List;


public class checkin extends AppCompatActivity {
    private ListView listView_checkin;
    private MyListAdapter_checkin adapter_checkin;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        //返回按钮
        back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(checkin.this, MainActivity.class);
                startActivity(intent);
            }
        });

        CheckInCatalog.getSingleCatalog().removeEventAll();
        CheckInCatalog.getSingleCatalog().addEvent();
        listView_checkin=(ListView)findViewById(R.id.listview_checkin);
        adapter_checkin = new MyListAdapter_checkin(checkin.this);
        listView_checkin.setAdapter(adapter_checkin);
     }

}
