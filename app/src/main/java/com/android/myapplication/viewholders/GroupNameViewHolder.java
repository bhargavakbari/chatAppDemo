package com.android.myapplication.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.myapplication.Pojo.SocialMediaGrps;
import com.android.myapplication.R;
import com.android.myapplication.listener.RecyclerViewClickListner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GroupNameViewHolder extends BaseViewHolder {

    private ArrayList<SocialMediaGrps> socialMediaGrpNameArrayList = new ArrayList<>();

    public TextView groupNameTv, timeTextViewUser;
    public RecyclerViewClickListner clicklistner;


    public GroupNameViewHolder(@NotNull View itemView, @NotNull Context mContext) {
        super(itemView, mContext);

        groupNameTv = (TextView) itemView.findViewById(R.id.tvGrpName);
        timeTextViewUser = (TextView) itemView.findViewById(R.id.tvTime);

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
        socialMediaGrpNameArrayList = (ArrayList<SocialMediaGrps>) items;
    }


    @Override
    public void currentItemPosition(int position) {
        super.currentItemPosition(position);
        groupNameTv.setText(socialMediaGrpNameArrayList.get(position).getGroupName());
        timeTextViewUser.setText("Time: " + socialMediaGrpNameArrayList.get(position).getLastUsedFormatedTime());
    }
}
