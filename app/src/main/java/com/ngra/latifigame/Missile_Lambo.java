package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Missile_Lambo extends GameObject {


    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;
    private boolean TopToBottom = false;

    public Missile_Lambo(Resources resources, int x, int y, int w, int h, int s, int numFrames, int HalfDeviceWidth) {

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

        Bitmap[] image = new Bitmap[numFrames];





        if(x + width + 10<= HalfDeviceWidth) {
            TopToBottom = true;
            spritesheet = BitmapFactory.decodeResource(
                    resources
                    , R.drawable.missile_lambo_ttb);
        } else {
            int check = x + width + 10;
            check = check - HalfDeviceWidth;
            if(check <= 70) {
                x = HalfDeviceWidth - width - 10;
                super.x = x;
                TopToBottom = true;
                spritesheet = BitmapFactory.decodeResource(
                        resources
                        , R.drawable.missile_lambo_ttb);
            } else {
                if (x <= HalfDeviceWidth) {
                    x = HalfDeviceWidth + 10;
                    super.x = x;
                }
                TopToBottom = false;
                spritesheet = BitmapFactory.decodeResource(
                        resources
                        , R.drawable.missile_lambo_btt);
            }
        }


        if (TopToBottom)
            //speed = 7 + (score / 200);
            speed = 7 + (int) (rand.nextDouble() * (score / 30)) + (score / 90);
        else {
            int min = 0;
            int max = 3;
            int random = new Random().nextInt((max - min) + 1) + min;
            speed = 2 + (score / 500) + random;
        }


        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spritesheet, 0, i * height, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100 - (speed));

    }


    public void update() {

        y += speed;
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
        return height - 10;
    }


}
