package com.prathmeshranaut.attendancemsit.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.prathmeshranaut.attendancemsit.R;
import com.prathmeshranaut.attendancemsit.adaptors.Classes_Adapter_Recycler;
import com.prathmeshranaut.attendancemsit.adaptors.Student_single_Adapter_Recycler;
import com.prathmeshranaut.attendancemsit.general.Classes;
import com.prathmeshranaut.attendancemsit.general.Constants;
import com.prathmeshranaut.attendancemsit.general.Student_single;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MarkAttendance extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Student_single> student_singles;
    Student_single_Adapter_Recycler student_single_adapter_recycler;
    DatabaseReference writedatabase;
    public FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id_required="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        Intent i = getIntent();
        id_required=i.getStringExtra("id");

        student_singles = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://mytwitter2-3f5b4.firebaseio.com/student");
        writedatabase=firebaseDatabase.getReferenceFromUrl("https://mytwitter2-3f5b4.firebaseio.com/attendance_record");
        student_single_adapter_recycler=new Student_single_Adapter_Recycler(this,student_singles);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(student_single_adapter_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Student_single>> t = new GenericTypeIndicator<List<Student_single>>() {};
                List<Student_single> fetch   = dataSnapshot.getValue(t);
//               for (DataSnapshot readSnapshot: dataSnapshot.getChildren()) {
//                   fetch = dataSnapshot.getValue(t);
//            }

                if (fetch != null) {
                    student_singles.clear();

                    student_singles.addAll(fetch);

                    student_single_adapter_recycler.notifyDataSetChanged();

                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void writeNewPost() {
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/" + id+"/no_of_present", Constants.present_ans);
//        childUpdates.put("/" + id+"/no_of_absent", Constants.absent_ans);
//        Student_single ss=new Student_single();
//        ss.setName(student_singles.get(0).getName());
//        ss.setClass_id(student_singles.get(0).getClass_id());
      student_singles.get(0).setPresent(Constants.present_ans);
        student_singles.get(0).setAbsent(Constants.absent_ans);

//ss.setAbsent(Constants.absent_ans);
//        ss.setPresent(Constants.present_ans);
//        student_singles.add(1,);
        writedatabase.setValue(student_singles.get(0));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.attendance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.submit_attendance) {



            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Confirm");
            b.setIcon(android.R.drawable.ic_dialog_alert);
            b.setMessage("Are You Sure you want to submit ?");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int index) {

           writeNewPost();
                }
            });

            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    dialogInterface.cancel();
                }
            });


            b.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
