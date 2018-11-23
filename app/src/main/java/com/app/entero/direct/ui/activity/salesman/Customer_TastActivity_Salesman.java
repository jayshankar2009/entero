package com.app.entero.direct.ui.activity.salesman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.ui.activity.main.BaseActivity;

public class Customer_TastActivity_Salesman extends BaseActivity implements View.OnClickListener {

    RelativeLayout btn_takeOrder,btn_collectpayment,btn_take_delivery;
    TextView textView_profile;
    Toolbar mToolbar;
    TextView txtHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_customer__tast);
        initLayout();
        setToolbar();
        onSetText();
        onClickEvent();
        }

    private void onSetText() {
        txtHeader.setText("Customer Task");
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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        btn_takeOrder = (RelativeLayout) findViewById(R.id.btn_take_order);
        btn_collectpayment = (RelativeLayout) findViewById(R.id.btn_collectpayment);
        textView_profile = (TextView) findViewById(R.id.btn_view_profile);
        btn_take_delivery=(RelativeLayout)findViewById(R.id.btn_take_delivery);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_profile :
                Intent i = new Intent(Customer_TastActivity_Salesman.this,ProfileActivity_Salesman.class);
                startActivity(i);
                break;

            case R.id.btn_collectpayment:
             //   Toast.makeText(getApplicationContext(),"Coll",Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(Customer_TastActivity_Salesman.this,CollectPaymentActivity_Salesman.class);
                startActivity(i2);
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
}
