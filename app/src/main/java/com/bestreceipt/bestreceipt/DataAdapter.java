package com.bestreceipt.bestreceipt;

/**
 * Created by pc4 on 10-02-2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<String> PetrolArray;

    public DataAdapter(ArrayList<String> PetrolArray) {
        this.PetrolArray = PetrolArray;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_petrol.setText(PetrolArray.get(i));
    }

    @Override
    public int getItemCount() {
        return PetrolArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_petrol;
        public ViewHolder(View view) {
            super(view);

            tv_petrol = (TextView)view.findViewById(R.id.tv_petrol);
        }
    }

}