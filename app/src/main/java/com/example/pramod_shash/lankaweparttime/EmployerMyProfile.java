package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployerMyProfile extends AppCompatActivity {
    private ImageView employerprofilepic,BackPic,employeredit;
    private TextView employercompanyname,employeremail1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_my_profile);

        employerprofilepic =findViewById(R.id.profileimage);
        BackPic =findViewById(R.id.header_cover_image);
        employercompanyname=findViewById(R.id.employercompanyname);
        employeremail1 =findViewById(R.id.employerEmail);
        employeredit =findViewById(R.id.employeredit);


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference datababaseReference =firebaseDatabase.getReference().child("users").child("Employers");
        datababaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EmployerUserProfile employerUserProfile =dataSnapshot.getValue(EmployerUserProfile.class);
                employercompanyname.setText(employerUserProfile.getCompanyName());
                employeremail1.setText(employerUserProfile.getEmail1());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        employeredit.setOnClickListener(new View.OnClickListener() {   //set the instructions for the buttons in the Qualification checking interface
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployerMyProfile.this,EmployerMyProfileEdit.class));
            }
        });

    }
    }

