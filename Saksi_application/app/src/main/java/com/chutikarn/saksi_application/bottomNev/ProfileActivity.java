package com.chutikarn.saksi_application.bottomNev;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chutikarn.saksi_application.loginRegis.LoginActivity;
import com.chutikarn.saksi_application.request.MyRequest;
import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.model.Users;
import com.chutikarn.saksi_application.slide.SlideStoreActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    TextView btnLogout;

    String profile,userID;
    String name;

    ImageView imgPro;
    TextView textNameProfile,btnNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        btnLogout = findViewById(R.id.btnLogout);
        textNameProfile = findViewById(R.id.textNameProfile);
        imgPro = findViewById(R.id.imgPro);
        btnNoti = findViewById(R.id.btnNoti);

        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                Intent intent=new Intent(ProfileActivity.this, MyRequest.class);
                userID = firebaseUser.getUid();
                startActivity(intent);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });


        //BottomNav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.store:
                        startActivity(new Intent(getApplicationContext(), SlideStoreActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }


        });

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            userID = user.getUid();
            databaseReference.child("Users/"+ userID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    showData(snapshot);
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }else {
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        }



    }//onCReate

    private  void showData(DataSnapshot snapshot) {

        Users uInfo = snapshot.getValue(Users.class);
        textNameProfile.setText(uInfo.getName());
        Picasso.get().load(uInfo.getProfile()).into(imgPro);

    }
}//Appcompat