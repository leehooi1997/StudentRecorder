package com.inti.student.result;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class CGPA extends AppCompatActivity {

    //private Button insert;
    private EditText os,datain,andS,python,rwp,se,ethic;
    private TextView subject;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference databaseReference;
    private FloatingActionButton floatingActionButton;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgp);


        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("CGPA Result");
        //add back arrow to toolbar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mFirebaseAuth= FirebaseAuth.getInstance();
        os = (EditText) findViewById(R.id.os);
        datain = (EditText) findViewById(R.id.datain);
        andS = (EditText) findViewById(R.id.andS);
        python = (EditText) findViewById(R.id.python);
        rwp = (EditText) findViewById(R.id.rwp);
        se =(EditText) findViewById(R.id.se);
        ethic =(EditText) findViewById(R.id.ethic);
       // subject =(TextView) findViewById(R.id.subject);

   /*
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CGPA.this,"Student Data has been inserted",Toast.LENGTH_SHORT).show();
                addStudents();

            }
        }); */
    }

    public void addStudents(){
        String user_id = mFirebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Result").child(user_id).push();

        int OS = Integer.parseInt( os.getText().toString());
        int DI = Integer.parseInt(datain.getText().toString());
        int AS = Integer.parseInt(andS.getText().toString());
        int PP = Integer.parseInt(python.getText().toString());
        int RWP = Integer.parseInt(rwp.getText().toString());
        int SE = Integer.parseInt(se.getText().toString());
        int EL = Integer.parseInt(ethic.getText().toString());
        double ttl = ((((double) OS+DI+AS+PP+RWP+SE+EL)/700)*100);

        //Toast.makeText(CGPA.this,"try:"+ttl,Toast.LENGTH_LONG).show();

        String operatingSystem = Integer.toString(OS);
        String dataInfo =Integer.toString(DI);
        String androidS = Integer.toString(AS);
        String programPython =Integer.toString(PP);
        String realWP = Integer.toString(RWP);
        String softwareE = Integer.toString(SE);
        String socialLegal =Integer.toString(EL);
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String totalResult = formatter.format(ttl);
        //String totalResult = Double.toString(ttl);


        if(OS>100 || DI>100 || AS>100 || PP>100 || RWP>100 || SE>100 || EL>100) {
            Toast.makeText(CGPA.this, "Please do not enter over 100 mark!", Toast.LENGTH_LONG).show();

        }
        else {

            Map newPost = new HashMap();
            newPost.put("operatingSystem", operatingSystem);
            newPost.put("dataInfo", dataInfo);
            newPost.put("androidS", androidS);
            newPost.put("programPython", programPython);
            newPost.put("realWP", realWP);
            newPost.put("softwareE", softwareE);
            newPost.put("socialLegal", socialLegal);
            newPost.put("totalResult", totalResult);
            current_user_db.setValue(newPost);
            os.setText("");
            datain.setText("");
            andS.setText("");
            python.setText("");
            rwp.setText("");
            se.setText("");
            ethic.setText("");

            Toast.makeText(this, "Insert Successfully ", Toast.LENGTH_SHORT).show();
            Intent inToMain = new Intent(CGPA.this,CgpaRetrieve.class);
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
            addStudents();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.enter,menu);
        return  true;
    }
}
