package me.ipcreator.recyclerviewtest;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.view.GestureDetector.OnGestureListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import static me.ipcreator.recyclerviewtest.Data.SLIDE_FLIP_DISTANCE;
import static me.ipcreator.recyclerviewtest.Data.GetRandomKeyFromGifMap;
import static me.ipcreator.recyclerviewtest.Data.GifMap;
import static me.ipcreator.recyclerviewtest.MyUtility.MyLog;

public class GifDrawActivity extends AppCompatActivity implements View.OnClickListener{

    private GifImageView gifView;
    private MediaController mc=null;
    private static GestureDetector mDetector = null;

    class MyGestureListener implements View.OnTouchListener,OnGestureListener
    {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
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
                    if (e1.getX() - e2.getX() > SLIDE_FLIP_DISTANCE) {
                        MyLog("MYTAG", "SLIDE to LEFT...");
                        showNameAndPic(true);
                        return true;
                    }
                    if (e2.getX() - e1.getX() > SLIDE_FLIP_DISTANCE) {

                        MyLog("MYTAG", "SLIDE to RIGHT...");
                        showNameAndPic(true);
                        return true;
                    }
                    if (e1.getY() - e2.getY() > SLIDE_FLIP_DISTANCE) {
                        MyLog("MYTAG", "SLIDE to BOTTOM...");
                        return true;
                    }
                    if (e2.getY() - e1.getY() > SLIDE_FLIP_DISTANCE) {
                        MyLog("MYTAG", "SLIDE to UP...");
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }


        public boolean onTouch(View v, MotionEvent event)
        {
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
    public void onBackPressed() {

        if(mc!=null){
            mc.clearAnimation();
            mc.onFinishInflate();
        }
        finish();
    }
}