package com.chutikarn.saksi_application.bottomNev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.chutikarn.saksi_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Store_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        //BottomNav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.store);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home_page.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.store:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profile_page.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}//Appcompat