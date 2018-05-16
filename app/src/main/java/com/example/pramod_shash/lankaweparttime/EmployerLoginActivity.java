package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmployerLoginActivity extends AppCompatActivity {
    private EditText Name, Password;
    private Button SignIn;
    private TextView tvRegister2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_login);

        Name = (EditText)findViewById(R.id.etUserName2);
        Password = (EditText)findViewById(R.id.etPassword2);
        SignIn = (Button)findViewById(R.id.btnSignIn2);
        tvRegister2 = (TextView)findViewById(R.id.tvRegister2); //Set the instructions for tvRegister

        tvRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmployerRegistrationActivity();
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateEmployer(Name.getText().toString(),Password.getText().toString());
            }
        });
    }
    public void openEmployerRegistrationActivity(){
        Intent intent = new Intent(this,EmployerRegistrationActivity.class);
        startActivity(intent);
    }
    public void ValidateEmployer(String userName2, String userPassword2){
        if((userName2.equals("toks")) && (userPassword2.equals("123"))){
            Intent intent = new Intent(EmployerLoginActivity.this,EmployeeHomeActivity.class);
            startActivity(intent);
        }

    }
}
