package com.example.pramod_shash.lankaweparttime;

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

public class EmployeeRegistrationActivity extends AppCompatActivity {
    private EditText userName,password,confirmPassword,email,mobileNumber;
    private Button btnRegister;
    private TextView tvLogin;

    private FirebaseAuth firebaseAuth;   //Create an instance of Firebase authenticator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        setUpUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload the registration details to the database.
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(EmployeeRegistrationActivity.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
                                //Ok.. then if the registration is sucessful go back to the login page.
                                startActivity(new Intent(EmployeeRegistrationActivity.this,EmployeeLoginActivity.class));
                            }else{
                                Toast.makeText(EmployeeRegistrationActivity.this,"Registration Failed!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() { //set the back to previous activity
            @Override
            public void onClick(View view) {
                openEmployeeLoginActivity();
            }
        });
    }

    private void setUpUIViews(){

        userName = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etPassword);
        confirmPassword = (EditText)findViewById(R.id.etConfirmPassword);
        email = (EditText)findViewById(R.id.etEmail);
        mobileNumber = (EditText)findViewById(R.id.etMobileNumber);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
    }
    private Boolean validate(){
        Boolean result = false;
        String userName1 = userName.getText().toString();
        String password1 = password.getText().toString();
        String confirmPassword1 = confirmPassword.getText().toString();
        String email1 = email.getText().toString();
        String mobileNumber1 = mobileNumber.getText().toString();

        if (userName1.isEmpty() || password1.isEmpty() || confirmPassword1.isEmpty() || email1.isEmpty() || mobileNumber1.isEmpty()){
            Toast.makeText(this, "Please Enter All the Details!", Toast.LENGTH_SHORT).show();
        }else if( !password1.equals(confirmPassword1)){
            Toast.makeText(this,"Passwords are not matching!",Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    public void openEmployeeLoginActivity(){
        Intent intent = new Intent(this,EmployeeLoginActivity.class);
        startActivity(intent);
    }
}
