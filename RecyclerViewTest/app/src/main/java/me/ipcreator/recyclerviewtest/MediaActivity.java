package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import static me.ipcreator.recyclerviewtest.MyUtility.MyLog;

public class MediaActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;

    private static final String TAG = "MediaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        videoView = (VideoView)findViewById(R.id.video_view);
        Button play = (Button)findViewById(R.id.playvideo);
        Button pause = (Button)findViewById(R.id.pausevideo);
        Button replay = (Button)findViewById(R.id.replayvideo);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(MediaActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MediaActivity.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            initVideoPath();
        }
    }

    private void initVideoPath(){

        String filePath = Environment.getExternalStorageDirectory()+"/Movies/"+"movie.mp4";
        File file = new File(filePath);

        MyLog(TAG, "initVideoPath: "+filePath);
        if(!file.exists()){
            MyUtility.ShowInfoDialog("something important", "file path error:  " + filePath);
        }

        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {

        switch(requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                }else{
                    Toast.makeText(this,"You denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.playvideo:
                if(!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.pausevideo:
                if(videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.replayvideo:
                if(videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
            default:
                break;

        }
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,MediaActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView!=null){
            videoView.suspend();
        }
    }
}
