package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.OrderDetailsCustomAdapter_Salesman;

public class OrderDetailsActivity_Saleesman extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    SearchView searchView;
    TextView txtHeader;
    private static RecyclerView.Adapter adapter_all_order_details;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_all_order_details;
    private static ArrayList<Outstandings> allOrderDetailsData;
    TextView order_details_name,order_details_status,order_details_id,order_details_total_amount,
            order_details_date,order_details_time,order_details_invoice_id,
            order_details_paid_amount,order_details_paid_date,order_details_paid_time,order_details_item_list_total,
            order_details_item_list_discount,order_details_item_list_grand_total;
    LinearLayout order_details,invoice_details;
    ImageView first_arrow,second_arrow;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_order_details);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();
        //Toolbar with name
        recycler_view_all_order_details.setHasFixedSize(true);
        recycler_view_all_order_details.setLayoutManager(layoutManager);
        recycler_view_all_order_details.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            allOrderDetailsData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }
              // set all data to UI field
                fetchAndSetData();
    }

    private void onClickEvent() {
        order_details.setOnClickListener(this);
        invoice_details.setOnClickListener(this);
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.orderdetails));
}

    private void setToolbar() {
        mToolbar.setTitle(getString(R.string.orderdetails));
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

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        order_details_name = (TextView) findViewById(R.id.order_details_name);
        order_details_status = (TextView) findViewById(R.id.order_details_status);
        order_details_id = (TextView) findViewById(R.id.order_details_id);
        order_details_total_amount = (TextView) findViewById(R.id.order_details_total_amount);
        order_details_date = (TextView) findViewById(R.id.order_details_date);
        order_details_time = (TextView) findViewById(R.id.order_details_time);
        order_details_invoice_id = (TextView) findViewById(R.id.order_details_invoice_id);
        order_details_paid_amount = (TextView) findViewById(R.id.order_details_paid_amount);
        order_details_paid_date = (TextView) findViewById(R.id.order_details_paid_date);
        order_details_paid_time = (TextView) findViewById(R.id.order_details_paid_time);
        order_details_item_list_total = (TextView) findViewById(R.id.order_details_item_list_total);
        order_details_item_list_discount = (TextView) findViewById(R.id.order_details_item_list_discount);
        order_details_item_list_grand_total = (TextView) findViewById(R.id.order_details_item_list_grand_total);
        order_details = (LinearLayout) findViewById(R.id.order_details);
        invoice_details = (LinearLayout) findViewById(R.id.invoice_details);
        first_arrow = (ImageView) findViewById(R.id.first_arrow);
        second_arrow = (ImageView) findViewById(R.id.second_arrow);
        recycler_view_all_order_details = (RecyclerView) findViewById(R.id.recycler_view_all_order_details);
        layoutManager = new LinearLayoutManager(this);
        allOrderDetailsData = new ArrayList<Outstandings>();
    }

    private void fetchAndSetData() {
        adapter_all_order_details = new OrderDetailsCustomAdapter_Salesman(allOrderDetailsData);
        recycler_view_all_order_details.setAdapter(adapter_all_order_details);
        //adapter_all_order_details.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_details:
                first_arrow.setVisibility(View.VISIBLE);
                second_arrow.setVisibility(View.GONE);
                fetchAndSetData();
                break;
            case R.id.invoice_details:
                second_arrow.setVisibility(View.VISIBLE);
                first_arrow.setVisibility(View.GONE);
                fetchAndSetData();
                break;
        }
    }

}
