package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Formatter;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.view.View.OnTouchListener;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton butt1, butt2, mainButton;
    int count;
    float time;
    float maxX, maxY;
    int buttonHeight, buttonWight;
    Timer objectTimer;
    int SCORE;
    TextView scoreScreen;

    void rand(){

        //спизженный код
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        maxX = size.x;
        maxY = size.y;
        /////////////////////////////////////////

        buttonHeight = butt1.getHeight();
        buttonWight = butt1.getWidth();
        butt2.setX((int)(Math.random() * (maxX - buttonWight)));
        butt2.setY((int)(Math.random() * (maxY - buttonHeight)));
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //SCORE = 0;
        scoreScreen = (TextView) findViewById(R.id.score);
        scoreScreen.setX(730);
        scoreScreen.setY(20);
        scoreScreen.setText(Integer.toString(SCORE));

        butt1 = findViewById(R.id.butt1);
        butt1.setX(maxX/2);
        butt1.setY(maxY/2);
        butt2 = findViewById(R.id.butt2);
        mainButton = findViewById(R.id.mainButton);

        buttonHeight = butt1.getHeight();
        buttonWight = butt1.getWidth();

        objectTimer = new Timer();
        objectTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                time++;
                if (time == 2){
                    rand();
                    time = 0;
                    SCORE -= 1;
                    scoreScreen.setText(Integer.toString(SCORE));
                }
            }
        }, 0, 300);

        butt1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    butt1.setX(butt2.getX());
                    butt1.setY(butt2.getY());
                    rand();
                    count++;
                    time = 0;
                    SCORE += 2;
                    scoreScreen.setText(Integer.toString(SCORE));
                }
                return false;
            }
        });
        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SCORE -= 3;
                scoreScreen.setText(Integer.toString(SCORE));
                //Toast.makeText(MainActivity.this, "LOSER", Toast.LENGTH_SHORT).show();
            }
        });
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "LOSER", Toast.LENGTH_SHORT).show();
                SCORE -= 2;
                scoreScreen.setText(Integer.toString(SCORE));
            }
        });
    }
}
//117
//19.12.21-12:22