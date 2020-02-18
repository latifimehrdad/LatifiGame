package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Missile_yaro extends GameObject {

    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;
    boolean rtl = false;
    int up;
    int down;

    public Missile_yaro(Resources resources, int x, int y, int w, int h, int s, int numFrames, int HalfDeviceHeight) {

        int hTemp = HalfDeviceHeight * 2;
        double temp = hTemp / 5.75;
        up = (int) Math.round(temp);
        down = hTemp - up - (h * 2);
        if (up > y) {
            int min = 20;
            int max = 80;
            int random = new Random().nextInt((max - min) + 1) + min;
            y = up + random;
        }

        if (down < y) {
            int min = 20;
            int max = 80;
            int random = new Random().nextInt((max - min) + 1) + min;
            y = down - random;
        }

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;

        Bitmap[] image = new Bitmap[numFrames];


        if (y + h + 15 > HalfDeviceHeight) {
            rtl = false;
//            spritesheet = BitmapFactory.decodeResource(
//                    resources
//                    , R.drawable.car_missile_yaro_ltr);
        } else {
            rtl = true;
//            spritesheet = BitmapFactory.decodeResource(
//                    resources
//                    , R.drawable.car_missile_yaro_rtl);
        }

        if (rtl)
            //speed = 7 + (score / 200);
            speed = 7 + (int) (rand.nextDouble() * (score / 30)) + (score / 90);
        else {
            int min = 0;
            int max = 3;
            int random = new Random().nextInt((max - min) + 1) + min;
            speed = 2 + (score / 500) + random;
        }


//        if (speed > 40) {
//            speed = 30;
//        }

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spritesheet, i * width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100 - (speed));

    }


    public void update() {

        x -= speed;

        animation.update();

    }

    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(animation.getImage(), x, y, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWidth() {
        return width - 10;
    }

}
