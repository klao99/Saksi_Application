package com.example.saksi_test1.RegisStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.saksi_test1.MyApplication;
import com.example.saksi_test1.R;
import com.example.saksi_test1.model.StoreDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisStore4 extends AppCompatActivity {

    EditText storeName,storeLocation;
    Button btnbankImg,btnSave;
    ImageView bankImg;

    DatabaseReference reff;
    StoreDataClass storeData;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_store4);

        Log.d("ชื่อ", MyApplication.storeData.getRealName());
        Log.d("นามสกุล",MyApplication.storeData.getLastName());
        Log.d("วันเกิด",MyApplication.storeData.getBirthday());
        Log.d("อีเมล",MyApplication.storeData.getEmail());
        Log.d("เบอร์",MyApplication.storeData.getPhoneNumber());
        Log.d("ชื่อบช",MyApplication.storeData.getAccountNumber());
        Log.d("เลขบัญชี",MyApplication.storeData.getBankName());
        Log.d("ประเภท",MyApplication.storeData.getTypeBank());


        storeName=findViewById(R.id.storeName);
        storeLocation=findViewById(R.id.storeLocation);
        btnSave=findViewById(R.id.btnSave);
        storeData= new StoreDataClass();
        reff= FirebaseDatabase.getInstance().getReference().child("Store");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyApplication.storeData.setRealName(storeName.getText().toString().trim());
                MyApplication.storeData.setLastName(storeLocation.getText().toString().trim());

                reff.child(String.valueOf(maxid+1)).setValue(storeData);
                Toast.makeText(RegisStore4.this,"data inserted Success",Toast.LENGTH_LONG).show();

            }
        });



    }
}