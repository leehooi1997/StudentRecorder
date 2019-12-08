package com.inti.student.result;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

public class CgpaRetrieve extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton floatingActionButton;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<Result> list;
    ResultAdapter adapter;
    FirebaseAuth mFirebasAuth;
    FirebaseDatabase mFirebaseDatabase;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_retrieve);
        //listView = findViewById(R.id.list_view);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Result>();

        String uid = mFirebasAuth.getInstance().getCurrentUser().getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =mFirebaseDatabase.getReference("Result").child(uid);

        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Result CGPA");
        //add back arrow to toolbar



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Result s = dataSnapshot1.getValue(Result.class);
                    list.add(s);
                }
                adapter = new ResultAdapter(CgpaRetrieve.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CgpaRetrieve.this, "Ooops....Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });

        /*
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String os = getIntent().getStringExtra("operatingSystem");
                String di = getIntent().getStringExtra("dataInfo");
                String as = getIntent().getStringExtra("androidS");
                String pp = getIntent().getStringExtra("programPython");
                String rwp = getIntent().getStringExtra("realWP");
                String se = getIntent().getStringExtra("softwareE");
                String sl = getIntent().getStringExtra("socialLegal");
                String ttl = getIntent().getStringExtra("totalResult");
                showDeleteDialog(os,di,as,pp,rwp,se,sl,ttl);
            }

        }); */

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(CgpaRetrieve.this,CGPA.class);
                startActivity(next);
            }
        });
    }
    /*
    private void showDeleteDialog(String os, String di, String as, String pp, String rwp, String se, String sl, final String ttl){
        AlertDialog.Builder builder = new AlertDialog.Builder(CgpaRetrieve.this);
        builder.setTitle("Delete");
        builder.setTitle("Are you sure you want to delete?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseReference.orderByChild("Result").equalTo(ttl).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postsnapshot:dataSnapshot.getChildren()){
                            postsnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(CgpaRetrieve.this, "Deleted",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CgpaRetrieve.this, Nav.class);
                startActivity(i);

            }
        });

    }
*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle arrow click
        if(item.getItemId() == android.R.id.home){
            Intent back = new Intent(CgpaRetrieve.this,Nav.class);
            startActivity(back);
        }
        return super.onOptionsItemSelected(item);
    }

}
