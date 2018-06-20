package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeMyProfile extends AppCompatActivity {
    private static final String G_TAG = "Global";
    private String uid;
    private ImageView profilepic,BackPic,employeeedit;
     private TextView  employeename,employeeemail1,employeephonenumber;
     private FirebaseAuth firebaseAuth;
     private FirebaseDatabase firebaseDatabase;
      private  FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_my_profile);

         Log.i(G_TAG, "onCreate : EmployeeMyProfile");

        profilepic =findViewById(R.id.profileimage);
        BackPic =findViewById(R.id.header_cover_image);
        employeename=findViewById(R.id.employeename);
        employeeemail1 =findViewById(R.id.employeeEmail);
        employeephonenumber =findViewById(R.id.employeephoneNumber);
        employeeedit =findViewById(R.id.employeeedit);


        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
         uid = firebaseUser.getUid();
        firebaseDatabase=FirebaseDatabase.getInstance();


        DatabaseReference datababaseReference =firebaseDatabase.getReference().child("users").child("Employees");

        datababaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                EmployeeUserProfile employeeUserProfile =dataSnapshot.getValue(EmployeeUserProfile.class);
                employeename.setText(employeeUserProfile.getUsername());
                employeeemail1.setText(employeeUserProfile.getEmail());
                employeephonenumber.setText(employeeUserProfile.getMobileNumber());

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
        } );


    }

}
