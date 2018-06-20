package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeRegistrationActivity extends AppCompatActivity {
    private static final String TAG = "EmployeeRegistrationActivity";
    private EditText userName,password,confirmPassword,email,mobileNumber;
    private Button btnRegister;
    private TextView tvLogin;
    String userName1,password1,confirmPassword1,email1,mobileNumber1;
    AwesomeValidation awesomeValidation;

    private FirebaseAuth firebaseAuth;   //Create an instance of Firebase authenticator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);
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
                                sendUserData();
                                Toast.makeText(EmployeeRegistrationActivity.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
                                firebaseAuth.signOut();
                                finish();
                                //Ok.. then if the registration is successful go back to the login page.
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

        userName1 = userName.getText().toString();
        password1 = password.getText().toString();
        confirmPassword1 = confirmPassword.getText().toString();
        email1 = email.getText().toString();
        mobileNumber1 = mobileNumber.getText().toString();

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(EmployeeRegistrationActivity.this,R.id.etUserName,"[a-zA-Z\\s]+",R.string.app_name);
        awesomeValidation.addValidation(EmployeeRegistrationActivity.this,R.id.etEmail, Patterns.EMAIL_ADDRESS,R.string.User_mailVal);
        awesomeValidation.addValidation(EmployeeRegistrationActivity.this,R.id.etMobileNumber, RegexTemplate.TELEPHONE,R.string.User_phonenbrVal);
        awesomeValidation.addValidation(EmployeeRegistrationActivity.this,R.id.etPassword,regexPassword,R.string.User_passwordVal);
        awesomeValidation.addValidation(EmployeeRegistrationActivity.this,R.id.etConfirmPassword,R.id.etPassword,R.string.User_ConfirmationpassVal);

        if (awesomeValidation.validate()){

            Toast.makeText(this, "Data receiverd Successfully", Toast.LENGTH_SHORT).show();
            result = true;

            } else{
                 Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    public void openEmployeeLoginActivity(){
        Intent intent = new Intent(this,EmployeeLoginActivity.class);
        startActivity(intent);
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("users/Employees"); //firebaseauth.getUid
        EmployeeUserProfile employeeUserProfile = new EmployeeUserProfile(userName1,email1,mobileNumber1);
        myRef.push().setValue(employeeUserProfile);
    }
}
