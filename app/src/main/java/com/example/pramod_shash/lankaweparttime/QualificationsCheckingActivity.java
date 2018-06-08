package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QualificationsCheckingActivity extends AppCompatActivity {
    private Button Yes,No;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualifications_checking);

        Yes = (Button)findViewById(R.id.btnYesQualifications);
        No  = (Button)findViewById(R.id.btnNoQualifications);

        Yes.setOnClickListener(new View.OnClickListener() {   //set the instructions for the buttons in the Qualification checking interface
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QualificationsCheckingActivity.this,QualificationsYesActivity.class));
            }
        });

        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QualificationsCheckingActivity.this,QualificationsNoActivity.class));
            }
        });
    }
}
