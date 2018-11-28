package com.app.entero.direct.ui.activity.salesman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;

import java.util.ArrayList;

public class Customer_TastActivity_Salesman extends BaseActivity implements View.OnClickListener {

    RelativeLayout btn_takeOrder,btn_collectpayment,btn_take_delivery;
    TextView textView_profile;
    Toolbar mToolbar;
    Bundle bundle;
    SalesmanDashBoardModel listSalesmanData;
    TextView txtHeader,txtCustId,txtDistrName,txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_customer__tast);
        initLayout();
        setToolbar();
//onVisiblityMode();
        onSetText();
        onClickEvent();

  //      Toast.makeText(getApplicationContext(),"---"+listSalesmanData,Toast.LENGTH_SHORT).show();
        }

    private void onSetText() {
        txtHeader.setText("Customer Task");
    txtCustId.setText(listSalesmanData.getChemistErpCode());
        txtAddress.setText(listSalesmanData.getChemistAddress());
        txtDistrName.setText(listSalesmanData.getChemistLegalName());
       /* txtAddress.setText(listSalesmanData.getChemistAddress());
        txtDistrName.setText(listSalesmanData.getChemistLegalName());*/
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

    private void onClickEvent() {
        textView_profile.setOnClickListener(this);
        btn_collectpayment.setOnClickListener(this);
        btn_takeOrder.setOnClickListener(this);
        btn_take_delivery.setOnClickListener(this);
    }

    private void initLayout() {
        bundle = getIntent().getExtras();
        listSalesmanData = (SalesmanDashBoardModel) bundle.getSerializable("array_list");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        btn_takeOrder = (RelativeLayout) findViewById(R.id.btn_take_order);
        btn_collectpayment = (RelativeLayout) findViewById(R.id.btn_collectpayment);
        textView_profile = (TextView) findViewById(R.id.btn_view_profile);
        btn_take_delivery=(RelativeLayout)findViewById(R.id.btn_take_delivery);
        txtCustId = (TextView) findViewById(R.id.txtCustId);
        txtDistrName = (TextView) findViewById(R.id.txtDistrName);
        txtAddress= (TextView) findViewById(R.id.txtAddress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_profile :
                Intent i = new Intent(Customer_TastActivity_Salesman.this,ProfileActivity_Salesman.class);
                i.putExtra("array_list",listSalesmanData);
                startActivity(i);
                break;

            case R.id.btn_collectpayment:
             //   Toast.makeText(getApplicationContext(),"Coll",Toast.LENGTH_LONG).show();
               // if(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getRoleID().equals(Constants.Salesman)) {
                    Intent i2 = new Intent(Customer_TastActivity_Salesman.this, CollectPaymentActivity_Salesman.class);
                    startActivity(i2);
             /*   }else {
                    Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();
                }*/
                break;

            case R.id.btn_take_order :
                Intent i3 = new Intent(Customer_TastActivity_Salesman.this,TakeOrderActivity_Salesman.class);
                startActivity(i3);
                break;

            case R.id.btn_take_delivery :
                Intent i4 = new Intent(Customer_TastActivity_Salesman.this,DeliveryActivity_Salesman.class);
                startActivity(i4);
                break;
        }

    }
    /*private void onVisiblityMode() {

        if(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getRoleID().equals(String.valueOf(Constants.Deliveryboy))) {
              btn_collectpayment.setVisibility(View.GONE);
              btn_takeOrder.setVisibility(View.GONE);
              btn_take_delivery.setVisibility(View.VISIBLE);
        }
        else if(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getRoleID().equals(String.valueOf(Constants.CollectionAgent))){
            btn_take_delivery.setVisibility(View.GONE);
            btn_takeOrder.setVisibility(View.GONE);
            btn_collectpayment.setVisibility(View.VISIBLE);


        }
    }*/
}
