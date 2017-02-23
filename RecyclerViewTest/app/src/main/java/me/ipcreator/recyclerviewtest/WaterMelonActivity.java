package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WaterMelonActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_melon);
        imageView = (ImageView)findViewById(R.id.image_watermelon);
        imageView.setOnClickListener(this);
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,WaterMelonActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.image_watermelon:
                finish();
                break;
            default:
                break;
        }

    }
}
