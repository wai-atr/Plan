package com.example.myapplication1.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication1.CheckInCatalog;
import com.example.myapplication1.Event;
import com.example.myapplication1.R;

import java.util.Calendar;
import java.util.List;


public class MyListAdapter_checkin extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Event> EventsArrayList;
    public MyListAdapter_checkin( Context context) {
        mInflater=LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return CheckInCatalog.getSingleCatalog().getNumberOfEvent();
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
            convertView = mInflater.inflate(R.layout.list_item_checkin,null);
        }
        /**
         * 找到item布局文件中对应的控件
         */
        TextView notes=(TextView)convertView.findViewById(R.id.textView1) ;
        TextView title = (TextView) convertView.findViewById(R.id.textView_name);
        TextView time = (TextView) convertView.findViewById(R.id.textView2);

        //获取相应索引的ItemBean对象
        title.setText(CheckInCatalog.getSingleCatalog().getCatalog().get(position).getEventName());
        notes.setText("累计打卡"+CheckInCatalog.getSingleCatalog().getCatalog().get(position).getClockCount()+"次");
        if(CheckInCatalog.getSingleCatalog().getCatalog().get(position).getNewClockTime()!=null) {
            time.setText("上次打卡：" + CheckInCatalog.getSingleCatalog().getCatalog().get(position).getNewClockTime().get(Calendar.YEAR)
                       +"年"+(CheckInCatalog.getSingleCatalog().getCatalog().get(position).getNewClockTime().get(Calendar.MONTH)+1)+"月"+
                    CheckInCatalog.getSingleCatalog().getCatalog().get(position).getNewClockTime().get(Calendar.DAY_OF_MONTH)+"日");
        }else{
            time.setText("上次打卡：无打卡记录");
        }

        return convertView;
    }

}

