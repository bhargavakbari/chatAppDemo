package com.android.myapplication.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    //private final int orientation;
    public static final String CHAT_TAG = "CHAT_ITEM";
    private final int separation;
    private final String flag;

    public SpacesItemDecoration(int orientation, int separation) {
        //this.orientation = orientation;
        flag = "";
        this.separation = separation;
    }

    public SpacesItemDecoration(String flag, int separation) {
        this.separation = separation;
        this.flag = flag;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (flag.equals(CHAT_TAG)) {
            outRect.top = separation;
        }
    }
}
