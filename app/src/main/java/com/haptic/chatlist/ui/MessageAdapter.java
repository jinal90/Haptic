package com.haptic.chatlist.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haptic.chatlist.R;
import com.haptic.chatlist.model.Count;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jinal on 12/10/2016.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<Count> list;

    MessageAdapter(HashMap<String, Count> messageCountMap) {
        list = new ArrayList<Count>(messageCountMap.values());
    }

    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MessageAdapter.MyViewHolder holder, int position) {
        final Count count = list.get(position);
        holder.tvName.setText(count.getName());
        holder.tvTotalMessages.setText(
                String.format(holder.itemView.getContext().getString(R.string.total_message),
                        String.valueOf(count.getTotalCount())));
        holder.tvTotalFavorites.setText(
                String.format(holder.itemView.getContext().getString(R.string.total_favorite),
                        String.valueOf(count.getTotalFavorite())));

        if (!TextUtils.isEmpty(count.getImageUrl()))
            Picasso.with(holder.itemView.getContext())
                    .load(count.getImageUrl())
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
                                    .load(count.getImageUrl())
                                    .placeholder(holder.itemView.getResources().getDrawable(R.drawable.ic_face_black_24dp))
                                    .error(holder.itemView.getResources().getDrawable(R.drawable.ic_face_black_24dp))
                                    .into(holder.ivPicture);
                        }
                    });
        else {
            holder.ivPicture.setImageResource(R.drawable.ic_face_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvTotalMessages, tvTotalFavorites;
        public ImageView ivPicture;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvUsername);
            tvTotalMessages = (TextView) view.findViewById(R.id.tvTotalMessages);
            tvTotalFavorites = (TextView) view.findViewById(R.id.tvTotalFavorites);
            ivPicture = (ImageView) view.findViewById(R.id.ivUserPic);
        }
    }
}
