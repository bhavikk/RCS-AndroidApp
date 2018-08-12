package com.royalcareerservices.rcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Register_Activity extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    Button selectFile,upload;
    Uri pdfUri;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    private ArrayList<ClientDetails> arrayList;
    private Intent quiz_activity;

    private Button mselectImage;
    private StorageReference mstorage;
    private static final int PICK_IMAGE_REQUEST = 10;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        setTitle("Registration Form");
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Register");
        setSupportActionBar(mActionBarToolbar);
        storage= FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        Bundle b = getIntent().getExtras();
        selectFile= (Button) findViewById(R.id.selectfile);



        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Button submit = (Button)findViewById(R.id.submit);
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        final long id = b.getLong("id")-1;
        final DatabaseReference myRef = database1.getReference("ClientDetails").child(""+id).child("UserDetails").push();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name,email,surname,contact;
                String name_val,email_val,surname_val;
                String contact_val;
                long cno;




                name = findViewById(R.id.name);
                surname = findViewById(R.id.surname);
                email = findViewById(R.id.email);
                contact = findViewById(R.id.contactno);
                name_val= name.getText().toString();
                surname_val = surname.getText().toString();
                email_val = email.getText().toString();
                contact_val = contact.getText().toString();
                cno = Long.parseLong(contact_val);
                UserDetails userDetails = new UserDetails();
                userDetails.setContactno(cno);
                userDetails.setEmail(email_val);
                userDetails.setName(name_val);
                userDetails.setSurname(surname_val);
                int f=0;
                if(TextUtils.isEmpty(name_val)){
                    Toast.makeText(Register_Activity.this,"Please Enter a Name",Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(TextUtils.isEmpty(surname_val)){
                    Toast.makeText(Register_Activity.this,"Please Enter a Surname",Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(TextUtils.isEmpty(email_val)){
                    Toast.makeText(Register_Activity.this,"Please Enter a Email",Toast.LENGTH_SHORT).show();
                    f=1;
                }

                if(TextUtils.isEmpty(contact_val)){
                    Toast.makeText(Register_Activity.this,"Please Enter a Contact Number",Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(f!=1)
                myRef.setValue(userDetails);

                if(pdfUri!=null&&f!=1)
                    uploadFile(pdfUri,name_val);
                else
                Toast.makeText(Register_Activity.this,"Select a File",Toast.LENGTH_SHORT).show();

            }
        });
        final DatabaseReference myRef1 = database1.getReference("ClientDetails");

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                arrayList = new ArrayList<ClientDetails>();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    ClientDetails clientDetails = childSnapshot.getValue(ClientDetails.class);
                    arrayList.add(clientDetails);
                }
                quiz_activity = new Intent(getApplicationContext(),QuizActivity.class);
                int int_id = (int) id;
                quiz_activity.putExtra("quiz",arrayList.get(int_id).getQuiz());
                quiz_activity.putExtra("id",id);


            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Error", "Failed to read value.", error.toException());
            }
        });

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(Register_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)==getPackageManager().PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                    ActivityCompat.requestPermissions(Register_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);

            }
        });
       /* upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri!=null)

                else
                    Toast.makeText(Register_Activity.this,"Select a File",Toast.LENGTH_SHORT).show();
            }
        });*/








    }




    private void uploadFile(Uri pdfUri,String name)
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String fileName=System.currentTimeMillis()+"";
        StorageReference storageReference= storage.getReference();
        Toast.makeText(Register_Activity.this,pdfUri.toString()
                ,Toast.LENGTH_SHORT).show();

        storageReference.child("Upload").child(name).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url=taskSnapshot.getDownloadUrl().toString();
                        DatabaseReference reference=database.getReference();
                        reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Register_Activity.this, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                    startActivity(quiz_activity);
                                }
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Register_Activity.this,"File Not Successfully Uploaded",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress=(int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                if(currentProgress == 100) progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            selectPdf();
        }
        else
            Toast.makeText(Register_Activity.this,"Please provide permission",Toast.LENGTH_SHORT).show();
    }

    private void selectPdf()
    {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setType("images/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==86 &&resultCode==RESULT_OK && data!=null)
        {
            pdfUri=data.getData();
        }
        else
            Toast.makeText(Register_Activity.this,"Please Select a File",Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
