package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

 import com.app.entero.direct.Helper.ReportData;
import com.app.entero.direct.R;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.AllDeliveryReportAdapter_Salesman;

public class DeliveryReportActivity_Salesman extends BaseActivity implements ViewSwitcher.ViewFactory,
        View.OnClickListener {

    Toolbar mToolbar;
    TextSwitcher date_filter;
    Button nextButton,previousButton;
    private int mCounter = 0;
    public static String[] weekfilter = {"Today", "Tomorrow", "Yesterday"};
TextView txtHeader;
    private static RecyclerView.Adapter adapter_all_Delivery_report;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_delivery_report;
    private static ArrayList<ReportData> allOrderData;
    public static View.OnClickListener allreportOnClickListener;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_delivery_report);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();
        setTextFilter();
        recycler_view_delivery_report.setHasFixedSize(true);
        recycler_view_delivery_report.setLayoutManager(layoutManager);
        recycler_view_delivery_report.setItemAnimator(new DefaultItemAnimator());
        adapter_all_Delivery_report = new AllDeliveryReportAdapter_Salesman(this, allOrderData);
        recycler_view_delivery_report.setAdapter(adapter_all_Delivery_report);
    }

    private void onClickEvent() {
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.deliveryreport));
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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        date_filter = (TextSwitcher) findViewById(R.id.date_filter);
        date_filter.setFactory(this);
        nextButton = (Button) findViewById(R.id.next);
        previousButton = (Button) findViewById(R.id.previous);
        recycler_view_delivery_report = (RecyclerView) findViewById(R.id.recycler_view_delivery_report);
        layoutManager = new LinearLayoutManager(this);
    }

    private void setTextFilter() {
        date_filter.setText(String.valueOf(weekfilter[mCounter]));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if(weekfilter.length != mCounter+1) {
                    mCounter++;
                    setTextFilter();
                }
                break;
            case R.id.previous:
                if(mCounter != 0) {
                    mCounter--;
                    setTextFilter();
                }
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View makeView() {
        TextView t = new TextView(this);
        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        t.setTextSize(14);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "font/Lato-Bold.ttf");
        t.setTypeface(face);
        t.setTextColor(R.color.colorPrimary);
        return t;
    }

}
