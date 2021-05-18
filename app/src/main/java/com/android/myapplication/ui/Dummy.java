package com.android.myapplication.ui;

public class Dummy {

    /*private void readAllChatMessage() {
        showProgressBar();
        final ArrayList<ReadMessage> allMessage = new ArrayList<>();
        DatabaseReference mFireBaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child(CHAT_REF);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                for (DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    String userId = userSnapShot.getKey();
                    String androidId = userSnapShot.child("AndroidId").getValue().toString();
                    String email = userSnapShot.child("Email").getValue().toString();
                    ArrayList<SocialMediaGroup> allSocialMediaGroupMessageList = getMessageForWhatsapp(userSnapShot);
                    ArrayList<SocialMediaGroup> allAbcIMessageList = getMessagetForInstagram(userSnapShot);
                    Log.d(TAG, "userSnapShot: " + userSnapShot.toString());
                    ReadMessage readMessage = new ReadMessage(userId, androidId, email, allSocialMediaGroupMessageList, allAbcIMessageList);
                    allMessage.add(readMessage);
                }

                hideProgressBar();
                //readMessageArrayList = allMessage;
                Log.d(TAG, allMessage.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mFireBaseDatabaseReference.addListenerForSingleValueEvent(postListener);
    }



    private ArrayList<SocialMediaGroup> getMessageForWhatsapp(DataSnapshot userSnapShot) {
        ArrayList<SocialMediaGroup> allSocialMediaGroupMessageList = new ArrayList<>();
        for (DataSnapshot abcWSnapChildShot : userSnapShot.child(Constants.SOCIAL_MEDIA_WHATS_APP).getChildren()) {
            String morningin12AMTime = "";
            String groupName = abcWSnapChildShot.getKey();
            SocialMediaGroup socialMediaGroup = new SocialMediaGroup();
            socialMediaGroup.groupName = groupName;
            Log.d(TAG, "SocialMediaGroup:   " + groupName);
            HashMap<String, List<OriginalChatMessage>> grpChatMap = new LinkedHashMap<>();
            for (DataSnapshot morning12AmTimeSnapSHot : abcWSnapChildShot.getChildren()) {
                morningin12AMTime = morning12AmTimeSnapSHot.getKey();
                socialMediaGroup.morning12AmTime = morningin12AMTime;
                Log.d(TAG, " moringin12AMTime:" + morningin12AMTime);
                List<OriginalChatMessage> wholeDayMessageList = getDayWiseMessage(morning12AmTimeSnapSHot, socialMediaGroup, morningin12AMTime);
                grpChatMap.put(morningin12AMTime, wholeDayMessageList);
            }
            socialMediaGroup.chatMessageList = grpChatMap;
            allSocialMediaGroupMessageList.add(socialMediaGroup);
        }
        return allSocialMediaGroupMessageList;
    }

    private ArrayList<SocialMediaGroup> getMessagetForInstagram(DataSnapshot userSnapShot) {
        ArrayList<SocialMediaGroup> allSocialMediaGroupMessageList = new ArrayList<>();
        for (DataSnapshot abcWSnapChildShot : userSnapShot.child(Constants.SOCIAL_MEDIA_INSTA_APP).getChildren()) {
            String morningin12AMTime = "";
            String groupName = abcWSnapChildShot.getKey();
            SocialMediaGroup socialMediaGroup = new SocialMediaGroup();
            socialMediaGroup.groupName = groupName;
            Log.d(TAG, "SocialMediaGroup:   " + groupName);
            HashMap<String, List<OriginalChatMessage>> grpChatMap = new LinkedHashMap<>();
            for (DataSnapshot morning12AmTimeSnapSHot : abcWSnapChildShot.getChildren()) {
                morningin12AMTime = morning12AmTimeSnapSHot.getKey();
                socialMediaGroup.morning12AmTime = morningin12AMTime;
                Log.d(TAG, " moringin12AMTime:" + morningin12AMTime);
                List<OriginalChatMessage> wholeDayMessageList = getDayWiseMessage(morning12AmTimeSnapSHot, socialMediaGroup, morningin12AMTime);
                grpChatMap.put(morningin12AMTime, wholeDayMessageList);
            }
            socialMediaGroup.chatMessageList = grpChatMap;
            allSocialMediaGroupMessageList.add(socialMediaGroup);
        }
        return allSocialMediaGroupMessageList;
    }


    private List<OriginalChatMessage> getDayWiseMessage(DataSnapshot morning12AmTimeSnapSHot, SocialMediaGroup socialMediaGroup, String morningin12AMTime) {
        List<OriginalChatMessage> originalChatMessageList = new ArrayList<>();
        for (DataSnapshot messageListSnapShot : morning12AmTimeSnapSHot.getChildren()) {
            Log.d(TAG, " read message:" + messageListSnapShot.getKey());
            Long timeStamp = 0L;
            if (messageListSnapShot.getKey().toString().matches("[0-9]+"))
                timeStamp = Long.valueOf(messageListSnapShot.getKey().toString());
            else
                timeStamp = Long.valueOf(11111);

            socialMediaGroup.currentTimeStamp = timeStamp;

            ArrayList<String> chatMessageList = getRawMessage(messageListSnapShot);
            OriginalChatMessage originalChatMessage = new OriginalChatMessage(chatMessageList, timeStamp);
            originalChatMessageList.add(originalChatMessage);
        }
        return originalChatMessageList;
    }*/

}
