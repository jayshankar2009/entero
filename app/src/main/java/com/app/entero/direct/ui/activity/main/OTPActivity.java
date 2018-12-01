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
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OTPActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "OTPActivity";
    private EditText et_otp;
    private Button btnSubmit;
    private String otp ="";
    private String mobileNumber ="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        initObjects();
        initView();
    }

    private void initView(){
        Intent intent = getIntent();
        if(intent.hasExtra(ApiConstants.OTP)) {
            otp = intent.getStringExtra(ApiConstants.OTP);
        }
        if(intent.hasExtra(ApiConstants.MOBILE_NUMBER)) {
            mobileNumber = intent.getStringExtra(ApiConstants.MOBILE_NUMBER);
        }

        et_otp =(EditText)findViewById(R.id.et_otp);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubmit){
            if(Utils.INSTENS.OtpValidation(et_otp.getText().toString().trim())){
                /*if(et_otp.getText().toString().trim().equals("123"))
                {

                    Intent intent = new Intent(this, ProfessionalDetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }*/
                hashMap = new LinkedHashMap<>();
                hashMap.put(ApiConstants.MOBILE_NUMBER, mobileNumber);
                hashMap.put(ApiConstants.OTP, et_otp.getText().toString());
                if (isNetworkAvailable()) {
                    callOTPApi(hashMap);
                }

            }else{
                Toast.makeText(OTPActivity.this,"Please enter Otp",Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void callOTPApi(LinkedHashMap<String, String> linkedHashMap) {
        this.mCompositeDisposable.add(getApiCallService().getOTPCode(SavePref.getInstance(this).getToken(), ApiConstants.GETAPPLOGIN, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }


    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void handleResponse(SalesmanModel mObject) {
        Log.e(TAG, " error: " + mObject);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(status.equals("success"))
        {
            Intent intent = new Intent(this, ProfessionalDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(this, ProfessionalDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }
    }
}
