package com.water.nvgtor.watermanegement.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/7/29.
 */
public class UnPatrolLoadListview extends ListView implements AbsListView.OnScrollListener {
    View footer;
    int totalItemCount;
    int lastVisibleItem;
    boolean isLoading;
    ILoadListener iLoadListener;

    public UnPatrolLoadListview(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public UnPatrolLoadListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public UnPatrolLoadListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    /**
     * 添加底部加载提示布局到Listview
     */
    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.unpatrol_list_footer, null);
        footer.findViewById(R.id.load_layout).setVisibility(GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibleItem && scrollState == SCROLL_STATE_IDLE){
            if (!isLoading){
                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(VISIBLE);
                iLoadListener.onLoad();
            }
        }
    }

    public void loadComplete(){
        isLoading = false;
        footer.findViewById(R.id.load_layout).setVisibility(GONE);
    }

    public void setInterface(ILoadListener iLoadListener){
        this.iLoadListener = iLoadListener;
    }

    public interface ILoadListener{
        public void onLoad();
    }
}
