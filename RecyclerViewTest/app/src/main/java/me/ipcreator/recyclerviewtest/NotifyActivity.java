package me.ipcreator.recyclerviewtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

import static me.ipcreator.recyclerviewtest.Data.NOTIFY_MUSIC_FILE_PATH;

public class NotifyActivity extends AppCompatActivity implements View.OnClickListener{

    NotificationManager manager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        Button sendNotice = (Button)findViewById(R.id.send_notice);
        Button closeNotice = (Button)findViewById(R.id.close_notice);
        Button clearNotice = (Button)findViewById(R.id.clear_notice);

        sendNotice.setOnClickListener(this);
        closeNotice.setOnClickListener(this);
        clearNotice.setOnClickListener(this);

        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,NotifyActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.send_notice:

                Intent intent = new Intent(this, NotifyDisplayActivity.class);
                PendingIntent pi = PendingIntent.getActivities(this,0, new Intent[]{intent},0);

                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("Title")
                        //.setContentText("Content")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to biuld notifications, send and sync data, and use Voice actions." +
                                "Get the official Android IDE and developer tools to build apps for Android."))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.title_bg)))
                        .setContentIntent(pi)
                        //.setAutoCancel(true)
                        //.setSound(Uri.fromFile(new File(NOTIFY_MUSIC_FILE_PATH)))
                        //.setVibrate(new long[]{0,1000,1000,1000})
                        //.setLights(Color.GREEN,1000,1000)
                        //.setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();

                manager.notify(1,notification);
                break;

            case R.id.close_notice:
                manager.cancel(1);
                break;

            case R.id.clear_notice:
                manager.cancel(1);
                break;
            default:
                break;
        }
    }
}
