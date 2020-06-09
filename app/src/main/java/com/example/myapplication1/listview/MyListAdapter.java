package com.example.myapplication1.listview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication1.Event;
import com.example.myapplication1.EventCatalog;
import com.example.myapplication1.MainActivity;
import com.example.myapplication1.R;
import com.example.myapplication1.activity_edit_event;

import java.util.Calendar;
import java.util.List;


public class MyListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    Calendar today = Calendar.getInstance();
    EventCatalog eventCatalog = EventCatalog.getSingleCatalog();
    public MyListAdapter( Context context) {
        mInflater=LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return eventCatalog.getNumberOfEvent();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_all,null);
            }

            /**
             * 找到item布局文件中对应的控件
             */
            TextView title = (TextView) convertView.findViewById(R.id.textView_all);
            TextView startTime = (TextView) convertView.findViewById(R.id.starttime_all);
            TextView endTime = (TextView)convertView.findViewById(R.id.endtime_all);
            TextView date =(TextView)convertView.findViewById(R.id.date_all);
            TextView dateOfWeek = (TextView)convertView.findViewById(R.id.dateOfWeek_all);

            //获取相应索引的ItemBean对象
            if(eventCatalog.getNumberOfEvent()>0) {
                title.setText(eventCatalog.getCatalog().get(position).getEventName());
                startTime.setText(eventCatalog.getCatalog().get(position).getStartTime().get(Calendar.HOUR)+":"
                        +eventCatalog.getCatalog().get(position).getStartTime().get(Calendar.MINUTE));
                endTime.setText(eventCatalog.getCatalog().get(position).getEndTime().get(Calendar.HOUR)+":"
                        +eventCatalog.getCatalog().get(position).getEndTime().get(Calendar.MINUTE));
                date.setText(String.valueOf(eventCatalog.getCatalog().get(position).getStartTime().get(Calendar.DAY_OF_MONTH)));
                dateOfWeek.setText((eventCatalog.getCatalog().get(position).getStartTime().get(Calendar.MONTH)+1)+"月"+
                        " "+"周"+getWeek(eventCatalog.getCatalog().get(position).getStartTime().get(Calendar.DAY_OF_WEEK)));
            }

            return convertView;
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

