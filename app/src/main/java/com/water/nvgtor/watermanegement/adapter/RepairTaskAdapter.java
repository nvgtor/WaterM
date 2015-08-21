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
import com.water.nvgtor.watermanegement.bean.RepairTask;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/21.
 */
public class RepairTaskAdapter extends BaseAdapter {

    ArrayList<RepairTask> repairList = new ArrayList<RepairTask>();
    LayoutInflater inflater;
    private Handler handler;

    public RepairTaskAdapter(Context context, ArrayList<RepairTask> repairList){
        this.repairList = repairList;
        this.inflater = LayoutInflater.from(context);
    }


    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<RepairTask> repairList){
        this.repairList = repairList;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return repairList.size();
    }

    @Override
    public Object getItem(int position) {
        return repairList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RepairTask patrolTask = repairList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.repair_list_item, null);
            holder.tv_des = (TextView)convertView.findViewById(R.id.repair_task_item1);
            holder.tv_addr = (TextView)convertView.findViewById(R.id.repair_task_item2);
            holder.tv_execute = (TextView)convertView.findViewById(R.id.repair_task_item3);
            holder.tv_deadline = (TextView)convertView.findViewById(R.id.repair_task_item4);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_des.setText(patrolTask.getTaskDes());
        holder.tv_addr.setText(patrolTask.getTaskAddr());
        holder.tv_execute.setText(patrolTask.getExecuteMan());
        holder.tv_deadline.setText(patrolTask.getDeadline());

        holder.btn_detail = (Button)convertView.findViewById(R.id.repair_task_item5);
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_des;
        TextView tv_addr;
        TextView tv_execute;
        TextView tv_deadline;
        Button btn_detail;
    }
}
