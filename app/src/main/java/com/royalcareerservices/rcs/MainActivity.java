package com.royalcareerservices.rcs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ClientDetails> arrayList;
    private DatabaseReference mDatabase;
//    mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView= (RecyclerView)findViewById(R.id.recyclerview);
        arrayList = new ArrayList<ClientDetails>();

        ClientDetails clientDetails = new ClientDetails();

        clientDetails.setName("ABC BANK");
        clientDetails.setNumberOpenings("10");
        arrayList.add(clientDetails);

        ClientDetails clientDetails1 = new ClientDetails();
        clientDetails1.setName("ABCD BANK");
        clientDetails1.setNumberOpenings("105");
        arrayList.add(clientDetails1);

        ClientDetails clientDetails2 = new ClientDetails();
        clientDetails2.setName("IDFC4 BANK");
        clientDetails2.setNumberOpenings("100");
        arrayList.add(clientDetails2);

        ClientDetails clientDetails3 = new ClientDetails();
        clientDetails3.setName("IDFC3 BANK");
        clientDetails3.setNumberOpenings("100");
        arrayList.add(clientDetails3);

        ClientDetails clientDetails4 = new ClientDetails();
        clientDetails4.setName("IDFC2 BANK");
        clientDetails4.setNumberOpenings("100");
        arrayList.add(clientDetails4);

        ClientDetails clientDetails5 = new ClientDetails();
        clientDetails5.setName("IDFC1 BANK");
        clientDetails5.setNumberOpenings("100");
        arrayList.add(clientDetails5);


        Toast.makeText(getApplicationContext(),"HERE",Toast.LENGTH_SHORT).show();
//  recyclerView.setItemAnimator(new SlideInUpAnimator());
//            RecyclerView.ItemDecoration itemDecoration = new
//                    DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//            recyclerView.addItemDecoration(itemDecoration);
        //TODO Uncomment this code for firebase database integration
        /*ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                ClientDetails post = dataSnapshot.getValue(ClientDetails.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("OnCancelled", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mPostReference.addValueEventListener(postListener);*/
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new RecyclerAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);

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
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
