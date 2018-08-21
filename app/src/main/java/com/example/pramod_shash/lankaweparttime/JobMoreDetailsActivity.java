package com.example.pramod_shash.lankaweparttime;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JobMoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_more_details);

        TextView allDetails = findViewById(R.id.tvAllDetails);
        Button applyJob = findViewById(R.id.btnApplyJob);

        JobCreatingWithQualifications listItem = (JobCreatingWithQualifications) getIntent().getExtras().get("JobDetails");

        allDetails.setText(
                        "job - "+listItem.getJobName()+"\n"+
                        "details - "+listItem.getJobDescription()+"\n"+
                        "No of workers need - "+listItem.getNumberOfEmployees()+"\n"+
                        "Payment(per each) - "+listItem.getPaymentPerEach()+"\n"+
                        "Duration - "+listItem.getDuration()+"\n"+
                        "Date - "+listItem.getDate()+"\n"+
                        "Contact - "+listItem.getContactNumber()+"\n"+
                        "Location - "+listItem.getLocation()
        );

        applyJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(JobMoreDetailsActivity.this);
                alertDialog.setMessage("Are you sure Do you want to apply for this job?");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(JobMoreDetailsActivity.this,"You have applied for the job!",Toast.LENGTH_LONG).show();
                        JobMoreDetailsActivity.super.onBackPressed();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //startActivity(new Intent(JobMoreDetailsActivity.this,TabTestActivity.class));
                    }
                });
                alertDialog.create().show();
            }
        });

    }
}
