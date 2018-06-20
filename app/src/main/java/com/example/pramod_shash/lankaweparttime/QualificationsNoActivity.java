package com.example.pramod_shash.lankaweparttime;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QualificationsNoActivity extends AppCompatActivity {
    private EditText jobName, jobDescription, numberOfEmployees, paymentPerEach, date, contactNumber, location2,locationAddress2;
    private Button createJob,getPlaceButton2;
    String jobName2, jobDescription2, numberOfEmployees2, paymentPerEach2, date2, contactNumber2, location3,locationAddress3;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 1;

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
        location2 = (EditText) findViewById(R.id.etLocation2);
        locationAddress2 = (EditText) findViewById(R.id.etLocationAddress2);
        createJob = (Button)findViewById(R.id.btnCreateJob2);

        requestPermission();



        getPlaceButton2 = (Button) findViewById(R.id.btGetPlace2);

        getPlaceButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(QualificationsNoActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
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
        location3 = location2.getText().toString();
        locationAddress3=locationAddress2.getText().toString();

        if (jobName2.isEmpty() || jobDescription2.isEmpty() || numberOfEmployees2.isEmpty() || paymentPerEach2.isEmpty() ||date2.isEmpty() || contactNumber2.isEmpty() || location3.isEmpty()){
            Toast.makeText(this, "You have to Enter All the Details!", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendNewJobdata(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("jobs/withNoQualifications");//setting database reference
        JobCreatingWithNoQualifications jobCreatingWithNoQualifications = new JobCreatingWithNoQualifications(jobName2,jobDescription2,numberOfEmployees2,paymentPerEach2,date2,contactNumber2,location3,locationAddress3 );
        myRef.push().setValue(jobCreatingWithNoQualifications);
    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){

            if (resultCode == RESULT_OK){

                Place place = PlacePicker.getPlace(QualificationsNoActivity.this, data);
                location2.setText(place.getName());
                locationAddress2.setText(place.getAddress());


            }else{

            }
        }

    }

}
