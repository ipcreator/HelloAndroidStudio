package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static me.ipcreator.recyclerviewtest.MyUtility.MyLog;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private static final String TAG = "MusicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Button play = (Button)findViewById(R.id.playmusic);
        Button pause = (Button)findViewById(R.id.pausemusic);
        Button stop = (Button)findViewById(R.id.stopmusic);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(MusicActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MusicActivity.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            initMediaPlayPath();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.playmusic:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case R.id.pausemusic:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stopmusic:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayPath();
                }
                break;
            default:
                break;
        }
    }

    private void initMediaPlayPath(){

        String filePath = Environment.getExternalStorageDirectory()+"/Music/"+"smile.mp3";
        File file = new File(filePath);

        MyLog(TAG, "initMediaPlayPath: "+filePath);

        if(!file.exists()){
            MyUtility.ShowInfoDialog("something important", "file path error:  " + filePath);
        }

        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {

        switch(requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    initMediaPlayPath();
                }else{
                    Toast.makeText(this,"You denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,MusicActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
