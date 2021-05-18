package com.android.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.myapplication.Pojo.OriginalChatMessage;
import com.android.myapplication.Pojo.SocialMediaGrps;
import com.android.myapplication.R;
import com.android.myapplication.adapters.GenericAdapter;
import com.android.myapplication.listener.RecyclerViewClickListner;
import com.android.myapplication.utils.IntentManager;
import com.android.myapplication.utils.SpacesItemDecoration;
import com.android.myapplication.utils.Utils;
import com.android.myapplication.viewholders.GroupNameViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import static com.android.myapplication.utils.SpacesItemDecoration.CHAT_TAG;
import static com.android.myapplication.utils.Utils.DATE_FORMAT;

public class ThirdActivity extends AppCompatActivity implements RecyclerViewClickListner {

    private static final String TAG = "ThirdActivity";
    private ArrayList<SocialMediaGrps> allSocialMeadiGrpMsg = new ArrayList<>();
    private GenericAdapter genericAdapter;
    private String firebasePath, landingScreenFireBasePath;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView messageRecyclerList;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readIntentData();
        messageRecyclerList = findViewById(R.id.lv_chat_message);
        mProgressBar = findViewById(R.id.progress_bar);
        setUpRecyclerView();
        readAllSocialMeadiGroup();
    }

    private void readAllSocialMeadiGroup() {

        showProgressBar();
        DatabaseReference mFireBaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child(firebasePath);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                for (DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    String grpName = userSnapShot.getKey();
                    String timeStamp = userSnapShot.getValue().toString();
                    SocialMediaGrps socialMediaGrps = new SocialMediaGrps(grpName, timeStamp);
                    allSocialMeadiGrpMsg.add(socialMediaGrps);
                }
                try {
                    sortAllSocialMediaGrpMsgBasedOnTime(allSocialMeadiGrpMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hideProgressBar();
                Log.d(TAG, allSocialMeadiGrpMsg.toString());
                genericAdapter = new GenericAdapter(allSocialMeadiGrpMsg, ThirdActivity.this, R.layout.item_grp_name, GroupNameViewHolder.class.getCanonicalName(), ThirdActivity.this);
                messageRecyclerList.setAdapter(genericAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mFireBaseDatabaseReference.addListenerForSingleValueEvent(postListener);
    }

    void sortAllSocialMediaGrpMsgBasedOnTime(ArrayList<SocialMediaGrps> allSocialMeadiGrpMsg) {
        Collections.sort(allSocialMeadiGrpMsg, new Comparator<SocialMediaGrps>() {
            @Override
            public int compare(SocialMediaGrps lhs, SocialMediaGrps rhs) {
                Date date1 = Utils.getDateFromString(lhs.getLastUsedFormatedTime(), DATE_FORMAT);
                Date date2 = Utils.getDateFromString(rhs.getLastUsedFormatedTime(), DATE_FORMAT);
                return date2.compareTo(date1);
            }
        });
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void readIntentData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            firebasePath = getIntent().getStringExtra(IntentManager.DETAILS_CHAT_FIREBASE_PATH);
            landingScreenFireBasePath = getIntent().getStringExtra(IntentManager.FIREBASE_PATH_LANDING_SCREEN);
        }
    }

    private void setUpRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        messageRecyclerList.addItemDecoration(new SpacesItemDecoration(CHAT_TAG, 15));
        messageRecyclerList.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onItemClicked(View view, int position) {
        IntentManager.openChatLandingActivity(this, landingScreenFireBasePath + "/" +
                allSocialMeadiGrpMsg.get(position).getGroupName());
    }
}
