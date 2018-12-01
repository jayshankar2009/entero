package com.app.entero.direct.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.app.entero.direct.model.ProductListModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public enum Utils {
    INSTENS;

    public static boolean OtpValidation(String text){
        if(text!=null&&text.trim().length()>0){
            return true;
        }else{
            return false;
        }
    }
    /*
      get device iemi number
     */
    public static String get_iemi(Context context) {
        TelephonyManager telephonyManager;
        String IMEI_Number_Holder;

        try {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            IMEI_Number_Holder = telephonyManager.getDeviceId();
            return IMEI_Number_Holder;

        } catch (Exception ex) {
            return "";
        }

    }
    public static JSONObject objectToJSONObject(Object object){
        Object json = null;
        JSONObject jsonObject = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }
        return jsonObject;
    }

    public static ProductListModel read(Context context, String fileName) {
        try {

            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ProductListModel mProductListModel  = (ProductListModel) ois.readObject();
            ois.close();

            return mProductListModel;
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean create(Context context, String fileName, ProductListModel jsonString){
        try {
            File file = null;
            file = new File(context.getCacheDir(), "MyCache"); // Pass getFilesDir() and "MyFile" to read file
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);

            if (jsonString != null) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(jsonString);
                oos.close();
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public static boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }



}
