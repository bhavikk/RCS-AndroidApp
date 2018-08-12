package com.royalcareerservices.rcs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home1 extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ClientDetails> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ClientDetails");
    private ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                arrayList = new ArrayList<ClientDetails>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    ClientDetails clientDetails = childSnapshot.getValue(ClientDetails.class);
                    arrayList.add(clientDetails);
                }
                ArrayList<ClientDetails> newArray = new ArrayList<>();
                String data = getArguments().getString("data");
                Toast.makeText(getContext(),data,Toast.LENGTH_SHORT).show();
                if(!data.equals("0")) {
                    for(ClientDetails all:arrayList){
                        for(Post post:all.getPost()){
                            for(String state:post.getStates()){
                                if(state.equals(data)){
                                    Toast.makeText(getContext(),state,Toast.LENGTH_SHORT).show();
                                    newArray.add(all);
                                }
                            }
                        }
                    }
                    layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    adapter = new RecyclerAdapter(newArray, getActivity());
                    recyclerView.setAdapter(adapter);
                }
                else {
                    layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    adapter = new RecyclerAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Error", "Failed to read value.", error.toException());
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) getView().findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.states:

                        Fragment fragment = new States();
                        FragmentManager fragmentManager= getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.linear ,fragment);
                        fragmentTransaction.addToBackStack("state");
                        fragmentTransaction.commit();
                        break;


                    case R.id.fields:

                        Intent intent2 = new Intent(getActivity(),Fields.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }

        };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
        recyclerView= (RecyclerView)getView().findViewById(R.id.recyclerview);
        final SwipeRefreshLayout mSwipeRefreshLayout;
        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.black,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        String data = getArguments().getString("data");

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        arrayList = new ArrayList<ClientDetails>();
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            ClientDetails clientDetails = childSnapshot.getValue(ClientDetails.class);
                            arrayList.add(clientDetails);
                        }
                        layoutManager= new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                        adapter=new RecyclerAdapter(arrayList,getActivity());
                        recyclerView.setAdapter(adapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("Error", "Failed to read value.", error.toException());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }
}