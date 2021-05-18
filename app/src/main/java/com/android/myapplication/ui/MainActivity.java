package com.android.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.myapplication.Pojo.Users;
import com.android.myapplication.R;
import com.android.myapplication.adapters.GenericAdapter;
import com.android.myapplication.listener.RecyclerViewClickListner;
import com.android.myapplication.utils.IntentManager;
import com.android.myapplication.utils.SpacesItemDecoration;
import com.android.myapplication.viewholders.FirstActivityViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.android.myapplication.utils.Constants.ALL_USER;
import static com.android.myapplication.utils.SpacesItemDecoration.CHAT_TAG;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListner {

    private String TAG = "MainActivity";
    private static final String CHAT_REF = "chat";

    ArrayList<Users> allUsersArrayList = new ArrayList<>();
    private GenericAdapter userAdapter;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView messageRecyclerList;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageRecyclerList = findViewById(R.id.lv_chat_message);
        mProgressBar = findViewById(R.id.progress_bar);
        setUpRecyclerView();
        readAllUserId();
    }

    private void readAllUserId() {
        showProgressBar();
        final ArrayList<Users> usersArrayList = new ArrayList<>();
        DatabaseReference mFireBaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child(CHAT_REF).child(ALL_USER);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                for (DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    String userId = userSnapShot.getKey();
                    String androidId = userSnapShot.child("AndroidId").getValue().toString();
                    String email = userSnapShot.child("Email").getValue().toString();
                    String accessiblityTime = "";
                    Users user = new Users(userId, androidId, email, accessiblityTime);
                    usersArrayList.add(user);
                }

                hideProgressBar();
                allUsersArrayList = usersArrayList;
                Log.d(TAG, allUsersArrayList.toString());
                userAdapter.refreshRecyclerView(allUsersArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mFireBaseDatabaseReference.addListenerForSingleValueEvent(postListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void setUpRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        messageRecyclerList.addItemDecoration(new SpacesItemDecoration(CHAT_TAG, 15));
        messageRecyclerList.setLayoutManager(linearLayoutManager);
        userAdapter = new GenericAdapter(allUsersArrayList, this, R.layout.item_grp_name, FirstActivityViewHolder.class.getCanonicalName(), this);
        messageRecyclerList.setAdapter(userAdapter);
    }


    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClicked(View view, int position) {
        IntentManager.openSecondActivity(this, CHAT_REF + "/" + allUsersArrayList.get(position).getAndroidId() + "_"
                , CHAT_REF + "/" + allUsersArrayList.get(position).getAndroidId());
    }
}
