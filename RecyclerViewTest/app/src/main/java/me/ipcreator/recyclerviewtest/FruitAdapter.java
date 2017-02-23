package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by user on 2/23/2017.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    private static final String TAG = "FruitAdapter";

    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruiImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            fruitView = view;
            fruiImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView)view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList){
        mFruitList = fruitList;
    }

    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        //final parentViewGroup = new ViewGroup();
        final Context context = parent.getContext();

        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                //Toast.makeText(v.getContext(),"you clicked view"+fruit.getName(), Toast.LENGTH_SHORT).show();

                // 执行窗口切换窗口
                switchActivity(context,fruit);
            }
        });

        holder.fruiImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                //Toast.makeText(v.getContext(),"you clicked image"+fruit.getName(), Toast.LENGTH_SHORT).show();

                // 执行窗口切换窗口
                switchActivity(context,fruit);
            }
        });

        holder.fruitName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                //Toast.makeText(v.getContext(),"you clicked name"+fruit.getName(), Toast.LENGTH_SHORT).show();

                // 执行窗口切换窗口
                switchActivity(context,fruit);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruiImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    private void switchActivity(Context context,Fruit fruit)
    {
        Log.d(TAG, "switchActivity: " + fruit.getName());

        switch (fruit.getName()){
            case "Apple":
                AppleActivity.actionStart(context,null,null);
                break;
            case "Banana":
                BananaActivity.actionStart(context,null,null);
                break;
            case "Orange":
                OrangeActivity.actionStart(context,null,null);
                break;
            case "Watermelon":
                WaterMelonActivity.actionStart(context,null,null);
                break;
            case "Pear":
                PearActivity.actionStart(context,null,null);
                break;
            case "Grape":
                GrapeActivity.actionStart(context,null,null);
                break;
            case "Pineapple":
                PineAppleActivity.actionStart(context,null,null);
                break;
            case "Strawberry":
                StrawBerryActivity.actionStart(context,null,null);
                break;
            case "Cherry":
                CherryActivity.actionStart(context,null,null);
                break;
            case "Mango":
                MangoActivity.actionStart(context,null,null);
                break;
            default:
            break;

        }

    }
}
