package me.ipcreator.recyclerviewtest;

/**
 * Created by user on 2/23/2017.
 */

public class Animal {
    private String name;
    private int imageId;

    public Animal(String name, int imageId){
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
