package com.example.myapplication1;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

public class Repeat {
    private boolean[] check = new boolean[8];
    private boolean switch_repeat;

    public Repeat(){
        for(int i=0;i<8;i++){
            check[i] = false;
        }
        switch_repeat = false;
    }

    public Repeat(boolean[] newCheck, boolean newSwitch){
        check = newCheck;
        switch_repeat = newSwitch;
    }

    public boolean[] getIfCheck(){
        return check;
    }

    public boolean getSwitch_repeat(){
        return this.switch_repeat;
    }

    public void setSwitch_repeat(boolean newSwitch){
        switch_repeat=newSwitch;
    }

}
