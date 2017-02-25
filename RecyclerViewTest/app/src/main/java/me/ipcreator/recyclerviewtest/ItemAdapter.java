package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import static me.ipcreator.recyclerviewtest.Data.PicMap;

/**
 * Created by user on 2/24/2017.
 */


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> mItemList;

    public ItemAdapter(List<Item> ItemList){
        mItemList = ItemList;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView itemImage;
        TextView itemName;

        public ViewHolder(View view){
            super(view);
            itemView = view;
            itemImage = (ImageView) view.findViewById(R.id.item_image);
            itemName = (TextView)view.findViewById(R.id.item_name);
        }
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        final Context context = parent.getContext();
        boolean bAnimation = false;

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemList.get(position);
                PicViewActivity.actionStart(context,item.getName(),PicMap.get(item.getName()));
            }
        });

        holder.itemImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemList.get(position);
                PicViewActivity.actionStart(context,item.getName(),PicMap.get(item.getName()));
            }
        });

        holder.itemName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemList.get(position);
                PicViewActivity.actionStart(context,item.getName(),PicMap.get(item.getName()));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        holder.itemImage.setImageResource(item.getImageId());
        holder.itemName.setText(item.getName());
    }

}
