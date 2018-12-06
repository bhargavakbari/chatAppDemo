package com.android.myapplication.Pojo;

import java.util.ArrayList;

/**
 * Created by Bhargav on 31/01/17.
 */

public class ChatMessage {
    public String dayTime;
    public long msgTime;
    public String type;
    public ArrayList<String> allMsg;


    public ChatMessage(String dayTime, long msgTime, String type, ArrayList<String> allMsg) {
        this.dayTime = dayTime;
        this.msgTime = msgTime;
        this.type = type;
        this.allMsg = allMsg;
    }

    public ChatMessage(String dayTime, String type) {
        this.dayTime = dayTime;
        this.type = type;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getAllMsg() {
        return allMsg;
    }

    public void setAllMsg(ArrayList<String> allMsg) {
        this.allMsg = allMsg;
    }
}
