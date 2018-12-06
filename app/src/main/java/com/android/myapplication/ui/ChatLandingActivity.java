package com.android.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.myapplication.Pojo.ChatMessage;
import com.android.myapplication.Pojo.OriginalChatMessage;
import com.android.myapplication.R;
import com.android.myapplication.adapters.ChatAdapter;
import com.android.myapplication.listener.RecyclerViewClickListner;
import com.android.myapplication.utils.Constants;
import com.android.myapplication.utils.IntentManager;
import com.android.myapplication.utils.SpacesItemDecoration;
import com.android.myapplication.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static com.android.myapplication.utils.IntentManager.FIREBASE_PATH_LANDING_SCREEN;
import static com.android.myapplication.utils.SpacesItemDecoration.CHAT_TAG;
import static com.android.myapplication.utils.Utils.DATE_FORMAT;

public class ChatLandingActivity extends AppCompatActivity implements RecyclerViewClickListner {

    private static final String TAG = "ChatLandingActivity";
    private static final String CHAT_REF = "chat";

    private ArrayList<ChatMessage> particularChatMessageArrayList = new ArrayList<>();
    private HashMap<String, List<OriginalChatMessage>> rawMessageData;
    private ChatAdapter chatAdapter;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView messageRecyclerList;
    private ProgressBar mProgressBar;
    private String landingScreenFireBasePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readIntentData();
        messageRecyclerList = findViewById(R.id.lv_chat_message);
        mProgressBar = findViewById(R.id.progress_bar);
        setUpRecyclerView();
        readMessageForParticularGroup();
    }

    private void readMessageForParticularGroup() {
        showProgressBar();
        final HashMap<String, List<OriginalChatMessage>> grpChatMap = new LinkedHashMap<>();
        DatabaseReference mFireBaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child(landingScreenFireBasePath);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d(TAG, dataSnapshot.toString());
                for (DataSnapshot dayWiseDataSnapShot : dataSnapshot.getChildren()) {
                    Log.d(TAG, dayWiseDataSnapShot.toString());
                    String day12AmTimeStr = dayWiseDataSnapShot.getKey();
                    List<OriginalChatMessage> wholeDayMessageList = getDayWiseMessage2(dayWiseDataSnapShot);
                    grpChatMap.put(day12AmTimeStr, wholeDayMessageList);
                }

                Log.e(TAG, grpChatMap.toString());
                processDataIntoChatPOJO(grpChatMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mFireBaseDatabaseReference.addListenerForSingleValueEvent(postListener);
    }

    private void readIntentData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            landingScreenFireBasePath = getIntent().getStringExtra(FIREBASE_PATH_LANDING_SCREEN);
        }
    }

    private List<OriginalChatMessage> getDayWiseMessage2(DataSnapshot morning12AmTimeSnapSHot) {
        List<OriginalChatMessage> originalChatMessageList = new ArrayList<>();
        for (DataSnapshot eachTimeWiseMessageSnapSHot : morning12AmTimeSnapSHot.getChildren()) {
            //Log.d(TAG, " read message:" + eachTimeWiseMessageSnapSHot.getKey());
            Long timeStamp = 0L;
            if (eachTimeWiseMessageSnapSHot.getKey().toString().matches("[0-9]+"))
                timeStamp = Long.valueOf(eachTimeWiseMessageSnapSHot.getKey().toString());
            else
                timeStamp = Long.valueOf(11111);

            ArrayList<String> chatMessageList = getRawMessage(eachTimeWiseMessageSnapSHot);
            OriginalChatMessage originalChatMessage = new OriginalChatMessage(chatMessageList, timeStamp);
            originalChatMessageList.add(originalChatMessage);
        }
        return originalChatMessageList;
    }

    private ArrayList<String> getRawMessage(DataSnapshot messageListSnapShot) {
        ArrayList<String> chatMessageList = new ArrayList<>();
        for (DataSnapshot eachMessage : messageListSnapShot.child(Constants.ALL_MESSAGE).getChildren()) {
            //Log.d(TAG, " eachMessage:" + eachMessage.getValue());
            chatMessageList.add(eachMessage.getValue().toString());
        }
        return chatMessageList;
    }


    private void processDataIntoChatPOJO(HashMap<String, List<OriginalChatMessage>> rawMessageData) {
        TreeMap<String, List<OriginalChatMessage>> dateWiseSortedMap = sortByKeys(rawMessageData);

        Iterator<Map.Entry<String, List<OriginalChatMessage>>> itr = dateWiseSortedMap.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, List<OriginalChatMessage>> entry = itr.next();
            String dayTime = entry.getKey();
            ChatMessage chatMessage = new ChatMessage(dayTime, Constants.TYPE_PARENT);
            Set<String> uniqueMessage = new LinkedHashSet<>();
            particularChatMessageArrayList.add(chatMessage);
            for (OriginalChatMessage msg : entry.getValue()) {
                ArrayList<String> uniqueRawMsg = new ArrayList<>();
                for (String singleRawMsg : msg.allMessage) {
                    if (uniqueMessage.add(singleRawMsg))
                        uniqueRawMsg.add(singleRawMsg);
                }
                chatMessage = new ChatMessage(dayTime, msg.currentTimeStamp, Constants.TYPE_CHILD, uniqueRawMsg);
                particularChatMessageArrayList.add(chatMessage);
            }
        }
        setDataInRecyclerView(particularChatMessageArrayList);
    }

    private void setDataInRecyclerView(ArrayList<ChatMessage> particularChatMessageArrayList) {
        hideProgressBar();
        chatAdapter.refreshAdapterData(particularChatMessageArrayList);
    }

    public TreeMap<String, List<OriginalChatMessage>> sortByKeys(HashMap<String, List<OriginalChatMessage>> map) {
        TreeMap<String, List<OriginalChatMessage>> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                Date date1 = Utils.getDateFromString(a.toString(), DATE_FORMAT);
                Date date2 = Utils.getDateFromString(b.toString(), DATE_FORMAT);
                return date1.compareTo(date2);
            }
        });

        treeMap.putAll(map);

        return treeMap;
    }

    private void setUpRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messageRecyclerList.addItemDecoration(new SpacesItemDecoration(CHAT_TAG, 15));
        messageRecyclerList.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(particularChatMessageArrayList, this);
        messageRecyclerList.setAdapter(chatAdapter);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void onItemClicked(View view, int position) {
        Log.d(TAG, view.getId() + "  --> Id");
        if (view.getId() == R.id.chatMessageContainer) {
            IntentManager.openChatDetailsLandingActivity(this, landingScreenFireBasePath + "/" +
                    particularChatMessageArrayList.get(position).dayTime + "/" + particularChatMessageArrayList.get(position).msgTime);
        }
    }
}
