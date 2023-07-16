package com.example.whitefear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private static MediaPlayer m = null;
    ImageView gohost ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button strbtn =(Button) findViewById(R.id.strbtn);
        SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar2);
        Bundle bundle = new Bundle();
        m = new MediaPlayer();
        m = MediaPlayer.create(this , R.raw.appstart);
        m.start();
        gohost = (ImageView)findViewById(R.id.imageView2);
        gohost.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
        strbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (seekbar.getProgress() == 0)
                {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MainActivity3.class);
                    m.stop();
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MainActivity5.class);
                    m.stop();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}