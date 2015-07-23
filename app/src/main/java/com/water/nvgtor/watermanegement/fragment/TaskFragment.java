package com.water.nvgtor.watermanegement.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/7/23.
 */
public class TaskFragment extends Fragment {
    String text;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_task, null);
        return view;
    }
}
