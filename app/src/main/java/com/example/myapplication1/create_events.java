package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class create_events extends AppCompatActivity {

    int index = -1;
    private ListView listview;
    private TextView tv_back;
    private Button button_back;
    private ImageButton button_f3;
    private ImageButton button_f1;
    private ImageButton button_f2;
    private ImageButton button_f4;
    private ImageButton button_f5;
    private Button create_event;
    private TextView startText;
    private TextView endText;
    private TextView repeat;
    public EditText name_text;
    String initEventName=null;
    StartDateCatalog startDateCatalog = StartDateCatalog.getSingleDate();
    EndDateCatalog endDateCatalog = EndDateCatalog.getSingleDate();
    EventCatalog catalog = EventCatalog.getSingleCatalog();
    Calendar start_c = Calendar.getInstance();
    Calendar end_c  = Calendar.getInstance();
    EventName eventName = EventName.getSingleCatalog();
    RepeatCatalog repeatCatalog = RepeatCatalog.getSingleCatalog();
    boolean clock_in = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);
        tv_back = (TextView) findViewById(R.id.textView10);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_events.this, MainActivity.class);
                startActivity(intent);
                eventName.removeEventNameAll();
                repeatCatalog.removeAllRepeat();
            }
        });

        button_back = (Button) findViewById(R.id.back_button);
        startText = (TextView)findViewById(R.id.starttime);
        button_f1 = (ImageButton) findViewById(R.id.forward_button1);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_events.this, MainActivity.class);
                startActivity(intent);
                eventName.removeEventNameAll();
                repeatCatalog.removeAllRepeat();
            }
        });

        //开始时间
        button_f1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(create_events.this,datepicker.class);
                startActivity(intent);
                startDateCatalog.setIndex(1);
            }
        });
        //设置开始时间
        if(startDateCatalog.getNumberOfStartDate()>0) {
            start_c.set(startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getYear(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getMonth(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getDay(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getHour(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate()- 1).getMinute());
        }
        startText.setText(start_c.get(Calendar.YEAR)+"年"+(start_c.get(Calendar.MONTH)+1)+"月"+start_c.get(Calendar.DAY_OF_MONTH)
                   +"日"+"  "+ start_c.get(Calendar.HOUR)+":"+start_c.get(Calendar.MINUTE));

        //结束时间
        endText = (TextView)findViewById(R.id.endtime);
        button_f2 = (ImageButton) findViewById(R.id.forward_button2);
        button_f2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(create_events.this, datepicker.class);
                startActivity(intent);
                endDateCatalog.setIndex(1);
            }
        });

        //设定结束时间
        if(endDateCatalog.getNumberOfEndDate()>0) {
            end_c.set(endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getYear(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getMonth(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate()- 1).getDay(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getHour(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getMinute());
        }
       endText.setText(end_c.get(Calendar.YEAR)+"年"+(end_c.get(Calendar.MONTH)+1)+"月"+end_c.get(Calendar.DAY_OF_MONTH)
                +"日"+"  "+ end_c.get(Calendar.HOUR)+":"+end_c.get(Calendar.MINUTE));

        //重复
        repeat = (TextView)findViewById(R.id.repeat);
        button_f3 = (ImageButton) findViewById(R.id.forward_button3);
        button_f3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(create_events.this, create_event_repeat.class);
                startActivity(intent);
            }
        });
        if(repeatCatalog.getNewRepeat().getSwitch_repeat()==true&&repeatCatalog.getNumberOfRepeat()>0){
            repeat.setText("重复");
        }else{
            repeat.setText("不重复");
        }

        //打卡的弹框
        button_f5=(ImageButton)findViewById(R.id.forward_button5);
        button_f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] array=new String[]{"添加到打卡计划","不添加到打卡计划"};

                AlertDialog.Builder builder=new AlertDialog.Builder(create_events.this);
                builder.setTitle("选择是否打卡").setSingleChoiceItems(array, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(create_events.this,array[which],Toast.LENGTH_SHORT).show();
                        if(which == 0){
                            clock_in = true;
                        }
                    }
                }).show();
            }
        });

        //提醒方式
        button_f4=(ImageButton)findViewById(R.id.forward_button4);
        button_f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] array=new String[]{"响铃","振动","无"};

                AlertDialog.Builder builder=new AlertDialog.Builder(create_events.this);
                builder.setTitle("提醒方式").setSingleChoiceItems(array, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(create_events.this,array[which],Toast.LENGTH_LONG).show();


                    }
                }).show();
            }
        });

        //搜集输入的事件名称,往事件库中添加事件
        name_text = findViewById(R.id.editText);
        create_event = findViewById(R.id.imageButton2);

        //设置文本框中的内容
        if(eventName.getNumberOfName()!=0){
            name_text.setText(eventName.getNewEventName());
        }else {
            name_text.setText("");
        }

        name_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                initEventName = s.toString();//获得事件名称
                eventName.addEventName(initEventName);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        //点击创建事件后
        create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //防止事件重复
                for (int i = 0; i < catalog.getNumberOfEvent(); i++) {
                    if (catalog.getCatalog().get(i).getEventName().equals(eventName.getNewEventName())) {
                        index = 1;
                        break;
                    }
                }

                if (index == 1) {
                    final AlertDialog.Builder eventRepeat = new AlertDialog.Builder(create_events.this);
                    eventRepeat.setTitle("提示").setMessage("事件已存在,请重新输入事件名称").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            name_text.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.equals(initEventName)) {
                                        index = -1;
                                        initEventName = s.toString();
                                        eventName.addEventName(initEventName);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if (!s.equals(initEventName)) {
                                        index = -1;
                                        initEventName = s.toString();
                                        eventName.addEventName(initEventName);
                                    }
                                }
                            });
                        }
                    });
                    eventRepeat.setNegativeButton("取消创建", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(create_events.this, MainActivity.class);
                            startActivity(intent);
                            eventName.removeEventNameAll();
                            repeatCatalog.removeAllRepeat();
                        }
                    }).create().show();//取消创建事件，返回主页面
                }

                //防止事件名称为空
                if (index == -1) {
                    if (String.valueOf(name_text.getText()).equals("")) {
                        AlertDialog.Builder eventEmpty = new AlertDialog.Builder(create_events.this);
                        eventEmpty.setTitle("提示").setMessage("请输入事件名称").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消创建", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(create_events.this, MainActivity.class);
                                startActivity(intent);
                                eventName.removeEventNameAll();
                                repeatCatalog.removeAllRepeat();
                            }
                        }).create().show();//取消创建

                    } else {

                        if(start_c.get(Calendar.YEAR)>end_c.get(Calendar.YEAR)||(start_c.get(Calendar.YEAR)
                                ==end_c.get(Calendar.YEAR)&&start_c.get(Calendar.MONTH)>end_c.get(Calendar.MONTH))
                                ||(start_c.get(Calendar.YEAR)==end_c.get(Calendar.YEAR)
                                &&start_c.get(Calendar.MONTH)==end_c.get(Calendar.MONTH)&&start_c.get(Calendar.DAY_OF_MONTH)
                                >end_c.get(Calendar.DAY_OF_MONTH))){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(create_events.this);
                            builder.setTitle("结束时间提前于开始时间").setMessage("请重新设置时间") .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                        }else {
                            Event newEvent = new Event(eventName.getNewEventName(), start_c, end_c, repeatCatalog.getNewRepeat());
                            newEvent.setPosition(catalog.getNumberOfEvent());
                            newEvent.setClock_in(clock_in);
                            catalog.addEvent(newEvent);
                            Toast t = Toast.makeText(create_events.this, "创建成功", Toast.LENGTH_LONG);
                            t.show();
                            eventName.removeEventNameAll();
                            repeatCatalog.removeAllRepeat();
                            Intent intent = new Intent(create_events.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

    }
}

