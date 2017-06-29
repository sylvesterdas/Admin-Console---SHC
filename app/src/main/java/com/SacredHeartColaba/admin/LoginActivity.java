package com.SacredHeartColaba.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.SacredHeartColaba.admin.helpers.LoginAsyncTask;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText userET, passET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userET = (EditText) findViewById(R.id.login_username);
        passET = (EditText) findViewById(R.id.login_password);
    }

    public void login(View view) {
        if (valid())
            new LoginAsyncTask(this).execute("login.php", userET.getText().toString(), passET.getText().toString());
    }

    private boolean valid() {
        boolean valid = true;
        if (userET.getText().toString().equals("")) {
            userET.setError("Enter username");
            valid = false;
        }
        if (passET.getText().toString().trim().equals("")) {
            passET.setError("Enter password");
            valid = false;
        }
        return valid;
    }
}
