package com.app.entero.direct.ui.activity.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.SalesmanModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.Utils;
import com.app.entero.direct.utils.get_imie_number;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {
    private String IMEI = "";

    private LinkedHashMap<String, String> hashMap;
    private LinkedHashMap<String, String> hashMapCallToken;

    public static final int MY_PERMISSIONS_REQUEST_PHONE_STATE = 125;

    private static final String TAG = SplashActivity.class.getName();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initObjects();
        intView();
        if(SavePref.getInstance(getApplicationContext()).getStringValue("ImeiNo","").length()>0){
            //  Toast.makeText(getApplicationContext(),"Check1--"+SavePref.getInstance(getApplicationContext()).getStringValue("ImeiNo",""),Toast.LENGTH_SHORT).show();
            isShowProgress(true);
            hashMap = new LinkedHashMap<>();
            hashMap.put(ApiConstants.IMEIno, SavePref.getInstance(getApplicationContext()).getStringValue("ImeiNo",""));

            callIMIApi(ApiConstants.Get_SalesmandetailsByIMEI,hashMap);

        }else {
            //  Toast.makeText(getApplicationContext(),"Check2",Toast.LENGTH_SHORT).show();
        }
        IMEI = new get_imie_number().check_imie_permission(this);



        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);*/

    }

    private void intView() {

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

        hashMap = new LinkedHashMap<>();
        hashMap.put(ApiConstants.IMEIno, IMEI);

        hashMapCallToken = new LinkedHashMap<>();
        hashMapCallToken.put(ApiConstants.EMAIL,"Entero_CRM");
        hashMapCallToken.put(ApiConstants.PASSWORD,ApiConstants.PASSWORD);

        JSONObject mObject = new JSONObject();
        try {

            mObject.put(ApiConstants.EMAIL,"Entero_CRM");
            mObject.put(ApiConstants.PASSWORD,ApiConstants.PASSWORD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(isNetworkAvailable()) {
            callToken(ApiConstants.GET_TOKEN, hashMapCallToken);
        }else {
            Toast.makeText(getApplicationContext(),"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
        }
      /*  if (isNetworkAvailable()) {
            if(!IMEI.equals(""))
            {
                isShowProgress(true);
                callIMIApi(ApiConstants.Get_SalesmandetailsByIMEI,hashMap);
            }
        }
        else
        {
            Toast.makeText(this,"No internet connection available", Toast.LENGTH_SHORT).show();
        }*/

    }


    private void callIMIApi(String url, LinkedHashMap<String, String> linkedHashMap) {
        isShowProgress(true);
        mCompositeDisposable.add(getApiCallService().get_SalesmandetailsByIMEI(SavePref.getInstance(getApplicationContext()).getToken(),url, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }


    private void callToken(String url, LinkedHashMap<String, String> hashMapCallToken) {
        isShowProgress(true);

        mCompositeDisposable.add(getApiCallService().get_Token(url, hashMapCallToken)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseforToken, this::handleError));

    }

    private void handleResponseforToken(Object mObject) {
        Log.d("jsonObject",""+mObject);
        Object json = null;
        JSONObject jsonObject = null;
        try {
            json = new JSONTokener(mObject.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }

        if(jsonObject.has("token"))
        {
            try {
                SavePref.getInstance(SplashActivity.this).setToken("Bearer "+jsonObject.get("token").toString());
               /* Intent i = new Intent(SplashActivity.this, ChemistLoginActivity.class);
                startActivity(i);
                finish();*/
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void handleResponse(SalesmanModel mSalesmanModel) {
        Log.e(TAG, " res: " + mSalesmanModel.getSalesmanInfo().get(0).getRoleName());
        //  Log.e(TAG, " res: " + raw.);
        isShowProgress(false);
        if(mSalesmanModel.getStatus().equals("success")) {
            if (mSalesmanModel.getMessage().equals("Record found")) {
                SavePref.getInstance(SplashActivity.this).setUserDetail(new Gson().toJson(mSalesmanModel));

                //   SavePref.getInstance(SplashActivity.this).setUserDetail(new Gson().toJson(mSalesmanModel));

                if (mSalesmanModel.getSalesmanInfo().get(0).getRoleID().equals(String.valueOf(Constants.Deliveryboy)) || mSalesmanModel.getSalesmanInfo().get(0).getRoleID().equals(String.valueOf(Constants.Salesman))) {
                    //    Toast.makeText(getApplicationContext(),"StockistId"+mSalesmanModel.getSalesmanInfo().get(0).getRole_ID(),Toast.LENGTH_LONG).show();
                    // Toast.makeText(getApplicationContext(),savePref.getUserDetail().getSalesmanInfo().get(0).getRole_ID(),Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else if (mSalesmanModel.getMessage().equals("No Record found")) {
                    Intent i = new Intent(SplashActivity.this, ChemistLoginActivity.class);
                    startActivity(i);
                    finish();
                }

            } else
            {
                Toast.makeText(this,mSalesmanModel.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    /* Runtime Permissions */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_PHONE_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IMEI = Utils.get_iemi(SplashActivity.this);
                    if(!IMEI.equals(""))
                    {
                        isShowProgress(true);
                        hashMap = new LinkedHashMap<>();
                        hashMap.put(ApiConstants.IMEIno, IMEI);
                        SavePref.getInstance(getApplicationContext()).setValue("ImeiNo",IMEI);
                        callIMIApi(ApiConstants.Get_SalesmandetailsByIMEI,hashMap);
                    }
                } else {
                    new get_imie_number().check_imie_permission(this);
                }
                return;
            }

        }
    }
}
