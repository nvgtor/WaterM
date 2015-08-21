package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.PatrolTaskListAdapter;
import com.water.nvgtor.watermanegement.bean.PatrolTask;
import com.water.nvgtor.watermanegement.tool.HttpUtil;
import com.water.nvgtor.watermanegement.view.UnPatrolLoadListview;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 2015/8/3.
 */
public class PatrolTaskListActivity extends Activity implements UnPatrolLoadListview.ILoadListener {

    ArrayList<PatrolTask> patrolList = new ArrayList<PatrolTask>();
    PatrolTaskListAdapter adapter;
    UnPatrolLoadListview loadListview;
    private Handler handler;

    private Button btn_patrol;
    private Button btn_patroled;
    private ImageView img_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patrol_task);

        downloadClick();

        btn_patrol = (Button) findViewById(R.id.btn_task_unpatrol);
        btn_patroled = (Button) findViewById(R.id.btn_task_patroled);
        img_back = (ImageView) findViewById(R.id.id_detail_back_img);
        tv_title = (TextView) findViewById(R.id.id_detail_back_title);

        tv_title.setText("待办巡检");

        btn_patroled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatrolTaskListActivity.this, PatroledListActivity.class);
                startActivity(intent);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatrolTaskListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getData();
        showListView(patrolList);

    }

    private void showListView(ArrayList<PatrolTask> patrolList) {
        if (adapter == null) {
            loadListview = (UnPatrolLoadListview) findViewById(R.id.task_list);
            loadListview.setInterface(this);
            adapter = new PatrolTaskListAdapter(this, patrolList);
            adapter.setHandler(handler_h);
            loadListview.setAdapter(adapter);
        } else {
            adapter.onDataChange(patrolList);
        }
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            PatrolTask entity = new PatrolTask();
            entity.setTaskName("A片区巡检");
            entity.setTaskArea("A片区");
            entity.setExecuteMan("张默默");
            entity.setDeadline("2015-8-15");
            patrolList.add(entity);
        }
    }

    private void getLoadData() {
        for (int i = 0; i < 2; i++) {
            PatrolTask entity = new PatrolTask();
            entity.setTaskName("B片区巡检");
            entity.setTaskArea("B片区");
            entity.setExecuteMan("李弘一");
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
            switch (msg.what) {//如果item项目里有多个按钮触发，可以在这里区分
                case R.id.patrol_task_item6:
                    //switch (msg.arg1){
                    //case 0:
                    Intent intent = new Intent(PatrolTaskListActivity.this, PatrolTaskDetailActivity.class);
                    PatrolTaskListActivity.this.startActivity(intent);
                    //}
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

    /**
     * asyc-http for patrolTask
     */
    public void downloadClick() {
        String url = "http://172.17.192.1:8080/water-patrol/patrol/patrolMission/listJson";
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.e("JSONTAG", response.toString());

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

                // Do something with the response
            }
        });
    }
}