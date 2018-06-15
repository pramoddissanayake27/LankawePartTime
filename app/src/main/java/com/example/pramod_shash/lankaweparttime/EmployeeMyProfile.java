package com.example.pramod_shash.lankaweparttime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeMyProfile extends AppCompatActivity {
     private ImageView profilepic,BackPic,employeeedit;
     private TextView  employeename,employeeemail1,employeephonenumber;
     private FirebaseAuth firebaseAuth;
     private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_my_profile);


        profilepic =findViewById(R.id.profileimage);
        BackPic =findViewById(R.id.header_cover_image);
        employeename=findViewById(R.id.employeename);
        employeeemail1 =findViewById(R.id.employeeEmail);
        employeephonenumber =findViewById(R.id.employeephoneNumber);
        employeeedit =findViewById(R.id.employeeedit);


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference datababaseReference =firebaseDatabase.getReference(firebaseAuth.getUid());
        datababaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                EmployeeUserProfile employeeUserProfile =dataSnapshot.getValue(EmployeeUserProfile.class);
                employeename.setText(employeeUserProfile.getUsername());
                employeeemail1.setText(employeeUserProfile.getUserEmail());
                employeephonenumber.setText(employeeUserProfile.getUserMobileNumber());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EmployeeMyProfile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
