package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployerMyProfileEdit extends AppCompatActivity {

    private EditText employercompanynameedit1,employeremailedit1;
    private Button save1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_my_profile_edit);


        employercompanynameedit1=findViewById(R.id.employercompanynameedit);
        employeremailedit1 =findViewById(R.id.employeremailedit);
        save1 =findViewById(R.id.btnsave1);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();

        final DatabaseReference datababaseReference =firebaseDatabase.getReference().child("users").child("Employers");
        datababaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EmployerUserProfile employerUserProfile =dataSnapshot.getValue(EmployerUserProfile.class);
                employercompanynameedit1.setText(employerUserProfile.getCompanyName());
               employeremailedit1.setText(employerUserProfile.getEmail1());

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
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyName  =employercompanynameedit1.getText().toString();
                String email1  =employeremailedit1.getText().toString();
                EmployerUserProfile employerUserProfile=new EmployerUserProfile(companyName,email1);
                datababaseReference.child("UserID").setValue(employerUserProfile);
                startActivity(new Intent(EmployerMyProfileEdit.this,EmployerMyProfile .class));
                finish();


            }
        });
    }
}
