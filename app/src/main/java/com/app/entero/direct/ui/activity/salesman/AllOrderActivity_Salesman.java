package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.AllOrderModel;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.AllOrderCustomAdapter_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class AllOrderActivity_Salesman extends BaseActivity implements OnItemRecycleClickListener {
    Toolbar mToolbar;
    SearchView searchView;

    private static AllOrderCustomAdapter_Salesman adapter_all_order;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_all_order;
    public static View.OnClickListener allOrderOnClickListener;
    ArrayList<AllOrderModel> listAllOrder;
    private Calendar calendar;
    Button btn_border_details_pending, btn_order_details_invoiced, btn_order_details_filter, btn_clear_filter;
    DatePickerDialog dpd_start_date, dpd_end_date;
    private Date current_date = Calendar.getInstance().getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdYear = new SimpleDateFormat("yyyy");
    private SimpleDateFormat sdMonth = new SimpleDateFormat("MM");
    private SimpleDateFormat sdDay = new SimpleDateFormat("dd");
    private int mYear, mMonth, mDay;
    private Date filter_start_date, filter_end_date;
    String date = null;
    String strStockistId, strSalesmanId;
    String fromDate, toDate;
    TextView txt_start_date, txt_end_date;
    TextView txtHeader;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_all_order);
        initObjects();
        initView();
        setToolbar();
        onSetText();
        onClickEvent();

        recycler_view_all_order.setHasFixedSize(true);

        recycler_view_all_order.setLayoutManager(layoutManager);
        recycler_view_all_order.setItemAnimator(new DefaultItemAnimator());

    }

    private void callAllOrder(String fromDate, String toDate) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Constants.StockistID, "1");
        jsonObject.addProperty(Constants.ChemistID, "0");
        jsonObject.addProperty(Constants.salesmanId, "4");
        jsonObject.addProperty(Constants.Transaction_No, "0");
        jsonObject.addProperty(Constants.sDate, fromDate);
        jsonObject.addProperty(Constants.eDate, toDate);

        mCompositeDisposable.add(getApiCallService().getAllOrder(SavePref.getInstance(getApplicationContext()).getToken(), ApiConstants.Get_OrdersAll, jsonObject)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));


    }

    private void handleResponse(AllOrderModel allOrderModel) {
        listAllOrder = new ArrayList<>();
        isShowProgress(false);
        Log.i("Response", "" + allOrderModel.getStatus());
        if (allOrderModel.getStatus().equals("success")) {
            if (allOrderModel.getMessage().equals("Record found")) {
                for (int i = 0; i < allOrderModel.getOrderData().size(); i++) {
                    listAllOrder.add(allOrderModel.getOrderData().get(i));
                }
                fetchData();

            }
        }

    }

    private void fetchData() {
        adapter_all_order = new AllOrderCustomAdapter_Salesman(this,listAllOrder);
        recycler_view_all_order.setAdapter(adapter_all_order);
    }

    private void handleError(Throwable throwable) {
        Log.e("All Order Error", " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void onClickEvent() {
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.allOrder));
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
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        fromDate = mYear + "-" + mMonth + "-" + mDay;
        toDate = mYear + "-" + mMonth + "-" + mDay;
        strStockistId = SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getStockistID();
        strSalesmanId = SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getID();

        Log.i("--" + strStockistId + "====" + fromDate, "----" + strSalesmanId + "--" + toDate);

        if (isNetworkAvailable()) {
            isShowProgress(true);
            callAllOrder(fromDate, toDate);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader = (TextView) findViewById(R.id.txtHeader);
        recycler_view_all_order = (RecyclerView) findViewById(R.id.recycler_view_all_order);
       // allOrderOnClickListener = new MyOnClickListener(this);
        layoutManager = new LinearLayoutManager(this);
    }

    // search and filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        MenuItem action_search = menu.findItem(R.id.action_search);
        MenuItem action_filter = menu.findItem(R.id.action_filter);
        action_search.setVisible(true);
        action_filter.setVisible(true);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Order List");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                   // fetchVisitPlan();
                    fetchData();
                    return false;
                }
                adapter_all_order.getFilter().filter(newText.toLowerCase());
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
            case R.id.action_filter:
                show_Filter_dialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void show_Filter_dialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogview = inflater.inflate(R.layout.salesman_dialog_order_details_list_filter, null);
        final Dialog infoDialog = new Dialog(this);//builder.create();
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infoDialog.setContentView(dialogview);

        btn_border_details_pending = (Button) dialogview.findViewById(R.id.btn_border_details_pending);
        btn_order_details_invoiced = (Button) dialogview.findViewById(R.id.btn_order_details_invoiced);
        txt_start_date = (TextView) dialogview.findViewById(R.id.txt_start_date);
        txt_end_date = (TextView) dialogview.findViewById(R.id.txt_end_date);


        if (filter_start_date != null) {
            txt_start_date.setText(sdf.format(filter_start_date));
        }
        if (filter_end_date != null) {
            txt_end_date.setText(sdf.format(filter_end_date));
        }


        txt_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = "from";
                pickUpDate();
            }


        });


        txt_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = "to";
                pickUpDate();
            }


        });

        dialogview.findViewById(R.id.btn_order_details_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH) + 1;
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                if (txt_start_date.getText().toString().trim().length() > 0) {
                    fromDate = txt_start_date.getText().toString();
                } else {
                    fromDate = mYear + "-" + mMonth + "-" + mDay;
                    // toDate = mYear + "-" + mMonth + "-" + mDay;

                }
                if (txt_end_date.getText().toString().trim().length() > 0) {
                    toDate = txt_end_date.getText().toString();
                } else {
                    toDate = mYear + "-" + mMonth + "-" + mDay;
                }
                Log.i("--" + strStockistId + "====" + fromDate, "----" + strSalesmanId + "--" + toDate);
                if (isNetworkAvailable()) {
                    callAllOrder(fromDate, toDate);
                    isShowProgress(true);
                }
                //filter_dialog_conditions(filter_start_date, filter_end_date, chk_pending.isChecked(), chk_completed.isChecked());
                infoDialog.dismiss();
            }
        });

        dialogview.findViewById(R.id.btn_clear_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //fill_orderlist(posts);
                infoDialog.dismiss();
            }
        });
        set_attributes(infoDialog);
        infoDialog.show();
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

                        if (date.equalsIgnoreCase("from")) {

                            txt_start_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        } else if (date.equalsIgnoreCase("to")) {

                            txt_end_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void set_attributes(Dialog dlg) {

        Window window = dlg.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);

        int[] textSizeAttr = new int[]{android.R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedValue typedValue = new TypedValue();
        TypedArray a = this.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionbarsize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        int maxX = mdispSize.x;
        wlp.gravity = Gravity.TOP | Gravity.LEFT;
        wlp.x = maxX;   //x position
        wlp.y = actionbarsize - 20;   //y position
        window.setAttributes(wlp);

    }

    @Override
    public void onItemClick(View view, int position) {
     //   Intent i = new Intent(getApplicationContext(),)
        Intent in = new Intent(getApplicationContext(), OrderDetailsActivity_Saleesman.class);
        in.putExtra("orderList",listAllOrder.get(position));
        startActivity(in);

    }


}
