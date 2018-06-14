package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QualificationsYesActivity extends AppCompatActivity {
    private EditText jobName, jobDescription, numberOfEmployees, paymentPerEach, duration, date, contactNumber, location;
    private Button createJob;
    String jobName1, jobDescription1, numberOfEmployees1, paymentPerEach1, duration1, date1, contactNumber1, location1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualifications_yes);

        jobName = (EditText)findViewById(R.id.etJobName);
        jobDescription = (EditText)findViewById(R.id.etJobDescription);
        numberOfEmployees = (EditText)findViewById(R.id.etNumberOfEmployees);
        paymentPerEach = (EditText)findViewById(R.id.etPaymentPerEach);
        duration = (EditText)findViewById(R.id.etDuration);
        date = (EditText)findViewById(R.id.etDate);
        contactNumber = (EditText)findViewById(R.id.etContactNumber);
        location = (EditText)findViewById(R.id.etLocation);
        createJob = (Button)findViewById(R.id.btnCreateJob);

        createJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    sendNewJobdata();
                    Toast.makeText(QualificationsYesActivity.this,"New Job Created!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QualificationsYesActivity.this,EmployerHomeActivity.class));
                }
            }
        });
    }
    private Boolean validate(){
        Boolean result = false;

        jobName1 = jobName.getText().toString();
        jobDescription1 = jobDescription.getText().toString();
        numberOfEmployees1 = numberOfEmployees.getText().toString();
        paymentPerEach1 = paymentPerEach.getText().toString();
        duration1 = duration.getText().toString();
        date1 = date.getText().toString();
        contactNumber1 = contactNumber.getText().toString();
        location1 = location.getText().toString();

        if (jobName1.isEmpty() || jobDescription1.isEmpty() || numberOfEmployees1.isEmpty() || paymentPerEach1.isEmpty() || duration1.isEmpty() ||date1.isEmpty() || contactNumber1.isEmpty() || location1.isEmpty()){
            Toast.makeText(this, "You have to Enter All the Details!", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendNewJobdata(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("jobs/withQualifications");
        JobCreatingWithQualifications jobCreatingWithQualifications = new JobCreatingWithQualifications(jobName1,jobDescription1,numberOfEmployees1, paymentPerEach1, duration1, date1, contactNumber1, location1);
        myRef.push().setValue(jobCreatingWithQualifications);

    }
}
