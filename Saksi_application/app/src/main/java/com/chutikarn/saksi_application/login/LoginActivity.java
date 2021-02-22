package com.chutikarn.saksi_application.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.Store.StoreProfileActivity2;
import com.chutikarn.saksi_application.UserProfileActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText email,passcode;
    Button btnLogin;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();

        email = findViewById(R.id.email);
        passcode = findViewById(R.id.passcode);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkField(email);
                checkField(passcode);
                //Log.d("TAG","onClick:" + )


                if(valid){
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),passcode.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }

        });
    } //onCreate

    private void checkUserAccessLevel(String uid) {
        DocumentReference df =fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess : " + documentSnapshot.getData());

                if(documentSnapshot.getString("isAdim") != null){
                    startActivity(new Intent(getApplicationContext(), StoreProfileActivity2.class));
                    finish();
                }
                if(documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                    finish();
                }
            }
        });
    }
    public boolean checkField(EditText textField) {
        if(textField.getText().toString().isEmpty()){
            textField.setError("Eorror");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}