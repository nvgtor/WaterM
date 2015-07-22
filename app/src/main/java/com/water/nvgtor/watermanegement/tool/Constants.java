package com.water.nvgtor.watermanegement.tool;

import com.water.nvgtor.watermanegement.bean.TabClassify;

import java.util.ArrayList;

/**
 * Created by dell on 2015/7/22.
 */
public class Constants {
    public static ArrayList<TabClassify> getData(){
        ArrayList<TabClassify> tabClassifies = new ArrayList<TabClassify>();
        TabClassify classify = new TabClassify();
        classify.setId(0);
        classify.setTitle("功能");
        tabClassifies.add(classify);
        classify = new TabClassify();
        classify.setId(1);
        classify.setTitle("任务");
        tabClassifies.add(classify);
        classify = new TabClassify();
        classify.setId(2);
        classify.setTitle("通讯");
        tabClassifies.add(classify);
        return tabClassifies;
    }
}
