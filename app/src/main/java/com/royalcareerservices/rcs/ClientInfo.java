package com.royalcareerservices.rcs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ClientInfo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ClientDetails");
    private ArrayList<ClientDetails> arrayList;

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                arrayList= new ArrayList<>();
                Bundle b = getIntent().getExtras();
                String desc=  b.getString("desc");
                ArrayList<String> arrayList1 = b.getStringArrayList("post");
                Toast.makeText(getApplicationContext(),((Integer)arrayList1.size()).toString(),Toast.LENGTH_SHORT).show();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    ClientDetails clientDetails = childSnapshot.getValue(ClientDetails.class);
                    arrayList.add(clientDetails);
                }
                layoutManager= new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                adapter=new RecyclerAdapterClientInfo(arrayList1,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Error", "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);
        Context context = getApplicationContext();
        ImageView imageView = findViewById(R.id.logoimage);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("ClientLogos/images.png");
        recyclerView= (RecyclerView)findViewById(R.id.Positioninfo);

        //        Uri uri = storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                // Got the download URL for 'users/me/profile.png'
//                Uri downloadUri = taskSnapshot.getMetadata().getDownloadUrl();
//                generatedFilePath = downloadUri.toString(); /// The string(file link) that you need
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle any errors
//            }
//        });
    String url= "https://firebasestorage.googleapis.com/v0/b/rcsapp-ace05.appspot.com/o/ClientLogos%2Fimages.png?alt=media&token=87e6cd9c-4352-4955-b4eb-07c2afdaf136";
        Glide.with(getApplicationContext())
                .load(url)
                .into(imageView);
    }
}
