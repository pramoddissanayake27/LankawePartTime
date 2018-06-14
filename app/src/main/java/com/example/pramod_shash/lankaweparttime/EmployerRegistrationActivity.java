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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployerRegistrationActivity extends AppCompatActivity {

    private EditText companyName, email, password, confirmPassword;
    private Button btnRegister2;
    private TextView tvLogin2;
    String companyName1, password2, confirmPassword2, email2;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_registration);

        setUpUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //upload the data to authenticating db..
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserData();
                                Toast.makeText(EmployerRegistrationActivity.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
                                //Ok.. then if the registration is successful go back to the login page.
                                startActivity(new Intent(EmployerRegistrationActivity.this,EmployeeLoginActivity.class));
                            }else{
                                Toast.makeText(EmployerRegistrationActivity.this,"Registration Failed!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        tvLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployerRegistrationActivity.this,EmployerLoginActivity.class));
            }
        });
    }

    private void setUpUIViews(){

        companyName = (EditText)findViewById(R.id.etCompanyName);
        password = (EditText)findViewById(R.id.etPassword2);
        confirmPassword = (EditText)findViewById(R.id.etConfirmPassword2);
        email = (EditText)findViewById(R.id.etEmail2);
        btnRegister2 = (Button)findViewById(R.id.btnRegister2);
        tvLogin2 = (TextView)findViewById(R.id.tvLogin2);
    }

    private Boolean validate(){

        Boolean result = false;
        companyName1 = companyName.getText().toString();
        password2 = password.getText().toString();
        confirmPassword2 = confirmPassword.getText().toString();
        email2 = email.getText().toString();
        if (companyName1.isEmpty() || password2.isEmpty() || confirmPassword2.isEmpty() || email2.isEmpty()){
            Toast.makeText(this, "Please Enter All the Details!", Toast.LENGTH_SHORT).show();
        }else if( !password2.equals(confirmPassword2)){
            Toast.makeText(this,"Passwords are not matching!",Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("users/Employers"); //firebaseauth.getUid
        EmployerUserProfile employerUserProfile = new EmployerUserProfile(companyName1, email2);
        myRef.push().setValue(employerUserProfile);
    }
}
