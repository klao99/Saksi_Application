package com.chutikarn.saksi_application;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class Util {

    static  public  byte[] getBytesFromBitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
}
