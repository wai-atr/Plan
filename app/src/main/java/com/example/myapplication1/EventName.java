package com.example.myapplication1;

import java.util.ArrayList;

public class EventName {
    private static EventName singleCatalog;
    private ArrayList<String> eventname = new ArrayList<String>();
    private EventName(){
    }

    public static EventName getSingleCatalog(){
        if(singleCatalog == null){
            singleCatalog = new EventName();
        }
        return singleCatalog;
    }
    public  String getNewEventName(){
        if(eventname.size()>0) {
            return eventname.get(eventname.size() - 1);
        }else{
            return "";
        }
    }

    public void addEventName(String s){
        eventname.add(s);
    }

    public int getNumberOfName(){
        return eventname.size();
    }

    public void removeEventNameAll(){
        while(eventname.size()>0){
            eventname.remove(0);
        }
    }
}
