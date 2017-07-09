package com.prathmeshranaut.attendancemsit.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.prathmeshranaut.attendancemsit.R;
import com.prathmeshranaut.attendancemsit.databases.attendance_record_helper_db;
import com.prathmeshranaut.attendancemsit.general.Constants;

import java.util.ArrayList;
import java.util.Calendar;

public class Add_attendance_offline extends AppCompatActivity {
    EditText class_name;
    Button set_class;
    public static String finaldate;
    Button time1, date1, set1;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance_offline);
        class_name = (EditText) findViewById(R.id.classname);


        time1 = (Button) findViewById(R.id.time);
        date1 = (Button) findViewById(R.id.date);
        set1 = (Button) findViewById(R.id.set);
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrenttime=Calendar.getInstance();
                int hour=mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int minute=mcurrenttime.get(Calendar.MINUTE);
                TimePickerDialog timepicker = new TimePickerDialog(Add_attendance_offline.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {
                        time1.setText(selectedhour + ":" + selectedminute);
                    }
                },hour,minute,true);

                timepicker.setTitle("SELECT TIME");
                timepicker.show();

            }
        });
        date1.setOnClickListener(new View.OnClickListener() {
            //Log.i("tag1","hello");
            //Toast.makeText()

            //@Override
            public void onClick(View view) {
                Log.i("Tag1", "hello1");
                Calendar mcurrenttime=Calendar.getInstance();
                int year=mcurrenttime.get(Calendar.YEAR);
                int month=mcurrenttime.get(Calendar.MONTH);
                int day=mcurrenttime.get(Calendar.DAY_OF_WEEK);
                datePickerDialog = new DatePickerDialog(Add_attendance_offline.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {


                        finaldate = day + "/" + (month + 1) + "/" + year;
                        date1.setText(finaldate);

                    }
                }, year, month, day);
                datePickerDialog.show();


            }
        });
        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                String class_nametext = class_name.getText().toString();
                String date = date1.getText().toString();
                String time = time1.getText().toString();
                // keeping constant for now

                int present=43;
                int absent=10;
                String teacher_idtemp="99";
                String student_idtemp="004";
//                setAlarm(Enteritem_professional.this);
//                String toSpeak=title;
//                Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
//                t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);


                if (!class_name.equals("") && !date.equals("") && !time.equals("")) {
                    attendance_record_helper_db helper=new attendance_record_helper_db(Add_attendance_offline.this);
                    SQLiteDatabase db = helper.getWritableDatabase();
//                    Helperfamily helperfamily = new Helperfamily(Enteritemfamily.this);

                    ContentValues cv = new ContentValues();
                    cv.put(Constants.date, date);
                    cv.put(Constants.time,time);
                    cv.put(Constants.teacher_id,teacher_idtemp);
                    cv.put(Constants.student_id, student_idtemp);
                    cv.put(Constants.no_of_absent,absent);
                    cv.put(Constants.no_of_present,present);

                    db.insert(Constants.attendance_record_table_name, null, cv);
                    Intent i = new Intent();


                    setResult(Activity.RESULT_OK, i);
                    finish();

                }
                else
                {
                    Toast.makeText(Add_attendance_offline.this,"INCOMPLETE",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
