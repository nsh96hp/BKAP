package com.test.cookcook.ui.cook.dfCooked;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by nsh96 on 2/3/2018.
 */

public class AdapterOnline extends RecyclerView.Adapter<AdapterOnline.ViewHolder> {

    Context mContext;
    ArrayList<Cooked> lstCooked= new ArrayList<>();


    private static AdapterDfc.OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(View itemView,int position);
    }
    public void setOnItemClickListener(AdapterDfc.OnItemClickListener listener){
        this.listener=listener;
    }
    public AdapterOnline(Context mContext, ArrayList<Cooked> lstCooked) {
        this.mContext = mContext;
        this.lstCooked = lstCooked;
    }

    @Override
    public AdapterOnline.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item_cooked,parent,false);
        return new AdapterOnline.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterOnline.ViewHolder holder, int position) {
        DownloadImageTask downloadImageTask = new DownloadImageTask(holder.item_imagecook);
        downloadImageTask.execute("https://firebasestorage.googleapis.com/v0/b/cookcook-c277c.appspot.com/o/Cooked%2F"+lstCooked.get(position).getImage()+"?alt=media&token=5f0a7fca-1379-4c30-be89-3619a8229390");
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
    public void add(Cooked cooked){
        lstCooked.add(cooked);
        //Collections.sort(mListData);
        notifyItemInserted(lstCooked.size());
        notifyItemRangeChanged(0,lstCooked.size());
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
