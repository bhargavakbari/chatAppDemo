package com.android.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.myapplication.R;
import com.android.myapplication.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.android.myapplication.utils.IntentManager.DETAILS_CHAT_FIREBASE_PATH;

public class DetailChatActivity extends AppCompatActivity {


    private static final String TAG = "DetailChatActivity";
    private static final String CHAT_REF = "chat";
    private String fireBasepath = "";

    public TextView messageTextViewUser;
    public TextView timeTextViewUser;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_chat_activity);

        messageTextViewUser = findViewById(R.id.tv_message_user);
        timeTextViewUser = findViewById(R.id.tv_time_user);
        mProgressBar = findViewById(R.id.progress_bar);

        readInentData();
        readDataFromFirebase();
    }


    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void readDataFromFirebase() {
        showProgressBar();
        DatabaseReference mFireBaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child(fireBasepath);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, dataSnapshot.toString());
                hideProgressBar();
                ArrayList<String> allRawMsg = getRawMessage(dataSnapshot);
                setDataToTextView(allRawMsg);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressBar();
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mFireBaseDatabaseReference.addListenerForSingleValueEvent(postListener);
    }

    private void setDataToTextView(ArrayList<String> allRawMsg) {
        String wholeMsg = "";
        for (String msg : allRawMsg) {
            wholeMsg = wholeMsg + "\n" + msg + "\n";
        }
        messageTextViewUser.setText(wholeMsg);
    }

    private ArrayList<String> getRawMessage(DataSnapshot messageListSnapShot) {
        ArrayList<String> chatMessageList = new ArrayList<>();
        for (DataSnapshot eachMessage : messageListSnapShot.child(Constants.ALL_MESSAGE).getChildren()) {
            // Log.d(TAG, " eachMessage:" + eachMessage.getValue());
            chatMessageList.add(eachMessage.getValue().toString());
        }
        return chatMessageList;
    }


    private void readInentData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            fireBasepath = getIntent().getStringExtra(DETAILS_CHAT_FIREBASE_PATH);
            Log.d(TAG, fireBasepath);
        }
    }
}
