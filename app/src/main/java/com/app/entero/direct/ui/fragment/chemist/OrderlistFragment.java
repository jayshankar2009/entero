package com.app.entero.direct.ui.fragment.chemist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OrdersModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.chemist.OrderDeatilsChemistActivity;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.activity.salesman.TakeOrderActivity_Salesman;
import com.app.entero.direct.ui.adapter.chemist.OrderAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class OrderlistFragment extends Fragment implements View.OnClickListener, OnItemRecycleClickListener {

    private HomeActivity activity;
    private String TAG = "ChemistHomeFragment";
    private int image[] = {R.drawable.banner, R.drawable.banner, R.drawable.banner};
    private ArrayList<OrdersModel> mOrderList = new ArrayList<>();
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
        activity.setTitle("Orders");
        activity.isVisible(false);
        activity.isVisibleAdd(true);
        activity.isVisibleSearch(true);
        activity.isVisibleAddTocart(false);
        activity.isVisibleFilter(true);
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

    private ArrayList<OrdersModel> setOfferListData() {

        OrdersModel model = new OrdersModel();
        model.setStatus("Delivered");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setOrderAmount("2345");
        model.setInvoicId("765545");
        model.setInvoiceAmount("2333");
        model.setOrderDateTime("7/12/18 \n 12:00AM");
        model.setInvoiceDateTime("7/12/18 \n 12:00AM");
        mOrderList.add(model);

        model = new OrdersModel();
        model.setStatus("Delivered");
        model.setChemistName("Welcom Pharma");
        model.setId("24567");
        model.setOrderAmount("6345");
        model.setInvoicId("965545");
        model.setInvoiceAmount("2433");
        model.setOrderDateTime("7/12/18 \n 12:12AM");
        model.setInvoiceDateTime("7/12/18 \n 12:00AM");
        mOrderList.add(model);

        model = new OrdersModel();
        model.setStatus("Pending");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setOrderAmount("2345");
        model.setInvoicId("765545");
        model.setInvoiceAmount("2333");
        model.setOrderDateTime("7/12/18 \n 12:00AM");
        model.setInvoiceDateTime("7/12/18 \n 12:00AM");
        mOrderList.add(model);

        model = new OrdersModel();
        model.setStatus("Delivered");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setOrderAmount("2345");
        model.setInvoicId("765545");
        model.setInvoiceAmount("2333");
        model.setOrderDateTime("7/12/18 \n 12:00AM");
        model.setInvoiceDateTime("7/12/18 \n 12:00AM");
        mOrderList.add(model);

        model = new OrdersModel();
        model.setStatus("Delivered");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setOrderAmount("2345");
        model.setInvoicId("765545");
        model.setInvoiceAmount("2333");
        model.setOrderDateTime("7/12/18 \n 12:00AM");
        model.setInvoiceDateTime("7/12/18 \n 12:00AM");
        mOrderList.add(model);

        model = new OrdersModel();
        model.setStatus("Pending");
        model.setChemistName("Marvis Pharma");
        model.setId("34667");
        model.setOrderAmount("2345");
        model.setInvoicId("765545");
        model.setInvoiceAmount("2333");
        model.setOrderDateTime("7/12/18 \n 12:00AM");
        model.setInvoiceDateTime("7/12/18 \n 12:00AM");
        mOrderList.add(model);

        model = new OrdersModel();
        model.setStatus("Deliver");
        model.setChemistName("LandMark Pharma");
        model.setId("34567");
        model.setOrderAmount("2345");
        model.setInvoicId("765545");
        model.setInvoiceAmount("2333");
        model.setOrderDateTime("7/12/18 \n 12:00AM");
        model.setInvoiceDateTime("7/12/18 \n 12:00AM");
        mOrderList.add(model);

        return mOrderList;
    }

    public void initview(View view) {
        activity.img_add_order.setOnClickListener(this);
        rv_orderslist = (RecyclerView) view.findViewById(R.id.rv_orderslist);
        rv_orderslist.setLayoutManager(new LinearLayoutManager(activity));
        rv_orderslist.setAdapter(new OrderAdapter(activity, this, setOfferListData()));
        inItListener();

    }

    private void inItListener() {
    }

    @Override
    public void onClick(View view) {

        if(view == activity.img_add_order)
        {
            Intent mIntent = new Intent(getActivity(),TakeOrderActivity_Salesman.class);
            startActivity(mIntent);
        }

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

        Intent mIntent = new Intent(getActivity(),OrderDeatilsChemistActivity.class);
        startActivity(mIntent);

    }
}
