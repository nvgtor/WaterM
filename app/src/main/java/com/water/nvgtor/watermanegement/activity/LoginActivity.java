package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.water.nvgtor.watermanegement.R;


/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends Activity {
    // UI references.
    private AutoCompleteTextView userName;
    private EditText password;
    private Button btn_login;
    private CheckBox rem_pw,auto_login;
    private TextView tv_register,tv_forgetPw;
    private String userNameValue,passwordValue;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        // Set up the login form.
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        userName = (AutoCompleteTextView)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);
        rem_pw = (CheckBox)findViewById(R.id.login_checkBox_remember);
        auto_login = (CheckBox)findViewById(R.id.login_checkBox_autoLogin);
        btn_login = (Button)findViewById(R.id.login_button);
        tv_forgetPw = (TextView)findViewById(R.id.login_forgetPw);
        tv_register = (TextView)findViewById(R.id.login_register);

        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK",false)){
            //设置默认是记录密码
            rem_pw.setChecked(true);
            userName.setText(sp.getString("USER_NAME",""));
            password.setText(sp.getString("PASSWORD",""));
            //判断自动登录多选框状态
            if (sp.getBoolean("AUTO_ISCHECK",false)){
                //设置默认是自动登录状态
                auto_login.setChecked(true);
                //跳转界面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        }

        //登录监听事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameValue = userName.getText().toString();
                passwordValue = password.getText().toString();
                if (userNameValue.length() > 0 && passwordValue.length() > 0) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //登录成功和记住密码框为选中才保存用户信息
                    if (rem_pw.isChecked()) {
                        //记住用户名、密码
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_NAME", userNameValue);
                        editor.putString("PASSWORD", passwordValue);
                        editor.commit();
                    }
                    //跳转界面
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
                }
            }
        });

        //监听记住密码多选框事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rem_pw.isChecked()){
                    Log.d("passwordCheck","密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();
                }else{
                    Log.d("passwordNoCheck","记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
            }
        });

        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (auto_login.isChecked()) {
                    Log.d("autoLoginCheck", "自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    Log.d("autoLoginNoCheck", "自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });

        //忘记密码监听事件
        tv_forgetPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        //注册账号监听事件
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterUsePhoneActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });

    }
}



