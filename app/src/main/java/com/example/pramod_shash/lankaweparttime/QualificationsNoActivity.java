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

public class QualificationsNoActivity extends AppCompatActivity {
    private EditText jobName, jobDescription, numberOfEmployees, paymentPerEach, date, contactNumber, location;
    private Button createJob;
    String jobName2, jobDescription2, numberOfEmployees2, paymentPerEach2, date2, contactNumber2, location2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualifications_no);

        jobName = (EditText)findViewById(R.id.etJobName2);
        jobDescription = (EditText)findViewById(R.id.etJobDescription2);
        numberOfEmployees = (EditText)findViewById(R.id.etNumberOfEmployees2);
        paymentPerEach = (EditText)findViewById(R.id.etPaymentPerEach2);
        date = (EditText)findViewById(R.id.etDate2);
        contactNumber = (EditText)findViewById(R.id.etContactNumber2);
        location = (EditText)findViewById(R.id.etLocation2);
        createJob = (Button)findViewById(R.id.btnCreateJob2);

        createJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    sendNewJobdata();
                    Toast.makeText(QualificationsNoActivity.this,"New Job Created!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QualificationsNoActivity.this,EmployerHomeActivity.class));
                }
            }
        });
    }

    private Boolean validate(){
        Boolean result = false;

        jobName2 = jobName.getText().toString();
        jobDescription2 = jobDescription.getText().toString();
        numberOfEmployees2 = numberOfEmployees.getText().toString();
        paymentPerEach2 = paymentPerEach.getText().toString();
        date2 = date.getText().toString();
        contactNumber2 = contactNumber.getText().toString();
        location2 = location.getText().toString();

        if (jobName2.isEmpty() || jobDescription2.isEmpty() || numberOfEmployees2.isEmpty() || paymentPerEach2.isEmpty() ||date2.isEmpty() || contactNumber2.isEmpty() || location2.isEmpty()){
            Toast.makeText(this, "You have to Enter All the Details!", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendNewJobdata(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("jobs/withNoQualifications");//setting database reference
        JobCreatingWithNoQualifications jobCreatingWithNoQualifications = new JobCreatingWithNoQualifications(jobName2,jobDescription2,numberOfEmployees2,paymentPerEach2,date2,contactNumber2,location2);
        myRef.push().setValue(jobCreatingWithNoQualifications);
    }
}
