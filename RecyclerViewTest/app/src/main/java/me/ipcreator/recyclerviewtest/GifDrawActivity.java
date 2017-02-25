package me.ipcreator.recyclerviewtest;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static android.view.GestureDetector.*;
import static me.ipcreator.recyclerviewtest.Data.FLIP_DISTANCE;
import static me.ipcreator.recyclerviewtest.Data.GetRandomKeyFromGifMap;
import static me.ipcreator.recyclerviewtest.Data.GifMap;

public class GifDrawActivity extends AppCompatActivity implements View.OnClickListener,NestedScrollView.OnScrollChangeListener {

    private GifImageView gifView;
    private MediaController mc=null;

    private static GestureDetector mDetector = null;

    class MyGestureListener implements View.OnTouchListener,OnGestureListener
    {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub
        }

        /**
         *
         * e1 The first down motion event that started the fling. e2 The
         * move motion event that triggered the current onFling.
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1 != null ) {
                if(e2 != null ){
                    if (e1.getX() - e2.getX() > FLIP_DISTANCE) {

                        Log.i("MYTAG", "向左滑...");

                        showNameAndPic(true);

                        return true;
                    }
                    if (e2.getX() - e1.getX() > FLIP_DISTANCE) {

                        Log.i("MYTAG", "向右滑...");

                        showNameAndPic(true);


                        return true;
                    }
                    if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                        Log.i("MYTAG", "向上滑...");
                        return true;
                    }
                    if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                        Log.i("MYTAG", "向下滑...");
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }


        public boolean onTouch(View v, MotionEvent event)
        {
            //这里面第一个参数v，就是用户单击的那个view，
            //在本例中就是那View2中的TextView
            //同时，你也可以使用这个参数来判定用户单击了哪一个View
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                //如有需要，可以在这样增加相关的代码进行处理
            }
            else if(event.getAction() == MotionEvent.ACTION_UP)
            {
            }
            mDetector.onTouchEvent(event);
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_draw);
        gifView = (GifImageView) findViewById(R.id.gifImageView);
        gifView.setOnClickListener(this);

        mDetector = new GestureDetector(this,new MyGestureListener());
        gifView.setOnTouchListener(new MyGestureListener());

        showNameAndPic(false);
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,GifDrawActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    private static final String TAG = "GifDrawActivity";

    private void showNameAndPic(boolean bRandom){

        String name,pic;

        if(bRandom)
        {
            name = GetRandomKeyFromGifMap();
            pic = GifMap.get(name);
        }else{

            Intent intent = getIntent();
            name = intent.getStringExtra("NAME");
            pic = intent.getStringExtra("IMG");
        }

        this.setTitle("GIF  "+name);
        gifView.setImageResource(Integer.parseInt(pic));

        if(mc!=null){
            mc.clearAnimation();
            mc.onFinishInflate();
        }

        mc = new MediaController(this);
        mc.setMediaPlayer( ( GifDrawable ) gifView.getDrawable() );
        mc.setAnchorView( gifView );
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gifImageView:
                if(mc.isShowing())
                {
                    mc.hide();
                }else{
                    mc.show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        Log.d(TAG, "onScrollChange: ");
        
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        if(mc!=null){
            mc.clearAnimation();
            mc.onFinishInflate();
        }

        finish();
    }
}