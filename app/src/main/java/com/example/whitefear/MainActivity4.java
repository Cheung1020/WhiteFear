package com.example.whitefear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    TextView count,find,time_t;
    Button restart,finish;
    int result1,result2,result3 ;
    private static MediaPlayer m = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        count = (TextView)findViewById(R.id.textView5);
        find = (TextView)findViewById(R.id.textView6);
        time_t = (TextView)findViewById(R.id.textView10);
        restart = (Button)findViewById(R.id.button);
        finish = (Button)findViewById(R.id.button2);
        Bundle bundle = this.getIntent().getExtras();
        result1 = bundle.getInt("count");
        result2 = bundle.getInt("find");
        result3 = bundle.getInt("time");
        count.setText("總共點擊次數：" + result1);
        find.setText("找到目標次數："+result2);
        if (result3 == 0 )
        {
            time_t.setVisibility(View.INVISIBLE);
        }
        else
        {
            time_t.setVisibility(View.VISIBLE);
            time_t.setText("花費時間：" + result3 + "秒");
        }
        m = new MediaPlayer();
        m = MediaPlayer.create(this , R.raw.love);
        m.start();
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity4.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}