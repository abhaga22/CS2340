package com.example.jongbeom.cs2340_m4;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.jongbeom.cs2340_m4.R.id.gtPassword;
import static com.example.jongbeom.cs2340_m4.R.id.gtUsername;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText gtUsername = (EditText) findViewById(R.id.gtUsername);
        final EditText gtPassword = (EditText) findViewById(R.id.gtPassword);
        final Button gtLogin = (Button) findViewById(R.id.gtLogin);
        final TextView registerLink = (TextView) findViewById(R.id.gtRegisterHere);
        final Button cancel = (Button) findViewById(R.id.cancel);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(LoginActivity.this, EndScreen.class);
                LoginActivity.this.startActivity(cancelIntent);
            }
        });

        gtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = gtUsername.getText().toString();
                final String password = gtPassword.getText().toString();

                final Response.Listener<String> responseListerner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse =  new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("age", age);

                                LoginActivity.this.startActivity((intent));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            }catch (JSONException e) {
                                e.printStackTrace();
                        }
                    }
                };
                    LoginRequest loginRequest = new LoginRequest(username, password, responseListerner);
            }
        });
    }
}