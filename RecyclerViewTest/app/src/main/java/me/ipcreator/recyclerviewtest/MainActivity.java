package me.ipcreator.recyclerviewtest;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import static me.ipcreator.recyclerviewtest.Data.FLIP_DISTANCE;
import static me.ipcreator.recyclerviewtest.Data.GifMap;
import static me.ipcreator.recyclerviewtest.Data.PicMap;

public class MainActivity extends AppCompatActivity implements MyConstant{

    private static CHOOSE gMyChoose =  MyConstant.CHOOSE.ICHOOSEFRUITS;
    private static ACTIVITY gCurActivity =  MyConstant.ACTIVITY.MAIN;
    private static GestureDetector mDetector = null;

    private   CHOOSE ICHOOSEFRUITS = CHOOSE.ICHOOSEFRUITS;
    private   CHOOSE ICHOOSEANIMALS = CHOOSE.ICHOOSEANIMALS;
    private   CHOOSE ICHOOSEGIFS=CHOOSE.ICHOOSEANIMALS;

    private List<Item> itemList = new ArrayList<>();

    private static final String TAG = "MainActivity";


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

                        if(gMyChoose == ICHOOSEFRUITS)
                        {
                            chooseDataForShow(ICHOOSEANIMALS);
                        }else if(gMyChoose == ICHOOSEANIMALS){

                            GifDrawActivity.actionStart(MainActivity.this,Data.GetRandomKeyFromGifMap(),GifMap.get(Data.GetRandomKeyFromGifMap()).toString());
                        }

                        return true;
                    }
                    if (e2.getX() - e1.getX() > FLIP_DISTANCE) {

                        Log.i("MYTAG", "向右滑...");

                        if(gMyChoose == ICHOOSEANIMALS)
                        {
                            chooseDataForShow(ICHOOSEFRUITS);
                        }
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
        setContentView(R.layout.activity_main);
        chooseDataForShow(ICHOOSEFRUITS);

        mDetector = new GestureDetector(this,new MyGestureListener());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setOnTouchListener(new MyGestureListener());
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    */

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
                GifDrawActivity.actionStart(this,Data.GetRandomKeyFromGifMap(),GifMap.get(Data.GetRandomKeyFromGifMap()).toString());
                break;

            default:
                break;
        }
        return true;
    }

    private void chooseDataForShow(CHOOSE choose){

        itemList.clear();
        initList(choose);

        switch (choose){
            case ICHOOSEFRUITS:
                gMyChoose = ICHOOSEFRUITS;
                this.setTitle("Roll and Pick"+"  Fruits");
                break;
            case ICHOOSEANIMALS:
                gMyChoose = ICHOOSEANIMALS;
                this.setTitle("Roll and Pick"+"  Animals");
                break;
            case ICHOOSEGIFS:
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

    private void initList(CHOOSE choose)
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

            case ICHOOSEGIFS:
                break;

            default:
                break;
        }
    }
}
