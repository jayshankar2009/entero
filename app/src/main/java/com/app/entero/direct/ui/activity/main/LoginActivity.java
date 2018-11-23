package com.app.entero.direct.ui.activity.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.entero.direct.R;
import com.app.entero.direct.utils.Utils;
import com.app.entero.direct.utils.get_imie_number;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    EditText et_Imei;
    Button btnLogin;
    private String IMEI = "";
    public static final int MY_PERMISSIONS_REQUEST_PHONE_STATE = 125;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initObjects();
        initView();
    }

    public void initView(){
        et_Imei=(EditText)findViewById(R.id.et_Imei);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        IMEI = new get_imie_number().check_imie_permission(this);
        et_Imei.setText(IMEI);

    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            Intent login =new Intent(LoginActivity.this,OTPActivity.class);
            startActivity(login);
        }
    }

    /* Runtime Permissions */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    IMEI = Utils.get_iemi(LoginActivity.this);
                    et_Imei.setText(IMEI);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    new get_imie_number().check_imie_permission(this);

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
