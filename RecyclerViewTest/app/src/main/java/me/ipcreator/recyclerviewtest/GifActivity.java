package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import  me.ipcreator.recyclerviewtest.GifView;


public class GifActivity extends AppCompatActivity implements View.OnClickListener{

    private GifView gifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        gifView = (GifView)findViewById(R.id.gif_common);
        gifView.setOnClickListener(this);
        showNameAndPic();
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,GifActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    private void showNameAndPic(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String pic = intent.getStringExtra("IMG");
        this.setTitle(name);

        //gifView.setGifImageType(GifView.GifImageType.COVER);
        //gifView.setShowDimension(1000, 1000);
        gifView.setGifImage(Integer.parseInt(pic));

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.image_common:
                finish();
                break;
            default:
                break;
        }
    }
}
