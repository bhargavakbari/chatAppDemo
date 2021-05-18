package com.android.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.myapplication.Pojo.ChatMessage;
import com.android.myapplication.R;
import com.android.myapplication.listener.RecyclerViewClickListner;
import com.android.myapplication.utils.Utils;

import java.util.ArrayList;

import static com.android.myapplication.utils.Constants.TYPE_PARENT;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int ITEM_PARENT = 0;
    private static int ITEM_CHILD = 1;
    ArrayList<ChatMessage> allChatMessage;
    RecyclerViewClickListner clickListner;

    public ChatAdapter(ArrayList<ChatMessage> allMessage, RecyclerViewClickListner clickListner) {
        this.allChatMessage = allMessage;
        this.clickListner = clickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_PARENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patent_date, parent, false);
            return new ParentItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, parent, false);
            return new CHildItemViewHolder(view);
        }
    }

    public void refreshAdapterData(ArrayList<ChatMessage> allMessage) {
        this.allChatMessage = allMessage;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ParentItemViewHolder)
            ((ParentItemViewHolder) holder).setUpData(position);
        else
            ((CHildItemViewHolder) holder).setUpData(position);
    }

    public void notifyAdapterData(ArrayList<ChatMessage> chatMessageArrayList) {
        this.allChatMessage = chatMessageArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (allChatMessage.get(position).type.equals(TYPE_PARENT))
            return ITEM_PARENT;
        return ITEM_CHILD;
    }

    @Override
    public int getItemCount() {
        return allChatMessage.size();
    }

    class ParentItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;

        public ParentItemViewHolder(final View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListner.onItemClicked(itemView, getAdapterPosition());
                }
            });
        }

        protected void setUpData(int position) {
            tvTime.setText(allChatMessage.get(position).dayTime);
        }
    }

    class CHildItemViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout containerLayoutUser;
        public TextView messageTextViewUser;
        public TextView timeTextViewUser;

        public CHildItemViewHolder(final View itemView) {
            super(itemView);
            containerLayoutUser = (LinearLayout) itemView.findViewById(R.id.ll_container_user);
            messageTextViewUser = (TextView) itemView.findViewById(R.id.tv_message_user);
            timeTextViewUser = (TextView) itemView.findViewById(R.id.tv_time_user);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListner.onItemClicked(itemView, getAdapterPosition());
                }
            });

        }

        protected void setUpData(int position) {
            containerLayoutUser.setVisibility(View.VISIBLE);
            String wholeMsg = "";
            for (String msg : allChatMessage.get(position).allMsg) {
                wholeMsg = wholeMsg + "\n" + msg + "\n";
            }
            messageTextViewUser.setText(wholeMsg);
            timeTextViewUser.setText("Time: " + Utils.getMillisToHumanRedableTime2(allChatMessage.get(position).msgTime));
        }
    }
}
