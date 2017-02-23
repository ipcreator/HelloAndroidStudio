package me.ipcreator.recyclerviewtest;

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
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    private List<Animal> animalList = new ArrayList<>();

    final int ICHOOSEFRUITS = 0;
    final int ICHOOSEANIMALS = 1;
    final int ICHOOSECARS = 2;
    final int ICHOOSEFOODS = 3;

    private static final String TAG = "MainActivity";

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
                chooseDataForShow(ICHOOSECARS);
                break;
            case R.id.food_item:
                chooseDataForShow(ICHOOSEFOODS);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseDataForShow(ICHOOSEFRUITS);
        Log.d(TAG, "onCreate: ");
    }

    private void chooseDataForShow(int choose){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        fruitList.clear();
        animalList.clear();

        switch (choose){
            case ICHOOSEFRUITS:
                initFruits();
                FruitAdapter fruitadapter = new FruitAdapter(fruitList);
                recyclerView.setAdapter(fruitadapter);
                this.setTitle("Roll and Pick"+"  Fruits");
                break;
            case ICHOOSEANIMALS:
                initAnimals();
                AnimalAdapter animaladapter = new AnimalAdapter(animalList);
                recyclerView.setAdapter(animaladapter);
                this.setTitle("Roll and Pick"+"  Animals");
                break;
            case ICHOOSECARS:
                this.setTitle("Roll and Pick"+"  Cars");
                break;
            case ICHOOSEFOODS:
                this.setTitle("Roll and Pick"+"  Foods");
                break;
            default:
                break;
        }
    }


    private void initFruits(){

        for(int i = 0; i < 100; i++){

            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    private void initAnimals(){

        for(int i = 0; i < 100; i++){

            Animal cat = new Animal("Cat", R.drawable.cat_pic);
            animalList.add(cat);
            Animal dog = new Animal("Dog", R.drawable.dog_pic);
            animalList.add(dog);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onBackPressed() {
        finish();
        Log.d(TAG, "onBackPressed: ");
    }
}
