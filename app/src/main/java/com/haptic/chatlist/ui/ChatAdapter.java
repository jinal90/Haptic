package com.haptic.chatlist.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haptic.chatlist.R;
import com.haptic.chatlist.model.Chat;
import com.haptic.chatlist.model.Message;

import java.util.ArrayList;

/**
 * Created by jinal on 12/10/2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private Chat chatObj;

    ChatAdapter(Chat chatObj){
        this.chatObj = chatObj;
    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.MyViewHolder holder, int position) {
        Message msg = chatObj.getMessages().get(position);
        holder.tvName.setText(msg.getName());
        holder.tvMessage.setText(msg.getBody());
        holder.tvTimestamp.setText(msg.getMessage_time());
    }

    @Override
    public int getItemCount() {
        return chatObj.getMessages().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvMessage, tvTimestamp;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvUsername);
            tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            tvTimestamp = (TextView) view.findViewById(R.id.tvTimeStamp);
        }
    }
}
