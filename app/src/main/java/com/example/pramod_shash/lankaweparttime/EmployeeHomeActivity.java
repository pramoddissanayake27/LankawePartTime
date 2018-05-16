package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeHomeActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);

        firebaseAuth = FirebaseAuth.getInstance();
        logOut = (Button)findViewById(R.id.btnLogOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Logout();
            }
        });
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(EmployeeHomeActivity.this,EmployeeLoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rightmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
