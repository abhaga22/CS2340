package com.StrangerPings2340.app;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.firebase.database.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewSourceReportMap extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private Button back;
    private ArrayList<WaterSourceReport> reports;
    private DatabaseReference dbRef;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private GoogleApiClient mGoogleApiClient;
    private Location lastLocation;

    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView title = ((TextView)myContentsView.findViewById(R.id.title));
            title.setText(marker.getTitle());
            //TextView snippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            //snippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_source_report_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        back = (Button) findViewById(R.id.back);

        auth = FirebaseAuth.getInstance();

        dbRef = FirebaseDatabase.getInstance().getReference();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewSourceReportMap.this, MainActivity.class));
                finish();
            }
        });

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(ViewSourceReportMap.this, LoginActivity.class));
                    finish();
                }
            }
        };

        mapFragment.getMapAsync(this);
    }



    @Override
    public void onStart() {

        auth.addAuthStateListener(authListener);
        super.onStart();
    }

    @Override
    public void onStop() {

        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
        super.onStop();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

        reports = new ArrayList<>();

        Query waterSources = dbRef.child("waterSourceReports").orderByChild("timestamp");
        waterSources.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WaterSourceReport w = new WaterSourceReport();
                    w.setReportNumber(i);
                    w.setWaterCondition((String) snapshot.child("waterCondition").getValue());
                    w.setWaterType((String) snapshot.child("waterType").getValue());
                    w.setLocation(new LatLng( (double) snapshot.child("location").child(
                            "latitude").getValue(), (double) snapshot.child("location").child(
                            "longitude").getValue() ));
                    w.setName((String) snapshot.child("name").getValue());
                    w.setAddressString((String) snapshot.child("addressString").getValue());
                    w.setReportNumber(i);

                    Log.d("waterSource", w.getLocation().toString());
                    reports.add(w);

                    mMap.addMarker(new MarkerOptions().position(w.getLocation()).title(w.toString()));

                    i++;

                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
