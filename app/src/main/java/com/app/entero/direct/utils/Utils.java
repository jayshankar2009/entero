package com.app.entero.direct.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

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


}
