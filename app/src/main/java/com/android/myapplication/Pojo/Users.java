package com.android.myapplication.Pojo;

public class Users {
    private String androidId;
    private String email;
    private String userId;
    private String accessibilityTime;

    public Users(String userid, String androidId, String email, String accessibilityTime) {
        this.userId = userid;
        this.androidId = androidId;
        this.email = email;
        this.accessibilityTime = accessibilityTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessibilityTime() {
        return accessibilityTime;
    }

    @Override
    public String toString() {
        return "Users{" +
                "androidId='" + androidId + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", accessibilityTime='" + accessibilityTime + '\'' +
                '}';
    }
}
