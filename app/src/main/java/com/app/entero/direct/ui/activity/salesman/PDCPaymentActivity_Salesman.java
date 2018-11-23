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

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.adapter.salesman.PDCPaymentCustomAdapter_Salesman;

public class PDCPaymentActivity_Salesman extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView txtToolbarHeader,text_pdc_payment_name,text_pdc_drawer_cheque_no,text_pdc_drawer_date,text_pdc_drawer_cheque_bank_name,text_pdc_drawer_invoice_id,text_pdc_drawer_amount;
    private RecyclerView recycler_view_pdc_payment;
    ArrayList<Outstandings> allOrderDetailsData;
    private static RecyclerView.Adapter adapter_pdc_payment;
    private RecyclerView.LayoutManager layoutManager;
    public static View.OnClickListener pdcPaymentOnClickListener;
    LinearLayout drawer_layout;
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

        //Toolbar with name
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.pdcPayment));
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

        text_pdc_payment_name = (TextView) findViewById(R.id.text_pdc_payment_name);
        // for remove RecyclerView item
        pdcPaymentOnClickListener = new MyOnClickListener(this);

        // outstandings bill list
        recycler_view_pdc_payment = (RecyclerView) findViewById(R.id.recycler_view_pdc_payment);
        recycler_view_pdc_payment.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recycler_view_pdc_payment.setLayoutManager(layoutManager);
        recycler_view_pdc_payment.setItemAnimator(new DefaultItemAnimator());

        /*LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.item_animation_fall_down);
        recycler_view_outstandings.setLayoutAnimation(animation);*/

        allOrderDetailsData = new ArrayList<Outstandings>();
        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            allOrderDetailsData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }


        adapter_pdc_payment = new PDCPaymentCustomAdapter_Salesman(allOrderDetailsData);
        recycler_view_pdc_payment.setAdapter(adapter_pdc_payment);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        //Drawer layout View
        //bottom_sheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        img_pdc_drawer_close = (ImageView) findViewById(R.id.img_pdc_drawer_close);
        img_pdc_drawer_close.setOnClickListener(this);
        text_pdc_drawer_cheque_no = (TextView) findViewById(R.id.text_pdc_drawer_cheque_no);
        text_pdc_drawer_date = (TextView) findViewById(R.id.text_pdc_drawer_date);
        text_pdc_drawer_cheque_bank_name = (TextView) findViewById(R.id.text_pdc_drawer_cheque_bank_name);
        text_pdc_drawer_invoice_id = (TextView) findViewById(R.id.text_pdc_drawer_invoice_id);
        text_pdc_drawer_amount = (TextView) findViewById(R.id.text_pdc_drawer_amount);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_pdc_drawer_close:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

    private class MyOnClickListener implements View.OnClickListener {

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
    }

}
