package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Mistake_RecyclerBin extends GameObject {


    private int score;
    private int speed;
    private Bitmap spritesheet;

    public Mistake_RecyclerBin(Resources resources, int x, int y, int w, int h, int s, int HalfDeviceWidth) {

        int wTemp = HalfDeviceWidth * 2;
        double temp = (wTemp  * 5) / 100;
        int left = (int) Math.round(temp);
        int right = wTemp - left;

        if (left >= x) {
            x = left;
        }

        if (right <= x + w) {
            x = right - w - 10;
        }

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;

        spritesheet = BitmapFactory.decodeResource(
                resources
                , R.drawable.mistake_recycler_bin);

        int min = 0;
        int max = 3;
        int random = new Random().nextInt((max - min) + 1) + min;
        speed = 2 + (score / 500) + random;

    }


    public void update() {

        y += speed;

    }


    public void Collection() {

        y = (y / 2);

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