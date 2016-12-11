package com.haptic.chatlist.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.haptic.chatlist.R;
import com.haptic.chatlist.helper.Constants;
import com.haptic.chatlist.helper.Utility;
import com.haptic.chatlist.model.Chat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatFragment extends Fragment {

    RecyclerView recyclerView;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            showContent();
        }
        return view;
    }

    public void showContent() {
        // Set the adapter
        String chatJson = Utility.getSavedStringDataFromPref(getActivity(), Constants.CHAT_DATA);
        Gson gson = new Gson();
        Chat chatObj = gson.fromJson(chatJson, Chat.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ChatAdapter adapter = new ChatAdapter(chatObj);
        recyclerView.setAdapter(adapter);
    }
}
