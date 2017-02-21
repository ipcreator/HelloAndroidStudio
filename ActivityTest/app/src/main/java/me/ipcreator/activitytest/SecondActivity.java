package me.ipcreator.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Task id is"+getTaskId());
        //Log.d(TAG, getClass().getSimpleName());

        setContentView(R.layout.second_layout);

        //Intent intent = getIntent();
        //String data = intent.getStringExtra("extra_data");
        //Log.d("SecondActivity", data);

        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);

                //Intent intent = getIntent();
                //intent.putExtra("data_return", "Hello FirstActivity");
                //setResult(RESULT_OK, intent);
                //finish();
            }
        });
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("data_return", "Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
