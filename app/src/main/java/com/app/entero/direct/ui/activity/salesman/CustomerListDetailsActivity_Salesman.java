package com.app.entero.direct.ui.activity.salesman;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Salesman_CustomerList_Model;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.CustomerListDetailsPagerAdapter_Salesman;
import com.app.entero.direct.ui.fragment.salesman.AllCustomerList_Fragment_Salesman;

import java.util.ArrayList;

public class CustomerListDetailsActivity_Salesman extends BaseActivity {
    Toolbar mToolbar;
    ViewPager customer_details_viewpager;
    Fragment selectedFragment;
    int position;
 // ArrayList<Salesman_CustomerList_Model> listCustomer;
    TextView txtHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_customer_list_detail_tab);
        initView();
        setToolbar();
        onSetText();
        position=getIntent().getIntExtra("position",0);
                CustomerListDetailsPagerAdapter_Salesman adapter = new CustomerListDetailsPagerAdapter_Salesman(this, position,getSupportFragmentManager());
        customer_details_viewpager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(customer_details_viewpager);


    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader = (TextView) findViewById(R.id.txtHeader);
        customer_details_viewpager = (ViewPager)  findViewById(R.id.customer_details_viewpager);
    }

    private void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Back to previous activity
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.customer_list));
    }
}
