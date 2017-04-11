package com.StrangerPings2340.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitSourceReportActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    //private EditText location;
    private Spinner waterCondition, waterType;

    private WaterSourceReport report;
    private User localUser;
    private LatLng cleanWaterPlace;
    private String cleanWaterAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_source_report);
        report = new WaterSourceReport();


        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        //location = (EditText) findViewById(R.id.location);
        Button submit = (Button) findViewById(R.id.submit);
        Button back = (Button) findViewById(R.id.back);
        localUser = getIntent().getParcelableExtra("LocalUser");
        waterType = (Spinner) findViewById(R.id.waterType);
        waterCondition = (Spinner) findViewById(R.id.waterCondition);



        String[] waterConditions = {"WASTE", "TREATABLE-CLEAR", "TREATABLE-MUDDY", "POTABLE"};

        ArrayAdapter<WaterTypes> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, WaterTypes.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterType.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, waterConditions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterCondition.setAdapter(adapter2);



        Log.d("localUser", localUser.toString());

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(SubmitSourceReportActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                cleanWaterPlace = place.getLatLng();
                cleanWaterAddress = place.getAddress().toString();

                Log.i("Places", "Place: " + place.getAddress());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Places", "An error occurred: " + status);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Changes not saved!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SubmitSourceReportActivity.this, MainActivity.class));
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String locationText = location.getText().toString();


                report.setLocation(cleanWaterPlace);
                report.setName(localUser.getEmail());
                report.setTimestamp(System.currentTimeMillis());
                report.setWaterType( (WaterTypes) waterType.getSelectedItem());
                report.setWaterCondition((String) waterCondition.getSelectedItem());
                report.setAddressString(cleanWaterAddress);


                dbRef.child("waterSourceReports").push().setValue(report);

                Toast.makeText(getApplicationContext(), "Report submitted!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SubmitSourceReportActivity.this, MainActivity.class));
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

