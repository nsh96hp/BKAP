package com.test.cookcook.ui.defaultCooked.dfCooked;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.cookcook.R;
import com.test.cookcook.data.entity.Comment;
import com.test.cookcook.data.entity.Cooked;
import com.test.cookcook.data.entity.Ingredients;
import com.test.cookcook.data.entity.Steps;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by NSH on 10/01/2018.
 */

public class AdapterDfc extends RecyclerView.Adapter<AdapterDfc.ViewHolder>{
    Context mContext;
    ArrayList<Cooked> lstCooked= new ArrayList<>();
    ArrayList<Ingredients> lstIngredients= new ArrayList<>();
    ArrayList<Steps> lstSteps= new ArrayList<>();
    ArrayList<Comment> lstComment= new ArrayList<>();


//    public AdapterDfc(Context mContext, ArrayList<Cooked> lstCooked, ArrayList<Ingredients> lstIngredients, ArrayList<Steps> lstSteps, ArrayList<Comment> lstComment) {
//        this.mContext = mContext;
//        this.lstCooked = lstCooked;
//        this.lstIngredients = lstIngredients;
//        this.lstSteps = lstSteps;
//        this.lstComment = lstComment;
//    }
    private static OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(View itemView,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public AdapterDfc(Context mContext, ArrayList<Cooked> lstCooked) {
        this.mContext = mContext;
        this.lstCooked = lstCooked;
    }

    AssetManager assetManager;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item_cooked,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        assetManager=mContext.getAssets();
        try {
            InputStream is= assetManager.open("image/"+lstCooked.get(position).getImage()+"");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.item_imagecook.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.item_namecook.setText(lstCooked.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return lstCooked.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_imagecook;
        TextView item_namecook;
        public ViewHolder(final View itemView) {
            super(itemView);
            item_imagecook=itemView.findViewById(R.id.item_imagecook);
            item_namecook=itemView.findViewById(R.id.item_namecook);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });
        }
    }
}
