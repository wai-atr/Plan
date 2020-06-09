package com.example.myapplication1;

import java.util.ArrayList;

public class Date {
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    public Date(){
    }

    public Date(int nyear,int nmonth,int nday,int nhour,int nminute) {
        this.year = nyear;
        this.month = nmonth;
        this.day = nday;
        this.hour =nhour;
        this.minute =nminute;
    }

    public int getYear(){
        return this.year;
    }

    public int getMonth(){
        return this.month;
    }

    public int getDay(){
        return this.day;
    }

    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return this.minute;
    }
}
