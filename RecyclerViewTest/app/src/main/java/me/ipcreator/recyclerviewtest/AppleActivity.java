package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import static android.R.attr.onClick;

public class AppleActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);

        imageView = (ImageView)findViewById(R.id.image_apple);
        imageView.setOnClickListener(this);

    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,AppleActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.image_apple:
                finish();
                break;
            default:
                break;
        }

    }
}
