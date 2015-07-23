package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.tool.BaseViewHolder;


/**
 * Created by dell on 2015/7/23.
 */
public class MyGridAdapter extends BaseAdapter {
    private Context mContext;
    public String[] img_text = {"待办巡检", "已办巡检", "事件上报", "临时任务",
            "待办维修", "已办维修", "巡检地图", "再瞅一个", "瞅你咋地"};
    public int[] imgs = {R.drawable.app_citycard, R.drawable.app_appcenter,
            R.drawable.app_assign, R.drawable.app_aligame, R.drawable.app_coupon,
            R.drawable.app_essential, R.drawable.app_exchange, R.drawable.app_facepay,
            R.drawable.app_creditcard};

    public MyGridAdapter(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.fragment_fun_grid_item, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(imgs[position]);
        tv.setText(img_text[position]);
        return convertView;
    }
}
