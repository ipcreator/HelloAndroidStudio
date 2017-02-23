package me.ipcreator.uibestpractice;

/**
 * Created by user on 2/23/2017.
 */

public class Msg {
    public  static final int TYPE_RECEIVED = 0;
    public  static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public Msg(String content,int type){
        this.content = content;
        this.type=type;
    }

    public String getContent(){
        return content;
    }

    public int getType(){
        return type;
    }
}
