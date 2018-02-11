package com.frame.basemodule.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Jiangjw on 2017/2/11.
 */
public class ParamsUtils {

    private static SharedPreferences sharedPreferences;

    private static final String NAME = "share_data";


    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static void setParameter(String key, Object value) {

        String type = value.getClass().getSimpleName();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) value);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) value);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) value);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) value);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) value);
        }
        editor.commit();
    }


    public static String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public static String getString(String key){
        return getString(key, null);
    }


    public static int getInt(String key,int defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static int getInt(String key){
        return getInt(key, 0);
    }


    public static boolean getBoolean(String key,boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(String key){
        return getBoolean(key, false);
    }


    public static float getFloat(String key, float defaulValue){
        return sharedPreferences.getFloat(key, defaulValue);
    }

    public static float getFloat(String key){
        return getFloat(key, 0.00f);
    }


    public static long getLong(String key,long defaultValue){
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static long getLong(String key){
        return getLong(key,0L);
    }
}
