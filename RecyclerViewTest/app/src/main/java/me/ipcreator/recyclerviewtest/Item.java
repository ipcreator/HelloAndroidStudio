package me.ipcreator.recyclerviewtest;

/**
 * Created by user on 2/24/2017.
 */

public class Item {
    private String name;
    private int imageId;

    public Item(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
