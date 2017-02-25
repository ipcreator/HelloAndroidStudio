package me.ipcreator.recyclerviewtest;

import android.os.Environment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by user on 2/24/2017.
 */



public class Data {

    public static final float FLIP_DISTANCE = 50;

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
        }
    };

    static final Map<String, String> GifMap = new HashMap<String, String>() {
        {
            put("Anim", Integer.toString(R.drawable.anim));
            put("Giftest", Integer.toString(R.drawable.giftest));
            put("Giftest1", Integer.toString(R.drawable.giftest1));
            put("Giftest2", Integer.toString(R.drawable.giftest2));
            put("Gif1", Integer.toString(R.drawable.gif1));
            put("Gif2", Integer.toString(R.drawable.gif2));
            put("Gif3", Integer.toString(R.drawable.gif3));
            put("Gif10", Integer.toString(R.drawable.gif10));
            put("A", Integer.toString(R.drawable.a));
            put("Wind", Integer.toString(R.drawable.wind));
            put("dogjump", Integer.toString(R.drawable.dogjump));
            put("twodog", Integer.toString(R.drawable.twodog));
            put("smokingcat", Integer.toString(R.drawable.smokingcat));
            put("drinkingcat", Integer.toString(R.drawable.drinkingcat));
            put("funningboy", Integer.toString(R.drawable.funningboy));
            put("dancinggirl", Integer.toString(R.drawable.dancinggirl));
            put("ballet", Integer.toString(R.drawable.birdman));
            put("jiemao", Integer.toString(R.drawable.jiemao));
            put("zhoulibo", Integer.toString(R.drawable.zhoulibo));
            put("benshan", Integer.toString(R.drawable.benshan));
            put("touch", Integer.toString(R.drawable.touch));
            put("cock", Integer.toString(R.drawable.cock));
            put("sleep", Integer.toString(R.drawable.sleep));
            put("handsome", Integer.toString(R.drawable.handsome));
            put("misswang", Integer.toString(R.drawable.misswang));
            put("baby", Integer.toString(R.drawable.baby));
            put("timg", Integer.toString(R.drawable.timg));
            put("following", Integer.toString(R.drawable.following));
            put("swaving", Integer.toString(R.drawable.swaving));
            put("songxiaobao", Integer.toString(R.drawable.songxiaobao));
            put("jordan1", Integer.toString(R.drawable.jordan1));
            put("jordan2", Integer.toString(R.drawable.jordan2));
            put("jordan23", Integer.toString(R.drawable.jordan23));
            put("jordan4", Integer.toString(R.drawable.jordan4));
            put("jordan5", Integer.toString(R.drawable.jordan5));
        }
    };

    public static String GetRandomKeyFromGifMap()
    {
        int size = GifMap.size();
        Random random=new Random();
        int result=random.nextInt(size);

        Set keys = GifMap.keySet( );
        Iterator<Object> iterator = keys.iterator();
        Object key=null;
        int i = 0;
        while(iterator.hasNext()){
            key = iterator.next( );
            if(i==result){
                break;
            }
            i++;
        }
        if (key != null)
        {
            return key.toString();
        }

        return null;
    }

    //TODO 从指定文件目录中取出随机文件，根据后缀过滤掉非gif文件
    public static String GetRandomFileFromDirectory()
    {
        //获取存储卡路径、构成保存文件的目标路径
        String fileName = "";
        //SD卡具有读写权限、指定附件存储路径为SD卡上指定的文件夹
        fileName = Environment.getExternalStorageDirectory()+"/Gif/"+"giftest2.gif";

        return fileName;

        /*
         GifDrawable gifDrawable;
        //Uri
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("http://olwhe6eha.bkt.clouddn.com/benshan.gif");
        try {
            gifDrawable = new GifDrawable( contentResolver,  uri);
            gifView.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
