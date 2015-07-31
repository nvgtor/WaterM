package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    UnPatrolLoadListview loadListview;
    LinearLayout backLayout;
    private Handler handler;

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
            loadListview = (UnPatrolLoadListview)findViewById(R.id.unPatrolList);
            loadListview.setInterface(this);
            adapter = new PatrolListAdapter(this, patroList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
            loadListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    Toast.makeText(UnPatrolListActivity.this, "you cliked " + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UnPatrolListActivity.this, PatrolMapDetailActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            adapter.onDataChange(patroList);
        }
    }

    private void getData(){
        for(int i = 0; i < 10; i++){
            UnPatrolEntity entity = new UnPatrolEntity();
            entity.setPatrolName("待办巡检");
            entity.setPatrolDes("任务详情任务详情任务详情任务详情任务详情任务详情任务详情任务详情任务详情");
            patroList.add(entity);
        }
    }

    private void getLoadData(){
        for (int i = 0; i < 2; i++){
            UnPatrolEntity entity = new UnPatrolEntity();
            entity.setPatrolName("待办");
            entity.setPatrolDes("更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多");
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
                loadListview.loadComplete();
            }
        }, 2000);
    }

    Handler handler_h = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what){//如果item项目里有多个按钮触发，可以在这里区分
                case R.id.unPatrol_item_btn1:
                    Toast.makeText(UnPatrolListActivity.this, "你点击了接受按钮" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.unPatrol_item_btn2:
                    Toast.makeText(UnPatrolListActivity.this, "你点击了延期按钮" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.unPatrol_item_btn3:
                    Toast.makeText(UnPatrolListActivity.this, "你点击了取消按钮" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };
}
