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
import com.water.nvgtor.watermanegement.bean.PatrolTaskDetailList;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/6.
 */
public class PatrolTaskDetailListAdapter extends BaseAdapter {

    ArrayList<PatrolTaskDetailList> patrolList = new ArrayList<PatrolTaskDetailList>();
    LayoutInflater inflater;
    private Handler handler;

    public PatrolTaskDetailListAdapter(Context context, ArrayList<PatrolTaskDetailList> patrolList){
        this.patrolList = patrolList;
        this.inflater = LayoutInflater.from(context);
    }

    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<PatrolTaskDetailList> patrolList){
        this.patrolList = patrolList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return patrolList.size();
    }

    @Override
    public Object getItem(int position) {
        return patrolList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PatrolTaskDetailList detailList = patrolList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.patrol_task_detail_list_item, null);
            holder.pointID = (TextView)convertView.findViewById(R.id.id_detail_item_ID);
            holder.pointName = (TextView)convertView.findViewById(R.id.id_detail_item_name);
            holder.pointaddr = (TextView)convertView.findViewById(R.id.id_detail_item_address);
            holder.deviceNum = (TextView)convertView.findViewById(R.id.id_detail_item_device_num);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.pointID.setText(detailList.getPatrolPointID());
        holder.pointName.setText(detailList.getPatrolPointName());
        holder.pointaddr.setText(detailList.getPatrolPointAddress());
        holder.deviceNum.setText(detailList.getDeviceNum()+"个");

        holder.btn_start = (Button)convertView.findViewById(R.id.id_detail_item_btn);
        holder.btn_start.setOnClickListener(new View.OnClickListener() {
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
        TextView pointID;
        TextView pointName;
        TextView pointaddr;
        TextView deviceNum;
        Button btn_start;
    }
}
