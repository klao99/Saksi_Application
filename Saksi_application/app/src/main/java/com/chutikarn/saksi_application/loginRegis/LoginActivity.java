package com.chutikarn.saksi_application.loginRegis;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.bottomNev.ProfileActivity;
import com.chutikarn.saksi_application.store.StoreProfileActivity2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;


@SuppressWarnings("ALL")
public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;
    private Button btnLogin,btnSignup,btn_regisStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btn_regisStore = (Button) findViewById(R.id.btn_regisStore);


        // progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
//                final String admin = "isAdmin";
//                final String user = "isUser";

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกอีเมล", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกรหัสผ่าน", Toast.LENGTH_SHORT).show();
                    return;
                }


                //progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // there was an error
                                    DatabaseReference DatabaseRefe = FirebaseDatabase.getInstance().getReference().child("Users");
                                    databaseReference = DatabaseRefe.child(auth.getCurrentUser().getUid());
                                    databaseReference.child("password").setValue(password);

                                    if (databaseReference.child("userType").equals("isStore")){
                                        Intent intent = new Intent(LoginActivity.this, StoreProfileActivity2.class);
                                        startActivity(intent);
                                        finish();
                                    }else if (databaseReference.child("userType").equals("isStore")){
                                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this, "Log in Not Successfully", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);

            }
        });

        btn_regisStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterStoreActivity.class);
                startActivity(intent);
            }
        });


    }//onCreate








}
