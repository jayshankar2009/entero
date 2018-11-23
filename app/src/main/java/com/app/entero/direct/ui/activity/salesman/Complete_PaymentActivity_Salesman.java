package com.app.entero.direct.ui.activity.salesman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.ui.activity.main.BaseActivity;


public class Complete_PaymentActivity_Salesman extends BaseActivity implements View.OnClickListener {
    LinearLayout lnrVissible;
    Button btn_payment;
    TextView txtHeader;
    Toolbar mToolbar;
    TextView txtCashPayment,txtCheque;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_complete__payment);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();
        setTextFilter();


    }

    private void setTextFilter() {

    }

    private void onClickEvent() {
        btn_payment.setOnClickListener(this);
        txtCashPayment.setOnClickListener(this);
        txtCheque.setOnClickListener(this);
    }

    private void onSetText() {
      txtHeader.setText("Complete Payment");
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
        btn_payment = (Button) findViewById(R.id.btn_paymentcomplete);
        lnrVissible=(LinearLayout)findViewById(R.id.lnrVissible);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        txtCashPayment=(TextView)findViewById(R.id.txtCashPayment);
        txtCashPayment.setBackgroundColor(getResources().getColor(R.color.highlights));
        txtCheque=(TextView)findViewById(R.id.txtCheque);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_paymentcomplete :
                Intent i = new Intent(Complete_PaymentActivity_Salesman.this,Payment_SuccessActivity_Salesman.class);
                startActivity(i);
                break;

            case R.id.txtCheque:
                lnrVissible.setVisibility(View.VISIBLE);
                txtCheque.setBackgroundColor(getResources().getColor(R.color.highlights));
                txtCashPayment.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;

            case R.id.txtCashPayment:
                lnrVissible.setVisibility(View.GONE);
                txtCashPayment.setBackgroundColor(getResources().getColor(R.color.highlights));
                txtCheque.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                break;
        }
    }
}
