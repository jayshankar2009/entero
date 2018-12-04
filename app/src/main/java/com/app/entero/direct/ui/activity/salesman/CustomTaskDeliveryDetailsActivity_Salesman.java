package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.CustTaskDeliveryDetailsAdapter_Salesman;
import com.app.entero.direct.utils.LocationTrack;
import com.app.entero.direct.utils.getLocation;

public class CustomTaskDeliveryDetailsActivity_Salesman extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    SearchView searchView;
    TextView txtHeader;
    LocationTrack locationTrack;
    private static RecyclerView.Adapter adapter_cust_task_delivery_detail;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_cust_task_delivery_detail;
    private static ArrayList<Outstandings> custTaskDeliveryDetailsData;
    TextView text_cust_task_delivery_detail_name,text_cust_task_delivery_detail_invoice_no,text_cust_task_delivery_detail_items_count,text_cust_task_delivery_detail_packets_count,
            text_cust_task_delivery_detail_boxes_count;
    Button btn_cust_task_delivery_detail_view_invoice;
TextView btn_cust_task_delivery_detail_deliver,btn_cust_task_delivery_detail_undelivered;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_customer_task_delivery_details);
        initLayout();
        new getLocation(this).checkLocation(this);
        setToolbar();
        onSetText();
        onClickEvent();
        recycler_view_cust_task_delivery_detail.setHasFixedSize(true);
        recycler_view_cust_task_delivery_detail.setLayoutManager(layoutManager);
        recycler_view_cust_task_delivery_detail.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            custTaskDeliveryDetailsData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }
              // set all data to UI field
                fetchAndSetData();
    }

    private void onClickEvent() {
        btn_cust_task_delivery_detail_view_invoice.setOnClickListener(this);
        btn_cust_task_delivery_detail_deliver.setOnClickListener(this);
        btn_cust_task_delivery_detail_undelivered.setOnClickListener(this);
    }

    private void onSetText() {
        txtHeader.setText("Delivery Details");
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

    private void initLayout() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        locationTrack= new LocationTrack(this);
        text_cust_task_delivery_detail_name = (TextView) findViewById(R.id.text_cust_task_delivery_detail_name);
        text_cust_task_delivery_detail_invoice_no = (TextView) findViewById(R.id.text_cust_task_delivery_detail_invoice_no);
        text_cust_task_delivery_detail_items_count = (TextView) findViewById(R.id.text_cust_task_delivery_detail_items_count);
        text_cust_task_delivery_detail_packets_count = (TextView) findViewById(R.id.text_cust_task_delivery_detail_packets_count);
        text_cust_task_delivery_detail_boxes_count = (TextView) findViewById(R.id.text_cust_task_delivery_detail_boxes_count);
        btn_cust_task_delivery_detail_view_invoice = (Button) findViewById(R.id.btn_cust_task_delivery_detail_view_invoice);
        btn_cust_task_delivery_detail_deliver = (TextView) findViewById(R.id.btn_cust_task_delivery_detail_deliver);
        btn_cust_task_delivery_detail_undelivered = (TextView) findViewById(R.id.btn_cust_task_delivery_detail_undelivered);
        recycler_view_cust_task_delivery_detail = (RecyclerView) findViewById(R.id.recycler_view_cust_task_delivery_detail);
        layoutManager = new LinearLayoutManager(this);
        custTaskDeliveryDetailsData = new ArrayList<Outstandings>();
    }

    private void fetchAndSetData() {
        adapter_cust_task_delivery_detail = new CustTaskDeliveryDetailsAdapter_Salesman(custTaskDeliveryDetailsData);
        recycler_view_cust_task_delivery_detail.setAdapter(adapter_cust_task_delivery_detail);
        //adapter_all_order_details.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cust_task_delivery_detail_view_invoice:

                break;
            case R.id.btn_cust_task_delivery_detail_deliver:
                if(locationTrack.get_location()) {
                    Intent in = new Intent(CustomTaskDeliveryDetailsActivity_Salesman.this, CustomTaskDeliverDetailStatusActivity_Salesman.class);
                    startActivity(in);
                }else {
                    Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btn_cust_task_delivery_detail_undelivered:
                if(locationTrack.get_location()) {
                    Intent in2 = new Intent(CustomTaskDeliveryDetailsActivity_Salesman.this, DeliveryActivity_Salesman.class);
                    startActivity(in2);
                }else {
                    Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

}
