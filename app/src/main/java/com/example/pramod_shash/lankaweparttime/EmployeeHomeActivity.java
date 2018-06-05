package com.example.pramod_shash.lankaweparttime;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class EmployeeHomeActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    int toolbarColorValue = Color.parseColor("#576094");
    int toolbarTextColorValue = Color.parseColor("#FFFFFF");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);

        firebaseAuth = FirebaseAuth.getInstance();


        toolbar = (Toolbar)findViewById(R.id.toolbarEmployee);
        toolbar.setTitle("Lankawe Part-time");
        toolbar.setTitleTextColor(toolbarTextColorValue);
        toolbar.setBackgroundColor(toolbarColorValue);

        //Mike Penz material drawer

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Pramod Dissanayake").withEmail("pramoddissanayake27@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Jobs");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Accepted Jobs");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("My Profile");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Settings");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Log Out");

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1, item2,item3,item4,item5,item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (position){
                            case 1 : break;
                            case 2 : break;
                            case 3 : break;
                            case 4 : break;
                            case 5 : break;
                            case 6 : Logout();
                        }
                        return true;
                    }
                })
                .build();
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(EmployeeHomeActivity.this,EmployeeLoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // This part is not used
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
