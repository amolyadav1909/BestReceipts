package com.bestreceipt.bestreceipt;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by pc4 on 09-02-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    Context context1;
    ArrayList<Integer> alImage;

    public RecyclerViewAdapter(Context context2,ArrayList<Integer> alImage){
        super();
        this.context1 = context2;
        this.alImage = alImage;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context1).inflate(R.layout.recycler_view_items,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(alImage.get(position));
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context1, "#" + position + " - " + alImage.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(context1, CameraActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context1.startActivity(i);
                } else {
                    Toast.makeText(context1, "#" + position + " - " + alImage.get(position), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context1, CameraActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context1.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  alImage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public ImageView imageView;
        private ItemClickListener clickListener;
        public ViewHolder(View v){
            super(v);
            imageView = (ImageView) v.findViewById(R.id.iv);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }
        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getPosition(), true);
            return true;
        }
    }
}
