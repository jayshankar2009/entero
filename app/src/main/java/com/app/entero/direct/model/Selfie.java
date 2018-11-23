package com.app.entero.direct.model;

import android.graphics.Bitmap;

public class Selfie {
    public Bitmap imageId;
    public String txt;

    public Selfie(Bitmap imageId, String text) {

        this.imageId = imageId;
        this.txt=text;
    }
}
