package com.app.entero.direct.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

public class ChemistLoginActivity extends BaseActivity implements View.OnClickListener{

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

    public void initView(){
        txtImei =(CustomTextView_Salesman) findViewById(R.id.txtImei);
        txtImei.setText("Mobile Number");
        mobileNumberEdt=(EditText)findViewById(R.id.et_Imei);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            if(mobileNumberEdt.getText().toString().equals(""))
            {
                Toast.makeText(this,"Please Enter Mobile Number", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Intent login =new Intent(ChemistLoginActivity.this,OTPActivity.class);
                startActivity(login);
            }

        }
    }

}
