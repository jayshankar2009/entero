package com.app.entero.direct.ui.activity.salesman;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.model.PDCModel;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.PDCPaymentCustomAdapter_Salesman;
import com.app.entero.direct.ui.listener.PDCListInterface;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PDCPaymentActivity_Salesman extends BaseActivity implements View.OnClickListener,PDCListInterface {

    private Toolbar mToolbar;
    private TextView txtToolbarHeader,text_pdc_payment_name,text_pdc_drawer_cheque_no,text_pdc_drawer_date,text_pdc_drawer_cheque_bank_name,text_pdc_drawer_invoice_id,text_pdc_drawer_amount;
    private RecyclerView recycler_view_pdc_payment;
   ArrayList<PDCModel> pdcList;

    private static RecyclerView.Adapter adapter_pdc_payment;
    private RecyclerView.LayoutManager layoutManager;
    public static View.OnClickListener pdcPaymentOnClickListener;
    LinearLayout drawer_layout;
    Bundle bundle;
    String chmstErp;
    //LinearLayout bottom_sheet;
    ImageView img_pdc_drawer_close;
    ImageView handleImage;
    TextView tv_commentDisplay;
    BottomSheetBehavior behavior;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_pdc_payment);
        initObjects();
        initLayout();
        setToolBar();
        onClickEvent();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Constants.StockistID,"1");
        jsonObject.addProperty(Constants.SalesmanID,"2");
        if(isNetworkAvailable()) {
            callPDCAPI(jsonObject);
        }else {

        }
      /*  allOrderDetailsData = new ArrayList<Outstandings>();
        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            allOrderDetailsData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }*/






        //Drawer layout View
        //bottom_sheet = (LinearLayout) findViewById(R.id.bottom_sheet);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

    }

    private void callPDCAPI(JsonObject jsonObject) {

        mCompositeDisposable.add(getApiCallService().getPDCModel(SavePref.getInstance(getApplicationContext()).getToken(), ApiConstants.getPdc,jsonObject).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlePDCResponse, this::handleError));
    }

    private void handlePDCResponse(PDCModel pdcModel) {
        if(pdcModel.getStatus().equals("success")){

            for(int i=0;i<pdcModel.getResult().size();i++) {
                Toast.makeText(getApplicationContext(),"----"+pdcModel.getResult().get(i).getChemistID().equalsIgnoreCase(chmstErp),Toast.LENGTH_SHORT).show();
                if(pdcModel.getResult().get(i).getChemistID().equalsIgnoreCase(chmstErp)){
                    text_pdc_payment_name.setText(pdcModel.getResult().get(i).getChemistLegalName());
                    adapter_pdc_payment = new PDCPaymentCustomAdapter_Salesman(this,(ArrayList<PDCModel>) pdcModel.getResult().get(i).getPDCDetails());
                    recycler_view_pdc_payment.setAdapter(adapter_pdc_payment);
                }
            }
        }
     //   Toast.makeText(getApplicationContext(),""+pdcModel.getStatus(),Toast.LENGTH_SHORT).show();
    }
    public void handleError(Throwable t) {
        Toast.makeText(getApplicationContext(),""+t.getMessage(),Toast.LENGTH_SHORT).show();

    }

    private void onClickEvent() {
      //  pdcPaymentOnClickListener = new MyOnClickListener(this);
        img_pdc_drawer_close.setOnClickListener(this);

    }

    private void setToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //  mToolbar.setTitle(getString(R.string.pdcPayment));
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtToolbarHeader = (TextView) findViewById(R.id.txtHeader);
        txtToolbarHeader.setText("PDC");

        // Back to previous activity
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initLayout() {
        bundle = getIntent().getExtras();
        //    if(bundle!=null) {

        chmstErp = bundle.getString(Constants.cmstErp);
        Toast.makeText(getApplicationContext(),"-----"+chmstErp,Toast.LENGTH_SHORT).show();
        img_pdc_drawer_close = (ImageView) findViewById(R.id.img_pdc_drawer_close);
        recycler_view_pdc_payment = (RecyclerView) findViewById(R.id.recycler_view_pdc_payment);
        recycler_view_pdc_payment.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_view_pdc_payment.setLayoutManager(layoutManager);
        recycler_view_pdc_payment.setItemAnimator(new DefaultItemAnimator());
        text_pdc_payment_name = (TextView) findViewById(R.id.text_pdc_payment_name);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        text_pdc_drawer_cheque_no = (TextView) findViewById(R.id.text_pdc_drawer_cheque_no);
        text_pdc_drawer_date = (TextView) findViewById(R.id.text_pdc_drawer_date);
        text_pdc_drawer_cheque_bank_name = (TextView) findViewById(R.id.text_pdc_drawer_cheque_bank_name);
        text_pdc_drawer_invoice_id = (TextView) findViewById(R.id.text_pdc_drawer_invoice_id);
        text_pdc_drawer_amount = (TextView) findViewById(R.id.text_pdc_drawer_amount);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_pdc_drawer_close:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position, ArrayList<PDCModel> pdcList) {
        text_pdc_drawer_cheque_no.setText(pdcList.get(position).getChequeNo());
        text_pdc_drawer_date.setText(pdcList.get(position).getPaymentDate());
        text_pdc_drawer_cheque_bank_name.setText(pdcList.get(position).getBank());
        text_pdc_drawer_amount.setText(pdcList.get(position).getAmount());
     //   text_pdc_drawer_invoice_id.setText(pdcList.get(position).get);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    /*private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            openSlide(v);
        }

        private void openSlide(View v) {
            int selectedItemPosition = recycler_view_pdc_payment.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recycler_view_pdc_payment.findViewHolderForPosition(selectedItemPosition);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        }
    }*/


}
