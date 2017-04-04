package com.StrangerPings2340.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private Button viewPurityReports;
    private Button submitPurityReport;
    private Button viewChart;
    private User localUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        Button signOut = (Button) findViewById(R.id.signOut);
        Button editProfile = (Button) findViewById(R.id.editProfile);
        Button viewSourceReports = (Button) findViewById(R.id.viewSourceReports);
        viewPurityReports = (Button) findViewById(R.id.viewPurityReports);
        Button submitSourceReport = (Button) findViewById(R.id.submitSourceReport);
        submitPurityReport = (Button) findViewById(R.id.submitPurityReport);
        viewChart = (Button) findViewById(R.id.viewChart);

        Button viewMap = (Button) findViewById(R.id.viewMap);

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        @SuppressWarnings("ConstantConditions")
        Query userTypeQuery = dbRef.child("users").child(user.getUid());
        userTypeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                localUser = dataSnapshot.getValue(User.class);
                if (!localUser.getUserType().equals(UserType.USER)){
                    submitPurityReport.setVisibility(View.VISIBLE);
                }

                if (localUser.getUserType().equals(UserType.MANAGER) ||
                        localUser.getUserType().equals(UserType.ADMIN)) {
                    viewChart.setVisibility(View.VISIBLE);
                    viewPurityReports.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, EditProfileActivity.class).putExtra(
                        "LocalUser", localUser));

                finish();
            }
        });

        viewSourceReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ViewSourceReportsActivity.class));

                finish();
            }
        });

        viewPurityReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ViewPurityReportsActivity.class));

                finish();
            }
        });

        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ViewSourceReportMap.class));

                finish();
            }
        });



        submitSourceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SubmitSourceReportActivity.class).putExtra(
                        "LocalUser", localUser));

                finish();
            }
        });

        submitPurityReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SubmitPurityReportActivity.class).putExtra(
                        "LocalUser", localUser));

                finish();
            }
        });

        viewChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ViewChartActivity.class));

                finish();
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
