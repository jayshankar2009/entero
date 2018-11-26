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
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.ui.activity.salesman.OutstandingsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.ReportsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.Visit_PlanActivity_Salesman;
import com.app.entero.direct.ui.adapter.salesman.HomeCustomerAdapter_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment_Salesman extends Fragment implements View.OnClickListener,OnItemRecycleClickListener {
    private RecyclerView.LayoutManager layoutManager;
    private MainActivity activity;
    BaseActivity baseActivity;
    LinearLayout lnrCustomer;
    SavePref savePref;
    ArrayList<SalesmanDashBoardModel> listDashBoard;
    TextView txtDlvryNo, txtPymntCol, txtCstmrVisit;
    LinkedHashMap<String, String> linkRequest;
    RecyclerView recyclerView;
    String strSalesmanId, strStockisId;
    private String TAG = "HomeFragment_Salesman";
    CardView btnOutSta, btnAllOrder, btnViewRe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        baseActivity = (BaseActivity) getActivity();
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
        savePref = new SavePref();
        strStockisId = savePref.getUserDetail().getSalesmanInfo().get(0).getStockistID();
        strSalesmanId = savePref.getUserDetail().getSalesmanInfo().get(0).getID();
        linkRequest = new LinkedHashMap<>();
        linkRequest.put(Constants.StockistID, "1");
        linkRequest.put(Constants.SalesmanID, "2");
        if(baseActivity.isNetworkAvailable()) {
            baseActivity.isShowProgress(true);
            callSalesmanDashBoard(ApiConstants.Get_SalesmanDashboard, linkRequest);
        }else {

        }
        activity.imgToolbar.setVisibility(View.VISIBLE);
        activity.txtToolbar.setVisibility(View.GONE);


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
        txtCstmrVisit = (TextView) view.findViewById(R.id.txtCstmrVisit);
        txtDlvryNo = (TextView) view.findViewById(R.id.txtDlvryNo);
        txtPymntCol = (TextView) view.findViewById(R.id.txtPymntCol);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
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
                i3.putExtra("array_list", listDashBoard);
                this.startActivity(i3);
                break;

        }


    }


    private void callSalesmanDashBoard(String url, LinkedHashMap<String, String> linkedHashMap) {
        baseActivity.isShowProgress(true);
        baseActivity.mCompositeDisposable.add(baseActivity.getApiCallService().getSalesmanDashBoard(SavePref.getInstance(getContext()).getToken(), url, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        baseActivity.isShowProgress(false);
    }

    private void handleResponse(SalesmanDashBoardModel mSalesmanDashBoard) {
        baseActivity.isShowProgress(false);
        if (mSalesmanDashBoard.getStatus().equals("success")) {
            if (mSalesmanDashBoard.getEntityCountSalesmanData().size() > 0) {
                if (mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getAssignedcustomerCount() != null) {
                    txtDlvryNo.setText(String.valueOf(mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getAssignedDeliveryCount()));
                } else {
                    txtDlvryNo.setText("0");
                }

                if (mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getAssignedcustomerCount() != null) {
                    txtCstmrVisit.setText(String.valueOf(mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getAssignedcustomerCount()));

                } else {
                    txtCstmrVisit.setText("0");
                }
                if (mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getTotalPaymentCollection() != null) {
                    txtPymntCol.setText(String.valueOf(mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getTotalPaymentCollection()));

                } else {
                    txtPymntCol.setText("0");
                }
            }else {
                Toast.makeText(getContext(),"Delivery , Customer Visit , Payment Collection Records are not found",Toast.LENGTH_SHORT).show();
            }
            if(mSalesmanDashBoard.getEntityChemListData().size()>0){
                listDashBoard = new ArrayList<>();
                for (int i = 0;i<mSalesmanDashBoard.getEntityChemListData().size();i++) {
                    listDashBoard.add(mSalesmanDashBoard.getEntityChemListData().get(i));
                }
                HomeCustomerAdapter_Salesman adapter = new HomeCustomerAdapter_Salesman(getContext(),this,listDashBoard);
                recyclerView.setAdapter(adapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

            }else {

            }
        }


    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
