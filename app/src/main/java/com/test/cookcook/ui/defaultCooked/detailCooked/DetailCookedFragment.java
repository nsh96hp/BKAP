package com.test.cookcook.ui.defaultCooked.detailCooked;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.cookcook.R;
import com.test.cookcook.data.entity.Cooked;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailCookedFragment extends Fragment {
    Context mContext;
    TextView detail_namecook;
    @SuppressLint("ValidFragment")
    public DetailCookedFragment(Context mContext) {
        this.mContext = mContext;
    }

    public DetailCookedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_detail_cooked, container, false);

        Bundle bundle= getArguments();
        Cooked cooked=(Cooked)bundle.getSerializable("TAG_Cooked");
        detail_namecook=rootView.findViewById(R.id.detail_namecook);
        detail_namecook.setText(cooked.getName());
        return rootView;
    }

}
