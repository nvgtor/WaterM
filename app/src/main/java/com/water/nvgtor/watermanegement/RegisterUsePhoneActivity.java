package com.water.nvgtor.watermanegement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by dell on 2015/7/20.
 */
public class RegisterUsePhoneActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_phone);
    }
}