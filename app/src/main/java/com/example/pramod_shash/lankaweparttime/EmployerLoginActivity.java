package com.example.pramod_shash.lankaweparttime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EmployerLoginActivity extends AppCompatActivity {
    private EditText Name, Password;
    private Button SignIn;
    private TextView tvRegister2;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_login);

        Name = (EditText)findViewById(R.id.etUserName2);
        Password = (EditText)findViewById(R.id.etPassword2);
        SignIn = (Button)findViewById(R.id.btnSignIn2);
        tvRegister2 = (TextView)findViewById(R.id.tvRegister2); //Set the instructions for tvRegister

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(EmployerLoginActivity.this, EmployerHomeActivity.class));
        }

        tvRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmployerRegistrationActivity();
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });
    }
    public void openEmployerRegistrationActivity(){
        Intent intent = new Intent(EmployerLoginActivity.this,EmployerRegistrationActivity.class);
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
                    Toast.makeText(EmployerLoginActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EmployerLoginActivity.this, EmployeeHomeActivity.class));
                }else {
                    Toast.makeText(EmployerLoginActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}
