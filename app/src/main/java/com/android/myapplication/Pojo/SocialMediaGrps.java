package com.android.myapplication.Pojo;

public class SocialMediaGrps {
    String groupName;
    String lastUsedFormatedTime;

    public SocialMediaGrps(String groupName, String lastUsedFormatedTime) {
        this.groupName = groupName;
        this.lastUsedFormatedTime = lastUsedFormatedTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLastUsedFormatedTime() {
        return lastUsedFormatedTime;
    }

    public void setLastUsedFormatedTime(String lastUsedFormatedTime) {
        this.lastUsedFormatedTime = lastUsedFormatedTime;
    }

    @Override
    public String toString() {
        return "SocialMediaGrps{" +
                "groupName='" + groupName + '\'' +
                ", lastUsedFormatedTime=" + lastUsedFormatedTime +
                '}';
    }
}
