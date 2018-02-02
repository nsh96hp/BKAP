package com.test.cookcook.ui.cook;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.cookcook.R;
import com.test.cookcook.data.ReadJSon;
import com.test.cookcook.data.entity.Comment;
import com.test.cookcook.data.entity.Cooked;
import com.test.cookcook.data.entity.Ingredients;
import com.test.cookcook.data.entity.Steps;
import com.test.cookcook.ui.cook.detailCooked.DetailCookedFragment;
import com.test.cookcook.ui.cook.dfCooked.AdapterDfc;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CookedFragment extends Fragment {

    Context mContext;
    FragmentManager fragmentManager;
    RecyclerView rcTitle1;
    ArrayList<Cooked> lstCooked = new ArrayList<>();
    ArrayList<Ingredients> lstIngredients = new ArrayList<>();
    ArrayList<Steps> lstSteps = new ArrayList<>();
    ArrayList<Comment> lstComment = new ArrayList<>();
    AdapterDfc adapterDfc;
    ImageView imageView;
    DatabaseReference mData;


    AssetManager assetManager;

    public CookedFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public CookedFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cooked, container, false);

        mData = FirebaseDatabase.getInstance().getReference();

        lstCooked = ReadJSon.ReadCookedJsonFile(mContext);

        rcTitle1 = rootView.findViewById(R.id.rcTitle1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapterDfc = new AdapterDfc(mContext, lstCooked);
        rcTitle1.setLayoutManager(layoutManager);
        rcTitle1.setAdapter(adapterDfc);

        adapterDfc.setOnItemClickListener(new AdapterDfc.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                fragmentManager = getFragmentManager();
                FragmentTransaction tran = fragmentManager.beginTransaction();
                //Gui du lieu
                Bundle bundle = new Bundle();
                bundle.putSerializable("TAG_Cooked", lstCooked.get(position));
                Fragment fragment = new DetailCookedFragment(mContext);
                fragment.setArguments(bundle);
                tran.replace(R.id.frame_layout, fragment);//Add co the tro ve hoac replace? lam cai nao?
                tran.commit();
            }
        });


        imageView = rootView.findViewById(R.id.img_test);
        DownloadImageTask downloadImageTask = new DownloadImageTask(imageView);
        downloadImageTask.execute("https://firebasestorage.googleapis.com/v0/b/cookcook-c277c.appspot.com/o/Cooked%2F783b18ce-bdc5-4a2c-86ef-5ba65e2bcd38?alt=media&token=5f0a7fca-1379-4c30-be89-3619a8229390");
//        webview_test=rootView.findViewById(R.id.webview_test);
//        webview_test.getSettings().setJavaScriptEnabled(true);
//        webview_test.setWebChromeClient(new WebChromeClient());
//        webview_test.setWebViewClient(new WebViewClient());
//        webview_test.loadUrl("https://firebasestorage.googleapis.com/v0/b/cookcook-c277c.appspot.com/o/Cooked%2F783b18ce-bdc5-4a2c-86ef-5ba65e2bcd38?alt=media&token=5f0a7fca-1379-4c30-be89-3619a8229390");
        return rootView;
    }

    //
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
