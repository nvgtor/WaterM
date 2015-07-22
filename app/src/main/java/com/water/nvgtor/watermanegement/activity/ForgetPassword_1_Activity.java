package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.water.nvgtor.watermanegement.R;

/**
 * Created by dell on 2015/7/20.
 */
public class ForgetPassword_1_Activity extends Activity {
    private EditText securityCode;
    private TextView backText;
    private Button btn_next;
    private TextView sendAgain;
    private String secuCode, phoneEmail, disText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forget_password_2);

        securityCode = (EditText)findViewById(R.id.forget_2_edit);
        backText = (TextView)findViewById(R.id.forget_2_text);
        btn_next = (Button)findViewById(R.id.forget_2_btn);
        sendAgain = (TextView)findViewById(R.id.forget_2_sendAgain);

        secuCode = securityCode.getText().toString();
        disText = "验证码已发送" + ",请注意查收";
        backText.setText(disText);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ForgetPassword_1_Activity.this, ForgetPassword_2_Activity.class);
                    ForgetPassword_1_Activity.this.startActivity(intent);
                    finish();
            }
        });

    }


}
