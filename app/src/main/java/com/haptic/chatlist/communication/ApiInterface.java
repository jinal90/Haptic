package com.haptic.chatlist.communication;

import com.haptic.chatlist.model.Chat;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jinal on 12/10/2016.
 */

public interface ApiInterface {

    @GET("test_data/")
    Call<Chat> getChatData();

}
