package com.app.entero.direct.ui.activity.chemist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.chemist.OffersAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;

public class OffersActivity extends BaseActivity implements OnItemRecycleClickListener {

    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView rv_offers;
    private OffersAdapter mOffersAdapter;
    private ArrayList<OffersModel> mOfferList;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
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
        mOffersAdapter = new OffersAdapter(this,this,mOfferList);
        rv_offers.setAdapter(mOffersAdapter);
        rv_offers.addItemDecoration(new SimpleDividerItemDecoration(this));

    }

    private ArrayList<OffersModel> setOfferListData() {

        OffersModel model = new OffersModel();
        model.setQuantitly("4");
        model.setTabName("Crocin pain relief Tablet");
        model.setTabDes("1 Strip of 10 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("5");
        model.setTabName("Uprise D30 100k Capsule");
        model.setTabDes("1 Strip of 12 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);


        model = new OffersModel();
        model.setQuantitly("3");
        model.setTabName("Disprin pain relief Tablet");
        model.setTabDes("1 Strip of 20 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);


        model = new OffersModel();
        model.setQuantitly("4");
        model.setTabName("Crocin pain relief Tablet");
        model.setTabDes("1 Strip of 30 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("8");
        model.setTabName("Dexorange Syrup");
        model.setTabDes("100 ml bottle");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("9");
        model.setTabName("Nerry syrup");
        model.setTabDes("200 ml bottle");
        model.setImg("");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("2");
        model.setTabName("Disprin pain relief Tablet");
        model.setTabDes("1 Strip of 15 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        return mOfferList;
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (TextView) findViewById(R.id.tv_title);
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
        tv_title.setText(R.string.offers);

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
