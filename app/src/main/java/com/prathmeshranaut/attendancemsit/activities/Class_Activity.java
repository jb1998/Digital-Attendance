package com.prathmeshranaut.attendancemsit.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.prathmeshranaut.attendancemsit.R;
import com.prathmeshranaut.attendancemsit.adaptors.Add_instance_Adapter_Recycler;
import com.prathmeshranaut.attendancemsit.adaptors.Student_single_Adapter_Recycler;
import com.prathmeshranaut.attendancemsit.general.Add_Instance;
import com.prathmeshranaut.attendancemsit.general.RecyclerItemClickListener;
import com.prathmeshranaut.attendancemsit.general.Student_single;
import com.prathmeshranaut.attendancemsit.general.firebase_class_attendance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class Class_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    String date_time="";
    int mYear;
    int mMonth;
    int teacher_id=9;
    int student_id=8;
    int mDay;
    String date;
    String time;
    int mHour;
    int mMinute;
    ArrayList<Add_Instance> add_instances;
  Button add_day_attendance;
      Add_instance_Adapter_Recycler add_instance_adapter_recycler;
    public FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        add_day_attendance=(Button)findViewById(R.id.add_day);

        add_instances = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://mytwitter2-3f5b4.firebaseio.com/attendance_record");
       temp=firebaseDatabase.getReferenceFromUrl("https://mytwitter2-3f5b4.firebaseio.com/");
        add_instance_adapter_recycler=new Add_instance_Adapter_Recycler(this,add_instances);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(add_instance_adapter_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Add_Instance>> t = new GenericTypeIndicator<List<Add_Instance>>() {};
                List<Add_Instance> fetch   = dataSnapshot.getValue(t);
//               for (DataSnapshot readSnapshot: dataSnapshot.getChildren()) {
//                   fetch = dataSnapshot.getValue(t);
//            }
                if (fetch != null) {
                    add_instances.clear();

                    add_instances.addAll(fetch);

                    add_instance_adapter_recycler.notifyDataSetChanged();

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = getIntent();
                        String str ="random";
//                                add_instances.get(position).getId() + "";
                        i.putExtra("id", str);
                        i.setClass(Class_Activity.this,MarkAttendance.class);
                       startActivity(i);

                    }

                    @Override
                    public void onLongItemClick(View view,final int position) {

                    }

                })
        );

        add_day_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          datePicker();
            }
        });

    }
    private void datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        mYear=year;
                        mMonth=month;
                        mDay=dayOfMonth;
                        date=dayOfMonth + "-" + (month + 1) + "-" + year;
//                                    dateview.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        //*************Call Time Picker Here ********************
                        timePicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private void timePicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;
                        time=date_time+" "+hourOfDay + ":" + minute;
//                        add_to_firebase();
                        Log.i("Time", "onTimeSet: "+date_time+" "+hourOfDay + ":" + minute);
//                        timeview.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }
    public void add_to_firebase()
    {
       firebase_class_attendance obj=new firebase_class_attendance(date,time,0,0,teacher_id,student_id);
//        Add_Instance obj=new Add_Instance(2,date,time,0,0,0,0);
        temp.child("attendance_record").push().setValue(obj);
        finish();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode==1)
        {
            if(resultCode==Activity.RESULT_OK)
            {

            }
            else
                Log.i("TAG", "onActivityResult: ");
        }
    }
}
