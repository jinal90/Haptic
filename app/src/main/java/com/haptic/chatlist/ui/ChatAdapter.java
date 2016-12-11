package com.haptic.chatlist.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haptic.chatlist.R;
import com.haptic.chatlist.model.Chat;
import com.haptic.chatlist.model.Message;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jinal on 12/10/2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private Chat chatObj;

    ChatAdapter(Chat chatObj) {
        this.chatObj = chatObj;
    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChatAdapter.MyViewHolder holder, final int position) {
        final Message msg = chatObj.getMessages().get(position);
        holder.tvName.setText(msg.getName());
        holder.tvMessage.setText(msg.getBody());
        holder.tvTimestamp.setText(msg.getMessage_time());
        if(msg.isFavorite()){
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        }else{
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

        if (!TextUtils.isEmpty(msg.getImage_url()))
            Picasso.with(holder.itemView.getContext())
                    .load(msg.getImage_url())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(holder.itemView.getResources().getDrawable(R.drawable.ic_face_black_24dp))
                    .error(holder.itemView.getResources().getDrawable(R.drawable.ic_face_black_24dp))
                    .into(holder.ivPicture, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Picasso.with(holder.itemView.getContext())
                                    .load(msg.getImage_url())
                                    .placeholder(holder.itemView.getResources().getDrawable(R.drawable.ic_face_black_24dp))
                                    .error(holder.itemView.getResources().getDrawable(R.drawable.ic_face_black_24dp))
                                    .into(holder.ivPicture);
                        }
                    });
        else{
            holder.ivPicture.setImageResource(R.drawable.ic_face_black_24dp);
        }

        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(msg.isFavorite()){
                    msg.setFavorite(false);
                    chatObj.getMessages().get(position).setFavorite(false);
                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else{
                    msg.setFavorite(true);
                    chatObj.getMessages().get(position).setFavorite(true);
                    holder.ivFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatObj.getMessages().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvMessage, tvTimestamp;
        public ImageView ivPicture, ivFavorite;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvUsername);
            tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            tvTimestamp = (TextView) view.findViewById(R.id.tvTimeStamp);
            ivPicture = (ImageView) view.findViewById(R.id.ivUserPic);
            ivFavorite = (ImageView) view.findViewById(R.id.ivFavorite);
        }
    }
}
