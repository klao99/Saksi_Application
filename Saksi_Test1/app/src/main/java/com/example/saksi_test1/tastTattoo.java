package com.example.saksi_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;

public class tastTattoo extends AppCompatActivity {

    public static final int PERMISSION_INSERT_IMAGE = 1001;

    StickerView stickerV;
    Button btntatt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tast_tattoo);

        //stickerV =findViewById(R.id.stickerV);
        btntatt =findViewById(R.id.btntatt);

        btntatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





//        TextSticker sticker = new TextSticker(this);
//        sticker.setTextColor(Color.BLACK);
//        sticker.setText("Captan eiei");
//        sticker.resizeText();
//
//        stickerV.addSticker(sticker);
    }
}