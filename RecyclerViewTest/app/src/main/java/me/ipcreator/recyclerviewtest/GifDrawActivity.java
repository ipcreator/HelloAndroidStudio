package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class GifDrawActivity extends AppCompatActivity implements View.OnClickListener{

    private GifImageView gifView;
    private MediaController mc=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_draw);
        gifView = (GifImageView)findViewById(R.id.gifImageView);
        gifView.setOnClickListener(this);
        showNameAndPic();
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,GifDrawActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    private void showNameAndPic(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String pic = intent.getStringExtra("IMG");
        this.setTitle(name);

        gifView.setImageResource(Integer.parseInt(pic));

        mc = new MediaController(this);
        mc.setMediaPlayer( ( GifDrawable ) gifView.getDrawable() );
        mc.setAnchorView( gifView );
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gifImageView:
                if(mc.isShowing())
                {
                    mc.hide();
                }else{
                    mc.show();
                }
                break;
            default:
                break;
        }
    }
}