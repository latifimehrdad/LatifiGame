package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import static com.ngra.latifigame.GamePanel.HEIGHT;
import static com.ngra.latifigame.GamePanel.WIDHT;

public class Kilometers extends GameObject {

    private Bitmap spritesheet;
    private float scaleFactorX;
    private float scaleFactorY;

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

        Bitmap img = spritesheet;
        if(scaleFactorX > scaleFactorY) {
            float dScale = scaleFactorX - scaleFactorY;
            int hImg = img.getHeight() + Math.round(img.getHeight() * dScale);
            spritesheet = Bitmap.createScaledBitmap(img, (int) (img.getWidth()), hImg, true);
        }
        else {
            float dScale = scaleFactorY - scaleFactorX;
            int wImg = img.getWidth() + Math.round(img.getWidth() * dScale);
            spritesheet = Bitmap.createScaledBitmap(img, wImg, (int) (img.getHeight()), true);
            x = x - (spritesheet.getWidth() / 2);
        }

    }


    public void update() {

    }

    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(spritesheet, x, y, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWidth() {
        return height - 10;
    }


}