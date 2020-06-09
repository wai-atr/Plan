package com.example.myapplication1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class datepicker extends AppCompatActivity {
    private DatePicker datePicker;
    private Button back;
    private TimePicker timePicker;
    private Button enter;
    StartDateCatalog startDateCatalog = StartDateCatalog.getSingleDate();
    EndDateCatalog endDateCatalog = EndDateCatalog.getSingleDate();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        datePicker = findViewById(R.id.datepicker_1);
        timePicker = findViewById(R.id.timepicker_1);
        enter = findViewById(R.id.button_enter);



        //获取设置的时间
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date newDate =  new Date(datePicker.getYear(),datePicker.getMonth(),
                        datePicker.getDayOfMonth(),timePicker.getHour(),timePicker.getMinute());
                if(startDateCatalog.getIndex()==1) {
                    startDateCatalog.addStartDates(newDate);
                    startDateCatalog.setIndex(-1);
            }
                if(endDateCatalog.getIndex()==1){
                    endDateCatalog.addEndDates(newDate);
                    endDateCatalog.setIndex(-1);
                }
                Intent intent = new Intent(datepicker.this, create_events.class);
                startActivity(intent);
            }
        });

    }
}

