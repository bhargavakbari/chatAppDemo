package com.android.myapplication.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.myapplication.Pojo.Users;
import com.android.myapplication.R;
import com.android.myapplication.listener.RecyclerViewClickListner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FirstActivityViewHolder extends BaseViewHolder {

    ArrayList<Users> allUsers;
    public TextView groupNameTv, timeTextViewUser;
    public RecyclerViewClickListner clicklistner;

    public FirstActivityViewHolder(@NotNull View itemView, @NotNull Context mContext) {
        super(itemView, mContext);

        groupNameTv = (TextView) itemView.findViewById(R.id.tvGrpName);
        timeTextViewUser = (TextView) itemView.findViewById(R.id.tvTime);

        this.clicklistner = clicklistner;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicklistner.onItemClicked(v, getAdapterPosition());
            }
        });
    }

    @Override
    public void itemClickListenerCallBack(@Nullable RecyclerViewClickListner clickListener) {
        super.itemClickListenerCallBack(clickListener);
        this.clicklistner = clickListener;
    }

    @Override
    public void initDataForRecyclerView(@NotNull List<?> items) {
        super.initDataForRecyclerView(items);
        allUsers = (ArrayList<Users>) items;
    }


    @Override
    public void currentItemPosition(int position) {
        super.currentItemPosition(position);

    }

    @Override
    public void currentItemPositionAndData(@NotNull List<?> items, int position) {
        super.currentItemPositionAndData(items, position);
        allUsers = (ArrayList<Users>) items;
        groupNameTv.setText("User Id: " + allUsers.get(position).getUserId()
                + "\n\n" + "androidId: " + allUsers.get(position).getAndroidId()
                + "\n\n" + "Email: " + allUsers.get(position).getEmail()
                + "\n\n" + allUsers.get(position).getAccessibilityTime());
        timeTextViewUser.setText("");
    }
}
