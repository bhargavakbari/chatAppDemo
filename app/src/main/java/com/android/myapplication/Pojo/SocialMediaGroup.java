package com.android.myapplication.Pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class SocialMediaGroup implements Serializable {

    public String groupName;
    public String morning12AmTime;
    public HashMap<String, List<OriginalChatMessage>> chatMessageList;
    public long currentTimeStamp;

    public SocialMediaGroup() {
    }

    @Override
    public String toString() {
        return "SocialMediaGroup{" +
                "groupName='" + groupName + '\'' +
                ", morning12AmTime='" + morning12AmTime + '\'' +
                ", chatMessageList=" + chatMessageList +
                ", currentTimeStamp=" + currentTimeStamp +
                '}';
    }
}
