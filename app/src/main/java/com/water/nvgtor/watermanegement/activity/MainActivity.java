package com.water.nvgtor.watermanegement.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.adapter.NewsFragmentPagerAdapter;
import com.water.nvgtor.watermanegement.bean.TabClassify;
import com.water.nvgtor.watermanegement.fragment.CommunFragment;
import com.water.nvgtor.watermanegement.fragment.FunFragment;
import com.water.nvgtor.watermanegement.fragment.TaskFragment;
import com.water.nvgtor.watermanegement.tool.BaseTools;
import com.water.nvgtor.watermanegement.tool.Constants;
import com.water.nvgtor.watermanegement.view.ColumnHorizontalScrollView;
import com.water.nvgtor.watermanegement.view.SlidingMenu;

import java.util.ArrayList;

/**
 * Created by dell on 2015/7/22.
 */
public class MainActivity extends FragmentActivity {
    /** 自定义HorizontalScrollView */
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    LinearLayout mRadioGroup_content;
    private RelativeLayout rl_column;
    private ViewPager mViewPager;

    private ImageView userImg;

    private SlidingMenu mLeftMenu;
    private RelativeLayout menu_item1;
    private RelativeLayout menu_item2;
    private RelativeLayout menu_item3;
    private RelativeLayout menu_item4;

    /** 滑动区分类列表 */
    private ArrayList<TabClassify> tabClassifies = new ArrayList<TabClassify>();
    /** 当前选中的栏目 */
    private int columnSelectIndex = 0;
    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mScreenWidth = BaseTools.getWindowsWidth(this);
        mItemWidth = mScreenWidth / 3;
        initView();
    }

    private void initView(){
        mColumnHorizontalScrollView = (ColumnHorizontalScrollView)findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout)findViewById(R.id.mRadioGroup_content);
        rl_column = (RelativeLayout)findViewById(R.id.rl_column);
        mViewPager = (ViewPager)findViewById(R.id.mViewPager);

        userImg = (ImageView)findViewById(R.id.top_head_userImg);

        mLeftMenu = (SlidingMenu)findViewById(R.id.id_menu);
        menu_item1 = (RelativeLayout)findViewById(R.id.menu_item1);
        menu_item2 = (RelativeLayout)findViewById(R.id.menu_item2);
        menu_item3 = (RelativeLayout)findViewById(R.id.menu_item3);
        menu_item4 = (RelativeLayout)findViewById(R.id.menu_item4);
        setChangeView();
    }
    private void setChangeView(){
        initTopHead();
        initLeftMenu();
        initColumnData();
        initTabColumn();
        initFragment();
    }

    /**
     * 初始化头部布局
     */
    private void initTopHead(){
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftMenu.toggle();
            }
        });
    }

    /**
     * 初始化左侧菜单
     */
    private void initLeftMenu(){
        menu_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clicked user info", Toast.LENGTH_SHORT).show();
            }
        });
        menu_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clicked item2", Toast.LENGTH_SHORT).show();
            }
        });
        menu_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clicked item3", Toast.LENGTH_SHORT).show();
            }
        });
        menu_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clicked item4", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** 获取column数据 */
    private void initColumnData(){
        tabClassifies = Constants.getData();
    }

    /** 初始化Column栏目项 */
    private void initTabColumn(){
        mRadioGroup_content.removeAllViews();
        int count = tabClassifies.size();
        mColumnHorizontalScrollView.setParam(this,mScreenWidth,mRadioGroup_content,rl_column);
        for (int i = 0; i < count; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            params.rightMargin = 10;
            TextView localTextView = new TextView(this);
            localTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
            localTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setPadding(5, 0, 5, 0);
            localTextView.setId(i);
            localTextView.setText(tabClassifies.get(i).getTitle());
            localTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if(columnSelectIndex == i){
                localTextView.setSelected(true);
            }
            localTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    //Toast.makeText(getApplicationContext(), tabClassifies.get(v.getId()).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(localTextView, i, params);
        }
    }

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        //判断是否选中
        for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {

        FunFragment funFragment = new FunFragment();
        fragments.add(funFragment);
        TaskFragment taskFragment = new TaskFragment();
        fragments.add(taskFragment);
        CommunFragment communFragment = new CommunFragment();
        fragments.add(communFragment);
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setOnPageChangeListener(pageListener);
    }

    /**
     *  ViewPager切换监听方法
     * */
    public OnPageChangeListener pageListener= new OnPageChangeListener(){

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
