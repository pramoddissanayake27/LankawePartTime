package com.example.pramod_shash.lankaweparttime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmployeeLoginActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button SignIn;
    private TextView tvRegister;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Name = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        SignIn = (Button)findViewById(R.id.btnSignIn);
        tvRegister = (TextView)findViewById(R.id.tvRegister); //Set the instructions for tvRegister

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(EmployeeLoginActivity.this, EmployeeHomeActivity.class));
        }

        SignIn.setOnClickListener(new View.OnClickListener() { //set the instruction for the validate method
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmployeeRegistrationActivity();
            }
        });

    }
    public void openEmployeeRegistrationActivity(){
        Intent intent = new Intent(this,EmployeeRegistrationActivity.class);
        startActivity(intent);
    }

    private void validate(String userName, String userPassword){


        progressDialog.setMessage("Processing");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(EmployeeLoginActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EmployeeLoginActivity.this, EmployeeHomeActivity.class));
                }else {
                    Toast.makeText(EmployeeLoginActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}
