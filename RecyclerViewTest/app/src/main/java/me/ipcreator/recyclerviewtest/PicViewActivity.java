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
    private static final String TAG = "PicViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_view);
        imageView = (ImageView)findViewById(R.id.image_common);
        imageView.setOnClickListener(this);
        showNameAndPic();
        Log.d(TAG, "onCreate: ");
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

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,PicViewActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
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

    private void showNameAndPic(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String pic = intent.getStringExtra("IMG");
        this.setTitle(name);
        imageView.setImageResource(Integer.parseInt(pic));
    }
}
