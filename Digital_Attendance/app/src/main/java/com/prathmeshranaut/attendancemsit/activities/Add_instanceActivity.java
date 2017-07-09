package com.prathmeshranaut.attendancemsit.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prathmeshranaut.attendancemsit.R;
import com.prathmeshranaut.attendancemsit.adaptors.Add_instance_Adapter_Recycler;
import com.prathmeshranaut.attendancemsit.databases.attendance_record_helper_db;
import com.prathmeshranaut.attendancemsit.general.Add_Instance;
import com.prathmeshranaut.attendancemsit.general.Constants;

import java.util.ArrayList;

public class Add_instanceActivity extends AppCompatActivity {
FloatingActionButton floatingActionButton;
    Add_instance_Adapter_Recycler adapter;
    attendance_record_helper_db attendance_db;
    ArrayList<Add_Instance> add_instances;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instance2);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floating_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        adapter = new Add_instance_Adapter_Recycler(Add_instanceActivity.this,add_instances);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Add_instanceActivity.this));
        attendance_db = new attendance_record_helper_db(Add_instanceActivity.this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                attendance_record_helper_db  attendance_helper=new attendance_record_helper_db(Add_instanceActivity.this);
//                SQLiteDatabase db = attendance_helper.getWritableDatabase();
//                ContentValues cv = new ContentValues();

                                // put your values here
//                cv.put();
//                cv.put();
//

                Intent i1 = new Intent();
                i1.setClass(Add_instanceActivity.this, Add_attendance_offline.class);
                startActivityForResult(i1,2);

            }
        });
    }
}
