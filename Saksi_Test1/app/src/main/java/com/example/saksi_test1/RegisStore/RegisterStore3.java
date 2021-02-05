package com.example.saksi_test1.RegisStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.saksi_test1.MyApplication;
import com.example.saksi_test1.R;

public class RegisterStore3 extends AppCompatActivity {

    EditText accountNumber,bankName,typeBank;
    Button btnNext3;
//    ImageView bankImg;
/////////////
//    StorageReference mStorageRef;
//    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_store3);

//        Log.d("ชื่อ",MyApplication.storeData.getRealName());
//        Log.d("นามสกุล",MyApplication.storeData.getLastName());
//        Log.d("วันเกิด",MyApplication.storeData.getBirthday());
//        Log.d("อีเมล",MyApplication.storeData.getEmail());
//        Log.d("เบอร์",MyApplication.storeData.getPhoneNumber());

        accountNumber=findViewById(R.id.accountNumber);
        bankName=findViewById(R.id.bankName);
        typeBank=findViewById(R.id.typeBank);
//        bankImg=findViewById(R.id.bankImg);
//        btnbankImg=findViewById(R.id.btnbankImg);
        btnNext3=findViewById(R.id.btnNext3);


//


        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fileupload();
                RegisStore1();

                MyApplication.storeData.setRealName(accountNumber.getText().toString().trim());
                MyApplication.storeData.setRealName(bankName.getText().toString().trim());
                MyApplication.storeData.setRealName(typeBank.getText().toString().trim());
//                MyApplication.storeData.setRealName(bankImg.toString().trim());

            }
        });

    } //end Oncreate



    public void RegisStore1(){
        Intent regisStore4Intent = new Intent(RegisterStore3.this, RegisStore4.class);
        //regisStore3Intent.putExtra("name",Rname.getText().toString());

        regisStore4Intent.putExtra("name",accountNumber.getText().toString());
        regisStore4Intent.putExtra("name",bankName.getText().toString());
        regisStore4Intent.putExtra("name",typeBank.getText().toString());
        //regisStore4Intent.putExtra("name",bankImg..toString());

        //regisStore4Intent.putExtra("name",bankImg.().toString());

        startActivity(regisStore4Intent);
    }



//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1 && resultCode==RESULT_OK)
//        {
//            //Bundle extras = data.getExtras();
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            byte[] byteImg = Util.getBytesFromBitmap(bitmap);
//                MyApplication.storeData.setBankImg(byteImg);
//                bankImg.setImageBitmap(bitmap);
//
//        }
//
//        if(requestCode==2 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
//            imguri = data.getData();
//            bankImg.setImageURI(imguri);
//
//        }
//    }

}