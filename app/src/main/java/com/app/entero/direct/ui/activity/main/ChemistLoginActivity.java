package com.app.entero.direct.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.SalesmanModel;
import com.app.entero.direct.model.SchemeListModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.Utils;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.LinkedHashMap;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChemistLoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ChemistLoginActivity";
    EditText mobileNumberEdt;
    Button btnLogin;
    private CustomTextView_Salesman txtImei;
    public static final int MY_PERMISSIONS_REQUEST_PHONE_STATE = 125;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemist_login);
        initObjects();
        initView();
    }

    public void initView() {
        txtImei = (CustomTextView_Salesman) findViewById(R.id.txtImei);
        txtImei.setText("Mobile Number");
        mobileNumberEdt = (EditText) findViewById(R.id.et_Imei);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            if (mobileNumberEdt.getText().toString().equals("")) {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();

            } else {
                hashMap = new LinkedHashMap<>();
                hashMap.put(ApiConstants.MOBILE_NUMBER, mobileNumberEdt.getText().toString());
                if (isNetworkAvailable()) {
                    callApi(hashMap);
                }
            }

        }
    }


    public void callApi(LinkedHashMap<String, String> linkedHashMap) {
        mCompositeDisposable.add(getApiCallService().getOTPCode(SavePref.getInstance(this).getToken(), ApiConstants.GETOTPCODE, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }


    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void handleResponse(SalesmanModel mObject) {
       // Log.e(TAG, " error: " + jsonObj);
       // JSONObject mObject = Utils.objectToJSONObject(jsonObj.toString());
        isShowProgress(false);
        String status ="";
        String message ="";
        String otp ="";
        try {
            if (!mObject.getStatus().equals("")) {
                status =  mObject.getStatus();
            }
            if (!mObject.getMessage().equals("")) {
               message = mObject.getMessage();
            }
            if (mObject.getOTP()!=null && !mObject.getOTP().equals("")) {
               otp = mObject.getOTP();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(status.equals("success"))
        {
            Intent login = new Intent(ChemistLoginActivity.this, OTPActivity.class);
            login.putExtra(ApiConstants.OTP,otp);
            login.putExtra(ApiConstants.MOBILE_NUMBER,mobileNumberEdt.getText().toString());
            startActivity(login);
        }
        else
        {
            Intent login = new Intent(ChemistLoginActivity.this, OTPActivity.class);
            login.putExtra(ApiConstants.OTP,otp);
            login.putExtra(ApiConstants.MOBILE_NUMBER,mobileNumberEdt.getText().toString());
            startActivity(login);
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }
    }




}
