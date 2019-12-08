package com.inti.student.result;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private Button insert;
    private EditText name,score;
    private TextView subject;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference databaseReference;
    //private FloatingActionButton floatingActionButton;
    android.support.v7.widget.Toolbar tb;

    Spinner classSelect, daySelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseReference = FirebaseDatabase.getInstance().getReference("Schedules");



        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Create Schedules");
        //add back arrow to toolbar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mFirebaseAuth= FirebaseAuth.getInstance();
        classSelect = (Spinner) findViewById(R.id.classSelector);
        daySelect = (Spinner) findViewById(R.id.daySelector);


        ArrayList<String> weekdays = new ArrayList<>();
        weekdays.add("MONDAY");
        weekdays.add("TUESDAY");
        weekdays.add("WEDNESDAY");
        weekdays.add("THURSDAY");
        weekdays.add("FRIDAY");
        weekdays.add("SATURDAY");
        weekdays.add("SUNDAY");

        ArrayAdapter<String> days;
        days = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weekdays);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Dropdownlist layout style
        daySelect.setAdapter(days); // attaching data adapter to spinner

        ArrayList<String> classes = new ArrayList<>();
        classes.add("Operating System, Security & Network");
        classes.add("Data and Information");
        classes.add("Technology & ITS Social, Legal & Ethical");
        classes.add("Software Engineering");
        classes.add("Real World Project");
        classes.add("Programming, Algorithms & Data Structures");
        classes.add("Android Development Skills");

        ArrayAdapter<String> classsub;
        classsub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes);
        classsub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Dropdownlist layout style
        classSelect.setAdapter(classsub); // attaching data adapter to spinner


        Button btn = (Button) findViewById(R.id.saveBUTTON_SCHEDULE);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSchedule(v);
                Toast.makeText(HomeActivity.this,"Schedule has been inserted",Toast.LENGTH_SHORT).show();
            }
        });

    }

        private void saveSchedule(View v) {
            String daySelected = daySelect.getSelectedItem().toString();
            String classSelected = classSelect.getSelectedItem().toString();

            daySelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getItemAtPosition(position).equals("Choose Subject")) {

                    } else {

                        String item = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            classSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getItemAtPosition(position).equals("Choose Subject")) {

                    } else {

                        String item = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            String user_id = mFirebaseAuth.getCurrentUser().getUid();
            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Schedules").child(user_id).push();
            TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
            int hour = timePicker.getCurrentHour();
            String hr = Integer.toString(hour);
            int min = timePicker.getCurrentMinute();
            String mn = Integer.toString(min);



            Map newPost = new HashMap();
            newPost.put("selectDay", daySelected);
            newPost.put("selectSub", classSelected);
            newPost.put("hour", hr);
            newPost.put("min", mn);
            current_user_db.setValue(newPost);

            Toast.makeText(this, "Insert Successfully ", Toast.LENGTH_SHORT).show();
            Intent inToMain = new Intent(HomeActivity.this,Nav.class);
            startActivity(inToMain);

           // String id =databaseReference.push().getKey();
            //Schedules schedules = new Schedules(id,daySelected,classSelected,hr+":",mn+"minutes");
            //databaseReference.child(id).setValue(schedules);



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
