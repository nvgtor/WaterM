package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PatrolListAdapter;
import com.water.nvgtor.watermanegement.bean.UnPatrolEntity;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/7/29.
 */
public class UnPatrolListActivity extends Activity implements UnPatrolLoadListview.ILoadListener{
    ArrayList<UnPatrolEntity> patroList = new ArrayList<UnPatrolEntity>();
    PatrolListAdapter adapter;
    UnPatrolLoadListview listview;
    LinearLayout backLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patrol_going_list);
        //getSupportActionBar().setTitle("返回");

        backLayout = (LinearLayout)findViewById(R.id.menu_back);

        getData();
        showListView(patroList);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void showListView(ArrayList<UnPatrolEntity> patroList){
        if (adapter == null){
            listview = (UnPatrolLoadListview)findViewById(R.id.unPatrolList);
            listview.setInterface(this);
            adapter = new PatrolListAdapter(this, patroList);
            listview.setAdapter(adapter);
        }else{
            adapter.onDataChange(patroList);
        }
    }

    private void getData(){
        for(int i = 0; i < 10; i++){
            UnPatrolEntity entity = new UnPatrolEntity();
            entity.setPatrolName("待办巡检");
            entity.setPatrolDes("任务详情");
            patroList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            UnPatrolEntity entity = new UnPatrolEntity();
            entity.setPatrolName("待办");
            entity.setPatrolDes("更多任务详情");
            patroList.add(entity);
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
                showListView(patroList);
                //通知listview加载完毕
                listview.loadComplete();
            }
        }, 2000);
    }
}
