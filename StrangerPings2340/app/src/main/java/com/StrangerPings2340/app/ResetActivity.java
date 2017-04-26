package com.StrangerPings2340.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {
    private EditText inputEmail;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        Button back = (Button) findViewById(R.id.back);
        Button reset = (Button) findViewById(R.id.reset_button);
        inputEmail = (EditText) findViewById(R.id.email);
        auth = FirebaseAuth.getInstance();
        String emailAddress = "user@example.com";



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                }
                            }
                        });
                Toast.makeText(getApplicationContext(), "Sent Recovery Email!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResetActivity.this, LoginActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetActivity.this, LoginActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                finish();
            }
        });

    }




}
