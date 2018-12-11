package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.ActionBar;
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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    Button btn_border_details_pending, btn_order_details_invoiced, btn_order_details_filter, btn_clear_filter;
    LinearLayout lnrOr;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    private Calendar calendar;
    String strSalesmanId, strStockisId;
    TextView text_order_details_amount;
    HashMap<String, List<String>> expandableListDetail;
    TextSwitcher date_filter;
    JsonObject jsonObject;
    Button nextButton,previousButton;
    private int mCounter = 0;
    String date = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private int mYear, mMonth, mDay;
    private Date filter_start_date, filter_end_date;

    String fromDate, toDate;
    TextView txt_start_date, txt_end_date;
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
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        fromDate = mYear + "-" + mMonth + "-" + mDay;
        toDate = mYear + "-" + mMonth + "-" + mDay;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        date_filter = (TextSwitcher) findViewById(R.id.date_filter);
        text_order_details_amount=(TextView)findViewById(R.id.text_order_details_amount);
        date_filter.setFactory(this);
        nextButton = (Button) findViewById(R.id.next);
        previousButton = (Button) findViewById(R.id.previous);
        expandableListView = (ExpandableListView) findViewById(R.id.expandListView_daily_collection_report);


        if(isNetworkAvailable()){
           // callDailyCollection(ApiConstants.App_GetDailyCollection_Report,jsonObject);
            callAllOrder(fromDate, toDate);
        }

    }

    private void callAllOrder(String fromDate, String toDate) {
        isShowProgress(true);
        jsonObject= new JsonObject();
        jsonObject.addProperty(Constants.StockistID, "1");
        jsonObject.addProperty(Constants.SalesmanID, "2");
        jsonObject.addProperty(Constants.StartDate,fromDate);
        jsonObject.addProperty(Constants.EndDate,toDate);
        Log.i("Log Request",""+jsonObject);
        mCompositeDisposable.add(getApiCallService().getDailyCollection(SavePref.getInstance(getApplicationContext()).getToken(),ApiConstants.App_GetDailyCollection_Report, jsonObject)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handelDailyResponse, this::handleError));
    }

    private void handelDailyResponse(DailyCollection_Report_Model report_model) {
       isShowProgress(false);
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
                if(report_model.getResult().size()>0){
                    int grand_total = 0;
                    for(int i=0;i<report_model.getResult().size();i++){
                        for(int j=0;j<report_model.getResult().get(i).getPaymentDetails().size();i++) {

                            grand_total = grand_total + Integer.parseInt(report_model.getResult().get(i).getPaymentDetails().get(j).getAmount());
if(report_model.getResult().get(i).getPaymentDetails().get(j).getPaymentMode().equals("cheque")) {

}
                        }
                        Log.i("Ampount",""+grand_total);
                    }


                }
            }
           // text_order_details_amount.setText(S);
        }
    }

    private void handleError(Throwable t) {
        Log.i("Response","t"+t.getMessage());
isShowProgress(false);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);

        MenuItem action_filter = menu.findItem(R.id.action_filter);

        action_filter.setVisible(true);

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
        lnrOr=(LinearLayout)dialogview.findViewById(R.id.lnrOr);
        btn_border_details_pending = (Button) dialogview.findViewById(R.id.btn_border_details_pending);
        btn_order_details_invoiced = (Button) dialogview.findViewById(R.id.btn_order_details_invoiced);
        txt_start_date = (TextView) dialogview.findViewById(R.id.txt_start_date);
        txt_end_date = (TextView) dialogview.findViewById(R.id.txt_end_date);
        btn_border_details_pending.setVisibility(View.GONE);
        btn_order_details_invoiced.setVisibility(View.GONE);
        lnrOr.setVisibility(View.GONE);
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
            //    Log.i("--" + strStockistId + "====" + fromDate, "----" + strSalesmanId + "--" + toDate);
                if (isNetworkAvailable()) {
                    callAllOrder(fromDate, toDate);
                  //  isShowProgress(true);
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
}
