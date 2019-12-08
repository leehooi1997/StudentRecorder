package com.inti.student.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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

public class NoteRetrieved extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<Announcement> list;
    NoteAdapter adapter;
    FirebaseAuth mFirebasAuth;
    FirebaseDatabase mFirebaseDatabase;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_retrieved);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Announcement>();

        //String uid = mFirebasAuth.getInstance().getCurrentUser().getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =mFirebaseDatabase.getReference("Notes");

        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Announcement");



        // databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Announcement s = dataSnapshot1.getValue(Announcement.class);
                    list.add(s);
                }
                adapter = new NoteAdapter(NoteRetrieved.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(NoteRetrieved.this, "Ooops....Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle arrow click
        if(item.getItemId() == android.R.id.home){
            Intent back = new Intent(NoteRetrieved.this,Nav.class);
            startActivity(back);
        }
        return super.onOptionsItemSelected(item);
    }


}
