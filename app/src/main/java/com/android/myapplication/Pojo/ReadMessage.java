package com.android.myapplication.Pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class ReadMessage implements Serializable {

    public String userId;
    public String androidId;
    public String email;
    public ArrayList<SocialMediaGroup> socialMediaGroupGrpList;
    public ArrayList<SocialMediaGroup> abcIGrpList;

    public ReadMessage(String userId, String androidId, String email, ArrayList<SocialMediaGroup> socialMediaGroupGrpList, ArrayList<SocialMediaGroup> abcIGrpList) {
        this.userId = userId;
        this.androidId = androidId;
        this.email = email;
        this.socialMediaGroupGrpList = socialMediaGroupGrpList;
        this.abcIGrpList = abcIGrpList;
    }

    @Override
    public String toString() {
        return "ReadMessage{" +
                "userId='" + userId + '\'' +
                ", androidId='" + androidId + '\'' +
                ", email='" + email + '\'' +
                ", socialMediaGroupGrpList=" + socialMediaGroupGrpList +
                ", abcIGrpList=" + abcIGrpList +
                '}';
    }
}
