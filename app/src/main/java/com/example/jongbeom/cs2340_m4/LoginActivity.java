package com.example.jongbeom.cs2340_m4;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.password;
import static com.example.jongbeom.cs2340_m4.R.id.gtLogin;
import static com.example.jongbeom.cs2340_m4.R.id.gtPassword;
import static com.example.jongbeom.cs2340_m4.R.id.gtUsername;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.gtUsername);
        final EditText password = (EditText) findViewById(R.id.gtPassword);
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
                // valid password and username
                if (!validUsername(username.getText().toString())) {
                    username.setError("Sorry! Invalid Username!");
                    username.requestFocus();
                } else if (!validPassword(password.getText().toString())) {
                    password.setError("Sorry! Invalid Password!");
                    password.requestFocus();
                } else {
                    Toast.makeText(LoginActivity.this, "Input Valid Success", Toast.LENGTH_LONG).show();

                    Intent application = new Intent(LoginActivity.this, Application.class);
                    LoginActivity.this.startActivity(application);
                }
            }

        });
    }
    // valid username or email
    protected boolean validUsername(String username) {
        //String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        //Pattern pattern = Pattern.compile(emailPattern);
        //Matcher matcher = pattern.matcher(username);
        if (username.equals("test")) {
            return true;
        }
        //return matcher.matches();
        return false;
    }

    //valid password
    protected boolean validPassword(String password) {
        //if (password != null && password.length() >9) {
        if (password.equals("password")) {
            return true;
        } else {
            return false;
        }
    }
}


/*

    gtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String aUsername = username.getText().toString();
                final String bPassword = password.getText().toString();

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
                                intent.putExtra("username", aUsername);
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
                    LoginRequest loginRequest = new LoginRequest(aUsername, bPassword, responseListerner);
            }
        });

        }
        */