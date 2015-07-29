package com.water.nvgtor.watermanegement.activity;

import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Created by dell on 2015/7/29.
 */
public class BaseActionBarActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置窗口风格为顶部像是ActionBar
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        //home图标 向左小箭头 可点击
        actionBar.setDisplayHomeAsUpEnabled(true);
        //是否显示应用程序图标
        actionBar.setDisplayShowHomeEnabled(false);

        try{
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null){
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        }catch (Exception e){

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();//点击返回图标事件

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
