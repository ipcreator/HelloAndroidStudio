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
        final Context context = parent.getContext();

        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                switchActivity(context,fruit);
            }
        });

        holder.fruiImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                switchActivity(context,fruit);
            }
        });

        holder.fruitName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
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
        switch (fruit.getName()){
            case "Apple":
                PicViewActivity.actionStart(context,"Apple",Integer.toString(R.drawable.apple));
                break;
            case "Banana":
                PicViewActivity.actionStart(context,"Banana",Integer.toString(R.drawable.banana));
                break;
            case "Orange":
                PicViewActivity.actionStart(context,"Orange",Integer.toString(R.drawable.orange));
                break;
            case "Watermelon":
                PicViewActivity.actionStart(context,"Watermelon",Integer.toString(R.drawable.watermelon));
                break;
            case "Pear":
                PicViewActivity.actionStart(context,"Pear",Integer.toString(R.drawable.pear));
                break;
            case "Grape":
                PicViewActivity.actionStart(context,"Grape",Integer.toString(R.drawable.grape));
                break;
            case "Pineapple":
                PicViewActivity.actionStart(context,"Pineapple",Integer.toString(R.drawable.pineapple));
                break;
            case "Strawberry":
                PicViewActivity.actionStart(context,"Strawberry",Integer.toString(R.drawable.strawberry));
                break;
            case "Cherry":
                PicViewActivity.actionStart(context,"Cherry",Integer.toString(R.drawable.cherry));
                break;
            case "Mango":
                PicViewActivity.actionStart(context,"Mango",Integer.toString(R.drawable.mango));
                break;
            default:
            break;
        }
    }
}
