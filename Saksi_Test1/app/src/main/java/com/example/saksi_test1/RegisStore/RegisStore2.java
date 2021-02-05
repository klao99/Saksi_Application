package com.example.saksi_test1.RegisStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.saksi_test1.MyApplication;
import com.example.saksi_test1.R;
import com.example.saksi_test1.Util;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisStore2 extends AppCompatActivity {
    Button btnIdCard,btnUserImg, btnNext2;
    ImageView idCardImg, userImg;
    EditText edtIdcard;
    // FirebaseStorage storage;
    StorageReference mStorageRef;
    public Uri imguri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_store2);
        Log.d("testbundle",getIntent().getExtras().getString("name"));
        Log.d("test", MyApplication.storeData.getRealName());
        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        btnIdCard=findViewById(R.id.btnidCardImg);
        btnUserImg=findViewById(R.id.btnUserImg);
        btnNext2=findViewById(R.id.btnNext2);
        idCardImg=findViewById(R.id.idCardImg);
        userImg=findViewById(R.id.userImg);
        edtIdcard=findViewById(R.id.edtIdcard);

        btnIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fileupload();
                RegisStore1();
                MyApplication.storeData.setRealName(edtIdcard.getText().toString().trim());

            }
        });
    }

    private String getExtentsion(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

//    private  void  Fileupload()
//    {
//        StorageReference Ref=mStorageRef.child(System.currentTimeMillis()+"."+getExtentsion(imguri));
//        Ref.putBytes(MyApplication.storeData.bankImg)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Get a URL to the uploaded content
//                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                        Toast.makeText(RegisStore2.this,"Image Upload Success",Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                        // ...
//                    }
//                });
//    }


    private void selectImage() {



        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };



        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, 1);
                    }

                }

                else if (options[item].equals("Choose from Gallery"))

                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,2);


                }

                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }

    private void Filechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    public void RegisStore1(){
        Intent regisStore3Intent = new Intent(RegisStore2.this, RegisterStore3.class);
        //regisStore3Intent.putExtra("name",Rname.getText().toString());
        startActivity(regisStore3Intent);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            //Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            byte[] byteImg = Util.getBytesFromBitmap(bitmap);
//            if ()
//            {
//                MyApplication.storeData.setIdCardImg(byteImg);
//                idCardImg.setImageBitmap(bitmap);
//            }
//            if ()
//            {
//                MyApplication.storeData.setUserImg(byteImg);
//                userImg.setImageBitmap(bitmap);
            //}
        }

        if(requestCode==2 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
            imguri = data.getData();
//            if ()
//            {
                idCardImg.setImageURI(imguri);
//            }
//            if( )
//            {
                userImg.setImageURI(imguri);
//            }
        }
    }
}