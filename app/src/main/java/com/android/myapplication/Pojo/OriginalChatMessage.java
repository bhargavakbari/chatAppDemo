package com.android.myapplication.Pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class OriginalChatMessage implements Serializable {

    public ArrayList<String> allMessage;
    public Long currentTimeStamp;

    public OriginalChatMessage(ArrayList<String> allMessage, Long currentTimeStamp) {
        this.allMessage = allMessage;
        this.currentTimeStamp = currentTimeStamp;
    }

    @Override
    public String toString() {
        return "OriginalChatMessage{" +
                "allMessage=" + allMessage +
                ", currentTimeStamp=" + currentTimeStamp +
                '}';
    }
}
