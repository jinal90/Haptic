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
import com.haptic.chatlist.helper.Utility;
import com.haptic.chatlist.model.Chat;
import com.haptic.chatlist.model.Count;

import java.util.HashMap;

public class MessagesFragment extends Fragment {

    RecyclerView recyclerView;

    public MessagesFragment() {
        // Required empty public constructor
    }

    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
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
        HashMap<String, Count> userMessageCountMap = Utility.processChatData(getActivity());
        // Set the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessageAdapter adapter = new MessageAdapter(userMessageCountMap);
        recyclerView.setAdapter(adapter);
    }
}
