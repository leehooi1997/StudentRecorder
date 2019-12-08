package com.inti.student.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;

import com.google.firebase.auth.FirebaseAuth;

public class Nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intToMain = new Intent(Nav.this, Notes.class);
            startActivity(intToMain);
        }

        return super.onOptionsItemSelected(item);
    }

    private void Logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intToMain = new Intent(Nav.this, LoginActivity.class);
        startActivity(intToMain);
    }
    private void Home(){

        Intent intToMain = new Intent(Nav.this, HomeActivity.class);
        startActivity(intToMain);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.db) {
            Home();
        } else if (id == R.id.sem1) {
            Intent s1 = new Intent(Nav.this,DataRetrieved.class);
            startActivity(s1);

        } else if (id == R.id.sem2) {

            Intent s2 = new Intent(Nav.this,Sem2Retrieved.class);
            startActivity(s2);

        } else if (id == R.id.sem3) {

            Intent s3 = new Intent(Nav.this,Sem3Retrieved.class);
            startActivity(s3);

        } else if (id == R.id.list) {
            Intent intToMain = new Intent(Nav.this,ScheduleRetrieved.class);
            startActivity(intToMain);

        }else if (id == R.id.result) {
            Intent r = new Intent(Nav.this,CgpaRetrieve.class);
            startActivity(r);

        }else if (id == R.id.notes) {
            Intent r = new Intent(Nav.this,NoteRetrieved.class);
            startActivity(r);

        }
        else if (id == R.id.cu) {
            Intent r = new Intent(Nav.this,WebActivity.class);
            startActivity(r);

        }
        else if (id == R.id.logout) {
            Logout();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
