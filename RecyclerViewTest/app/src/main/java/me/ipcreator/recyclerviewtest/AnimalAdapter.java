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

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private List<Animal> mAnimalList;

    private static final String TAG="AnimalAdapter";

    public AnimalAdapter(List<Animal> animalList){
        mAnimalList = animalList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item,parent,false);
        final AnimalAdapter.ViewHolder holder = new AnimalAdapter.ViewHolder(view);
        final Context context = parent.getContext();

        holder.animalView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Animal animal = mAnimalList.get(position);
                //Toast.makeText(v.getContext(),"you clicked view"+animal.getName(), Toast.LENGTH_SHORT).show();

                // 执行窗口切换窗口
                switchActivity(context,animal);
            }
        });

        holder.animalImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Animal animal = mAnimalList.get(position);
                //Toast.makeText(v.getContext(),"you clicked image"+animal.getName(), Toast.LENGTH_SHORT).show();

                // 执行窗口切换窗口
                switchActivity(context,animal);
            }
        });

        holder.animalName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Animal animal = mAnimalList.get(position);
                //Toast.makeText(v.getContext(),"you clicked name"+animal.getName(), Toast.LENGTH_SHORT).show();

                // 执行窗口切换窗口
                switchActivity(context,animal);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal = mAnimalList.get(position);
        holder.animalImage.setImageResource(animal.getImageId());
        holder.animalName.setText(animal.getName());
    }

    @Override
    public int getItemCount() {
        return mAnimalList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        View animalView;
        ImageView animalImage;
        TextView animalName;

        public ViewHolder(View view){
            super(view);
            animalView = view;
            animalImage = (ImageView) view.findViewById(R.id.animal_image);
            animalName = (TextView)view.findViewById(R.id.animal_name);
        }
    }

    private void switchActivity(Context context, Animal animal)
    {
        Log.d(TAG, "switchActivity: " + animal.getName());

        switch (animal.getName()){
            case "Cat":
                CatActivity.actionStart(context,null,null);
                break;
            case "Dog":
                DogActivity.actionStart(context,null,null);
                break;
            default:
                break;

        }

    }

    }
