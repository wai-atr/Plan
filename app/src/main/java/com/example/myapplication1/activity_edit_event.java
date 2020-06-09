package com.example.myapplication1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class activity_edit_event extends AppCompatActivity {

    int position = Position.getSinglePosition().getPosition();
    int index = -1;
    String change  = "";
    int newPosition;
    StartDateCatalog startDateCatalog = StartDateCatalog.getSingleDate();
    EndDateCatalog endDateCatalog = EndDateCatalog.getSingleDate();
    EventCatalog eventCatalog = EventCatalog.getSingleCatalog();
    Calendar start = Calendar.getInstance();
    Calendar end = Calendar.getInstance();
    EventCatalog catalog = EventCatalog.getSingleCatalog();
    RepeatCatalog repeatCatalog = RepeatCatalog.getSingleCatalog();
    EventName eventName = EventName.getSingleCatalog();

    private Button back;
    private ImageButton startTime ;
    private ImageButton endTime;
    private ImageButton repeat;
    private ImageButton check_in ;
    private EditText editText;
    private Button enter ;
    private Button delete ;
    private TextView startText;
    private TextView endText;
    private TextView repeat_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        back = (Button) findViewById(R.id.back_button_edit);
        startTime = (ImageButton) findViewById(R.id.forward_button1_edit);
        endTime = (ImageButton) findViewById(R.id.forward_button2_edit);
        repeat = (ImageButton) findViewById(R.id.forward_button3_edit);
        check_in = (ImageButton) findViewById(R.id.forward_button5_edit);
        editText = (EditText)findViewById(R.id.editText_edit);
        enter = (Button)findViewById(R.id.surebutton_edit);
        delete = (Button)findViewById(R.id.delete_button_edit);
        startText =(TextView)findViewById(R.id.starttime_edit);
        endText = (TextView)findViewById(R.id.endtime_edit);
        repeat_text = (TextView)findViewById(R.id.repeat_edit);

        //获取当前事件的位置
        if(Index.getSingleIndex().getIndex()==-1){
            newPosition = position;
        }else{
            newPosition = TodayEventCatalog.getSingleCatalog().getCatalog().get(position).getPosition();
        }


        //返回按钮
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_edit_event.this,MainActivity.class);
                startActivity(intent);
                eventName.removeEventNameAll();
                repeatCatalog.removeAllRepeat();
            }
        });


        //设置标题名
        change= eventCatalog.getCatalog().get(newPosition).getEventName();
        editText.setText(change);

        //标题是否发生改变
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            //重新命名
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                change=s.toString();
                eventName.addEventName(change);
            }

            @Override
            public void afterTextChanged(Editable s) {
                change=s.toString();
                eventName.addEventName(change);
            }
        });



        //重新设置开始和结束时间
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_edit_event.this,datepicker_edit.class);
                startActivity(intent);
                startDateCatalog.setIndex(1);
            }
        });
        endTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity_edit_event.this, datepicker_edit.class);
                startActivity(intent);
                endDateCatalog.setIndex(1);
            }
        });
        if(startDateCatalog.getNumberOfStartDate()>0) {
            start.set(startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getYear(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getMonth(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getDay(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate() - 1).getHour(),
                    startDateCatalog.getStartDateCatalog().get(startDateCatalog.getNumberOfStartDate()- 1).getMinute());
        }
        if(endDateCatalog.getNumberOfEndDate()>0) {
            end.set(endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getYear(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getMonth(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate()- 1).getDay(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getHour(),
                    endDateCatalog.getEndDateCatalog().get(endDateCatalog.getNumberOfEndDate() - 1).getMinute());
        }
        startText.setText(start.get(Calendar.YEAR)+"年"+(start.get(Calendar.MONTH)+1)+"月"+start.get(Calendar.DAY_OF_MONTH)
                +"日"+"  "+ start.get(Calendar.HOUR)+":"+start.get(Calendar.MINUTE));
        endText.setText(end.get(Calendar.YEAR)+"年"+(end.get(Calendar.MONTH)+1)+"月"+end.get(Calendar.DAY_OF_MONTH)
                +"日"+"  "+ end.get(Calendar.HOUR)+":"+end.get(Calendar.MINUTE));


        //重新设置重复
        repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity_edit_event.this, repeat_edit.class);
                startActivity(intent);
            }
        });
        if(repeatCatalog.getNumberOfRepeat()>0){
            eventCatalog.getCatalog().get(newPosition).setRepeat(repeatCatalog.getNewRepeat());
        }
        if(eventCatalog.getCatalog().get(newPosition).getRepeat().getSwitch_repeat()==true){
            repeat_text.setText("重复");
        }else{
            repeat_text.setText("不重复");
        }

        //打卡记录
        check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_edit_event.this, checkin.class);
                startActivity(intent);
            }
        });





        //点击确定
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //防止事件重复
                for (int i = 0; i < catalog.getNumberOfEvent(); i++) {
                    if (catalog.getCatalog().get(i).getEventName().equals(eventName.getNewEventName())&& (i!=newPosition)) {
                        index = 1;
                        break;
                    }
                }

                if (index == 1) {
                    final AlertDialog.Builder eventRepeat = new AlertDialog.Builder(activity_edit_event.this);
                    eventRepeat.setTitle("提示").setMessage("事件已存在,请重新输入事件名称").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            editText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.equals(change)) {
                                        index = -1;
                                        eventName.addEventName(s.toString());
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if (!s.equals(change)) {
                                        index = -1;
                                        eventName.addEventName(s.toString());
                                    }
                                }
                            });
                        }
                    });
                    eventRepeat.setNegativeButton("取消创建", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(activity_edit_event.this, MainActivity.class);
                            startActivity(intent);
                            eventName.removeEventNameAll();
                        }
                    }).create().show();//取消创建事件，返回主页面
                }

                //防止事件名称为空
                if (index == -1) {
                    if (String.valueOf(editText.getText()).equals("")) {
                        final AlertDialog.Builder eventEmpty = new AlertDialog.Builder(activity_edit_event.this);
                        eventEmpty.setTitle("提示").setMessage("请输入事件名称").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消创建", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(activity_edit_event.this, MainActivity.class);
                                startActivity(intent);
                                eventName.removeEventNameAll();
                            }
                        }).create().show();//取消创建

                    } else {

                        if (start.get(Calendar.YEAR) > end.get(Calendar.YEAR) || (start.get(Calendar.YEAR)
                                == end.get(Calendar.YEAR) && start.get(Calendar.MONTH) > end.get(Calendar.MONTH))
                                || (start.get(Calendar.YEAR) == end.get(Calendar.YEAR) && start.get(Calendar.MONTH)
                                == end.get(Calendar.MONTH) && start.get(Calendar.DAY_OF_MONTH) > end.get(Calendar.DAY_OF_MONTH))) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(activity_edit_event.this);
                            builder.setTitle("结束时间提前于开始时间").setMessage("请重新设置时间").setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                        } else {
                            //保持原来的名称不变
                            eventName.addEventName(catalog.getCatalog().get(newPosition).getEventName());
                            catalog.getCatalog().get(newPosition).setEventName(eventName.getNewEventName());
                            catalog.getCatalog().get(newPosition).setStartTime(start);
                            catalog.getCatalog().get(newPosition).setEndTime(end);
                            repeatCatalog.removeAllRepeat();
                            eventName.removeEventNameAll();
                            Intent intent = new Intent(activity_edit_event.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });


        //删除事件
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dele =new AlertDialog.Builder(activity_edit_event.this);
                dele.setTitle("确定删除").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventName.removeEventNameAll();
                        repeatCatalog.removeAllRepeat();
                        eventCatalog.getCatalog().remove(newPosition);
                        Intent intent = new Intent(activity_edit_event.this,MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });


    }
}
