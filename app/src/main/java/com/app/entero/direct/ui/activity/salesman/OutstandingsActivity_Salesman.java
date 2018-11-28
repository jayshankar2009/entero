package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.app.entero.direct.Helper.SummaryReportExpandListData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.SummaryReport;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.OutstandingsCustomAdapter_Salesman;
import com.app.entero.direct.ui.adapter.salesman.SummaryReportAdapter_Salesman;

import java.util.List;

public class OutstandingsActivity_Salesman extends BaseActivity {
    Toolbar mToolbar;
    SearchView searchView;
    TextView txtHeader;
    ExpandableListView exp_outstanding_bill;
    List<SummaryReport> outstandingExpandData;
    OutstandingsCustomAdapter_Salesman adapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_all_outstanding);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();
        outstandingExpandData = SummaryReportExpandListData.getData();
        adapter = new OutstandingsCustomAdapter_Salesman(outstandingExpandData, this);
        exp_outstanding_bill.setAdapter(adapter);
    }

    private void onClickEvent() {
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.outstangings));
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
        exp_outstanding_bill =(ExpandableListView)findViewById(R.id.exp_outstanding_bill);
        exp_outstanding_bill.setChildDivider(getResources().getDrawable(R.color.graybg));

    }

    // search and filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        MenuItem action_search = menu.findItem(R.id.action_search);
        action_search.setVisible(true);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Outstandings List");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
