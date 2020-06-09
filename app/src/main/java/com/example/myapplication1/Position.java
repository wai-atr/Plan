package com.example.myapplication1;

public class Position {
    private Position(){}

    private static Position singlePosition;
    private int position=0;

    public void setPosition(int nPosition){
        position = nPosition;
    }

    public int getPosition(){
        return position;
    }

    public static Position getSinglePosition(){
        if(singlePosition ==null){
            singlePosition = new Position();
        }
            return singlePosition;
    }
}
