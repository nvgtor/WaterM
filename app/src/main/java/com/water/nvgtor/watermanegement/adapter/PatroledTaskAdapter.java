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
import com.water.nvgtor.watermanegement.bean.PatroledTask;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/11.
 */
public class PatroledTaskAdapter extends BaseAdapter{
    ArrayList<PatroledTask> patroledList = new ArrayList<PatroledTask>();
    LayoutInflater inflater;
    private Handler handler;

    public PatroledTaskAdapter(Context context, ArrayList<PatroledTask> patroledList){
        this.patroledList = patroledList;
        this.inflater = LayoutInflater.from(context);
    }


    // handler回发是为了处理每个按钮的点击事件
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void onDataChange(ArrayList<PatroledTask> patroledList){
        this.patroledList = patroledList;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return patroledList.size();
    }

    @Override
    public Object getItem(int position) {
        return patroledList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PatroledTask patrolTask = patroledList.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.patroled_list_item, null);
            holder.tv_name = (TextView)convertView.findViewById(R.id.patroled_task_item1);
            holder.tv_area = (TextView)convertView.findViewById(R.id.patroled_task_item2);
            holder.tv_execute = (TextView)convertView.findViewById(R.id.patroled_task_item3);
            holder.tv_deadline = (TextView)convertView.findViewById(R.id.patroled_task_item4);
            holder.tv_state = (TextView)convertView.findViewById(R.id.patroled_task_item5);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(patrolTask.getTaskName());
        holder.tv_area.setText(patrolTask.getTaskArea());
        holder.tv_execute.setText(patrolTask.getExecuteMan());
        holder.tv_deadline.setText(patrolTask.getDeadline());
        if (patrolTask.getFinalState() == 0){
            holder.tv_state.setText("待审核");
        }else {
            holder.tv_state.setText("审核通过");
        }
        //holder.tv_state.setText(patrolTask.getFinalState());

        holder.btn_detail = (Button)convertView.findViewById(R.id.patroled_task_item6);
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
        TextView tv_deadline;
        TextView tv_state;
        Button btn_detail;
    }
}
