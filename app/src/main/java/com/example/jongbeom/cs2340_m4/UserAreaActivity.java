package com.example.jongbeom.cs2340_m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.jongbeom.cs2340_m4.R.id.gtAge;
import static com.example.jongbeom.cs2340_m4.R.id.gtUsername;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText gtUsernname = (EditText) findViewById(gtUsername);
        final EditText gtAge = (EditText) findViewById(R.id.gtAge);
        final TextView welcomeMessage = (TextView) findViewById(R.id.gtWelcomeMsg);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        String message = name + "welcome to your user area";
        welcomeMessage.setText(message);
        gtUsernname.setText(username);
        gtAge.setText(age+"");

    }
}
