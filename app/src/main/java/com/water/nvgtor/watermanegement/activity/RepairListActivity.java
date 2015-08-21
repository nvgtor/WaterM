package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.RepairTaskAdapter;
import com.water.nvgtor.watermanegement.bean.RepairTask;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/21.
 */
public class RepairListActivity extends Activity implements UnPatrolLoadListview.ILoadListener{
    ArrayList<RepairTask> repairList = new ArrayList<RepairTask>();
    RepairTaskAdapter adapter;
    UnPatrolLoadListview loadListview;
    private Handler handler;

    private Button btn_repair;
    private Button btn_repaired;
    private ImageView img_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.repair_list);

        btn_repair = (Button)findViewById(R.id.btn_task_repair);
        btn_repaired = (Button)findViewById(R.id.btn_task_repaired);
        img_back = (ImageView)findViewById(R.id.id_detail_back_img);
        tv_title = (TextView)findViewById(R.id.id_detail_back_title);
        tv_title.setText("待办维修");

        btn_repaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RepairListActivity.this, PatrolTaskListActivity.class);
                startActivity(intent);
            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RepairListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getData();
        showListView(repairList);
    }

    private void showListView(ArrayList<RepairTask> repairList){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_repair_task_list);
            loadListview.setInterface(this);
            adapter = new RepairTaskAdapter(this, repairList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(repairList);
        }
    }

    private void getData(){
        for(int i = 0; i < 10; i++){
            RepairTask entity = new RepairTask();
            entity.setTaskDes("水表损坏");
            entity.setTaskAddr("天津滨海高新区渤龙湖基地");
            entity.setExecuteMan("张默默");
            entity.setDeadline("2015-8-15");
            repairList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            RepairTask entity = new RepairTask();
            entity.setTaskDes("闸门松懈");
            entity.setTaskAddr("高新七路距海油大道20米");
            entity.setExecuteMan("李弘一");
            entity.setDeadline("2015-9-1");
            repairList.add(entity);
        }
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取更多数据
                getLoadData();
                //更新listview显示
                showListView(repairList);
                //通知listview加载完毕
                loadListview.loadComplete();
            }
        }, 1000);
    }

    Handler handler_h = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what){//如果item项目里有多个按钮触发，可以在这里区分
                case R.id.repair_task_item5:
                    //switch (msg.arg1){
                    //case 0:
                    //Intent intent = new Intent(RepairListActivity.this, repairListDetailActivity.class);
                    //startActivity(intent);

                    Toast.makeText(RepairListActivity.this, "你点击了详情" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };

}
