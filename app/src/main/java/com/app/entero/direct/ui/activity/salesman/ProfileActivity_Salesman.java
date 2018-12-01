package com.app.entero.direct.ui.activity.salesman;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;

public class ProfileActivity_Salesman extends BaseActivity {
    TextView txtHeader;
    TextView txtcustName,txtCustAddrs,txtCustPhone,txtCustCode,txtEmail,txtPending;
    Toolbar mToolbar;
    Bundle bundle;
    SalesmanDashBoardModel listSalesmanData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_profile);
        initView();
        setToolbar();
        onSetText();

     //   position = bundle.getInt("position");
       // onClickEvent();

    }

    private void onSetText() {
        txtHeader.setText("Customer Profile");
        txtcustName.setText(listSalesmanData.getChemistLegalName());
        txtCustAddrs.setText(listSalesmanData.getChemistAddress());
        txtCustPhone.setText(listSalesmanData.getMobileNo());
        txtCustCode.setText(listSalesmanData.getChemistErpCode());
        txtEmail.setText(listSalesmanData.getEmail());
        txtPending.setText(listSalesmanData.getOutStanding());

    }

    private void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        bundle=getIntent().getExtras();
        listSalesmanData = (SalesmanDashBoardModel) bundle.getSerializable("array_list");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        txtcustName =(TextView)findViewById(R.id.txtCustName);
        txtCustAddrs=(TextView)findViewById(R.id.txtCustAddrs);
        txtCustCode=(TextView)findViewById(R.id.text_Custcode);
        txtEmail=(TextView)findViewById(R.id.text_emilId);

        txtCustPhone=(TextView)findViewById(R.id.text_phno);
        txtPending=(TextView)findViewById(R.id.text_pendingamt);

    }
}
