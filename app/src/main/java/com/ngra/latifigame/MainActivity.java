package com.ngra.latifigame;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {


    TextView textView;
    LinearLayout layout;
    GamePanel gamePanel;
    private MediaPlayer mediaCoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txt);

        layout = (LinearLayout) findViewById(R.id.layout);

        gamePanel = new GamePanel(MainActivity.this, MainActivity.this);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gamePanel != null) {
                    layout.removeAllViews();
                    gamePanel = null;
                }

                textView.setVisibility(View.GONE);
                GamePanel.MOVESPEED = 5;
                Player.SpeedScore = 1;
                Player.score = 0;
                gamePanel = new GamePanel(MainActivity.this, MainActivity.this);
                layout.addView(gamePanel);
            }
        });

        //setContentView(new GamePanel(this));
    }


    public void ResetGame() {
        textView.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(gamePanel != null)
            gamePanel.StopGame();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(gamePanel != null)
            gamePanel.StopGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.removeAllViews();
        layout.addView(gamePanel);
        textView.setVisibility(View.GONE);
    }
}
