package com.example.myapplication1;

import android.content.Intent;

public class Index {
    private static Index single;
    private int index = 0;
    private Index(){}

    public static Index getSingleIndex(){
        if(single == null){
            single = new Index();
        }
        return single;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int i){
        index = i;
    }
}
