package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.app.entero.direct.Helper.CollectionReportExpandListData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.DailyCollection_Report_Model;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.DailyCollectionListAdapter_Salesman;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.google.gson.JsonObject;
import com.squareup.picasso.Downloader;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DailyCollectionReportActivity_Salesman extends BaseActivity implements ViewSwitcher.ViewFactory,
        View.OnClickListener {
    TextView txtHeader;
    Toolbar mToolbar;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    String strSalesmanId, strStockisId;
    TextView text_order_details_amount;
    HashMap<String, List<String>> expandableListDetail;
    TextSwitcher date_filter;
    JsonObject jsonObject;
    Button nextButton,previousButton;
    private int mCounter = 0;
    public static String[] weekfilter = {"This Week", "Second Week", "Last Week"};
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_daily_collection_report);
        initObjects();
        initLayout();
        setToolbar();
        onSetText();
        onClickEvent();
        setTextFilter();

    }

    private void onClickEvent() {
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.dailycollection));
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

    @SuppressLint("ResourceAsColor")
    private void initLayout() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        date_filter = (TextSwitcher) findViewById(R.id.date_filter);
        text_order_details_amount=(TextView)findViewById(R.id.text_order_details_amount);
        date_filter.setFactory(this);
        nextButton = (Button) findViewById(R.id.next);
        previousButton = (Button) findViewById(R.id.previous);
        expandableListView = (ExpandableListView) findViewById(R.id.expandListView_daily_collection_report);

        jsonObject= new JsonObject();
        jsonObject.addProperty(Constants.StockistID, "1");
        jsonObject.addProperty(Constants.SalesmanID, "2");
        jsonObject.addProperty(Constants.StartDate,"2018-11-01");
        jsonObject.addProperty(Constants.EndDate,"2018-12-21");
        if(isNetworkAvailable()){
            callDailyCollection(ApiConstants.App_GetDailyCollection_Report,jsonObject);
        }

    }

    private void callDailyCollection(String app_getDailyCollection_report, JsonObject jsonObject) {
        mCompositeDisposable.add(getApiCallService().getDailyCollection(SavePref.getInstance(getApplicationContext()).getToken(), app_getDailyCollection_report, jsonObject)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handelDailyResponse, this::handleError));
    }

    private void handelDailyResponse(DailyCollection_Report_Model report_model) {
        if(report_model.getStatus().equals("success")) {
            if(report_model.getMessage().equals("Record found")) {
                expandableListView.setGroupIndicator(null);
                expandableListView.setChildIndicator(null);
                //expandableListView.setChildDivider(getResources().getDrawable(R.color.graybg));
                expandableListView.setDivider(getResources().getDrawable(R.color.white));
                expandableListView.setDividerHeight(0);
                expandableListDetail = CollectionReportExpandListData.getData();
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

                expandableListAdapter = new DailyCollectionListAdapter_Salesman(this, report_model.getResult());
                expandableListView.setAdapter(expandableListAdapter);

              for(int i=0; i < expandableListAdapter.getGroupCount(); i++)
                    expandableListView.expandGroup(i);

                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {


                    @Override
                    public void onGroupExpand(int groupPosition) {
               Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
                    }
                });

                expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                    @Override
                    public void onGroupCollapse(int groupPosition) {
                        expandableListView.expandGroup(groupPosition);
               Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

                    }
                });

                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
                        Toast.makeText(
                                getApplicationContext(),
                                expandableListTitle.get(groupPosition)
                                        + " -> "
                                        + expandableListDetail.get(
                                        expandableListTitle.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT
                        ).show();
                        return false;
                    }
                });
            }
           // text_order_details_amount.setText(S);
        }
    }

    private void handleError(Throwable t) {
        Log.i("Response","t"+t.getMessage());

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
        t.setGravity(Gravity.CENTER );
        t.setTextSize(14);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "font/Lato-Bold.ttf");
       t.setTypeface(face);
        t.setTextColor(R.color.colorPrimary);
        return t;
    }
}
