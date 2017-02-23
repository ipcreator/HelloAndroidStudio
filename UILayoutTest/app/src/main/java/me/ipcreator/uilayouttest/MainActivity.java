package me.ipcreator.uilayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.to_relative);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.to_frame);
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.to_percent);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_relative:
                Log.d(TAG, "R.id.to_relative onClick: ");
                RelativeLayoutActivity.actionStart(MainActivity.this,"data1","data2");
                break;
            case R.id.to_frame:
                Log.d(TAG, "R.id.to_frame onClick: ");
                FrameLayoutActivity.actionStart(MainActivity.this,"data1","data2");
                break;
            case R.id.to_percent:
                Log.d(TAG, "R.id.to_percent onClick: ");
                PercentLayoutActivity.actionStart(MainActivity.this,"data1","data2");
                break;
            default:
                break;
        }
    }
}
