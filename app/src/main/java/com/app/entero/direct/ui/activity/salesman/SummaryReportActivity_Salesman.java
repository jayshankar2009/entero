package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

import com.app.entero.direct.Helper.SummaryReportExpandListData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.SummaryReport;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.SummaryReportAdapter_Salesman;


public class SummaryReportActivity_Salesman extends BaseActivity {

    Toolbar mToolbar;
    ExpandableListView _exp_pending_bill;
    SummaryReportAdapter_Salesman adapter;
    List<SummaryReport> summaryExpandData;
    TextView txtHeader;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_summary_report);
        initView();
        setToolbar();
        onSetText();
       // onClickEvent();
       summaryExpandData = SummaryReportExpandListData.getData();
        adapter = new SummaryReportAdapter_Salesman(summaryExpandData, this);
        _exp_pending_bill.setAdapter(adapter);
    }

    private void onSetText() {
        txtHeader.setText(getResources().getString(R.string.summaryreport));
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

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        _exp_pending_bill =(ExpandableListView)findViewById(R.id.ex_summary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        return true;
    }
}
