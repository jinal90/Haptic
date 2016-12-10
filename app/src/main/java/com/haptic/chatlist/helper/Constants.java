package com.haptic.chatlist.helper;

import java.util.HashMap;

/**
 * Created by jinal on 12/10/2016.
 */

public class Constants {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;
    public static final int NETWORK_ERROR = 2;

    public static final int CHAT_LIST = 11;
    public static final int MESSAGE = 12;

    public static final HashMap<String, Integer> TAB_HASHMAP;
    static{
        TAB_HASHMAP = new HashMap<>();
        TAB_HASHMAP.put("Chat List", CHAT_LIST);
        TAB_HASHMAP.put("Message", MESSAGE);
    }
}
