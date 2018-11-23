package com.app.entero.direct.ui.fragment.chemist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OutstandingModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.adapter.chemist.OutStandingAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class OutStandingFragment extends Fragment implements View.OnClickListener, OnItemRecycleClickListener {

    private HomeActivity activity;
    private String TAG = "ChemistHomeFragment";
    private int image[] = {R.drawable.banner, R.drawable.banner, R.drawable.banner};
    private ArrayList<OutstandingModel> mOrderList = new ArrayList<>();
    private RecyclerView rv_orderslist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (HomeActivity) getActivity();
        activity.initObjects();

    }

    @Override
    public void onResume() {
        super.onResume();
        activity.isVisible(false);
        activity.isVisibleAdd(false);
        activity.isVisibleSearch(true);
        activity.isVisibleAddTocart(false);
        activity.isVisibleFilter(false);
        activity.setTitle("OutStanding");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        activity = ((HomeActivity) getActivity());
        activity.initObjects();
        activity.setTitle("");
        initview(view);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private ArrayList<OutstandingModel> setOfferListData() {

        OutstandingModel model = new OutstandingModel();
        model.setStatus("Deliver");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setBillID("UOO2345");
        model.setPendingDays("5");
        model.setBillItems("3");
        model.setBillDate("7/12/18 \n 12:00AM");
        model.setGrandTotal("2455");
        model.setDiscount("5");
        model.setFinalAmount("2340");
        mOrderList.add(model);

        model = new OutstandingModel();
        model.setStatus("Deliver");
        model.setChemistName("Welcom Pharma");
        model.setId("24567");
        model.setBillID("TYU2345");
        model.setPendingDays("5");
        model.setBillItems("3");
        model.setBillDate("7/12/18 \n 12:00AM");
        model.setGrandTotal("2345");
        model.setDiscount("5");
        model.setFinalAmount("2340");
        mOrderList.add(model);

        model = new OutstandingModel();
        model.setStatus("Pending");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setBillID("UOO2345");
        model.setPendingDays("5");
        model.setBillItems("3");
        model.setBillDate("7/12/18 \n 12:00AM");
        model.setGrandTotal("2455");
        model.setDiscount("5");
        model.setFinalAmount("2340");
        mOrderList.add(model);

        model = new OutstandingModel();
        model.setStatus("Deliver");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setBillID("UOO2345");
        model.setPendingDays("5");
        model.setBillItems("3");
        model.setBillDate("7/12/18 \n 12:00AM");
        model.setGrandTotal("2455");
        model.setDiscount("5");
        model.setFinalAmount("2340");
        mOrderList.add(model);

        model = new OutstandingModel();
        model.setStatus("Deliver");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setBillID("TYU2345");
        model.setPendingDays("5");
        model.setBillItems("3");
        model.setBillDate("7/12/18 \n 12:00AM");
        model.setGrandTotal("2345");
        model.setDiscount("5");
        model.setFinalAmount("2340");
        mOrderList.add(model);

        model = new OutstandingModel();
        model.setStatus("Pending");
        model.setChemistName("Marvis Pharma");
        model.setId("34567");
        model.setBillID("UOOWE2345");
        model.setPendingDays("5");
        model.setBillItems("3");
        model.setBillDate("7/12/18 \n 12:00AM");
        model.setGrandTotal("2455");
        model.setDiscount("5");
        model.setFinalAmount("2340");
        mOrderList.add(model);

        model = new OutstandingModel();
        model.setStatus("Deliver");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setBillID("UOORE2345");
        model.setPendingDays("5");
        model.setBillItems("3");
        model.setBillDate("7/12/18 \n 12:00AM");
        model.setGrandTotal("2455");
        model.setDiscount("5");
        model.setFinalAmount("2340");
        mOrderList.add(model);

        return mOrderList;
    }

    public void initview(View view) {
        rv_orderslist = (RecyclerView) view.findViewById(R.id.rv_orderslist);
        rv_orderslist.setLayoutManager(new LinearLayoutManager(activity));
        rv_orderslist.setAdapter(new OutStandingAdapter(activity, this, setOfferListData()));
        inItListener();


    }


    private void inItListener() {
    }

    @Override
    public void onClick(View view) {

    }

    public void callApi(LinkedHashMap<String, String> linkedHashMap) {
        activity.mCompositeDisposable.add(activity.getApiCallService().getHomeData(ApiConstants.type, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }


    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        activity.isShowProgress(false);
    }

    private void handleResponse(Object mObject) {


    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
