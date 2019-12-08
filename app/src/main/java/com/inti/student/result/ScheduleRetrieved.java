package com.inti.student.result;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleRetrieved extends AppCompatActivity {

    private ListView listView;

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<Schedules> list;
    ScheduleAdapter adapter;
    FirebaseAuth mFirebasAuth;
    FirebaseDatabase mFirebaseDatabase;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_retrieved);
        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Schedules>();
        //listView = findViewById(R.id.sche_view);
        String uid = mFirebasAuth.getInstance().getCurrentUser().getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =mFirebaseDatabase.getReference("Schedules").child(uid);

        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Schedule List");
        //add back arrow to toolbar

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Schedules s = dataSnapshot1.getValue(Schedules.class);
                    list.add(s);
                }
                adapter = new ScheduleAdapter(ScheduleRetrieved.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ScheduleRetrieved.this, "Ooops....Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle arrow click
        if(item.getItemId() == android.R.id.home){
            finish(); //close this activity & return to preview activity
        }
        return super.onOptionsItemSelected(item);
    }


}
