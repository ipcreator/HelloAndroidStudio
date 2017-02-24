package me.ipcreator.recyclerviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static me.ipcreator.recyclerviewtest.Data.PicMap;

public class MainActivity extends AppCompatActivity {

    private static final int ICHOOSEFRUITS = 0;
    private static final int ICHOOSEANIMALS = 1;
    private static final int ICHOOSECARS = 2;
    private static final int ICHOOSEFOODS = 3;

    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseDataForShow(ICHOOSEFRUITS);
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
            case R.id.car_item:
                //chooseDataForShow(ICHOOSECARS);
                AnimationActivity.actionStart(this,null,null);
                Toast.makeText(MainActivity.this,"AnimationActivity.actionStart(this,null,null);",Toast.LENGTH_SHORT).show();
                break;
            case R.id.food_item:
                //chooseDataForShow(ICHOOSEFOODS);
                AnimationActivity.actionStart(this,"Conan",PicMap.get("Conan"));
                Toast.makeText(MainActivity.this,"AnimationActivity.actionStart(this,\"Conan\",PicMap.get(\"Conan\"));",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    private void chooseDataForShow(int choose){

        itemList.clear();
        initList(choose);

        switch (choose){
            case ICHOOSEFRUITS:
                this.setTitle("Roll and Pick"+"  Fruits");
                break;
            case ICHOOSEANIMALS:
                this.setTitle("Roll and Pick"+"  Animals");
                break;
            case ICHOOSECARS:
                this.setTitle("Roll and Pick"+"  Cars");
                break;
            case ICHOOSEFOODS:
                this.setTitle("Roll and Pick"+"  Foods");
                break;
            default:
                this.setTitle("Roll and Pick"+"  Fruits");
                break;
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
    }

    private void initList(int choose)
    {
        switch (choose){

            case ICHOOSEANIMALS:
                for(int i = 0; i<100; i++){
                    for (Map.Entry<String, String> entry : Data.animalListMap.entrySet()) {
                        Item animal = new Item(entry.getKey(), Integer.parseInt(entry.getValue()));
                        itemList.add(animal);
                    }
                }
                break;

            case ICHOOSECARS:
                break;

            case ICHOOSEFOODS:
                break;

            case ICHOOSEFRUITS:
            default:
                for(int i = 0; i<100; i++){
                    for (Map.Entry<String, String> entry : Data.fruitListMap.entrySet()) {
                        Item fruit = new Item(entry.getKey(), Integer.parseInt(entry.getValue()));
                        itemList.add(fruit);
                    }
                }
                break;
        }
    }
}
