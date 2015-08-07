package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PatrolTaskDetailListAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolTaskDetailList;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/6.
 */
public class PatrolTaskDetailActivity extends Activity implements UnPatrolLoadListview.ILoadListener{
    ArrayList<PatrolTaskDetailList> patrolList = new ArrayList<PatrolTaskDetailList>();
    PatrolTaskDetailListAdapter adapter;
    UnPatrolLoadListview loadListview;
    private Handler handler;

    private LinearLayout head_back;
    private ImageView patrol_loc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patrol_task_detail);
        initView();
        initChanged();
        getData();
        showListView(patrolList);
    }

    private void initView() {
        head_back = (LinearLayout)findViewById(R.id.id_detail_menu_back);
        patrol_loc = (ImageView) findViewById(R.id.id_detail_patrol_loc);
    }
     private void initChanged(){
         head_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
         patrol_loc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(PatrolTaskDetailActivity.this, "you clicked loc", Toast.LENGTH_SHORT).show();
             }
         });
     }

    private void getData(){
        for(int i = 0; i < 10; i++){
            PatrolTaskDetailList entity = new PatrolTaskDetailList();
            entity.setPatrolPointID("AK0183");
            entity.setPatrolPointName("A巡检点");
            entity.setPatrolPointAddress("高新七路距海油大道20m");
            entity.setDeviceNum(4);
            patrolList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            PatrolTaskDetailList entity = new PatrolTaskDetailList();
            entity.setPatrolPointID("B1111111");
            entity.setPatrolPointName("B巡检点");
            entity.setPatrolPointAddress("塘沽外滩公园解放路站下水道");
            entity.setDeviceNum(3);
            patrolList.add(entity);
        }
    }

    private void showListView(ArrayList<PatrolTaskDetailList> patrolList){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_detail_list);
            loadListview.setInterface(this);
            adapter = new PatrolTaskDetailListAdapter(this, patrolList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(patrolList);
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
                showListView(patrolList);
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
                case R.id.id_detail_item_btn:
                    Intent intent = new Intent(PatrolTaskDetailActivity.this, PatrolMapDetailActivity.class);
                    startActivity(intent);
                    Toast.makeText(PatrolTaskDetailActivity.this, "你点击了开始" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };
}
