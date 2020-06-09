package com.example.myapplication1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication1.listview.MyListAdapter;

import java.util.Calendar;

public class allEvent extends AppCompatActivity {

    private ImageButton back_all;
    private MyListAdapter adapter;
    private ListView listView_1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event);

        back_all = (ImageButton)findViewById(R.id.imageButton);
        back_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(allEvent.this,MainActivity.class);
                startActivity(intent);
            }
        });

        listView_1 = findViewById(R.id.listview_checkin);
        adapter = new MyListAdapter(allEvent.this);
        listView_1.setAdapter(adapter);


        //设置列表监听事件

        listView_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Position.getSinglePosition().setPosition(position);
                Index.getSingleIndex().setIndex(-1);
                Intent intent = new Intent(allEvent.this, activity_edit_event.class);
                startActivity(intent);
            }
        });

        //列表长按打卡
        listView_1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if(EventCatalog.getSingleCatalog().getCatalog().get(position).getClock_in()==true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(allEvent.this);
                    builder.setTitle("打卡").setMessage("确定打卡?").setPositiveButton("点错了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(allEvent.this, "取消打卡", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("确定打卡", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(EventCatalog.getSingleCatalog().getCatalog().get(position).getClockNumber()>0&&
                                    EventCatalog.getSingleCatalog().getCatalog().get(position).getNewClockTime().
                                            get(Calendar.YEAR)==Calendar.getInstance().get(Calendar.YEAR)&&
                                    EventCatalog.getSingleCatalog().getCatalog().get(position).getNewClockTime().get(Calendar.MONTH)==
                                            Calendar.getInstance().get(Calendar.MONTH)&&
                                    EventCatalog.getSingleCatalog().getCatalog().get(
                                            position).getNewClockTime().get(Calendar.DAY_OF_MONTH)==Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
                                Toast.makeText(allEvent.this, "今日已打卡", Toast.LENGTH_SHORT).show();
                            }else{
                                EventCatalog.getSingleCatalog().getCatalog().get(position).clock();
                                EventCatalog.getSingleCatalog().getCatalog().get(
                                      position).setClockTime(Calendar.getInstance());
                                Toast.makeText(allEvent.this, "打卡成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNeutralButton("查看打卡记录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(allEvent.this, checkin.class);
                            startActivity(intent);
                        }
                    }).show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(allEvent.this);
                    builder.setTitle("未添加到打卡").setNeutralButton("查看打卡记录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(allEvent.this, checkin.class);
                            startActivity(intent);
                        }
                    }).show();
                }
                return true;
            }
        });





    }

}
