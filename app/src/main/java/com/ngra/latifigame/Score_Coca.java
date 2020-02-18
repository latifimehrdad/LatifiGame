package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

import static com.ngra.latifigame.GamePanel.MOVESPEED;

public class Score_Coca extends GameObject {


    private int score;
    private int speed;
    private Random rand = new Random();
    private Bitmap spritesheet;

    public Score_Coca(Resources resources, int x, int y, int w, int h, int s, int HalfDeviceWidth) {

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
                , R.drawable.score_coca);

        int min = 0;
        int max = 3;
        int random = new Random().nextInt((max - min) + 1) + min;
        speed = 2 + (score / 500) + random;

    }


    public void update() {

        y += speed;

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
