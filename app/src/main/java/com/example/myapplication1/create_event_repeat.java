package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class create_event_repeat extends AppCompatActivity {
    private CheckBox [] check = new CheckBox[8];
    private boolean[] ifCheck = new boolean[8];
    private boolean ifSwitch;
    private Button back1;
    private Switch switch_repeat;
    private RelativeLayout relativeLayoutLayout_repeat_on;
    RepeatCatalog repeatCatalog = RepeatCatalog.getSingleCatalog();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_event_repeat);

        switch_repeat = (Switch) findViewById(R.id.switch1);
        relativeLayoutLayout_repeat_on = (RelativeLayout) findViewById(R.id.repeat_on);
        check[0] = (CheckBox)findViewById(R.id.checkEveryDay_repeat);
        check[1] = (CheckBox)findViewById(R.id.checkOne_repeat);
        check[2] = (CheckBox)findViewById(R.id.checkTwo_repeat);
        check[3] = (CheckBox)findViewById(R.id.checkThree_repeat);
        check[4] = (CheckBox)findViewById(R.id.checkFour_repeat);
        check[5] = (CheckBox)findViewById(R.id.checkFive_repeat);
        check[6] = (CheckBox)findViewById(R.id.checkSix_repeat);
        check[7] = (CheckBox)findViewById(R.id.checkSeven_repeat);

        //上次的设置的重复选项
        for(int i=0;i<8;i++){
            check[i].setChecked(repeatCatalog.getNewRepeat().getIfCheck()[i]);
        }
        switch_repeat.setChecked(repeatCatalog.getNewRepeat().getSwitch_repeat());


        back1=(Button)findViewById(R.id.back_button2);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_event_repeat.this, create_events.class);
                startActivity(intent);
                for(int i=0;i<8;i++){
                    if(check[i].isChecked()){
                        ifCheck[i] = true;
                    }else {
                        ifCheck[i] = false;
                    }
                }
                if(switch_repeat.isChecked()){
                    ifSwitch = true;
                }
                Repeat newRepeat = new Repeat(ifCheck,ifSwitch);
                repeatCatalog.addRepeat(newRepeat);
            }
        });
        relativeLayoutLayout_repeat_on.setVisibility(switch_repeat.isChecked()?View.VISIBLE:View.GONE);
        switch_repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                relativeLayoutLayout_repeat_on.setVisibility(switch_repeat.isChecked()?View.VISIBLE:View.GONE);
            }
        });



        //若选每天，自动选择所有星期数
        check[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for(int i=1;i<8;i++){
                        check[i].setChecked(true);
                        check[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(!isChecked){
                                    check[0].setChecked(false);
                                }
                            }
                        });
                    }
                }
            }
        });

    }
}
