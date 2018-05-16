package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class WelcomeScreenActivity extends AppCompatActivity {

    private Button btnEmployee;
    private Button btnEmployer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnEmployee = (Button) findViewById(R.id.btnEmployee);  //set the instructions for btnEmployee
        btnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmployeeLoginActivity();
            }
        });

        btnEmployer = (Button) findViewById(R.id.btnEmployer);   //set the instructions for btnEmployer
        btnEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmployerLoginActivity();
            }
        });
    }
    public void openEmployeeLoginActivity(){
        Intent intent = new Intent(this,EmployeeLoginActivity.class);
        startActivity(intent);
    }
    public void openEmployerLoginActivity(){
        Intent intent = new Intent(this,EmployerLoginActivity.class);
        startActivity(intent);
    }
}
