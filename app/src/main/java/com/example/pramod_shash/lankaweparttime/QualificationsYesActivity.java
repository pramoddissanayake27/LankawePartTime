package com.example.pramod_shash.lankaweparttime;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

public class QualificationsYesActivity extends AppCompatActivity {
    private EditText jobName, jobDescription, numberOfEmployees, paymentPerEach, duration, date, contactNumber, location,locationAddress;
    private Button createJob;
    String jobName1, jobDescription1, numberOfEmployees1, paymentPerEach1, duration1, date1, contactNumber1, location1,locationAddress1;

    private static final String TAG = "LankawePartTime";
    Button getPlaceButton;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 1;

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
        location = (EditText) findViewById(R.id.etLocation);
        locationAddress = (EditText) findViewById(R.id.etLocationAddress);
        createJob = (Button)findViewById(R.id.btnCreateJob);

        requestPermission();



        getPlaceButton = (Button) findViewById(R.id.btGetPlace);
        getPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(QualificationsYesActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        android.util.Log.i(TAG,"in oncreate methode ");




        createJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    sendNewJobdata();
                    Toast.makeText(QualificationsYesActivity.this,"New Job Created!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QualificationsYesActivity.this,EmployerHomeActivity.class));


                }
                NotificationGenerator.openActivityNotification(getApplicationContext());

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
        locationAddress1=locationAddress.getText().toString();

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
        JobCreatingWithQualifications jobCreatingWithQualifications = new JobCreatingWithQualifications(jobName1,jobDescription1,numberOfEmployees1, paymentPerEach1, duration1, date1, contactNumber1, location1,locationAddress1);
        myRef.push().setValue(jobCreatingWithQualifications);

    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
        android.util.Log.i(TAG,"in requestPermission ");
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
        android.util.Log.i(TAG,"in onrequestPermissionResult ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){

            if (resultCode == RESULT_OK){
                android.util.Log.i(TAG,"i am in result ok if ");
                Place place = PlacePicker.getPlace(QualificationsYesActivity.this, data);
                location.setText(place.getName());
                locationAddress.setText(place.getAddress());


            }else{
                android.util.Log.i(TAG,"in ooooooooo ");
            }
        }
        android.util.Log.i(TAG,"in onactivity methode ");
    }

}
