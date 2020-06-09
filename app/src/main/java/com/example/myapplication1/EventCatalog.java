package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.Event;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.BitSet;

public class EventCatalog extends AppCompatActivity {

    private static EventCatalog singleCatalog;
    private  ArrayList<Event> eventCatalog = new ArrayList<Event>();
    public EventCatalog(){
    }

    public static EventCatalog getSingleCatalog(){
        if(singleCatalog == null){
            singleCatalog = new EventCatalog();
        }
        return singleCatalog;
    }
    public  ArrayList<Event> getCatalog(){
        return eventCatalog;
    }

    public void addEvent(Event addEvent){
        eventCatalog.add(addEvent);
    }

    public int getNumberOfEvent(){
        return eventCatalog.size();
    }

    public void removeEvent(Event e){
        eventCatalog.remove(e);
    }

    public void removeEventAll(){
        while(eventCatalog.size()>0){
            eventCatalog.remove(0);
        }
    }
}