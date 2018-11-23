package com.app.entero.direct.ui.activity.chemist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.entero.direct.R;
import com.app.entero.direct.model.NotificationModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.chemist.NotificationAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;

public class NotificationActivity extends BaseActivity implements OnItemRecycleClickListener {

    private Toolbar toolbar;
    private CustomTextView_Salesman tv_title;
    private RecyclerView rv_offers;
    private NotificationAdapter mNotificationAdapter;
    private ArrayList<NotificationModel> mOfferList;
    private Context mContext;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_chemist);
        mContext = this;
        initObjects();
        setToolbar();
        initView();
    }

    private void initView() {
        mOfferList = new ArrayList<>();
        mOfferList = setOfferListData();
        rv_offers = (RecyclerView) findViewById(R.id.rv_navigation);
        rv_offers.setLayoutManager(new LinearLayoutManager(this));
        mNotificationAdapter = new NotificationAdapter(this,this,mOfferList);
        rv_offers.setAdapter(mNotificationAdapter);
        rv_offers.addItemDecoration(new SimpleDividerItemDecoration(this));

    }

    private ArrayList<NotificationModel> setOfferListData() {

        NotificationModel model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Maruti Medicals" + "</b> ");
        model.setTime("4:30 pm");
        model.setDate("28 Sept -2018");
        mOfferList.add(model);

        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Wellcare Pharmacy" + "</b>");
        model.setTime("2:45 pm");
        mOfferList.add(model);


        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Payment of Rs. 12250 received from" + "<b>" + " Sri Krishna medicals" + "</b>");
        model.setTime("2:30 pm");
        mOfferList.add(model);


        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Maruti Medicals" + "</b> ");
        model.setTime("1:30 pm");
        model.setDate("27 Sept -2018");
        mOfferList.add(model);

        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Payment of Rs. 12250 received from" +" Wellcare Pharmacy");
        model.setTime("4:30pm");
        mOfferList.add(model);

        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Wellcare Pharmacy" + "</b>");
        model.setTime("12:30 pm");
        mOfferList.add(model);

        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Payment of Rs. 12250 received from" + "<b>" + " Sri Krishna medicals" + "</b>");
        model.setTime("1:30pm");
        model.setDate("26 Sept -2018");
        mOfferList.add(model);

        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Wellcare Pharmacy" + "</b>");
        model.setTime("8:30pm");
        mOfferList.add(model);

        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Maruti Medicals" + "</b> ");
        model.setTime("1:30pm");
        mOfferList.add(model);


        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Maruti Medicals" + "</b> ");
        model.setTime("5:30pm");
        model.setDate("28 Sept -2018");
        mOfferList.add(model);

        model = new NotificationModel();
        model.setQuantitly("5");
        model.setChemistName("Order Generated For" + "<b>" + " Maruti Medicals" + "</b> ");
        model.setTime("6:30pm");
        mOfferList.add(model);

        return mOfferList;
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (CustomTextView_Salesman) findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.backbutton);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("");
        tv_title.setText("Notifications");

    }

    @Override
    public void onItemClick(View view, int position) {


    }

}
