package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.util.Random;

import static com.ngra.latifigame.GamePanel.HEIGHT;
import static com.ngra.latifigame.GamePanel.WIDHT;

public class Coin_Score extends GameObject {


    private Animation animation = new Animation();
    private Bitmap spritesheet;
    private int count = 0;
    private float scaleFactorX;
    private float scaleFactorY;

    public Coin_Score(Resources resources,
                      int HalfDeviceWidth,
                      int DeviceWidth,
                      int DeviceHeight,
                      int PlayerX,
                      int PlayerY) {


        scaleFactorX = DeviceWidth / (WIDHT * 1.f);
        scaleFactorY = DeviceHeight / (HEIGHT * 1.f);


        int wTemp = HalfDeviceWidth * 2;
        double temp = (wTemp  * 5) / 100;
        int left = (int) Math.round(temp);
        int right = wTemp - left - 35;

        super.x = PlayerX + 20;
        super.y = PlayerY - 60;
        width = 35;
        height = 74;
        count = 0;

        Bitmap[] image = new Bitmap[9];
        spritesheet = BitmapFactory.decodeResource(
                resources
                , R.drawable.coin_score);


        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spritesheet, 0, i * height, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(30);

    }


    public void update() {

        count++;
        animation.update();

    }

    public void draw(Canvas canvas) {
        try {
            Bitmap img = animation.getImage();
            if(scaleFactorX > scaleFactorY) {
                float dScale = scaleFactorX - scaleFactorY;
                int hImg = img.getHeight() + Math.round(img.getHeight() * dScale);
                spritesheet = Bitmap.createScaledBitmap(img, (int) (img.getWidth()), hImg, true);
            }
            else {
                float dScale = scaleFactorY - scaleFactorX;
                int wImg = img.getWidth() + Math.round(img.getWidth() * dScale);
                spritesheet = Bitmap.createScaledBitmap(img, wImg, (int) (img.getHeight()), true);
            }
            canvas.drawBitmap(spritesheet, x, y, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWidth() {
        return height - 10;
    }

    public int getCount() {
        return count;
    }
}