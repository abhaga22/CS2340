package com.example.jongbeom.cs2340_m4;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.jongbeom.cs2340_m4.R.id.gtAge;
import static com.example.jongbeom.cs2340_m4.R.id.gtLogin;
import static com.example.jongbeom.cs2340_m4.R.id.gtName;
import static com.example.jongbeom.cs2340_m4.R.id.gtPassword;
import static com.example.jongbeom.cs2340_m4.R.id.gtUsername;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText gtAge = (EditText) findViewById(R.id.gtAge);
        final EditText gtName= (EditText) findViewById(R.id.gtName);
        final EditText gtUsername = (EditText) findViewById(R.id.gtUsername);
        final EditText gtPassword = (EditText) findViewById(R.id.gtPassword);
        final Button gtRegister = (Button) findViewById(R.id.gtRegister);

        gtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = gtName.getText().toString();
                final String username = gtUsername.getText().toString();
                final String password = gtPassword.getText().toString();
                final int age = Integer.parseInt(gtAge.getText().toString());


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse =  new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                 Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                 RegisterActivity.this.startActivity((intent));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}


