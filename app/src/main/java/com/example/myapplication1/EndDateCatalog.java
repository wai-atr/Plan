package com.example.myapplication1;

import java.util.ArrayList;

public class EndDateCatalog {

    private static EndDateCatalog singleDate;
    ArrayList<Date> dates =new ArrayList<>();
    private int index = -1;

    private EndDateCatalog(){
    }

    public static EndDateCatalog getSingleDate(){
        if(singleDate==null){
            singleDate =new EndDateCatalog();
        }
        return singleDate;
    }

    public ArrayList<Date> getEndDateCatalog(){
        return dates;
    }

    public void removeAllEndDate(){
        while (dates.size()>0){
            dates.remove(0);
        }
    }

    public void addEndDates(Date newdate)
    {
        dates.add(newdate);
    }

    public int getNumberOfEndDate(){
        return dates.size();
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int i){
        this.index = i;
    }
}

