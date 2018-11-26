package com.app.entero.direct.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.app.entero.direct.model.SalesmanModel;
import com.google.gson.Gson;


public class SavePref {

    private static Context context1;
    private Activity activity;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static SavePref ourInstance = new SavePref();


    public static SavePref getInstance(Context context) {
        context1 = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context1);
        editor = sharedPreferences.edit();
        return ourInstance;
    }

    public void setUserId(String userId) {

        editor.putString(Constants.USER_ID, userId).commit();
    }

    public String getUserId() {
        return getUserDetail().getID();
    }

    public void setToken(String token) {
        editor.putString(Constants.TOKEN, token).commit();
    }

    public String getToken() {
        return sharedPreferences.getString(Constants.TOKEN, "");
    }

    public void setUserDetail(String string) {
        editor.putString(Constants.USER_DETAIL, string).commit();
    }

    public SalesmanModel getUserDetail() {
        if (sharedPreferences.getString(Constants.USER_DETAIL, "").equals(""))
            return null;
        else
            return new Gson().fromJson(sharedPreferences.getString(Constants.USER_DETAIL, ""), SalesmanModel.class);
    }
    public void setValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringValue(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }


}
