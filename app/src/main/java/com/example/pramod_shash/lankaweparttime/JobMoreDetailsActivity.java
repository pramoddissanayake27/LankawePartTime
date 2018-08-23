package com.example.pramod_shash.lankaweparttime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JobMoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_more_details);

        TextView allDetails = findViewById(R.id.tvAllDetails);

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

    }
}
