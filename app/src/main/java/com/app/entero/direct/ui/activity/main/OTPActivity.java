package com.app.entero.direct.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.utils.Utils;

public class OTPActivity extends BaseActivity implements View.OnClickListener{

    EditText et_otp;
    Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        initView();
    }

    private void initView(){
        et_otp =(EditText)findViewById(R.id.et_otp);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubmit){
            if(Utils.INSTENS.OtpValidation(et_otp.getText().toString().trim())){

                if(et_otp.getText().toString().trim().equals("123"))
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
                }

            }else{
                Toast.makeText(OTPActivity.this,"Please enter valid",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
