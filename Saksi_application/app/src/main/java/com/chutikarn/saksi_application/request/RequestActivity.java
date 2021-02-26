package com.chutikarn.saksi_application.request;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.model.Request;
import com.chutikarn.saksi_application.model.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.HashMap;

public class RequestActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    private EditText theDate,inPutposition,inPutdetail;
    private RadioButton size1,size2,size3,colorful,blackWhite;
    private ImageView selectImgTattoo;
    private Button btnSentRequest;
    private TextView inPutidStore,inPutidUser;

    Request request;
    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storageRef;

    Uri imageUri;
    boolean isImageAdded = false;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    private String storeId;
    private String userId;

    private String dateTime;
    private String position;
    private String detail;
    private String size;
    private String color;
    private String imageUrlT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        initViewData();
        initUserId();
        initControl();


        storeId = getIntent().getStringExtra("storeId");
        inPutidStore.setText("" + storeId);

        request = new Request();
        storageRef = FirebaseStorage.getInstance().getReference().child("RequestPic");
        reference = database.getInstance().getReference().child("Request");

    }//onCreate

    private void initUserId() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        databaseReference.child("Users/"+ userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                showData(snapshot);
            }

            private void showData(DataSnapshot snapshot) {
                Users uInfo = snapshot.getValue(Users.class);
                inPutidUser.setText(uInfo.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void initViewData() {
        theDate = findViewById(R.id.selectDate);
        inPutposition = findViewById(R.id.position);
        inPutdetail = findViewById(R.id.detail);
        selectImgTattoo = findViewById(R.id.selectImgTattoo);
        btnSentRequest = findViewById(R.id.btnSentRequest);
        size1 = findViewById(R.id.size1);
        size2 = findViewById(R.id.size2);
        size3 = findViewById(R.id.size3);
        colorful = findViewById(R.id.colorful);
        blackWhite = findViewById(R.id.blackWhite);

        inPutidStore = findViewById(R.id.idStore);
        inPutidUser = findViewById(R.id.idUser);
    }

    private void initControl() {
        theDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);


            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth +"/"+ month + "/" + year;
                        theDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        /////เพิ่มรูป
        selectImgTattoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });

        ////ลงข้อมูล
        btnSentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                String s1 = size1.getText().toString();
                String s2 = size2.getText().toString();
                String s3 = size3.getText().toString();
                String c1 = colorful.getText().toString();
                String c2 = blackWhite.getText().toString();

                if (colorful.isChecked()){
                    color = c1;
                    reference.setValue(color);
                }else {
                    color = c2;
                    reference.setValue(color);
                }

                dateTime = theDate.getText().toString().trim();
                position = inPutposition.getText().toString().trim();
                detail =inPutdetail.getText().toString().trim();
                storeId =inPutidStore.getText().toString().trim();
                userId =inPutidUser.getText().toString().trim();

                if (size1.isChecked()){
                    size = s1;
                    reference.setValue(size);
                }else if (size2.isChecked()){
                    size = s2;
                    reference.setValue(size);
                }else {
                    size = s3;
                    reference.setValue(size);
                }

                Intent intent=new Intent(RequestActivity.this, BillRequestActivity.class);
                intent.putExtra("dateTime",dateTime);
                intent.putExtra("position",position);
                intent.putExtra("detail",detail);
                intent.putExtra("storeId",storeId);
                intent.putExtra("userId",userId);
                intent.putExtra("size",size);
                intent.putExtra("color",color);
                    startActivity(intent);

            }

        });
    }///init

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_IMAGE && data!=null)
        {
            imageUri=data.getData();
            isImageAdded = true;
            selectImgTattoo.setImageURI(imageUri);
        }
    }


    private void uploadImage() {
        final String key = reference.push().getKey();
        storageRef.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap=new HashMap();
                        hashMap.put("detail",detail);
                        hashMap.put("position",position);
                        hashMap.put("dateTime",dateTime);
                        hashMap.put("size",size);
                        hashMap.put("color",color);
                        hashMap.put("storeId",storeId);
                        hashMap.put("userId",userId);
                        hashMap.put("imageUrl",uri.toString());

                        reference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RequestActivity.this,"Data success",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });
    }


}