package com.water.nvgtor.watermanegement.tool;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by dell on 2015/7/22.
 */
public class BaseTools {
    /** 获取屏幕宽度 */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

}
