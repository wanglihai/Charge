package com.znxk.charge.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sj on 2015/11/15.
 */
public class SharedPrefrencesUtil {
    public static void saveData(Context context, String fileName, String key, Object data) {
        try {
            String type = data.getClass().getSimpleName();
            SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if ("Integer".equals(type)) {
                editor.putInt(key, (Integer) data);
            } else if ("Boolean".equals(type)) {
                editor.putBoolean(key, (Boolean) data);
            } else if ("String".equals(type)) {
                editor.putString(key, (String) data);
            } else if ("Float".equals(type)) {
                editor.putFloat(key, (Float) data);
            } else if ("Long".equals(type)) {
                editor.putLong(key, (Long) data);
            }
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static <T extends Object> T getData(Context context, String fileName, String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)) {
            return (T) (Integer) sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return (T) (Boolean) sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return (T) sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return (T) (Float) sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return (T) (Long) sharedPreferences.getLong(key, (Long) defValue);
        }
        throw new RuntimeException("get SharedPrefrences error!!");
    }

    /**
     * 删除
     *
     * @param context
     * @param key
     * @return
     */
    public static void removeData(Context context, String fileName, String key) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
