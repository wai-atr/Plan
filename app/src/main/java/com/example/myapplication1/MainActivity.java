package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.listview.MyListAdapter;
import com.example.myapplication1.listview.MyListAdapter_checkin;
import com.example.myapplication1.listview.TodayListAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private Button button_1;
    private ImageButton button_checkin;
    private NoScrollListView listView_2;
    private BounceScrollView scrollView;
    private TextView dateOfWeek_show;
    private TextView date_show;
    private TodayListAdapter adapter2;
    private  ImageButton allEvent;
    EventCatalog eventCatalog = EventCatalog.getSingleCatalog();


    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date_show = (TextView) findViewById(R.id.Date);
        dateOfWeek_show = (TextView) findViewById(R.id.dateOfWeek);
        Calendar calendar = Calendar.getInstance();
        String Date_show = String.format("%d月%d号", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
        String DateOfWeek_show = String.format("星期%s", getWeek(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        dateOfWeek_show.setText(DateOfWeek_show);
        date_show.setText(Date_show);

        button_1 = (Button) findViewById(R.id.button1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, create_events.class);
                startActivity(intent);

            }

        });

             //打卡界面
        button_checkin = (ImageButton) findViewById(R.id.checkin);
        button_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, checkin.class);
                startActivity(intent);
            }
        });


        //显示全部事件
        allEvent = (ImageButton)findViewById(R.id.more);
        allEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, allEvent.class);
                startActivity(intent);
            }
        });


        TodayEventCatalog.getSingleCatalog().removeEventAll();
        TodayEventCatalog.getSingleCatalog().addEvent();
        Log.d("length",String.valueOf(TodayEventCatalog.getSingleCatalog().getNumberOfEvent()));
        scrollView = (BounceScrollView) findViewById(R.id.scrollview1);
        listView_2 = (NoScrollListView) findViewById(R.id.lv);
        adapter2 = new TodayListAdapter(MainActivity.this);
        listView_2.setAdapter(adapter2);

        listView_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Position.getSinglePosition().setPosition(position);
                Index.getSingleIndex().setIndex(1);
                Intent intent = new Intent(MainActivity.this, activity_edit_event.class);
                startActivity(intent);
            }
        });

        //列表长按打卡
        listView_2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if(TodayEventCatalog.getSingleCatalog().getCatalog().get(position).getClock_in()==true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("打卡").setMessage("确定打卡?").setPositiveButton("点错了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "取消打卡", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("确定打卡", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(eventCatalog.getCatalog().get(TodayEventCatalog.getSingleCatalog()
                                    .getCatalog().get(position).getPosition()).getClockNumber()>0&&
                                    EventCatalog.getSingleCatalog().getCatalog().get(TodayEventCatalog.getSingleCatalog()
                                    .getCatalog().get(position).getPosition()).getNewClockTime().
                                            get(Calendar.YEAR)==Calendar.getInstance().get(Calendar.YEAR)&&
                                  EventCatalog.getSingleCatalog().getCatalog().get(TodayEventCatalog.getSingleCatalog().
                                          getCatalog().get(position).getPosition()).getNewClockTime().get(Calendar.MONTH)==Calendar.getInstance().get(Calendar.MONTH)&&
                                  EventCatalog.getSingleCatalog().getCatalog().get(TodayEventCatalog.getSingleCatalog().
                                          getCatalog().get(position).getPosition()).getNewClockTime().get(Calendar.DAY_OF_MONTH)==Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
                                Toast.makeText(MainActivity.this, "今日已打卡", Toast.LENGTH_SHORT).show();
                            }else{
                            EventCatalog.getSingleCatalog().getCatalog().get(TodayEventCatalog.getSingleCatalog()
                            .getCatalog().get(position).getPosition()).clock();
                            EventCatalog.getSingleCatalog().getCatalog().get(TodayEventCatalog.getSingleCatalog()
                                    .getCatalog().get(position).getPosition()).setClockTime(Calendar.getInstance());
                            Toast.makeText(MainActivity.this, "打卡成功", Toast.LENGTH_SHORT).show();
                        }
                        }
                    }).setNeutralButton("查看打卡记录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, checkin.class);
                            startActivity(intent);
                        }
                    }).show();
                }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("未添加到打卡").setNeutralButton("查看打卡记录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, checkin.class);
                                startActivity(intent);
                            }
                        }).show();
                }
                return true;
            }
        });

    }



    public String getWeek(int i){
        String dayOfWeek="";
        switch(i){
            case 1:
                dayOfWeek="一";
                break;
            case 2:
                dayOfWeek="二";
                break;
            case 3:
                dayOfWeek="三";
                break;
            case 4:
                dayOfWeek="四";
                break;
            case 5:
                dayOfWeek="五";
                break;
            case 6:
                dayOfWeek="六";
                break;
            case 0:
                dayOfWeek="日";
                break;
        }
        return dayOfWeek;
    }

}




