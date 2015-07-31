package com.water.nvgtor.watermanegement.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.UnPatrolEntity;

import java.util.ArrayList;

/**
 * Created by dell on 2015/7/29.
 */
public class PatrolListAdapter extends BaseAdapter{
    ArrayList<UnPatrolEntity> patrolName_List;
    LayoutInflater inflater;
    private Handler handler;

    public PatrolListAdapter(Context context, ArrayList<UnPatrolEntity> patrolName_List){
        this.patrolName_List = patrolName_List;
        this.inflater = LayoutInflater.from(context);
    }

    public void onDataChange(ArrayList<UnPatrolEntity> patrolName_List){
        this.patrolName_List = patrolName_List;
        this.notifyDataSetChanged();
    }

    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return patrolName_List.size();
    }

    @Override
    public Object getItem(int position) {
        return patrolName_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UnPatrolEntity entity = patrolName_List.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.patrol_going_item, null);
            holder.name_tv = (TextView)convertView.findViewById(R.id.unPatro_item_name);
            holder.des_tv = (TextView)convertView.findViewById(R.id.unPatro_item_des);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.name_tv.setText(entity.getPatrolName());
        holder.des_tv.setText(entity.getPatrolDes());
        holder.btn_accepted = (Button)convertView.findViewById(R.id.unPatrol_item_btn1);
        holder.btn_delay = (Button)convertView.findViewById(R.id.unPatrol_item_btn2);
        holder.btn_cancel = (Button)convertView.findViewById(R.id.unPatrol_item_btn3);
        holder.btn_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handler != null){
                    Message msg = handler.obtainMessage();
                    msg.what = v.getId();
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            }
        });
        holder.btn_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handler != null){
                    Message msg = handler.obtainMessage();
                    msg.what = v.getId();
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            }
        });
        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handler != null){
                    Message msg = handler.obtainMessage();
                    msg.what = v.getId();
                    msg.arg1 = position;
                    handler.sendMessage(msg);
                }
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView name_tv;
        TextView des_tv;
        Button btn_accepted;
        Button btn_delay;
        Button btn_cancel;
    }
}
