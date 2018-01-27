package com.test.cookcook.ui.defaultCooked;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.cookcook.R;
import com.test.cookcook.data.ReadJSon;
import com.test.cookcook.data.entity.Comment;
import com.test.cookcook.data.entity.Cooked;
import com.test.cookcook.data.entity.Ingredients;
import com.test.cookcook.data.entity.Steps;
import com.test.cookcook.ui.defaultCooked.detailCooked.DetailCookedFragment;
import com.test.cookcook.ui.defaultCooked.dfCooked.AdapterDfc;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CookedFragment extends Fragment {

    Context mContext;
    FragmentManager fragmentManager;
    RecyclerView rcTitle1;
    ArrayList<Cooked> lstCooked= new ArrayList<>();
    ArrayList<Ingredients> lstIngredients= new ArrayList<>();
    ArrayList<Steps> lstSteps= new ArrayList<>();
    ArrayList<Comment> lstComment= new ArrayList<>();
    AdapterDfc adapterDfc;

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
        View rootView= inflater.inflate(R.layout.fragment_cooked, container, false);

        mData= FirebaseDatabase.getInstance().getReference();

        lstCooked= ReadJSon.ReadCookedJsonFile(mContext);

        rcTitle1=rootView.findViewById(R.id.rcTitle1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapterDfc=new AdapterDfc(mContext,lstCooked);
        rcTitle1.setLayoutManager(layoutManager);
        rcTitle1.setAdapter(adapterDfc);

        adapterDfc.setOnItemClickListener(new AdapterDfc.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                fragmentManager=getFragmentManager();
                FragmentTransaction tran=fragmentManager.beginTransaction();
                //Gui du lieu
                Bundle bundle= new Bundle();
                bundle.putSerializable("TAG_Cooked",lstCooked.get(position));
                Fragment fragment= new DetailCookedFragment(mContext);
                fragment.setArguments(bundle);
                tran.replace(R.id.frame_layout,fragment);//Add co the tro ve hoac replace? lam cai nao?
                tran.commit();
            }
        });
        return rootView;
    }
//

}
