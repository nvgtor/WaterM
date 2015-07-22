package com.water.nvgtor.watermanegement.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/7/22.
 */
public class FunFragment extends Fragment {
    String text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        text = args != null ? args.getString("text") : "";
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.function_fragment, null);
        TextView item_textView = (TextView)view.findViewById(R.id.item_textview);
        item_textView.setText(text);
        return view;
    }
}
