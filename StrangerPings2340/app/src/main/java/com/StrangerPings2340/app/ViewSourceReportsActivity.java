package com.StrangerPings2340.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewSourceReportsActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_source_reports);



        auth = FirebaseAuth.getInstance();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        Button back = (Button) findViewById(R.id.back);

        final ArrayList<String> reports = new ArrayList<>();

        Query waterSources = dbRef.child("waterSourceReports").orderByChild("timestamp");
        waterSources.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WaterSourceReport w = new WaterSourceReport();
                    w.setReportNumber(i);
                    w.setWaterCondition((String) snapshot.child("waterCondition").getValue());
                    w.setWaterType(WaterTypes.stringToWaterType(
                                    (String) snapshot.child("waterType").getValue()));
                    w.setLocation(new LatLng( (double) snapshot.child("location").child(
                            "latitude").getValue(), (double) snapshot.child("location").child(
                            "longitude").getValue() ));
                    w.setName((String) snapshot.child("name").getValue());
                    w.setAddressString((String) snapshot.child("addressString").getValue());
                    w.setReportNumber(i);

                    reports.add(w.toString());
                    i++;
                }
                ListView waterReportList = (ListView) findViewById(R.id.waterReports);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewSourceReportsActivity.this, android.R.layout.simple_list_item_1, reports);

                waterReportList.setAdapter(adapter);

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
                    startActivity(new Intent(ViewSourceReportsActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewSourceReportsActivity.this, MainActivity.class));
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

