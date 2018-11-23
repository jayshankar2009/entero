package com.app.entero.direct.ui.fragment.salesman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.LinkedHashMap;

import com.app.entero.direct.R;
import com.app.entero.direct.network.ApiConstants;

import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.ui.activity.salesman.OutstandingsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.ReportsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.Visit_PlanActivity_Salesman;
import com.app.entero.direct.ui.adapter.salesman.HomeCustomerAdapter_Salesman;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment_Salesman extends Fragment implements View.OnClickListener {
    private RecyclerView.LayoutManager layoutManager;
    private MainActivity activity;
    LinearLayout lnrCustomer;
    RecyclerView recyclerView;
    private String TAG = "HomeFragment_Salesman";
    String[] customer = {"LandMark Pharma", "Life Distributors", "Welcome Pharma", "Anushuman Pharma", "J.D Pharmaceticual"};
    String[] location = {"CBD Belapur", "Nerul", "Sanpada", "Sanpada", "Vashi"};
    CardView btnOutSta, btnAllOrder, btnViewRe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        activity.initObjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salesman_fragment_home, container, false);
        //Toast.makeText(getContext(), "Toast", Toast.LENGTH_LONG).show();
        activity = ((MainActivity) getActivity());
        activity.initObjects();
        initview(view);
        activity.imgToolbar.setVisibility(View.VISIBLE);
        activity.txtToolbar.setVisibility(View.GONE);
        activity.imgFilter.setVisibility(View.GONE);
        activity.imgSearch.setVisibility(View.GONE);
        setOnClick();
        return view;

    }

    private void setOnClick() {
        btnOutSta.setOnClickListener(this);
        btnAllOrder.setOnClickListener(this);
        btnViewRe.setOnClickListener(this);
        lnrCustomer.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void initview(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btnOutSta = (CardView) view.findViewById(R.id.btnOutSta);
        btnAllOrder = (CardView) view.findViewById(R.id.btnAllOrder);
        btnViewRe = (CardView) view.findViewById(R.id.btnViewRe);
        lnrCustomer = (LinearLayout) view.findViewById(R.id.lnrCustomer);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        HomeCustomerAdapter_Salesman adapter = new HomeCustomerAdapter_Salesman(getContext(), customer, location);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOutSta:
                Intent i = new Intent(getActivity(), OutstandingsActivity_Salesman.class);
                this.startActivity(i);
                break;
            case R.id.btnAllOrder:
                Intent i1 = new Intent(getActivity(), AllOrderActivity_Salesman.class);
                this.startActivity(i1);
                break;
            case R.id.btnViewRe:
                Intent i2 = new Intent(getActivity(), ReportsActivity_Salesman.class);
                this.startActivity(i2);
                break;

            case R.id.lnrCustomer:
                Intent i3 = new Intent(getActivity(), Visit_PlanActivity_Salesman.class);
                this.startActivity(i3);
                break;

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

}
