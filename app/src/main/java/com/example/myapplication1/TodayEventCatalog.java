package com.example.myapplication1;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.Event;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;

public class TodayEventCatalog extends AppCompatActivity {

    EventCatalog eventCatalog = EventCatalog.getSingleCatalog();
    Calendar today = Calendar.getInstance();
    private static TodayEventCatalog singleCatalog;
    private  ArrayList<Event> todayEventCatalog = new ArrayList<Event>();
    public TodayEventCatalog(){
    }

    public static TodayEventCatalog getSingleCatalog(){
        if(singleCatalog == null){
            singleCatalog = new TodayEventCatalog();
        }
        return singleCatalog;
    }
    public  ArrayList<Event> getCatalog(){
        return todayEventCatalog;
    }

    public void addEvent(){
        for(int position=0;position<eventCatalog.getNumberOfEvent();position++) {
            if (eventCatalog.getNumberOfEvent() > 0 && ((eventCatalog.getCatalog().get(position).getStartTime()
                    .get(Calendar.YEAR) <= today.get(Calendar.YEAR))&& eventCatalog.getCatalog().get(position).getEndTime()
                    .get(Calendar.YEAR) >= today.get(Calendar.YEAR))&& ((eventCatalog.getCatalog().get(position).getStartTime()
                    .get(Calendar.MONTH) <= today.get(Calendar.MONTH))&& (eventCatalog.getCatalog().get(position).getEndTime()
                    .get(Calendar.MONTH) >= today.get(Calendar.MONTH)))&& ((eventCatalog.getCatalog().get(position).getStartTime()
                    .get(Calendar.DAY_OF_MONTH) <= today.get(Calendar.DAY_OF_MONTH))&& eventCatalog.getCatalog().get(position).getEndTime()
                    .get(Calendar.DAY_OF_MONTH) >= today.get(Calendar.DAY_OF_MONTH))) {
                    todayEventCatalog.add(eventCatalog.getCatalog().get(position));
            }
        }

    }

    public int getNumberOfEvent(){
        return todayEventCatalog.size();
    }

    public void removeEvent(Event e){
        todayEventCatalog.remove(e);
    }

    public void removeEventAll(){
        while(todayEventCatalog.size()>0){
           todayEventCatalog.remove(0);
        }
    }
}