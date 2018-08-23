package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeMyProfilleEdit extends AppCompatActivity {

    private EditText employeeusernameedit,employeeemailedit,employeemobilenbredit;
    private Button save2;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_my_profille_edit);


        employeeusernameedit=findViewById(R.id.employeeUserNameedit);
       employeeemailedit=findViewById(R.id.employeeemailedit);
        employeemobilenbredit=findViewById(R.id.employeemobilenbredit);
        save2 =findViewById(R.id.save2);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference datababaseReference =firebaseDatabase.getReference().child("users").child("Employees");
        datababaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               EmployeeUserProfile employeeUserProfile =dataSnapshot.getValue(EmployeeUserProfile.class);
                employeeusernameedit.setText(employeeUserProfile.getUsername());
                employeeemailedit.setText(employeeUserProfile.getEmail());
                employeemobilenbredit.setText(employeeUserProfile.getMobileNumber());


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
        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newemployeename  =employeeusernameedit.getText().toString();
                String newemployeeemail  =employeeemailedit.getText().toString();
                String newemployeemobilenbr  =employeemobilenbredit.getText().toString();
               EmployeeUserProfile employeeUserProfile=new EmployeeUserProfile(newemployeename,newemployeeemail,newemployeemobilenbr);
                employeeUserProfile.setEmail(newemployeeemail);
                employeeUserProfile.setMobileNumber(newemployeemobilenbr);
                employeeUserProfile.setUsername(newemployeename);
               datababaseReference.child(firebaseUser.getUid()).child("username").setValue(newemployeename);
                datababaseReference.child(firebaseUser.getUid()).child("mobileNumber").setValue(newemployeemobilenbr);
                datababaseReference.child(firebaseUser.getUid()).child("email").setValue(newemployeeemail);

                save2.setOnClickListener(new View.OnClickListener() {   //set the instructions for the buttons in the Qualification checking interface
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(EmployeeMyProfilleEdit.this,EmployeeMyProfile.class));
                    }
                });

                finish();


            }
        });
    }
}
