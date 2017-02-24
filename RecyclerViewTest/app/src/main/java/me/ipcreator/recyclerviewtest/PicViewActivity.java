package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class PicViewActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_view);
        imageView = (ImageView)findViewById(R.id.image_common);
        imageView.setOnClickListener(this);
        showNameAndPic();
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,PicViewActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    private void showNameAndPic(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String pic = intent.getStringExtra("IMG");
        this.setTitle(name);
        imageView.setImageResource(Integer.parseInt(pic));
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
