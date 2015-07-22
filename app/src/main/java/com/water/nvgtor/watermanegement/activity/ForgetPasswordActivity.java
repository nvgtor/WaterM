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
public class ForgetPasswordActivity extends Activity {
    private EditText phoneEmail;
    private Button btn_send;
    private String phoneEmailValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forget_password_1);

        phoneEmail = (EditText)findViewById(R.id.forget_1_phEm);
        btn_send = (Button)findViewById(R.id.forget_1_securityCode);
        phoneEmailValue = phoneEmail.getText().toString();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ForgetPasswordActivity.this, ForgetPassword_1_Activity.class);
                    ForgetPasswordActivity.this.startActivity(intent);
                    finish();
            }
        });

    }
}
