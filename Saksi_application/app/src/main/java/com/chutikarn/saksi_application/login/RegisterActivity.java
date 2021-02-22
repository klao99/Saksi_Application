//package com.chutikarn.saksi_application.login;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.chutikarn.saksi_application.R;
//import com.chutikarn.saksi_application.userRegister.LoginActivity2;
//import com.chutikarn.saksi_application.userRegister.UserRegisterActivity2;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    private EditText nameUser,email,passcode,phoneNumber;
//    private Button btnLogin, btnCreate;
//    private FirebaseAuth fAuth;
//    private DatabaseReference databaseReference;
//    //CheckBox radiAdmin,radiUser;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        fAuth = FirebaseAuth.getInstance();
//
//        nameUser = findViewById(R.id.nameUser);
//        email = findViewById(R.id.email);
//        passcode = findViewById(R.id.passcode);
//        phoneNumber = findViewById(R.id.phoneNumber);
//        btnLogin = findViewById(R.id.btnLogin);
//        btnCreate = findViewById(R.id.btnCreate);
//       // radiUser = findViewById(R.id.radiUser);
//       // radiAdmin = findViewById(R.id.radiAdmin);
//
//        initControl();
//    }
//
//    private void initControl() {
//        btnCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                email = email.getText().toString().trim();
//                passcode = passcode.getText().toString().trim();
//                nameUser = nameUser.getText().toString().trim();
//                ph =inputLastName.getText().toString().trim();
//
//                if (TextUtils.isEmpty(name)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกชื่อ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(lastName)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกนามสกุล", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกอีเมล", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกรหัสผ่าน", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (password.length() < 8) {
//                    Toast.makeText(getApplicationContext(), "รหัสผ่านต้องมากกว่า 8 ตัว", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                progressBar.setVisibility(View.VISIBLE);
//                auth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(UserRegisterActivity2.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    progressBar.setVisibility(View.GONE);
//                                    auth.signInWithEmailAndPassword(email, password);
//
//                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
//                                    databaseReference = mDatabase.child(auth.getCurrentUser().getUid());
//
//                                    databaseReference.child("name").setValue(name);
//                                    databaseReference.child("lastName").setValue(lastName);
//                                    databaseReference.child("email").setValue(email);
//                                    databaseReference.child("password").setValue(password);
//                                    databaseReference.child("userType").setValue("isUser");
//                                    // databaseReference.child("default").setValue("default");
//
//
//                                    // Intent i = new Intent(Page_firebase_register_4.this, LoginActivity.class);
//                                    Intent i = new Intent(UserRegisterActivity2.this, LoginActivity2.class);
//                                    startActivity(i);
//                                    finish();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//
//                Button sign_in_button = (Button) findViewById(R.id.sign_in_button);
//                sign_in_button.setOnClickListener(new View.OnClickListener() {
//
//                    public void onClick(View V) {
//
//                        Intent login = new Intent(UserRegisterActivity2.this, LoginActivity2.class);
//                        startActivity(login);
//                    }
//
//
//                });
//
//
//
//            }
//
//        });
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
//                //intent.putExtra("imageUrl",ImageUrl);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//}