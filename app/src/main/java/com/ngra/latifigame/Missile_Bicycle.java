package com.ngra.latifigame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

import static com.ngra.latifigame.GamePanel.MOVESPEED;

public class Missile_Bicycle extends GameObject {


    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;
    private boolean TopToBottom = false;
//    private float scaleFactorX;
//    private float scaleFactorY;

    public Missile_Bicycle(Resources resources,
                        int x,
                        int y,
                        int w,
                        int h,
                        int s,
                        int numFrames,
                        int HalfDeviceWidth,
                        int DeviceWidth,
                        int DeviceHeight) {

//        scaleFactorX = DeviceWidth / (WIDHT * 1.f);
//        scaleFactorY = DeviceHeight / (HEIGHT * 1.f);

        int wTemp = HalfDeviceWidth * 2;
        double temp = (wTemp  * 5) / 100;
        int left = (int) Math.round(temp);
        int right = wTemp - left;

        if (left >= x) {
            x = left;
        }

//        int NewW = w;
//        if(scaleFactorX < scaleFactorY)
//            NewW = Math.round(w * scaleFactorY);

        if (right <= x + w) {
            x = right - w - 10;
        }

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;

        Bitmap[] image = new Bitmap[numFrames];


        int min = 0;
        int max = 2;
        int random = new Random().nextInt((max - min) + 1) + min;
        if(random == 0){
            spritesheet = BitmapFactory.decodeResource(
                    resources
                    , R.drawable.missile_bicycle);
        } else if(random == 1) {
            spritesheet = BitmapFactory.decodeResource(
                    resources
                    , R.drawable.missile_bicycle2);
        } else {
            spritesheet = BitmapFactory.decodeResource(
                    resources
                    , R.drawable.missile_bicycle3);
        }


        if(x + width + 10<= HalfDeviceWidth) {
            TopToBottom = true;

        } else {
            int check = x + width + 10;
            check = check - HalfDeviceWidth;
            if(check <= 70) {
                x = HalfDeviceWidth - width - 10;
                super.x = x;
                TopToBottom = true;
            } else {
                if (x <= HalfDeviceWidth) {
                    x = HalfDeviceWidth + 10;
                    super.x = x;
                }
                TopToBottom = false;
            }
        }


        //int speedTest;
        if (TopToBottom) {
            //speed = 7 + (score / 200);
            double round = rand.nextDouble();
            //speed = 7 + (int) (round * (score / 30)) + (score / 90);
            speed = 7 + (int) (round * MOVESPEED + (MOVESPEED * 2.5 / 5 * round) + (MOVESPEED / 5));
            //Log.i("game","LEFT speed : " + speed + " speedTest : "+speedTest);
        }
        else {
            min = 0;
            max = 3;
            random = new Random().nextInt((max - min) + 1) + min;
            //speed = 2 + (score / 500) + random;
            speed = 2 + (MOVESPEED / 12) + random + (MOVESPEED / 20);
            //Log.i("game","RIGHT speed : " + speed + " speedTest : "+speedTest);
        }




        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spritesheet, 0, i * height, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(80 - (speed));

    }


    public void update() {

        y += speed;
        animation.update();

    }

    public void draw(Canvas canvas) {
        try {
//            Bitmap img = animation.getImage();
//            Bitmap resize = null;
//            if(scaleFactorX > scaleFactorY) {
//                float dScale = scaleFactorX - scaleFactorY;
//                int hImg = img.getHeight() + Math.round(img.getHeight() * dScale);
//                resize = Bitmap.createScaledBitmap(img, (int) (img.getWidth()), hImg, true);
//            }
//            else {
//                float dScale = scaleFactorY - scaleFactorX;
//                int wImg = img.getWidth() + Math.round(img.getWidth() * dScale);
//                resize = Bitmap.createScaledBitmap(img, wImg, (int) (img.getHeight()), true);
//            }
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
