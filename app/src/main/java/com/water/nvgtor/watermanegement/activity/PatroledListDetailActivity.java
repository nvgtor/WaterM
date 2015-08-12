package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PatroledDetailListAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolTaskDetailList;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/11.
 */
public class PatroledListDetailActivity extends Activity implements UnPatrolLoadListview.ILoadListener {
    ArrayList<PatrolTaskDetailList> patrolList = new ArrayList<PatrolTaskDetailList>();
    PatroledDetailListAdapter adapter;
    UnPatrolLoadListview loadListview;
    private Handler handler;
    private ImageView img_back;
    private TextView tv_title;
    private ImageView patrol_loc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patroled_list_detail);
        initView();
        initChanged();
        getData();
        showListView(patrolList);
    }

    private void initView() {
        img_back = (ImageView)findViewById(R.id.id_detail_back_img);
        tv_title = (TextView)findViewById(R.id.id_detail_back_title);
        patrol_loc = (ImageView) findViewById(R.id.id_detail_patrol_loc);
    }
    private void initChanged(){
        tv_title.setText("已办巡检详情");
        patrol_loc.setImageResource(R.drawable.icon_activity_lbs);
        patrol_loc.setMaxWidth(30);
        patrol_loc.setMaxHeight(30);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        patrol_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatroledListDetailActivity.this, "you clicked loc", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(){
        for(int i = 0; i < 5; i++){
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
            loadListview = (UnPatrolLoadListview) findViewById(R.id.id_patroled_item_list);
            loadListview.setInterface(this);
            adapter = new PatroledDetailListAdapter(this, patrolList);
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
                case R.id.id_patroled_item_btn_1:
                    Intent intent = new Intent(PatroledListDetailActivity.this, PatrolMapDetailActivity.class);
                    startActivity(intent);
                    Toast.makeText(PatroledListDetailActivity.this, "你点击了查看" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };
}

