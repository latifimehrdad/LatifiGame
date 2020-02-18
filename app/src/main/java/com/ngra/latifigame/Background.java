package com.ngra.latifigame;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Background {

    private Bitmap image;
    private int x, y, dy;


    public Background(Bitmap image) {
        this.image = image;
        dy = GamePanel.MOVESPEED;
    }


    public void SetSpeedBackground(){
        dy = GamePanel.MOVESPEED;
    }

    public void update() {
        y+= dy;
        if(y > GamePanel.HEIGHT) {
            y = 0;
        }
    }

    public void draw(Canvas canvas ){
        canvas.drawBitmap(image, x, y, null);
        if(y > 0) {
            canvas.drawBitmap(image, x, y-GamePanel.HEIGHT, null);
        }
    }


}
