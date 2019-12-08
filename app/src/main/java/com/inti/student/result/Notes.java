package com.inti.student.result;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Notes extends AppCompatActivity {
    private Button noteSaveButton;
    private EditText noteTitle,noteBody;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference databaseReference;
    private FloatingActionButton floatingActionButton;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        mFirebaseAuth= FirebaseAuth.getInstance();
        noteTitle = (EditText) findViewById(R.id.noteTitle);
        noteBody = (EditText) findViewById(R.id.noteBody);


        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Create Announcement");
        //add back arrow to toolbar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }
    public void addNotes(){
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference("Notes").push();
        String studentTitle = noteTitle.getText().toString();
        String studentNote = noteBody.getText().toString();

        if(studentTitle.trim().isEmpty() ){
            noteTitle.setError("Please enter Title");
            noteTitle.requestFocus();
        }
        else if(studentNote.isEmpty()){
            noteBody.setError("Please enter something!");
            noteBody.requestFocus();
        }


        else {
            Map newPost = new HashMap();
            newPost.put("studentTitle", studentTitle);
            newPost.put("studentNote", studentNote);
            current_user_db.setValue(newPost);
            noteTitle.setText("");
            noteBody.setText("");

            Toast.makeText(this, "Insert Successfully ", Toast.LENGTH_SHORT).show();
            Intent inToMain = new Intent(Notes.this,Nav.class);
            startActivity(inToMain);
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle arrow click
        if(item.getItemId() == android.R.id.home){
            finish(); //close this activity & return to preview activity
        }
        else if(item.getItemId() == R.id.enter){
            addNotes();
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.enter,menu);
        return  true;
    }


}
