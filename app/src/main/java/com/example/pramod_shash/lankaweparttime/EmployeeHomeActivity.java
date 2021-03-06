package com.example.pramod_shash.lankaweparttime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

import java.util.ArrayList;
import java.util.List;

public class EmployeeHomeActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    int toolbarColorValue = Color.parseColor("#576094");
    int toolbarTextColorValue = Color.parseColor("#FFFFFF");

    //private ListView jobList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<JobCreatingWithQualifications> listItems;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //private ArrayList<String> arrayList;
    //private ArrayAdapter<String> adapter;
    JobCreatingWithQualifications jobCreatingWithQualifications;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Jobs");
        progressDialog.show();

        firebaseAuth = FirebaseAuth.getInstance();
        jobCreatingWithQualifications = new JobCreatingWithQualifications();

        recyclerView = findViewById(R.id.rvJobList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("jobs/withQualifications");

        adapter = new JobShowAdapter(listItems,this);
        //adapter = new ArrayAdapter<String>(this, R.layout.job_info, R.id.jobInfo, arrayList);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                        jobCreatingWithQualifications = ds.getValue(JobCreatingWithQualifications.class);
                        listItems.add(jobCreatingWithQualifications);
                }

                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
                //jobList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
                            case 4 : startActivity(new Intent(EmployeeHomeActivity.this,EmployeeMyProfile.class));
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
