package me.ipcreator.recyclerviewtest;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NotifyDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_display);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        MyUtility.ShowInfoDialog(NotifyDisplayActivity.this,"NotificationManager value of manager", manager.toString());
        manager.cancel(1);

    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,NotifyDisplayActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }
}
