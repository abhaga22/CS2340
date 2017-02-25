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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {
    private static final String TAG = "EditProfileActivity";
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    private EditText email, address;
    private TextView  userType;

    private Button back, save;
    private User localUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();


        email = (EditText) findViewById(R.id.email);
        userType = (TextView) findViewById(R.id.userType);
        address = (EditText) findViewById(R.id.address);
        save = (Button) findViewById(R.id.save);
        back = (Button) findViewById(R.id.back);
        localUser = getIntent().getParcelableExtra("LocalUser");
        Log.d("localUser", localUser.toString());
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        userType.setText(localUser.getUserType().toString());
        address.setText(localUser.getAddress());
        email.setText(localUser.getEmail());


        /*
        Query userTypeQuery = dbRef.child("users").child(user.getUid());
        userTypeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                localUser = dataSnapshot.getValue(User.class);

                userType.setText(localUser.getUserType().toString());
                address.setText(localUser.getAddress());
                email.setText(localUser.getEmail());


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(EditProfileActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Changes not saved!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String addressText = address.getText().toString().trim();


                if (TextUtils.isEmpty(emailText)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(addressText)) {
                    Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.updateEmail(emailText)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User email address updated.");
                                }
                            }
                        });
                localUser.setAddress(addressText);
                localUser.setEmail(emailText);
                Log.d("localUser", localUser.toString());
                dbRef.child("users").child(user.getUid()).setValue(localUser);

                Toast.makeText(getApplicationContext(), "Changes saved!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
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
