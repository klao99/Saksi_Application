package com.example.saksi_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logIn extends AppCompatActivity {
    Button btnRegisStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        btnRegisStore=(Button)findViewById(R.id.btnRegisStore);
        btnRegisStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisStore();
            }
        });
    }

    public void RegisStore(){
        Intent regisStoreIntent = new Intent(logIn.this,MainActivity.class);
        startActivity(regisStoreIntent);
    }
}