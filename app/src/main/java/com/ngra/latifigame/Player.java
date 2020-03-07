package com.ngra.latifigame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player extends GameObject{

    public static double SpeedScore = 1;
    private Bitmap spritesheet;
    private Bitmap MistakeSpritesheet;
    private int numFrames;
    public static int score = 0;
    private boolean up;
    private int xNew;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private int Delay = 280;
    private int SpeedMove = 25;
    private int Mistake = 0;
//    private float scaleFactorX;
//    private float scaleFactorY;

    public Player(Bitmap res,
                  Bitmap mistake,
                  int w,
                  int h,
                  int numFrames,
                  int DeviceWidth,
                  int DeviceHeight) {


//        scaleFactorX = DeviceWidth / (WIDHT * 1.f);
//        scaleFactorY = DeviceHeight / (HEIGHT * 1.f);

        x = GamePanel.WIDHT / 2;
        y = GamePanel.HEIGHT -  (GamePanel.HEIGHT * 30 / 100) - ( h / 6);

        dy = 0;
        height = h;
        width = w;
        xNew = x;

        this.numFrames = numFrames;
        spritesheet = res;
        MistakeSpritesheet = mistake;

        SetImagePlayer();

    }


    public void MistakeCollection() {
        Bitmap[] image = new Bitmap[numFrames];
        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(MistakeSpritesheet,0,i*height,width, height);
        }

        animation.setFrames(image);
        animation.setDelay(50);
        startTime = System.nanoTime();
        Mistake = 60;
    }


    private void SetImagePlayer() {

        Bitmap[] image = new Bitmap[numFrames];
        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet,0,i*height,width, height);
        }

        animation.setFrames(image);
        animation.setDelay(Delay);
        startTime = System.nanoTime();
        Mistake = 0;
    }


    public void SetDelayAnimation() {
        Delay = Delay - 10;
        if(Delay < 40)
            Delay = 40;
        animation.setDelay(Delay);
        //SpeedMove = Delay / 3;
        if(SpeedMove < 7)
            SpeedMove = 7;
    }


    public void SetLowOfDelayAnimation() {
        Delay = Delay + 10;
        if(Delay < 40)
            Delay = 40;
        animation.setDelay(Delay);
        //SpeedMove = Delay / 3;
        if(SpeedMove < 7)
            SpeedMove = 7;
    }

    public void setUp(boolean b){
         up = b;
    }

    public void setNewX(int xNew, int HalfDevicewidth) {

        int w = HalfDevicewidth * 2;
        double temp = (w  * 8) / 100;
        int left = (int) Math.round(temp);
        int right = w - left;

        if(xNew < left)
            xNew = left;

        if(xNew > right)
            xNew = right;

//        int NewW = width;
//        if(scaleFactorX < scaleFactorY) {
//            float dScale = scaleFactorY - scaleFactorX;
//            NewW = NewW + Math.round(NewW * dScale);
//        }

        if(xNew >= left && xNew <= right)
            this.xNew = xNew - (width / 2);
    }

    public void update() {

        if(Mistake > 1)
            Mistake--;
        else if (Mistake == 1) {
            Mistake = 0;
            SetImagePlayer();
        }

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if(elapsed > 100) {
            int plus = (int) SpeedScore;
            score+= plus;
            startTime = System.nanoTime();
        }
        animation.update();

        int difrentX;

        if(xNew < x) {
            difrentX = x - xNew;
            if(difrentX > 20)
                dx -= SpeedMove;
            else
                dx -= difrentX;

        } else if(xNew > x) {
            difrentX = xNew - x;
            if(difrentX > 20)
                dx += SpeedMove;
            else
                dx += difrentX;
        }
        else
            dx = 0;


        if(dx > 30)
            dx = 30;

        if(dx < -30)
            dx = -30;


        x += dx ;

        dx = 0;

    }


    public void setSpeedScore() {
        SpeedScore = SpeedScore + 0.2;
    }


    public void setLowOfSpeedScore() {
        SpeedScore = SpeedScore - 0.2;
        if(SpeedScore < 1)
            SpeedScore = 1;
    }

    public double getSpeedScore() {
        return SpeedScore;
    }

    public void draw(Canvas canvas) {
        try {
            Bitmap img = animation.getImage();
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

    public int getScore() {
        return  score;
    }

    public boolean getPlaying(){
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetDY() {
        dy = 0;
    }

    public void resetScore() {
        score = 0;
    }

}
