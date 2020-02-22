package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;

import static com.ngra.latifigame.GamePanel.HEIGHT;
import static com.ngra.latifigame.GamePanel.MOVESPEED;
import static com.ngra.latifigame.GamePanel.WIDHT;

public class Kilometers extends GameObject {

    private Bitmap spritesheet;
    private float scaleFactorX;
    private float scaleFactorY;
    private Bitmap KilometerArrow;
    private Bitmap Arrow;
    private float degree = -20;
    private float NewDegree = -22;

    public Kilometers(Resources resources,
                      int y,
                      int w,
                      int h,
                      int HalfDeviceWidth,
                      int DeviceWidth,
                      int DeviceHeight) {

        scaleFactorX = DeviceWidth / (WIDHT * 1.f);
        scaleFactorY = DeviceHeight / (HEIGHT * 1.f);

        super.x = HalfDeviceWidth;
        super.y = y;
        width = w;
        height = h;
        spritesheet = BitmapFactory.decodeResource(
                resources
                , R.drawable.kilometer_back);


        KilometerArrow = BitmapFactory.decodeResource(
                resources
                , R.drawable.kilometer_arrow);

        Bitmap img = spritesheet;

        if (scaleFactorX > scaleFactorY) {
            float dScale = scaleFactorX - scaleFactorY;
            int hImg = img.getHeight() + Math.round(img.getHeight() * dScale);
            spritesheet = Bitmap.createScaledBitmap(img, (int) (img.getWidth()), hImg, true);

        } else {
            float dScale = scaleFactorY - scaleFactorX;
            int wImg = img.getWidth() + Math.round(img.getWidth() * dScale);
            spritesheet = Bitmap.createScaledBitmap(img, wImg, (int) (img.getHeight()), true);
            x = x - (spritesheet.getWidth() / 2);
        }

        update(0, 1f);


    }


    public void update(int speed, float SpeedScore) {

        int sorat = (speed * 2) - 20;
        sorat = sorat * 90;
        NewDegree = sorat / 60;
        if (NewDegree > degree) {
            degree += 0.03 * SpeedScore;
            Bitmap arrow = RotateBitmap(KilometerArrow, degree);
            float dScale = Math.abs(scaleFactorY - scaleFactorX);
            int hImg = arrow.getHeight() + Math.round(arrow.getHeight() * dScale);
            Arrow = Bitmap.createScaledBitmap(arrow, (int) (arrow.getWidth()), hImg, true);
            int wImg = arrow.getWidth() + Math.round(arrow.getWidth() * dScale);
            Arrow = Bitmap.createScaledBitmap(arrow, wImg, (int) (arrow.getHeight()), true);

        } else {
            degree = NewDegree;
            Bitmap arrow = RotateBitmap(KilometerArrow, degree);
            float dScale = Math.abs(scaleFactorY - scaleFactorX);
            int hImg = arrow.getHeight() + Math.round(arrow.getHeight() * dScale);
            Arrow = Bitmap.createScaledBitmap(arrow, (int) (arrow.getWidth()), hImg, true);
            int wImg = arrow.getWidth() + Math.round(arrow.getWidth() * dScale);
            Arrow = Bitmap.createScaledBitmap(arrow, wImg, (int) (arrow.getHeight()), true);
        }

    }


    public Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    public void draw(Canvas canvas, double SpeedScore, int score, Paint paint, boolean playing) {
        try {
            canvas.drawBitmap(spritesheet, x, y, null);
            int xArrow = x + ((spritesheet.getWidth() - Arrow.getWidth()) / 2);
            int yArrow = y + ((spritesheet.getHeight() - Arrow.getHeight()) / 2);
            if (score > 35 && playing)
                update(MOVESPEED, (float) SpeedScore);
            canvas.drawBitmap(Arrow, xArrow, yArrow, null);
            paint.setColor(Color.BLACK);
            canvas.drawText(String.valueOf(score), xArrow+Arrow.getWidth() / 2,y+spritesheet.getHeight() - (Math.round(spritesheet.getHeight() / 4.5)),paint );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWidth() {
        return width;
    }


}