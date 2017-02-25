package me.ipcreator.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import static me.ipcreator.recyclerviewtest.Data.GifMap;
import static me.ipcreator.recyclerviewtest.Data.SLIDE_FLIP_DISTANCE;
import static me.ipcreator.recyclerviewtest.Data.SLIDE_VELOCITY;
import static me.ipcreator.recyclerviewtest.Data.CHOOSE.ICHOOSEANIMALS;
import static me.ipcreator.recyclerviewtest.Data.CHOOSE.ICHOOSEFRUITS;
import static me.ipcreator.recyclerviewtest.MyUtility.MyLog;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList = new ArrayList<>();
    private static Data.CHOOSE mMyChoose =  ICHOOSEFRUITS;
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
                    if(e1.getX() - e2.getX() > SLIDE_FLIP_DISTANCE && Math.abs(velocityX) > SLIDE_VELOCITY )
                    {
                        MyLog("SLIDETAG", "SLIDE to LEFT...");

                        if(mMyChoose == ICHOOSEFRUITS)
                        {
                            chooseDataForShow(ICHOOSEANIMALS);

                            return true;

                        }else if(mMyChoose == ICHOOSEANIMALS){

                            GifDrawActivity.actionStart(MainActivity.this,Data.GetRandomKeyFromGifMap(),GifMap.get(Data.GetRandomKeyFromGifMap()));

                            return true;
                        }

                    }
                    else if(e2.getX() - e1.getX() > SLIDE_FLIP_DISTANCE && Math.abs(velocityX) > SLIDE_VELOCITY )
                    {
                        MyLog("SLIDETAG", "SLIDE to RIGHT...");

                        if(mMyChoose == ICHOOSEANIMALS)
                        {
                            chooseDataForShow(ICHOOSEFRUITS);

                            return true;
                        }
                    }

                    if (e1.getY() - e2.getY() > SLIDE_FLIP_DISTANCE) {
                        MyLog("SLIDETAG", "SLIDE to DOWN...");
                        return true;
                    }
                    if (e2.getY() - e1.getY() > SLIDE_FLIP_DISTANCE) {
                        MyLog("SLIDETAG", "SLIDE to UP...");
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

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            /*
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
            }*/
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
        setContentView(R.layout.activity_main);
        chooseDataForShow(ICHOOSEFRUITS);

        mDetector = new GestureDetector(this,new MyGestureListener());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setOnTouchListener(new MyGestureListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.fruit_item:
                chooseDataForShow(ICHOOSEFRUITS);
                break;

            case R.id.animal_item:
                chooseDataForShow(ICHOOSEANIMALS);
                break;

            case R.id.gif_item:
                GifDrawActivity.actionStart(this,Data.GetRandomKeyFromGifMap(),GifMap.get(Data.GetRandomKeyFromGifMap()));
                break;

            case R.id.notice_item:
                NotifyActivity.actionStart(this,null,null);
                break;

            case R.id.camera_item:
                CameraActivity.actionStart(this,null,null);
                break;

            case R.id.music_item:
                MusicActivity.actionStart(this,null,null);
                break;

            case R.id.video_item:
                MediaActivity.actionStart(this,null,null);
                break;

            default:
                break;
        }
        return true;
    }

    private void chooseDataForShow(Data.CHOOSE choose){

        itemList.clear();
        initList(choose);

        switch (choose){
            case ICHOOSEFRUITS:
                mMyChoose = ICHOOSEFRUITS;
                this.setTitle("Roll and Pick"+"  Fruits");
                break;
            case ICHOOSEANIMALS:
                mMyChoose = ICHOOSEANIMALS;
                this.setTitle("Roll and Pick"+"  Animals");
                break;
            default:
                break;
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
    }

    private void initList(Data.CHOOSE choose)
    {
        switch (choose){

            case ICHOOSEFRUITS:
                for(int i = 0; i<100; i++){
                    for (Map.Entry<String, String> entry : Data.fruitListMap.entrySet()) {
                        Item fruit = new Item(entry.getKey(), Integer.parseInt(entry.getValue()));
                        itemList.add(fruit);
                    }
                }
                break;

            case ICHOOSEANIMALS:
                for(int i = 0; i<100; i++){
                    for (Map.Entry<String, String> entry : Data.animalListMap.entrySet()) {
                        Item animal = new Item(entry.getKey(), Integer.parseInt(entry.getValue()));
                        itemList.add(animal);
                    }
                }
                break;
            default:
                break;
        }
    }
}
