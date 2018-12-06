package com.android.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.myapplication.R;
import com.android.myapplication.utils.IntentManager;

import static com.android.myapplication.utils.Constants.SOCIAL_MEDIA_INSTA_APP;
import static com.android.myapplication.utils.Constants.SOCIAL_MEDIA_WHATS_APP;

public class SeconActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "SeconActivity";
    private String fireBasePath, landingScreenFireBasePath;
    private TextView whatsAppTv, instaTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        whatsAppTv = findViewById(R.id.tvWhatsAppChat);
        instaTv = findViewById(R.id.tvInstaAppChat);
        whatsAppTv.setOnClickListener(this);
        instaTv.setOnClickListener(this);
        readIntentData();
    }

    private void readIntentData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            fireBasePath = getIntent().getStringExtra(IntentManager.FIREBASE_PATH);
            landingScreenFireBasePath = getIntent().getStringExtra(IntentManager.FIREBASE_PATH_LANDING_SCREEN);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == whatsAppTv)
            IntentManager.openThirdActivity(this, landingScreenFireBasePath + "/" + SOCIAL_MEDIA_WHATS_APP
                    , fireBasePath + "/" + SOCIAL_MEDIA_WHATS_APP);
        else if (v == instaTv)
            IntentManager.openThirdActivity(this, landingScreenFireBasePath + "/" + SOCIAL_MEDIA_INSTA_APP
                    , fireBasePath + "/" + SOCIAL_MEDIA_INSTA_APP);
    }
}
