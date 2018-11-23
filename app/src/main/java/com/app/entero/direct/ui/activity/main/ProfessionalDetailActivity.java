package com.app.entero.direct.ui.activity.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.utils.Utils;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;
import com.app.entero.direct.utils.get_imie_number;

import org.w3c.dom.Text;

public class ProfessionalDetailActivity extends BaseActivity implements View.OnClickListener{

    EditText shop_or_chemist_name;
    TextView btnLogin;
    private CustomTextView_Salesman skippTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);
        initObjects();
        initView();
    }

    public void initView(){
        shop_or_chemist_name =(EditText)findViewById(R.id.et_Imei);
        skippTV  =(CustomTextView_Salesman) findViewById(R.id.skippTV);
        btnLogin=(TextView)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        skippTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin || v == skippTV){
            Intent login =new Intent(ProfessionalDetailActivity.this,HomeActivity.class);
            startActivity(login);
        }
    }

}
