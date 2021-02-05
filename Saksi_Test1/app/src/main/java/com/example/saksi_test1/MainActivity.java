package com.example.saksi_test1;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saksi_test1.RegisStore.Profile_store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText Rname,Lname,email,Bdate,phoneNum;
    Button btnNext1;

    DatabaseReference reff;
    Store store;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(MainActivity.this,"Firebase connection Success",Toast.LENGTH_LONG).show();

        Rname=(EditText)findViewById(R.id.Rname);
        Lname=(EditText)findViewById(R.id.Lname);
        email=(EditText)findViewById(R.id.email);
        Bdate=(EditText)findViewById(R.id.Bdate);
        phoneNum=(EditText)findViewById(R.id.phoneNum);
        btnNext1=(Button)findViewById(R.id.btnNext1);
        store= new Store();
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

        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                store.setRealName(Rname.getText().toString().trim());
                store.setLastName(Lname.getText().toString().trim());
                store.setBirthday(Bdate.getText().toString().trim());
                store.setEmail(email.getText().toString().trim());
                store.setPhoneNumber(phoneNum.getText().toString().trim());

//              MyApplication.storeData.setRealName(Rname.getText().toString().trim());
//              MyApplication.storeData.setLastName(Lname.getText().toString().trim());
//              MyApplication.storeData.setBirthday(Bdate.getText().toString().trim());
//              MyApplication.storeData.setEmail(email.getText().toString().trim());
//              MyApplication.storeData.setPhoneNumber(phoneNum.getText().toString().trim());



                RegisStore1();
//                Long birth=Long.parseLong(Bdate.getText().toString().trim());
//                Long phone=Long.parseLong(phoneNum.getText().toString().trim());
//                store.setfName(Fname.getText().toString().trim());
//                store.setlName(Lname.getText().toString().trim());
//                store.setEmail(email.getText().toString().trim());
//                store.setbDate(birth);
//                store.setPhone(phone);
                reff.child(String.valueOf(maxid+1)).setValue(store);
                Toast.makeText(MainActivity.this,"data inserted Success",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void RegisStore1(){
//        Intent regisStore2Intent = new Intent(MainActivity.this,RegisStore2.class);
        Intent regisStore2Intent = new Intent(MainActivity.this, Profile_store.class);
//        regisStore2Intent.putExtra("name",Rname.getText().toString());
//        regisStore2Intent.putExtra("lastname",Lname.getText().toString());
//        regisStore2Intent.putExtra("birthday",Bdate.getText().toString());
//        regisStore2Intent.putExtra("email",email.getText().toString());
//        regisStore2Intent.putExtra("phoneNumber ",phoneNum.getText().toString());
        startActivity(regisStore2Intent);
    }
}