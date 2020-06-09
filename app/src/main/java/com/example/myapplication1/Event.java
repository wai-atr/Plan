package com.example.myapplication1;

import java.util.ArrayList;
import java.util.Calendar;

public class Event  {
    private String name;
    private boolean clock_in =false;
    private Calendar startTime;
    private Calendar endTime;
    private Repeat repeat ;
    private int position=0;
    private int clockCount;
    private ArrayList<Calendar> clockTime=new ArrayList<>();


    public Event(ArrayList<Calendar> clockTime){
        this.clockTime = clockTime;
    }

    public Event(String s){
        this.name =s;
    }

    public Event(String name,Calendar startTime,Calendar endTime) {
        this.name=name;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public Event(String name,Calendar startTime,Calendar endTime,Repeat newRepeat) {
        this.name=name;
        this.startTime=startTime;
        this.endTime=endTime;
        this.repeat = newRepeat;
    }

    public String getEventName(){
        return this.name;
    }

    public boolean getClock_in(){
        return clock_in;
    }
    public Calendar getStartTime(){
        return this.startTime;
    }
    public Calendar getEndTime(){
        return this.endTime;
    }

    public Repeat getRepeat(){
        return repeat;
    }

    public void setRepeat(Repeat r){
        repeat = r;
    }

    public void setEventName(String s)
    {
        this.name =s;
    }
    public void setClock_in(boolean c)
    {
        this.clock_in=c;
    }
    public void setStartTime(Calendar start)
    {
        this.startTime = start;
    }

    public void setEndTime(Calendar end)
    {
        this.endTime = end;
    }

    public void setPosition(int i){
        position =i;
    }

    public int getPosition(){
        return position;
    }

    public void clock(){
        clockCount++;
    }

    public int getClockCount()
    {
        return clockCount;
    }

    public void setClockTime(Calendar nClockTime){
        clockTime.add(nClockTime);
    }

    public Calendar getNewClockTime(){
        if(clockTime.size()>0) {
            return clockTime.get(clockTime.size() - 1);
        }else {
            return null;
        }
    }

    public int getClockNumber(){
            return clockTime.size();
    }
}
