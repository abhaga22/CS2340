package com.example.jongbeom.cs2340_m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Application extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        final Button alogout = (Button) findViewById(R.id.logout);

        alogout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cancelIntent = new Intent(Application.this, OpenScreen.class);
            Application.this.startActivity(cancelIntent);
        }
    });
    }
}
