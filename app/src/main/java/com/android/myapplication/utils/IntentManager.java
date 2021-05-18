package com.android.myapplication.utils;

import android.content.Context;
import android.content.Intent;

import com.android.myapplication.ui.ChatLandingActivity;
import com.android.myapplication.ui.DetailChatActivity;
import com.android.myapplication.ui.MainActivity;
import com.android.myapplication.ui.SeconActivity;
import com.android.myapplication.ui.ThirdActivity;

public class IntentManager {

    public static String DETAILS_CHAT_FIREBASE_PATH = "details_chat_firebase_path";
    public static String FIREBASE_PATH = "firebase_path";
    public static String FIREBASE_PATH_LANDING_SCREEN = "firebase_path_landing_screen";

    public static void openSecondActivity(Context context, String landingScreenFireBasePath, String firebasepath) {
        Intent secondActivity = new Intent(context, SeconActivity.class);
        secondActivity.putExtra(FIREBASE_PATH, firebasepath);
        secondActivity.putExtra(FIREBASE_PATH_LANDING_SCREEN, landingScreenFireBasePath);
        context.startActivity(secondActivity);
    }

    public static void openThirdActivity(Context context, String landingScreenFireBasePath, String firebasePath) {
        Intent thirdActivity = new Intent(context, ThirdActivity.class);
        thirdActivity.putExtra(DETAILS_CHAT_FIREBASE_PATH, firebasePath);
        thirdActivity.putExtra(FIREBASE_PATH_LANDING_SCREEN, landingScreenFireBasePath);
        context.startActivity(thirdActivity);
    }

    public static void openChatLandingActivity(Context context, String landingScreenFireBasePath) {
        Intent chatLandingActivity = new Intent(context, ChatLandingActivity.class);
        chatLandingActivity.putExtra(FIREBASE_PATH_LANDING_SCREEN, landingScreenFireBasePath);
        context.startActivity(chatLandingActivity);
    }

    public static void openMainActivity(Context context) {
        Intent mainActivity = new Intent(context, MainActivity.class);
        context.startActivity(mainActivity);
    }


    public static void openChatDetailsLandingActivity(Context context, String fireBasePath) {
        Intent chatLandingActivity = new Intent(context, DetailChatActivity.class);
        chatLandingActivity.putExtra(DETAILS_CHAT_FIREBASE_PATH, fireBasePath);
        context.startActivity(chatLandingActivity);
    }

}
