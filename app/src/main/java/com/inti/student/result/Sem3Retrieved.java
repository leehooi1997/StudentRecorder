package com.inti.student.result;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class Sem3Retrieved extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton floatingActionButton;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<Students> list;
    StudentInfoAdapter adapter;
    FirebaseAuth mFirebasAuth;
    FirebaseDatabase mFirebaseDatabase;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem3_retrieved);
        //listView = findViewById(R.id.list_view);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Students>();

        String uid = mFirebasAuth.getInstance().getCurrentUser().getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =mFirebaseDatabase.getReference("Sem3").child(uid);

        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Semester 3");
        //add back arrow to toolbar



        // databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Students s = dataSnapshot1.getValue(Students.class);
                    list.add(s);
                }
                adapter = new StudentInfoAdapter(Sem3Retrieved.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Sem3Retrieved.this, "Ooops....Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Sem3Retrieved.this,Sem3Activity.class);
                startActivity(next);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle arrow click
        if(item.getItemId() == android.R.id.home){
            Intent back = new Intent(Sem3Retrieved.this,Nav.class);
            startActivity(back);
        }
        return super.onOptionsItemSelected(item);
    }




}
