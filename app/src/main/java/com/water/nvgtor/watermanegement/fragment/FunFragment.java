package com.water.nvgtor.watermanegement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.MyGridAdapter;

/**
 * Created by dell on 2015/7/22.
 */
public class FunFragment extends Fragment {
    //String text;
    private GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*Bundle args = getArguments();
        text = args != null ? args.getString("text") : "";*/
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_function, null);
        gridView = (GridView)view.findViewById(R.id.gridview);
        MyGridAdapter adapter = new MyGridAdapter(getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        return view;
    }
}
