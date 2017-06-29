package com.SacredHeartColaba.admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.SacredHeartColaba.admin.preference.AdminSharedPreference;


/**
 * {@link SplashActivity}
 *
 * @author Sylvester Das
 * @version 1.0.0
 * @since 11-Jun-2017
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AdminSharedPreference preference = new AdminSharedPreference(SplashActivity.this);
                Intent intent;
                if (preference.isLoggedIn()) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}