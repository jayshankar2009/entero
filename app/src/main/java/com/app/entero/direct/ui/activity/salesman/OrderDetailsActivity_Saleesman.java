package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.app.entero.direct.R;
import com.app.entero.direct.model.AllOrderModel;
import com.app.entero.direct.model.AllOrderSecondModel;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.OrderDetailsCustomAdapter_Salesman;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderDetailsActivity_Saleesman extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    SearchView searchView;
    TextView txtHeader;
    AllOrderModel listAllOrder;
    Bundle bundle;
    int total;
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    Date date = null;
    String formattedDate;
    ArrayList<AllOrderSecondModel> listOrder;
    private static RecyclerView.Adapter adapter_all_order_details;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_all_order_details;
    private static ArrayList<Outstandings> allOrderDetailsData;
    TextView order_details_name,order_details_status,order_details_id,order_details_total_amount,
            order_details_date,order_details_time,order_details_invoice_id,
            order_details_paid_amount,order_details_paid_date,order_details_paid_time,order_details_item_list_total,
            order_details_item_list_discount,order_details_item_list_grand_total;
    LinearLayout order_details,invoice_details;
    ImageView first_arrow,second_arrow;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_order_details);
        initObjects();
        initView();
        setToolbar();
        onSetText();
        onClickEvent();
        //Toolbar with name
        recycler_view_all_order_details.setHasFixedSize(true);
        recycler_view_all_order_details.setLayoutManager(layoutManager);
        recycler_view_all_order_details.setItemAnimator(new DefaultItemAnimator());

              // set all data to UI field
              //  fetchAndSetData();
    }

    private void onClickEvent() {
        order_details.setOnClickListener(this);
        invoice_details.setOnClickListener(this);
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.orderdetails));
        order_details_id.setText(listAllOrder.getTransactionNo());
        order_details_total_amount.setText("Rs. "+listAllOrder.getAmount());
        formattedDate = outputFormat.format(date);
        Log.i("Formated Date","---"+formattedDate);
        String[] dateSplit = formattedDate.split("\\s+");
      Log.i("Split--"+dateSplit[0],"---"+dateSplit[1]);
      order_details_date.setText(dateSplit[0]);
      order_details_time.setText(dateSplit[1]+" "+dateSplit[2].replace("AM","am").replace("PM","pm"));


}

    private void setToolbar() {
        mToolbar.setTitle(getString(R.string.orderdetails));
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
        bundle = getIntent().getExtras();
        listAllOrder= (AllOrderModel) bundle.getSerializable("orderList");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        order_details_name = (TextView) findViewById(R.id.order_details_name);
        order_details_status = (TextView) findViewById(R.id.order_details_status);
        order_details_id = (TextView) findViewById(R.id.order_details_id);
        order_details_total_amount = (TextView) findViewById(R.id.order_details_total_amount);
        order_details_date = (TextView) findViewById(R.id.order_details_date);
        order_details_time = (TextView) findViewById(R.id.order_details_time);
        order_details_invoice_id = (TextView) findViewById(R.id.order_details_invoice_id);
        order_details_paid_amount = (TextView) findViewById(R.id.order_details_paid_amount);
        order_details_paid_date = (TextView) findViewById(R.id.order_details_paid_date);
        order_details_paid_time = (TextView) findViewById(R.id.order_details_paid_time);
        order_details_item_list_total = (TextView) findViewById(R.id.order_details_item_list_total);
        order_details_item_list_discount = (TextView) findViewById(R.id.order_details_item_list_discount);
        order_details_item_list_grand_total = (TextView) findViewById(R.id.order_details_item_list_grand_total);
        order_details = (LinearLayout) findViewById(R.id.order_details);
        invoice_details = (LinearLayout) findViewById(R.id.invoice_details);
        first_arrow = (ImageView) findViewById(R.id.first_arrow);
        second_arrow = (ImageView) findViewById(R.id.second_arrow);
        recycler_view_all_order_details = (RecyclerView) findViewById(R.id.recycler_view_all_order_details);
        try {
            date = inputFormat.parse(listAllOrder.getDocDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        layoutManager = new LinearLayoutManager(this);
        allOrderDetailsData = new ArrayList<Outstandings>();
        if(isNetworkAvailable()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(Constants.StockistID,"0");
            jsonObject.addProperty(Constants.ChemistID,"0");
            jsonObject.addProperty(Constants.salesmanId,"0");
            jsonObject.addProperty(Constants.Transaction_No,listAllOrder.getTransactionNo());
            jsonObject.addProperty(Constants.eDate,"0");
            jsonObject.addProperty(Constants.sDate,"0");
            callOrderList(ApiConstants.Get_OrdersAll,jsonObject);
        }
    }

    private void callOrderList(String get_ordersAll, JsonObject jsonObject) {
        mCompositeDisposable.add(getApiCallService().getAllOrderSecond(SavePref.getInstance(getApplicationContext()).getToken(), ApiConstants.Get_OrdersAll, jsonObject)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }
public void handleResponse(AllOrderSecondModel allOrderSecondModel){
    listOrder = new ArrayList<>();
        Log.i("Success",""+allOrderSecondModel.getStatus());
        if(allOrderSecondModel.getStatus().equals("success")) {
            if (allOrderSecondModel.getMessage().equals("Record found")) {

                for(int i=0;i<allOrderSecondModel.getOrderData().size();i++){
                    listOrder.add(allOrderSecondModel.getOrderData().get(i));
                }
                fetchAndSetData(listOrder);
                int grand_total=0;
                for (int j = 0; j < allOrderSecondModel.getOrderData().size(); j++) {
                    grand_total = grand_total + Integer.parseInt(allOrderSecondModel.getOrderData().get(j).getPrice());
                }
                if (grand_total != 0) {
                    order_details_item_list_total.setText("Rs. " + String.valueOf(grand_total));
                } else {
                    order_details_item_list_total.setText("Rs. 0");
                }

            }
        }

}
public void handleError(Throwable t) {
    Log.i("Error",""+t.getMessage());

}

    private void fetchAndSetData(ArrayList<AllOrderSecondModel> listOrder) {
        adapter_all_order_details = new OrderDetailsCustomAdapter_Salesman(listOrder);
        recycler_view_all_order_details.setAdapter(adapter_all_order_details);
        //adapter_all_order_details.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_details:
                first_arrow.setVisibility(View.VISIBLE);
                second_arrow.setVisibility(View.GONE);
                //fetchAndSetData(listOrder);
                break;
            case R.id.invoice_details:
                second_arrow.setVisibility(View.VISIBLE);
              //  first_arrow.setVisibility(View.GONE);
             //   fetchAndSetData(listOrder);
                break;
        }
    }

}
