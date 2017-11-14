package com.prathmeshranaut.attendancemsit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.prathmeshranaut.attendancemsit.R;
import com.prathmeshranaut.attendancemsit.adaptors.Classes_Adapter_Recycler;
import com.prathmeshranaut.attendancemsit.general.Classes;
import com.prathmeshranaut.attendancemsit.general.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SetUp extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Classes> classes;
    Classes_Adapter_Recycler classes_adapter_recycler;
    public FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_temp);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);


        classes = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://mytwitter2-3f5b4.firebaseio.com/class");
        classes_adapter_recycler=new Classes_Adapter_Recycler(this,classes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(classes_adapter_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Classes>> t = new GenericTypeIndicator<List<Classes>>() {};
                List<Classes> fetch   = dataSnapshot.getValue(t);
//               for (DataSnapshot readSnapshot: dataSnapshot.getChildren()) {
//                   fetch = dataSnapshot.getValue(t);
//            }
                if (fetch != null) {
                    classes.clear();

                    classes.addAll(fetch);

                    classes_adapter_recycler.notifyDataSetChanged();

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
                        i.setClass(SetUp.this,Class_Activity.class);
                        startActivity(i);

                    }

                    @Override
                    public void onLongItemClick(View view,final int position) {

                    }

                })
        );
    }
}
