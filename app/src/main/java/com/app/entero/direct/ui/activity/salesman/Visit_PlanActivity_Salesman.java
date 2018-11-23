package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Visitplanmodal;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.Adapter_Visitplan_Salesman;

public class Visit_PlanActivity_Salesman extends BaseActivity implements View.OnClickListener {

    RecyclerView recycler_vie;
    Adapter_Visitplan_Salesman mAdapter;
    Toolbar mToolbar;
    TextView txtHeader;
    private List<Visitplanmodal> mList;
    TextView textView_date;
    RelativeLayout baseLayout;
    SearchView searchView;
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,dd LLL yyyy");
    Date filter_start_date = Calendar.getInstance().getTime();
    private Date current_date = Calendar.getInstance().getTime();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private int mYear, mMonth, mDay;

    String date = null;
    private SimpleDateFormat sdYear = new SimpleDateFormat("yyyy");
    private SimpleDateFormat sdMonth = new SimpleDateFormat("MM");
    private SimpleDateFormat sdDay = new SimpleDateFormat("dd");

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_visit__plan);
        initView();
        setToolBar();
        onClickEvent();
        onSetText();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        prepareMovieData();
        mAdapter = new Adapter_Visitplan_Salesman(this,mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_vie.setLayoutManager(mLayoutManager);
        recycler_vie.setItemAnimator(new DefaultItemAnimator());
        recycler_vie.setAdapter(mAdapter);
    }

    private void onSetText() {
        txtHeader.setText(getResources().getString(R.string.visit_paln));
        textView_date.setText(dateFormat.format(filter_start_date));
    }

    private void onClickEvent() {
        textView_date.setOnClickListener(this);
        baseLayout.setOnClickListener(this);
    }

    private void setToolBar() {
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
        mList = new ArrayList<>();
        recycler_vie = (RecyclerView) findViewById(R.id.recycler_view);
        textView_date = (TextView) findViewById(R.id.txt_date);
        baseLayout=(RelativeLayout)findViewById(R.id.baseLayout);

    }


    private void prepareMovieData() {
    /*    Visitplanmodal modal_visitplan = new Visitplanmodal("10001", "Wellcare Medical General medcal", "72,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);

        modal_visitplan = new Visitplanmodal("10002", "og Medical General medcal", "73,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);*/



        Visitplanmodal modal_visitplan = new Visitplanmodal("55791", "Wellcare medical Gen Stores", "2,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);

        modal_visitplan = new Visitplanmodal("55792", "Yash medical Gen Stores, Kids & Family", "3,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);

        modal_visitplan = new Visitplanmodal("55793", "Vashi medical Gen Stores", "4,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);

        modal_visitplan = new Visitplanmodal("55794", "Thane medical Gen Stores", "5,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_date:
                break;

            case R.id.baseLayout:

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

                        textView_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
