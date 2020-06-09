package com.example.myapplication1;

import java.util.ArrayList;

public class StartDateCatalog {

    private static StartDateCatalog singleDate;
    ArrayList<Date> dates =new ArrayList<>();
    private int index = -1;

    private StartDateCatalog(){
    }

    public static StartDateCatalog getSingleDate(){
        if(singleDate==null){
            singleDate =new StartDateCatalog();
        }
        return singleDate;
    }

    public ArrayList<Date> getStartDateCatalog(){
        return dates;
    }

    public void removeAllStartDate(){
        while (dates.size()>0){
            dates.remove(0);
        }
    }

    public void addStartDates(Date newdate)
    {
        dates.add(newdate);
    }

    public int getNumberOfStartDate(){
        return dates.size();
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int i){
        this.index = i;
    }
}
