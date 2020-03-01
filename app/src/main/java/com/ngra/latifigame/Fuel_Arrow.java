package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import static com.ngra.latifigame.GamePanel.HEIGHT;
import static com.ngra.latifigame.GamePanel.MOVESPEED;
import static com.ngra.latifigame.GamePanel.WIDHT;

public class Fuel_Arrow extends GameObject {

    private Bitmap spritesheet;


    private float scaleFactorX;
    private float scaleFactorY;
    private Bitmap FuelArrow;
    private Bitmap Arrow;
    private float degree = -20;
    private float NewDegree = -22;
    private Resources resources;

    public Fuel_Arrow(Resources resources,
                      int w,
                      int h,
                      int HalfDeviceWidth,
                      int DeviceWidth,
                      int DeviceHeight,
                      int KilometersX,
                      int KilometersW) {

        this.resources = resources;
        scaleFactorX = DeviceWidth / (WIDHT * 1.f);
        scaleFactorY = DeviceHeight / (HEIGHT * 1.f);

        super.x = KilometersX + KilometersW;
        super.y = DeviceHeight;
        width = w;
        height = h;


        FuelArrow = BitmapFactory.decodeResource(
                resources
                , R.drawable.fuel_arrow);

        spritesheet = BitmapFactory.decodeResource(
                resources
                , R.drawable.fuel_background);

        Bitmap img = spritesheet;
        int hImg = img.getHeight();// + Math.round(img.getHeight() * scaleFactorY);
        int wImg = img.getWidth();// + Math.round(img.getWidth() * scaleFactorX);
        hImg = (int) Math.round(hImg * 0.8);
        wImg = (int) Math.round(wImg * 0.8);
        spritesheet = Bitmap.createScaledBitmap(img, wImg, hImg, true);
        x = x + 30;
        y = y - height - 10;
//
//        if (scaleFactorX > scaleFactorY) {
//            //float dScale = scaleFactorX - scaleFactorY;
//            int hImg = img.getHeight() + Math.round(img.getHeight() * dScale);
//            hImg = (int) Math.round(hImg * 1.5);
//            spritesheet = Bitmap.createScaledBitmap(img, hImg, hImg, true);
//
//        } else {
//            //float dScale = scaleFactorY - scaleFactorX;
//            int wImg = img.getWidth() + Math.round(img.getWidth() * dScale);
//            wImg = (int) Math.round(wImg * 1.5);
//            spritesheet = Bitmap.createScaledBitmap(img, wImg, wImg, true);
//            x = x - (spritesheet.getWidth() / 2);
//        }

//
//        if (scaleFactorX > scaleFactorY) {
//            //float dScale = scaleFactorX - scaleFactorY;
//            int hImg = img.getHeight() + Math.round(img.getHeight() * dScale);
//            hImg = (int) Math.round(hImg * 1.5);
//            spritesheetMax = Bitmap.createScaledBitmap(img, hImg, hImg, true);
//
//        } else {
//            //float dScale = scaleFactorY - scaleFactorX;
//            int wImg = img.getWidth() + Math.round(img.getWidth() * dScale);
//            wImg = (int) Math.round(wImg * 1.5);
//            spritesheetMax = Bitmap.createScaledBitmap(img, wImg, wImg, true);
//        }

        update(12);


    }


    public void update(int fuel_level) {
        int sorat = (fuel_level * 15) + 5;
        NewDegree = sorat;
        degree = NewDegree;
        Bitmap arrow = RotateBitmap(FuelArrow, degree);
        if (scaleFactorY > scaleFactorX) {
            float dScale = Math.abs(scaleFactorY - scaleFactorX);
            int hImg = arrow.getHeight() + Math.round(arrow.getHeight() * dScale);
            int wImg = arrow.getWidth();
            hImg = (int) Math.round(hImg * 0.8);
            wImg = (int) Math.round(wImg * 0.8);
            Arrow = Bitmap.createScaledBitmap(arrow, wImg, hImg, true);
        } else {
            float dScale = Math.abs(scaleFactorY - scaleFactorX);
            int hImg = arrow.getHeight();
            int wImg = arrow.getWidth() + Math.round(arrow.getWidth() * dScale);
            hImg = (int) Math.round(hImg * 0.8);
            wImg = (int) Math.round(wImg * 0.8);
            Arrow = Bitmap.createScaledBitmap(arrow, wImg, hImg, true);
        }

    }


    public Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    public void draw(Canvas canvas, int fuel_level) {
        try {

            canvas.drawBitmap(spritesheet, x, y, null);
            int xArrow = x + ((spritesheet.getWidth() - Arrow.getWidth()) / 2);
            int yArrow = y + ((spritesheet.getHeight() - Arrow.getHeight()) / 2);
            update(fuel_level);
            canvas.drawBitmap(Arrow, xArrow, yArrow, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWidth() {
        return width;
    }


}