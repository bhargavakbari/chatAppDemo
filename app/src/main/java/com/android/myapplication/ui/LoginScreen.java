package com.android.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.myapplication.R;
import com.android.myapplication.utils.IntentManager;

public class LoginScreen extends AppCompatActivity {

    EditText userNameEt, passwordEt;
    Button loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEt = findViewById(R.id.userNameEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userNameEt.getText().toString().equals("bbbbhargav") && passwordEt.getText().toString().equals("abazyx")) {
                    IntentManager.openMainActivity(LoginScreen.this);
                    finish();
                }
            }
        });
    }
}
