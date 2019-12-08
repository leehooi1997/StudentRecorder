package com.inti.student.result;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sem1Activity extends AppCompatActivity {

    private Button insert;
    private EditText score,course,exam;
    private TextView subject;
    Spinner spinner;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference databaseReference;
    private FloatingActionButton floatingActionButton;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem1);


        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Sem 1 Subjects");
        //add back arrow to toolbar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mFirebaseAuth= FirebaseAuth.getInstance();
        course = (EditText) findViewById(R.id.course);
        exam = (EditText) findViewById(R.id.exam);
        //score = (EditText) findViewById(R.id.score);
        subject =(TextView) findViewById(R.id.subject);
        spinner = (Spinner) findViewById(R.id.spinner);
        insert = (Button) findViewById(R.id.insert);

        List<String> subjects = new ArrayList<>();
        subjects.add("Operating System, Security & Network");
        subjects.add("Data and Information");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Dropdownlist layout style
        spinner.setAdapter(dataAdapter); // attaching data adapter to spinner

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Choose Subject")){

                }
                else{

                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected "+item,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sem1Activity.this,"Student Data has been inserted",Toast.LENGTH_SHORT).show();
                addStudents();

            }
        });


    }
    public void addStudents(){
        String user_id = mFirebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Sem1").child(user_id).push();
        String studentSub = spinner.getSelectedItem().toString();
        int cc = Integer.parseInt("" + course.getText());
        String studentCourse = Integer.toString(cc);
        int ex = Integer.parseInt(""+exam.getText());
        String studentExam =Integer.toString(ex);
        int ttl = cc+ex;
        String studentScore = Integer.toString(ttl);

        if(cc >50 ) {

            Toast.makeText(Sem1Activity.this, "Coursework must not over 50%!", Toast.LENGTH_LONG).show();
        }
        else if (ex >50){
            Toast.makeText(Sem1Activity.this, "Exam mark must not over 50%!", Toast.LENGTH_LONG).show();
        }


        else {
                Map newPost = new HashMap();  // Create a array map
                newPost.put("studentSub", studentSub); // get from spinner
                newPost.put("studentCourse", studentCourse); // get coursework from textview
                newPost.put("studentExam", studentExam); // get exam mark from textview
                newPost.put("studentScore", studentScore); //  get from coursework + exam =score
                current_user_db.setValue(newPost); // store data into logged in user's database
                course.setText("");
                exam.setText("");

                Toast.makeText(this, "Insert Successfully ", Toast.LENGTH_SHORT).show();
                Intent inToMain = new Intent(Sem1Activity.this,DataRetrieved.class);
                startActivity(inToMain);
        }



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
