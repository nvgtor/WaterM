package com.water.nvgtor.watermanegement.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PatrolTaskListAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolTask;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/3.
 */
public class PatrolTaskListActivity extends Activity implements UnPatrolLoadListview.ILoadListener {

    ArrayList<PatrolTask> patrolList = new ArrayList<PatrolTask>();
    PatrolTaskListAdapter adapter;
    UnPatrolLoadListview loadListview;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patrol_task);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle("返回");
        }

        getData();
        showListView(patrolList);

    }

    private void showListView(ArrayList<PatrolTask> patrolList){
        if (adapter == null){
            loadListview = (UnPatrolLoadListview) findViewById(R.id.task_list);
            loadListview.setInterface(this);
            adapter = new PatrolTaskListAdapter(this, patrolList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        }else {
            adapter.onDataChange(patrolList);
        }
    }

    private void getData(){
        for(int i = 0; i < 10; i++){
            PatrolTask entity = new PatrolTask();
            entity.setTaskName("A片区巡检");
            entity.setTaskArea("A片区");
            entity.setExecuteMan("张默默");
            entity.setCreateMan("李某某");
            entity.setDeadline("2015-8-15");
            patrolList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            PatrolTask entity = new PatrolTask();
            entity.setTaskName("B片区巡检");
            entity.setTaskArea("B片区");
            entity.setExecuteMan("李弘一");
            entity.setCreateMan("王三心");
            entity.setDeadline("2015-9-1");
            patrolList.add(entity);
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
                case R.id.patrol_task_item6:
                    Toast.makeText(PatrolTaskListActivity.this, "你点击了详情" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
