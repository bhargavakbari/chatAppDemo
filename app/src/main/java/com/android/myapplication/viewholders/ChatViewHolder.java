package com.android.myapplication.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.myapplication.R;
import com.android.myapplication.listener.RecyclerViewClickListner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChatViewHolder extends BaseViewHolder {

    private ArrayList<String> socialMediaGrpNameArrayList = new ArrayList<>();

    public TextView messageTextViewUser;
    public TextView timeTextViewUser;
    public RecyclerViewClickListner clicklistner;


    public ChatViewHolder(@NotNull View itemView, @NotNull Context mContext) {
        super(itemView, mContext);
        messageTextViewUser = (TextView) itemView.findViewById(R.id.tv_message_user);
        timeTextViewUser = (TextView) itemView.findViewById(R.id.tv_time_user);

        this.clicklistner = clicklistner;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicklistner.onItemClicked(v, getAdapterPosition());
            }
        });
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void itemClickListenerCallBack(@Nullable RecyclerViewClickListner clickListener) {
        super.itemClickListenerCallBack(clickListener);
        this.clicklistner = clickListener;
    }

    @Override
    public void initDataForRecyclerView(@NotNull List<?> items) {
        super.initDataForRecyclerView(items);
        socialMediaGrpNameArrayList = (ArrayList<String>) items;
    }


    @Override
    public void currentItemPosition(int position) {
        super.currentItemPosition(position);
        messageTextViewUser.setText(socialMediaGrpNameArrayList.get(position));
        //timeTextViewUser.setText("Time: " + socialMediaGrpNameArrayList.get(position).currentTimeStamp);
    }
}
