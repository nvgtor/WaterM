package com.water.nvgtor.watermanegement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.activity.PatrolTaskListActivity;
import com.water.nvgtor.watermanegement.adapter.MyGridAdapter;

/**
 * Created by dell on 2015/7/22.
 */
public class FunFragment extends Fragment {
    //String text;
    private GridView gridView;
    public String[] img_text = {"巡检", "维修", "临时任务", "事件上报",
            "巡检地图", "通讯录", "系统设置", "再瞅一个", "瞅你咋地"};
    public int[] imgs = {R.drawable.app_citycard, R.drawable.app_appcenter,
            R.drawable.app_assign, R.drawable.app_aligame, R.drawable.app_coupon,
            R.drawable.app_essential, R.drawable.app_exchange, R.drawable.app_facepay,
            R.drawable.app_creditcard};

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
        MyGridAdapter adapter = new MyGridAdapter(getActivity().getApplicationContext(), img_text, imgs);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), PatrolTaskListActivity.class);
                        startActivity(intent);
                        //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                }
                Toast.makeText(getActivity().getApplicationContext(), "you clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
