package me.ipcreator.recyclerviewtest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2/24/2017.
 */

public class Data {

    static final Map<String, String> fruitListMap = new HashMap<String, String>() {
        {
            put("Apple", Integer.toString(R.drawable.apple_pic));
            put("Banana", Integer.toString(R.drawable.banana_pic));
            put("Orange", Integer.toString(R.drawable.orange_pic));
            put("Watermelon", Integer.toString(R.drawable.watermelon_pic));
            put("Pear", Integer.toString(R.drawable.pear_pic));
            put("Grape", Integer.toString(R.drawable.grape_pic));
            put("Pineapple", Integer.toString(R.drawable.pineapple_pic));
            put("Strawberry", Integer.toString(R.drawable.strawberry_pic));
            put("Cherry", Integer.toString(R.drawable.cherry_pic));
            put("Mango", Integer.toString(R.drawable.mango_pic));
        }
    };

    static final Map<String, String> animalListMap = new HashMap<String, String>() {
        {
            put("Cat", Integer.toString(R.drawable.cat_pic));
            put("Dog", Integer.toString(R.drawable.dog_pic));
        }
    };

    static final Map<String, String> PicMap = new HashMap<String, String>() {
        {
            put("Apple", Integer.toString(R.drawable.apple));
            put("Banana", Integer.toString(R.drawable.banana));
            put("Orange", Integer.toString(R.drawable.orange));
            put("Watermelon", Integer.toString(R.drawable.watermelon));
            put("Pear", Integer.toString(R.drawable.pear));
            put("Grape", Integer.toString(R.drawable.grape));
            put("Pineapple", Integer.toString(R.drawable.pineapple));
            put("Strawberry", Integer.toString(R.drawable.strawberry));
            put("Cherry", Integer.toString(R.drawable.cherry));
            put("Mango", Integer.toString(R.drawable.mango));
            put("Cat", Integer.toString(R.drawable.cat));
            put("Dog", Integer.toString(R.drawable.dog));
            put("Anim", Integer.toString(R.drawable.giftest));
            put("Conan", Integer.toString(R.drawable.conan));
        }
    };
}
