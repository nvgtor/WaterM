package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/7/20.
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome);
    }
}
