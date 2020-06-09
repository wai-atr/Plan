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
import com.example.myapplication1.TodayEventCatalog;
import com.example.myapplication1.activity_edit_event;

import java.util.Calendar;
import java.util.List;


public class TodayListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    public TodayListAdapter( Context context) {
        mInflater=LayoutInflater.from(context);
    }
    //获取相应索引的ItemBean对象

    @Override
    public int getCount() {
        return TodayEventCatalog.getSingleCatalog().getNumberOfEvent();
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
            convertView = mInflater.inflate(R.layout.list_item,null);
        }

        /**
         * 找到item布局文件中对应的控件
         */
        TextView title = (TextView) convertView.findViewById(R.id.textView);
        TextView startTime = (TextView) convertView.findViewById(R.id.starttime);
        TextView endTime = (TextView)convertView.findViewById(R.id.endtime);


        //获取相应索引的ItemBean对象
        if(TodayEventCatalog.getSingleCatalog().getNumberOfEvent()>0) {
            title.setText(TodayEventCatalog.getSingleCatalog().getCatalog().get(position).getEventName());
            startTime.setText(TodayEventCatalog.getSingleCatalog().getCatalog().get(position).getStartTime().get(Calendar.HOUR) + ":"
                    + TodayEventCatalog.getSingleCatalog().getCatalog().get(position).getStartTime().get(Calendar.MINUTE));
            endTime.setText(TodayEventCatalog.getSingleCatalog().getCatalog().get(position).getEndTime().get(Calendar.HOUR) + ":"
                    + TodayEventCatalog.getSingleCatalog().getCatalog().get(position).getEndTime().get(Calendar.MINUTE));
        }
        return convertView;
    }

}

