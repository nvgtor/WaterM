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
import com.water.nvgtor.watermanegement.bean.PatrolTask;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/3.
 */
public class PatrolTaskListAdapter extends BaseAdapter {
    ArrayList<PatrolTask> patrolList = new ArrayList<PatrolTask>();
    LayoutInflater inflater;
    private Handler handler;

    public PatrolTaskListAdapter(Context context, ArrayList<PatrolTask> patrolList){
        this.patrolList = patrolList;
        this.inflater = LayoutInflater.from(context);
    }


    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<PatrolTask> patrolList){
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
        PatrolTask patrolTask = patrolList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.patrol_task_item, null);
            holder.tv_name = (TextView)convertView.findViewById(R.id.patrol_task_item1);
            holder.tv_area = (TextView)convertView.findViewById(R.id.patrol_task_item2);
            holder.tv_execute = (TextView)convertView.findViewById(R.id.patrol_task_item3);
            holder.tv_create = (TextView)convertView.findViewById(R.id.patrol_task_item4);
            holder.tv_deadline = (TextView)convertView.findViewById(R.id.patrol_task_item5);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(patrolTask.getTaskName());
        holder.tv_area.setText(patrolTask.getTaskArea());
        holder.tv_execute.setText(patrolTask.getExecuteMan());
        holder.tv_create.setText(patrolTask.getCreateMan());
        holder.tv_deadline.setText(patrolTask.getDeadline());

        holder.btn_detail = (Button)convertView.findViewById(R.id.patrol_task_item6);
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
        TextView tv_name;
        TextView tv_area;
        TextView tv_execute;
        TextView tv_create;
        TextView tv_deadline;
        Button btn_detail;
    }
}
