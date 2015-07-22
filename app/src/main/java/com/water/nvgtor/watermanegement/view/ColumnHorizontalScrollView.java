package com.water.nvgtor.watermanegement.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by dell on 2015/7/22.
 */
public class ColumnHorizontalScrollView extends HorizontalScrollView {
    /**
     * 传入整体布局
     */
    private View ll_content;
    /**
     * 传入更多栏目选择布局
     */
    private View ll_more;
    /**
     * 传入拖动栏布局
     */
    private View rl_column;
    /**
     * 屏幕宽度
     */
    private int mScreenWitdh = 0;
    /**
     * 父类的活动activity
     */
    private Activity activity;

    public ColumnHorizontalScrollView(Context context) {
        super(context);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 传入父类布局中的资源文件
     */
    public void setParam(Activity activity, int mScreenWitdh, View paramView1, View paramView2) {
        this.activity = activity;
        this.mScreenWitdh = mScreenWitdh;
        ll_content = paramView1;
        rl_column = paramView2;
    }
}
