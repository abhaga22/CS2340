package com.example.jongbeom.cs2340_m4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText gtAge = (EditText) findViewById(R.id.gtAge);
        final EditText gtName= (EditText) findViewById(R.id.gtName);
        final EditText gtUsernname = (EditText) findViewById(R.id.gtUsername);
        final EditText gtPassword = (EditText) findViewById(R.id.gtPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
    }
}
