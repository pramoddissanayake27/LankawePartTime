package com.example.pramod_shash.lankaweparttime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobShowActivity extends AppCompatActivity {
    private TextView job, description, noOfEmployees, paymentPerEach, duration, date, contact, location,locationAddress;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_show);

        job = findViewById(R.id.tvJob_s);
        description = findViewById(R.id.tvDescription_s);
        noOfEmployees = findViewById(R.id.tvNoOfEmployees_s);
        paymentPerEach = findViewById(R.id.tvPaymentPerEach_s);
        duration = findViewById(R.id.tvDuration_s);
        date = findViewById(R.id.tvDate_s);
        contact = findViewById(R.id.tvContact_s);
        location = findViewById(R.id.tvLocation_s);
        locationAddress=findViewById(R.id.tvLocation_address);

        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("jobs/withQualifications");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                JobCreatingWithQualifications jobCreatingWithQualifications = dataSnapshot.getValue(JobCreatingWithQualifications.class);

                job.setText(jobCreatingWithQualifications.getJobName());
                description.setText(jobCreatingWithQualifications.getJobDescription());
                noOfEmployees.setText(jobCreatingWithQualifications.getNumberOfEmployees());
                paymentPerEach.setText(jobCreatingWithQualifications.getPaymentPerEach());
                duration.setText(jobCreatingWithQualifications.getDuration());
                date.setText(jobCreatingWithQualifications.getDate());
                contact.setText(jobCreatingWithQualifications.getContactNumber());
                location.setText(jobCreatingWithQualifications.getLocation());
                locationAddress.setText(jobCreatingWithQualifications.getLocationAddress());
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
    }

}
