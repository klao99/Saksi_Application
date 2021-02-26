package com.chutikarn.saksi_application;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestTattooActivity extends AppCompatActivity {

    float scalediff;
    Toolbar toolbar;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;



    private ImageView addImgCustomer,tattooImgId,stickerView;
    Button btnSave,btnSize2X,btnSize;
    LinearLayout groupBtn;


    RelativeLayout rootview ;
    int windowwidth;
    int windowheight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tattoo);


        initViewData();
        initControl();



    }




    private void initViewData() {
        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();

        addImgCustomer=findViewById(R.id.addImgCustomer);
        tattooImgId=findViewById(R.id.tattooImgId);
        btnSize=findViewById(R.id.btnSize);
        btnSave=findViewById(R.id.btnSave);
        btnSize2X=findViewById(R.id.btnSize2X);
        groupBtn=findViewById(R.id.groupBtn);

        rootview = findViewById(R.id.rootView);
        Picasso.get().load(getIntent().getStringExtra("imageUrl")).into(tattooImgId);
    }

    private void initControl() {
        addImgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap TattooCusmerImg = takeScreenshot();
                rootview.setBackgroundDrawable( new BitmapDrawable(getResources(), TattooCusmerImg));
                addImgCustomer.setVisibility(View.GONE);
                tattooImgId.setVisibility(View.GONE);
                saveBitmap(TattooCusmerImg);

                Intent intent=new Intent(TestTattooActivity.this, ViewActivity2.class);
                startActivity(intent);
            }
        });

        btnSize.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View v) {

                selectImage();
                //TransitionManager.beginDelayedTransition();
//                visible = !visible;
//                groupBtn.setVisibility(visible ? View.VISIBLE: View.GONE);


            }
        });

        // 2x
//        btnSize2X.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                    RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams) tattooImgId.getLayoutParams();
//                    params.width = tattooImgId.getLayoutParams().width * 2;
//                    params.height = tattooImgId.getLayoutParams().width * 2;
//                    tattooImgId.setLayoutParams(params);
//            }
//        });



        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, 250);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 50;
        layoutParams.bottomMargin = -250;
        layoutParams.rightMargin = -250;
        tattooImgId.setLayoutParams(layoutParams);

        tattooImgId.setOnTouchListener(new View.OnTouchListener() {

            RelativeLayout.LayoutParams parms;
            int startwidth;
            int startheight;
            float dx = 0, dy = 0, x = 0, y = 0;
            float angle = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final ImageView view = (ImageView) v;

                ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        startwidth = parms.width;
                        startheight = parms.height;
                        dx = event.getRawX() - parms.leftMargin;
                        dy = event.getRawY() - parms.topMargin;
                        mode = DRAG;
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 10f) {
                            mode = ZOOM;
                        }

                        d = rotation(event);

                        break;
                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {

                            x = event.getRawX();
                            y = event.getRawY();

                            parms.leftMargin = (int) (x - dx);
                            parms.topMargin = (int) (y - dy);

                            parms.rightMargin = 0;
                            parms.bottomMargin = 0;
                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                            view.setLayoutParams(parms);

                        } else if (mode == ZOOM) {

                            if (event.getPointerCount() == 2) {

                                newRot = rotation(event);
                                float r = newRot - d;
                                angle = r;

                                x = event.getRawX();
                                y = event.getRawY();

                                float newDist = spacing(event);
                                if (newDist > 10f) {
                                    float scale = newDist / oldDist * view.getScaleX();
                                    if (scale > 0.6) {
                                        scalediff = scale;
                                        view.setScaleX(scale);
                                        view.setScaleY(scale);

                                    }
                                }

                                view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                x = event.getRawX();
                                y = event.getRawY();

                                parms.leftMargin = (int) ((x - dx) + scalediff);
                                parms.topMargin = (int) ((y - dy) + scalediff);

                                parms.rightMargin = 0;
                                parms.bottomMargin = 0;
                                parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                view.setLayoutParams(parms);


                            }
                        }
                        break;
                }

                return true;

            }
        });



//        tattooImgId.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tattooImgId.getLayoutParams();
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int x_cord = (int) event.getRawX();
//                        int y_cord = (int) event.getRawY();
//
//
//                        if (x_cord > windowwidth) {
//                            x_cord = windowwidth;
//                        }
//                        if (y_cord > windowheight) {
//                            y_cord = windowheight;
//                        }
//
//                        v.setX(x_cord - (v.getWidth()/2));
//                        v.setY(y_cord - (v.getHeight()/2));
//                        break;
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });

    } ///initControl

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);

    }

    public Bitmap takeScreenshot() {
        View rootView = rootview;
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);
        Bitmap b1 = Bitmap.createBitmap(rootView.getDrawingCache(true));
        rootView.setDrawingCacheEnabled(false); // clear drawing cache
        return b1;
    }

    public void saveBitmap(Bitmap bitmap) {
        if(bitmap==null){
            return;
        }
        File imagePath1 = new File(Environment.getExternalStorageDirectory().toString(),"screenshot.jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath1);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void selectImage() {


        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, 1);
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 2);


                } else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imguri;
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            //Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            addImgCustomer.setImageBitmap(bitmap);
        }

        if(requestCode==2 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
            imguri = data.getData();
            addImgCustomer.setImageURI(imguri);
        }
    }


}