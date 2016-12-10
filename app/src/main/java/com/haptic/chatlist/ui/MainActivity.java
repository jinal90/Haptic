package com.haptic.chatlist.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.haptic.chatlist.R;
import com.haptic.chatlist.communication.ApiClient;
import com.haptic.chatlist.communication.ApiInterface;
import com.haptic.chatlist.helper.Constants;
import com.haptic.chatlist.helper.Utility;
import com.haptic.chatlist.model.Chat;

import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.rlErrorView)
    RelativeLayout rlErrorView;
    @BindView(R.id.rlProgressIndicator)
    RelativeLayout rlProgressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupUI();
    }

    public void setupUI() {
        setSupportActionBar(toolbar);
        showProgressIndicator();
        fetchContent();
    }

    public void showProgressIndicator() {

        rlProgressIndicator.setVisibility(View.VISIBLE);
        rlErrorView.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        mTabs.setVisibility(View.GONE);

    }

    public void showErrorView() {
        rlProgressIndicator.setVisibility(View.GONE);
        rlErrorView.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.GONE);
        mTabs.setVisibility(View.GONE);
    }

    public void showContent() {
        rlProgressIndicator.setVisibility(View.GONE);
        rlErrorView.setVisibility(View.GONE);
        mViewPager.setVisibility(View.VISIBLE);
        mTabs.setVisibility(View.VISIBLE);
        setupViewPager(mViewPager);
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mTabs.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ChatFragment.newInstance(), "Chat");
        adapter.addFragment(ChatFragment.newInstance(), "Message");
        viewPager.setAdapter(adapter);
    }

    public void fetchContent(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Chat> call = apiService.getChatData();
        call.enqueue(new Callback<Chat>() {

            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {

                Log.d("OnResponse", " -- "+response.body());
                Gson gson = new Gson();
                Utility.saveStringDataInPref(MainActivity.this, "chatData", gson.toJson(response.body()));
                showContent();
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                Log.d("OnFailure", " -- ");
                showErrorView();
            }
        });
    }
}
