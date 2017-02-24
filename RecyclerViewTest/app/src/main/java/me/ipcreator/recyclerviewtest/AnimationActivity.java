package me.ipcreator.recyclerviewtest;

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{

    private PowerImageView animationView;
    private static final String TAG = "AnimationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        animationView = (PowerImageView)findViewById(R.id.animation_common);
        //animationView.setAutoPlay(true);
        animationView.setOnClickListener(this);
        showNameAndAnimation();
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,AnimationActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }

    private void showNameAndAnimation(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String pic = intent.getStringExtra("IMG");
        if(name != null)
        {
            this.setTitle(name);
        }
        if(pic != null)
        {
            animationView.setImageResource(Integer.parseInt(pic));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.animation_common:
                //finish();
                break;
            default:
                break;
        }
    }
}
