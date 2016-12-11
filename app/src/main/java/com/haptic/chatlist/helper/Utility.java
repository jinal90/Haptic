package com.haptic.chatlist.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haptic.chatlist.R;
import com.haptic.chatlist.model.Chat;
import com.haptic.chatlist.model.Count;
import com.haptic.chatlist.model.Message;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by jinal on 12/10/2016.
 */

public class Utility {

    public static void saveStringDataInPref(Context context, String key,
                                            String data) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putString(key, data).commit();
    }

    public static String getSavedStringDataFromPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null)
            return info.isConnected();
        else
            return false;
    }

    public static AlertDialog showTwoButtonDialog(Context context, String title, String message,
                                                  final String positiveBtn, final Callable positiveBtnCallable,
                                                  final String negativeBtn, final Callable negativeBtnCallable) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (positiveBtnCallable != null)
                    try {
                        positiveBtnCallable.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        builder.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (negativeBtnCallable != null)
                    try {
                        negativeBtnCallable.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                else
                    dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }

    public static void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();

        // Changing snackbar background color
        sbView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));

        // Changing snackbar text color
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(view.getContext(), R.color.primary_text));
        snackbar.show();
    }

    public static HashMap<String, Count> processChatData(Context context){
        Gson gson = new Gson();
        Chat chatObj = gson.fromJson(getSavedStringDataFromPref(context, Constants.CHAT_DATA), Chat.class);

        HashMap<String, Count> userMessageCountMap = new HashMap<>();

        for (Message msg : chatObj.getMessages()){

            if(userMessageCountMap.get(msg.getUsername()) != null){
                userMessageCountMap.get(msg.getUsername())
                        .setTotalCount(userMessageCountMap
                                .get(msg.getUsername()).getTotalCount() + 1);

                if(msg.isFavorite()){
                    userMessageCountMap.get(msg.getUsername())
                            .setTotalFavorite(userMessageCountMap
                                    .get(msg.getUsername()).getTotalFavorite() + 1);
                }
            }else{
                Count count = new Count();
                count.setName(msg.getName());
                count.setImageUrl(msg.getImage_url());
                count.setTotalCount(1);
                if(msg.isFavorite())
                    count.setTotalFavorite(1);
                else
                    count.setTotalFavorite(0);

                userMessageCountMap.put(msg.getUsername(), count);
            }

        }

        return  userMessageCountMap;
    }

}
