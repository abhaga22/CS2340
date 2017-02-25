package com.StrangerPings2340.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubmitReportActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    private EditText location;
    private Spinner waterCondition, waterType;

    private Button back, submit;
    private WaterSourceReport report;
    private User localUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);
        report = new WaterSourceReport();


        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        location = (EditText) findViewById(R.id.location);
        submit = (Button) findViewById(R.id.submit);
        back = (Button) findViewById(R.id.back);
        localUser = getIntent().getParcelableExtra("LocalUser");
        waterType = (Spinner) findViewById(R.id.waterType);
        waterCondition = (Spinner) findViewById(R.id.waterCondition);


        String[] waterTypes = {"Bottled", "Well", "Stream", "Lake", "Spring", "Other"};
        String[] waterConditions = {"Waste", "Treatable-Clear", "Treatable-Muddy", "Potable"};

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, waterTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterType.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, waterConditions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterCondition.setAdapter(adapter2);



        Log.d("localUser", localUser.toString());
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(SubmitReportActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Changes not saved!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SubmitReportActivity.this, MainActivity.class));
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = location.getText().toString();



                if (TextUtils.isEmpty(locationText)) {
                    Toast.makeText(getApplicationContext(), "Enter location!", Toast.LENGTH_SHORT).show();
                    return;
                }

                report.setLocation(locationText);
                report.setName(localUser.getEmail());
                report.setTimestamp(System.currentTimeMillis());
                report.setWaterType( (String) waterType.getSelectedItem());
                report.setWaterCondition((String) waterCondition.getSelectedItem());


                dbRef.child("waterSourceReports").push().setValue(report);

                Toast.makeText(getApplicationContext(), "Report submitted!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SubmitReportActivity.this, MainActivity.class));
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

