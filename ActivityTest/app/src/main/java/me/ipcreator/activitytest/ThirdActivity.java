package me.ipcreator.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {

    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Task id is"+getTaskId());
        //Log.d(TAG, getClass().getSimpleName());

        setContentView(R.layout.third_layout);

        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}
