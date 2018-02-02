package com.test.cookcook.ui.cook.addCooked;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.cookcook.R;
import com.test.cookcook.data.entity.Steps;

import java.util.ArrayList;

/**
 * Created by nsh96 on 1/31/2018.
 */

public class AdapterAdd extends RecyclerView.Adapter<AdapterAdd.ViewHolder>{

    Context mContext;
//    ArrayList<Cooked> lstCooked= new ArrayList<>();
//    ArrayList<Ingredients> lstIngredients= new ArrayList<>();
    ArrayList<Steps> lstSteps= new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item_steps,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_steps_num.setText(lstSteps.get(position).getNum());
        holder.item_steps_name.setText(lstSteps.get(position).getName());
        holder.item_steps_time.setText(lstSteps.get(position).getTime()+"");
        holder.item_steps_unit.setText(lstSteps.get(position).getNum());

        
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_steps_num,item_steps_name,item_steps_time,item_steps_unit;
        ImageView item_steps_img;
        public ViewHolder(View itemView) {
            super(itemView);
            item_steps_num=itemView.findViewById(R.id.item_steps_num);
            item_steps_name=itemView.findViewById(R.id.item_steps_name);
            item_steps_time=itemView.findViewById(R.id.item_steps_time);
            item_steps_unit=itemView.findViewById(R.id.item_steps_unit);
            item_steps_img=itemView.findViewById(R.id.item_steps_img);

        }
    }
}
