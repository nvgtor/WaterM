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
import com.water.nvgtor.watermanegement.adapter.PatroledTaskAdapter;
import com.water.nvgtor.watermanegement.bean.PatroledTask;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/11.
 */
public class PatroledListActivity extends Activity implements UnPatrolLoadListview.ILoadListener{

    ArrayList<PatroledTask> patroledList = new ArrayList<PatroledTask>();
    PatroledTaskAdapter adapter;
    UnPatrolLoadListview loadListview;
    private Handler handler;

    private Button btn_patroled;
    private Button btn_patrol;
    private ImageView img_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patroled_list);

        btn_patrol = (Button)findViewById(R.id.btn_task_unpatrol);
        btn_patroled = (Button)findViewById(R.id.btn_task_patroled);
        img_back = (ImageView)findViewById(R.id.id_detail_back_img);
        tv_title = (TextView)findViewById(R.id.id_detail_back_title);
        tv_title.setText("已办巡检");

        btn_patrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatroledListActivity.this, PatrolTaskListActivity.class);
                startActivity(intent);
            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatroledListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getData();
        showListView(patroledList);
    }

    private void showListView(ArrayList<PatroledTask> patroledList){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.patroled_list);
            loadListview.setInterface(this);
            adapter = new PatroledTaskAdapter(this, patroledList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(patroledList);
        }
    }

    private void getData(){
        for(int i = 0; i < 10; i++){
            PatroledTask entity = new PatroledTask();
            entity.setTaskName("A片区巡检");
            entity.setTaskArea("A片区");
            entity.setExecuteMan("张默默");
            entity.setDeadline("2015-8-15");
            entity.setFinalState(0);
            patroledList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            PatroledTask entity = new PatroledTask();
            entity.setTaskName("B片区巡检");
            entity.setTaskArea("B片区");
            entity.setExecuteMan("李弘一");
            entity.setDeadline("2015-9-1");
            entity.setFinalState(1);
            patroledList.add(entity);
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
                showListView(patroledList);
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
                case R.id.patroled_task_item6:
                    //switch (msg.arg1){
                    //case 0:
                    Intent intent = new Intent(PatroledListActivity.this, PatroledListDetailActivity.class);
                    startActivity(intent);

                    Toast.makeText(PatroledListActivity.this, "你点击了详情" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };

}
