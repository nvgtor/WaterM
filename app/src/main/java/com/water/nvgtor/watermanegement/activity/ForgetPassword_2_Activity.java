package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/7/20.
 */
public class ForgetPassword_2_Activity extends Activity {
    private EditText new_pw, again_pw;
    private Button btn_sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forget_password_3);

        new_pw = (EditText)findViewById(R.id.forget_3_newPw);
        again_pw = (EditText)findViewById(R.id.forget_3_againPw);
        btn_sure = (Button)findViewById(R.id.forget_3_btn);

        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassword_2_Activity.this, MainActivity.class);
                ForgetPassword_2_Activity.this.startActivity(intent);
                finish();
            }
        });

    }

/*    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ForgetPassword_2_Activity.this, LoginActivity.class);
        ForgetPassword_2_Activity.this.startActivity(intent);
    }*/
}
