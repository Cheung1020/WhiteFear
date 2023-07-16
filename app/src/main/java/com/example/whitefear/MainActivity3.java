package com.example.whitefear;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    //@Override
    int lastx,lasty,a,b,hard,total=0,count=0,count_bt=0 , time =0;
    int []location = new int [2];
    private Rect rect = new Rect();
    private static MediaPlayer m = null;
    private static MediaPlayer touch = null;
    Handler handler = new Handler();
    ImageButton target ;
    TextView text , cnt;
    Button restart , finish;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        int diff,hitCount = 0,lastx,lasty;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        m = new MediaPlayer();
        m = MediaPlayer.create(this , R.raw.gamestart);
        m.start();
        Button buttonclick =(Button)findViewById(R.id.btn);
        restart = (Button) findViewById(R.id.restart);
        finish = (Button) findViewById(R.id.finish);
        restart.setVisibility(View.INVISIBLE);
        finish.setVisibility(View.INVISIBLE);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Log.d("demo", "width: " + Integer.toString(width));
        Log.d("demo", "height: " + Integer.toString(height));
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect);
        text = (TextView) findViewById(R.id.textView2);
        cnt = (TextView) findViewById(R.id.textView8);
        target = (ImageButton) findViewById(R.id.target);
        target.setX(((float)Math.random()*840));
        target.setY(((float)Math.random()*1640));
        target.setVisibility(View.INVISIBLE);
        text.setText("目前點擊的次數為：");
        buttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonclick.setVisibility(View.INVISIBLE);
                //int []location = new int [2];
                target.getLocationOnScreen(location);
                a=location[0];
                b=location[1];
                Log.d("demo","a:"+a+" b:"+b);
                Log.d("demo","left:"+target.getLeft());
                Log.d("demo","right:"+target.getRight());
                Log.d("demo","top:"+target.getTop());
                Log.d("demo","bottom:"+target.getBottom());
                Log.d("demo", "widht: " + target.getWidth());
                Log.d("demo", "height: " + target.getHeight());

                Log.d("demo", "buttonW: " + view.getWidth());
                Log.d("demo", "buttonH: " + view.getHeight());
                view.getLocalVisibleRect(rect);
                Log.d("demo", "Local: " + rect);
                view.getGlobalVisibleRect(rect);
                Log.d("demo", "Global: " + rect);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count_bt >= 10 && count_bt < 50)
                {
                    target.setImageDrawable(ContextCompat.getDrawable(MainActivity3.this,R.drawable.target2));
                    //target.setBackgroundResource(R.drawable.target2);
                }
                else if (count_bt >= 50)
                {
                    target.setImageDrawable(ContextCompat.getDrawable(MainActivity3.this,R.drawable.boss));
                }
                target.setX(((float)Math.random()*840));
                target.setY(((float)Math.random()*1640));
                target.getLocationOnScreen(location);
                a=location[0];
                b=location[1];
                target.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.INVISIBLE);
                finish.setVisibility(View.INVISIBLE);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("find",count_bt);
                bundle.putInt("count",count);
                bundle.putInt("time",time);
                Intent intent = new Intent();
                intent.setClass(MainActivity3.this, MainActivity4.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }
    /*public void onWindowsFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            StringBuilder sb = new StringBuilder();
            Dimension dimen3 = getAreaThree(MainActivity3.this);
            Width = dimen3.mWidth;
            Height = dimen3.mHeight;
        }
    }
    private Dimension getAreaThree(MainActivity3 activity){
        Dimension dimen = new Dimension();
        // 用戶繪製區域
        Rect outRect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        dimen.mWidth = outRect.width() ;
        dimen.mHeight = outRect.height();
        // end
        return dimen;
    }
    public class Dimension {
        public int mWidth ;
        public int mHeight ;
        public Dimension(){}
    }*/
    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                count++;
                text.setText("目前點擊的次數為：" + count);
                lastx = x;
                lasty = y;
                Log.d("touched: ","(" + x + "," + y + ")");
                if (a > x && b > y)
                {
                    total = (a-x) + (b-y);
                }
                else if ((x > a && x < (a+239)) && b > y)
                {
                    total = b-y;
                }
                else if ( x > (a+239) && b > y)
                {
                    total = (x-a-239) + (b-y);
                }
                else if (a > x && (y > b && (y< (b+294))))
                {
                    total = a-x;
                }
                else if ( x > (a+239) && (y > b && (y< (b+294))))
                {
                    total = (x-a-239);
                }
                else if (a > x && y > (b+294))
                {
                    total = (a-x) + (y-b-294);
                }
                else if ((x > a && x < (a+239)) && y > (b+294))
                {
                    total = y-b-294;
                }
                else
                {
                    total = (x-a-239) + (y-b-294);
                }
                if (total <=2500 && total >2000)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(0.3f));
                    touch.start();
                }
                else if (total <= 2000 && total > 1500)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(0.5f));
                    touch.start();
                }
                else if (total <= 1500 && total > 1000)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(0.8f));
                    touch.start();
                }
                else if (total <= 1000 && total > 700)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(1f));
                    touch.start();
                }
                else if (total <= 700 && total > 500)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(1.3f));
                    touch.start();
                }
                else if (total <= 500 && total > 300)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(1.6f));
                    touch.start();
                }
                else if (total <= 300 && total >100)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(2f));
                    touch.start();
                }
                else if (total<= 100 && total >0)
                {
                    touch = new MediaPlayer();
                    touch = MediaPlayer.create(this , R.raw.bear2);
                    touch.setVolume(100/100 , 100/100);
                    touch.setPlaybackParams(touch.getPlaybackParams().setSpeed(2.5f));
                    touch.start();
                }
                else if (total <= 0 )
                {
                    Toast.makeText(getApplicationContext(), "恭喜找到一個了!", Toast.LENGTH_SHORT).show();
                    count_bt++;
                    cnt.setText("找到" + count_bt + "次");
                    target.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.VISIBLE);
                    finish.setVisibility(View.VISIBLE);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetx = x - lastx;
                int offsety = y - lasty;
                Log.d("moved x: ","(" + offsetx + ")");
                Log.d("moved y: ","(" + offsety  + ")");
            case MotionEvent.ACTION_CANCEL:
                Log.d("leaved: ","(" + x + "," + y + ")");
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.view:
                target.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        target.setVisibility(View.INVISIBLE);
                    }
                },100);
                break;
            case R.id.distance:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("查看距離");
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setMessage("距離：" + total);
                builder.setPositiveButton("返回遊戲", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.mode:
                Intent intent = new Intent();
                intent.setClass(MainActivity3.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}