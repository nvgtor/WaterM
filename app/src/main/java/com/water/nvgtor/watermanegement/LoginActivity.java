package com.water.nvgtor.watermanegement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends Activity {
    // UI references.
    private AutoCompleteTextView username;
    private EditText password;
    private Button login;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Set up the login form.
        username = (AutoCompleteTextView)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);
        login = (Button)findViewById(R.id.login_button);
        register = (TextView)findViewById(R.id.login_register);
    }
}



