package com.example.exam1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Store {

        private static String getUserInfo(String key, Context context, String defaultValue) {
            SharedPreferences settings = context.getSharedPreferences(key,
                    Activity.MODE_PRIVATE);
            return settings.getString(key, defaultValue);
        }

        private static void setUserInfo(String key, String Value, Context context) {
            SharedPreferences settings = context.getSharedPreferences(key,
                    Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(key, Value);
            editor.commit();
        }

        public static String getMoney(Context context) {
            return getUserInfo(MainActivity.PREF_BALANCE, context, "0.00");
        }

        public static void setMoney(String session, Context context) {
            setUserInfo(MainActivity.PREF_BALANCE, session, context);
        }

        public static String getNote(Context context) {
            return getUserInfo(MainActivity.PREF_NOTE, context, "");
        }

        public static void setNote(String session, Context context) {
            setUserInfo(MainActivity.PREF_NOTE, session, context);
        }


    }
