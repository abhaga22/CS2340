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

public class ViewPurityReportsActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purity_reports);



        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();

        back = (Button) findViewById(R.id.back);

        final ArrayList<String> reports = new ArrayList<>();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        Query waterSources = dbRef.child("waterPurityReports").orderByChild("timestamp");
        waterSources.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WaterPurityReport w = new WaterPurityReport();
                    w.setReportNumber(i);
                    w.setWaterCondition((String) snapshot.child("waterCondition").getValue());
                    w.setLocation(new LatLng( (double) snapshot.child("location").child(
                            "latitude").getValue(), (double) snapshot.child("location").child(
                            "longitude").getValue() ));
                    w.setName((String) snapshot.child("name").getValue());
                    w.setAddressString((String) snapshot.child("addressString").getValue());
                    Long cont = (Long) snapshot.child("contaminantPPM").getValue();
                    w.setContaminantPPM(cont.intValue());
                    Long vir = (Long) snapshot.child("virusPPM").getValue();
                    w.setVirusPPM(vir.intValue());
                    w.setReportNumber(i);

                    reports.add(w.toString());
                    i++;
                }
                ListView waterReportList = (ListView) findViewById(R.id.waterReports);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewPurityReportsActivity.this, android.R.layout.simple_list_item_1, reports);

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
                    startActivity(new Intent(ViewPurityReportsActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewPurityReportsActivity.this, MainActivity.class));
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

