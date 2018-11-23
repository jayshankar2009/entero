package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

 import com.app.entero.direct.Helper.SalesData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Sales;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.SalesDataCustomAdapter_Salesman;

public class SalesReportActivity_Salesman extends BaseActivity implements
        View.OnClickListener{

    Toolbar mToolbar;
    private static RecyclerView.Adapter adapter_sales_details;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_all_sales_data;
    private static ArrayList<Sales> allSalesData = new ArrayList<Sales>();
TextView txtHeader;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView from_date_View,to_date_View,sales_summary_salesman_name,sales_summary_total_target,sales_summary_grand_total;
    private int mYear, mMonth, mDay;
    LinearLayout from_layout, to_layout;
    String date = null;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_summary_layout);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();

         mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(mYear, mMonth+1, mDay);

        // outstandings bill list
               recycler_view_all_sales_data.setHasFixedSize(true);


        recycler_view_all_sales_data.setLayoutManager(layoutManager);
        recycler_view_all_sales_data.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < SalesData.sales_data.length; i++) {
            allSalesData.add(new Sales(
                    SalesData.id_[i],
                    SalesData.sales_data_qty[i],
                    SalesData.return_data_qty[i],
                    SalesData.sales_data[i],
                    SalesData.return_data[i],
                    SalesData.sales_pending_Amount_data[i]

            ));
        }

        adapter_sales_details = new SalesDataCustomAdapter_Salesman(allSalesData);
        recycler_view_all_sales_data.setAdapter(adapter_sales_details);

    }

    private void onClickEvent() {
        from_date_View.setOnClickListener(this);

        to_date_View.setOnClickListener(this);

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
        txtHeader.setText("Sales Details");
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        from_layout = (LinearLayout) findViewById(R.id.from_layout);
        to_layout = (LinearLayout) findViewById(R.id.to_layout);
        from_date_View = (TextView) findViewById(R.id.text_from_date);
        to_date_View = (TextView) findViewById(R.id.text_to_date);
        sales_summary_salesman_name = (TextView) findViewById(R.id.sales_summary_salesman_name);
        sales_summary_total_target = (TextView) findViewById(R.id.sales_summary_total_target);
        sales_summary_grand_total = (TextView) findViewById(R.id.sales_summary_grand_total);
        calendar = Calendar.getInstance();
        recycler_view_all_sales_data = (RecyclerView) findViewById(R.id.recycler_view_all_sales_data);
        layoutManager = new LinearLayoutManager(this);
        }

    private void showDate(int year, int month, int day) {
        from_date_View.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        to_date_View.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.from_layout:
                date = "from";
                pickUpDate();
                break;
            case R.id.to_layout:
                date = "to";
                pickUpDate();
                break;
        }
    }

    private void pickUpDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                   if(date.equalsIgnoreCase("from")) {

                       from_date_View.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                   }else {

                       to_date_View.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                       }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
