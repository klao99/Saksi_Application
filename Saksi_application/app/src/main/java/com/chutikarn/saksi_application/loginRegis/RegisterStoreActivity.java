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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterStoreActivity extends AppCompatActivity {

    private EditText inputNameUser, inputEmail,inputPasscode,inputPhoneNumber;
    private Button btnCreate, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    private String email;
    private String password;
    private String name;
    private String number;
    private DatabaseReference databaseReference;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_register);

        auth = FirebaseAuth.getInstance();

        inputNameUser = findViewById(R.id.nameUser);
        inputEmail = findViewById(R.id.email);
        inputPasscode = findViewById(R.id.passcode);
        inputPhoneNumber = findViewById(R.id.phoneNumber);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreate);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterStoreActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = inputEmail.getText().toString().trim();
                password = inputPasscode.getText().toString().trim();
                name = inputNameUser.getText().toString().trim();
                number =inputPhoneNumber.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกชื่อ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกเบอร์", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกอีเมล", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกรหัสผ่าน", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "รหัสผ่านต้องมากกว่า 8 ตัว", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterStoreActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
//                                    progressBar.setVisibility(View.GONE);
                                    auth.signInWithEmailAndPassword(email, password);

                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                                    databaseReference = mDatabase.child(auth.getCurrentUser().getUid());

                                    databaseReference.child("name").setValue(name);
                                    databaseReference.child("number").setValue(number);
                                    databaseReference.child("email").setValue(email);
                                    databaseReference.child("password").setValue(password);
                                    databaseReference.child("userType").setValue("isStore");


                                    Intent i = new Intent(RegisterStoreActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


    }///OnCreate



}