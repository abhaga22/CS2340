package com.StrangerPings2340.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewChartActivity extends AppCompatActivity {

    private BarChart barChart;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    private Spinner placeSpinner, yearSpinner, dataSpinner;
    private ArrayList<String> placeArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chart);

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        placeArray = new ArrayList<>();
        placeSpinner = (Spinner) findViewById(R.id.locSpinner);
        Button back = (Button) findViewById(R.id.back);

        ArrayList<String> years = new ArrayList<>();

        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2016; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, years);
        yearSpinner = (Spinner)findViewById(R.id.yearSpinner);
        yearSpinner.setAdapter(yearAdapter);


        ArrayList<String> data = new ArrayList<>();
        data.add("Virus");
        data.add("Contaminant");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, data);
        dataSpinner = (Spinner)findViewById(R.id.dataSpinner);
        dataSpinner.setAdapter(dataAdapter);



        Query waterSources = dbRef.child("waterPurityReportsLocations").orderByKey();
        waterSources.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()) {
                    placeArray.add(snap.getKey());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewChartActivity.this, android.R.layout.simple_dropdown_item_1line, placeArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                placeSpinner.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        barChart = (BarChart) findViewById(R.id.barChart);

        IAxisValueFormatter MyXAxisValueFormatter  = new IAxisValueFormatter() {

            final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value];
            }
        };

        Description none = new Description();
        none.setText("");

        XAxis xAxis2 = barChart.getXAxis();
        YAxis leftAxis2 = barChart.getAxisLeft();
        YAxis rightAxis2 = barChart.getAxisRight();
        leftAxis2.setAxisMinimum(0);
        rightAxis2.setAxisMinimum(0);
        xAxis2.setValueFormatter(MyXAxisValueFormatter);
        xAxis2.setAxisMaximum(11);
        xAxis2.setAxisMinimum(0);
        xAxis2.setGranularityEnabled(true);
        xAxis2.setGranularity(1f);
        barChart.setDescription(none);


        AdapterView.OnItemSelectedListener select = new AdapterView.OnItemSelectedListener() {
        @Override
            public void onItemSelected(final AdapterView<?> parent, View view, int position, long id) {


                barChart.clear();

                Query reports = dbRef.child("waterPurityReports").orderByChild("timestamp");
                reports.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int yearVal = Integer.parseInt(yearSpinner.getSelectedItem().toString());
                        String locVal = placeSpinner.getSelectedItem().toString();
                        String dataVal = dataSpinner.getSelectedItem().toString();

                        ArrayList<BarEntry> virusEntries = new ArrayList<>();
                        ArrayList<BarEntry> contEntries = new ArrayList<>();

                        Calendar start = Calendar.getInstance();
                        start.set(yearVal, 0, 1, 0, 0, 0);
                        Calendar end = Calendar.getInstance();
                        end.set(yearVal + 1, 0, 1, 0, 0, 0);
                        int[] monthsVirus = new int[12];
                        int[] monthsContaminant = new int[12];
                        int[] reportsPerMonth = new int[12];

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {


                            String addressString = (String) snapshot.child("addressString").getValue();
                            long contaminant = (Long) snapshot.child("contaminantPPM").getValue();
                            long virus = (Long) snapshot.child("virusPPM").getValue();
                            long timestamp = (Long) snapshot.child("timestamp").getValue();

                            if (addressString.equals(locVal) && timestamp >= start.getTimeInMillis()
                                    && timestamp < end.getTimeInMillis()){

                                Calendar cur = Calendar.getInstance();
                                cur.setTimeInMillis(timestamp);
                                int a = cur.get(Calendar.MONTH);
                                monthsVirus[a] += virus;
                                monthsContaminant[a] += contaminant;
                                reportsPerMonth[a] += 1;
                            }

                        }
                        for (int i = 0; i < reportsPerMonth.length; i++) {
                            if (reportsPerMonth[i] != 0) {
                                double avgVirus = (monthsVirus[i] * 1.0)/reportsPerMonth[i];
                                double avgCont = (monthsContaminant[i] * 1.0)/reportsPerMonth[i];
                                virusEntries.add(new BarEntry(i, (float) avgVirus));
                                contEntries.add(new BarEntry(i, (float) avgCont));
                            }
                        }
                        if (dataVal.equals("Virus") && !virusEntries.isEmpty()) {

                            BarDataSet barSet = new BarDataSet(virusEntries, "Virus PPM");
                            BarData barData = new BarData(barSet);
                            barSet.setColor(ColorTemplate.rgb("71ACD6"));
                            barChart.setData(barData);
                            barChart.animateY(2000);

                        } else if (dataVal.equals("Contaminant") && !contEntries.isEmpty()){

                            BarDataSet barSet = new BarDataSet(contEntries, "Contaminant PPM");
                            BarData barData = new BarData(barSet);
                            barSet.setColor(ColorTemplate.rgb("71ACD6"));
                            barChart.setData(barData);
                            barChart.animateY(2000);

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        };

        yearSpinner.setOnItemSelectedListener(select);
        placeSpinner.setOnItemSelectedListener(select);
        dataSpinner.setOnItemSelectedListener(select);


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(ViewChartActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    finish();
                }
            }
        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewChartActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
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
