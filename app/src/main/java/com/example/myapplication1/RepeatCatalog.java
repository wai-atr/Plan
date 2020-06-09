package com.example.myapplication1;

import java.util.ArrayList;

public class RepeatCatalog {
    private static RepeatCatalog singleCatalog;
    private ArrayList<Repeat> repeats = new ArrayList<>();
    Repeat initRepeat=new Repeat();

    public static RepeatCatalog getSingleCatalog(){
        if(singleCatalog==null){
            singleCatalog =new RepeatCatalog();
        }
        return singleCatalog;
    }

    private RepeatCatalog(){
    }

    public void addRepeat(Repeat newRepeat){
        repeats.add(newRepeat);
    }

    public void removeAllRepeat(){
        while(repeats.size()>0){
            repeats.remove(0);
        }
    }

    public int getNumberOfRepeat(){
        return repeats.size();
    }

    public Repeat getNewRepeat(){
        if(repeats.size()>0){
            return repeats.get(repeats.size()-1);
        }else{
            return initRepeat;
        }
    }

}
