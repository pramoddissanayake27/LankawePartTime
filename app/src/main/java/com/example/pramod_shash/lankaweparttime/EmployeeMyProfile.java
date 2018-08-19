package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
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
      private  FirebaseUser user;
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
       // FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase=FirebaseDatabase.getInstance();



        user=FirebaseAuth.getInstance() .getCurrentUser()  ;
        uid =user.getUid();



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            String name=user.getDisplayName();

            String mobilenbr=user.getPhoneNumber();

            employeeemail1.setText(email);
            employeename.setText(name);
            employeephonenumber.setText(mobilenbr);

        }



            employeeedit.setOnClickListener(new View.OnClickListener() {   //set the instructions for the buttons in the Qualification checking interface
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeMyProfile.this,EmployeeMyProfilleEdit.class));
            }
        });


    }

}
