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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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
import com.test.cookcook.ui.cook.dfCooked.AdapterOnline;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CookedFragment extends Fragment {

    Context mContext;
    FragmentManager fragmentManager;
    RecyclerView rcTitle1;
    RecyclerView rcTitle2;
    ArrayList<Cooked> lstCooked = new ArrayList<>();
    ArrayList<Cooked> lstCooked_online = new ArrayList<>();
    ArrayList<Ingredients> lstIngredients = new ArrayList<>();
    ArrayList<Steps> lstSteps = new ArrayList<>();
    ArrayList<Comment> lstComment = new ArrayList<>();
    AdapterDfc adapterDfc;
    AdapterOnline adapterOnline;
    DatabaseReference mData;



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


        lstCooked = ReadJSon.ReadCookedJsonFile(mContext);

        rcTitle1 = rootView.findViewById(R.id.rcTitle1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapterDfc = new AdapterDfc(mContext, lstCooked);
        rcTitle1.setLayoutManager(layoutManager);
        rcTitle1.setAdapter(adapterDfc);

        
        getDataFromFB();
        Log.e( "data2---lstCooked: ",lstCooked_online.size()+"");
        rcTitle2 = rootView.findViewById(R.id.rcTitle2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mContext.getApplicationContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapterOnline = new AdapterOnline(mContext, lstCooked_online);
        rcTitle2.setLayoutManager(layoutManager2);
        rcTitle2.setAdapter(adapterOnline);

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


        return rootView;
    }

    private void getDataFromFB() {
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("Cooked").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("datasnapshot-------",dataSnapshot.getValue().toString());
                    try {
                        JSONObject jsonRoot= new JSONObject(dataSnapshot.getValue().toString());
                        Cooked cooked= new Cooked();
                        cooked.setIdUser(jsonRoot.getString("idUser"));
                        cooked.setImage(jsonRoot.getString("image"));
                        cooked.setLike(jsonRoot.getInt("like"));
                        cooked.setDownload(jsonRoot.getInt("download"));
                        cooked.setPeople(jsonRoot.getInt("people"));
                        cooked.setShare(jsonRoot.getInt("share"));
                        cooked.setName(jsonRoot.getString("name"));
                        cooked.setIntro(jsonRoot.getString("intro"));
                        cooked.setIdCooked(jsonRoot.getString("idCooked"));
                        if(cooked.getIdCooked().toString().equals("UP")){
                            adapterOnline.add(cooked);
                        }
                        Log.e("Cooked----", cooked.toString() );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //
}
