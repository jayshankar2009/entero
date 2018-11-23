package com.app.entero.direct.ui.fragment.salesman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.salesman.CreditNotesActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.PDCPaymentActivity_Salesman;
import com.app.entero.direct.ui.adapter.salesman.CustomerPendingBillListAdapter_Salesman;

public class CustomerPendingBillFragment_Salesman extends Fragment implements View.OnClickListener {

    private static RecyclerView.Adapter adapter_customer_pending_bill;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_customer_list_pending_bill;
    private static ArrayList<Outstandings> allCustomerData;
    public static View.OnClickListener customerPendingBillOnClickListener;
    View rootView;
    TextView txt_customer_list_outstanding_amount,txt_customer_list_pdc,txt_customer_list_CreditNote;

    public CustomerPendingBillFragment_Salesman() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.salesman_customer_list_tab_pending_bill, container, false);

        initView(rootView);
        onClickEvent();


        allCustomerData = new ArrayList<Outstandings>();
        layoutManager = new LinearLayoutManager(getActivity());
        recycler_view_customer_list_pending_bill.setHasFixedSize(true);
        customerPendingBillOnClickListener = new MyOnClickListener(getActivity());
        recycler_view_customer_list_pending_bill.setLayoutManager(layoutManager);
        recycler_view_customer_list_pending_bill.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            allCustomerData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }

        adapter_customer_pending_bill = new CustomerPendingBillListAdapter_Salesman(allCustomerData);
        recycler_view_customer_list_pending_bill.setAdapter(adapter_customer_pending_bill);

        return rootView;
    }

    private void initView(View rootView) {
        recycler_view_customer_list_pending_bill = (RecyclerView) rootView.findViewById(R.id.recycler_view_customer_list_pending_bill);
        txt_customer_list_outstanding_amount=(TextView) rootView.findViewById(R.id.txt_customer_list_outstanding_amount);
        txt_customer_list_pdc=(TextView) rootView.findViewById(R.id.txt_customer_list_pdc);
        txt_customer_list_CreditNote=(TextView) rootView.findViewById(R.id.txt_customer_list_CreditNote);
    }

    private void onClickEvent() {
        txt_customer_list_pdc.setOnClickListener(this);
        txt_customer_list_CreditNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_customer_list_pdc:
                Intent i = new Intent(getActivity(), PDCPaymentActivity_Salesman.class);
                this.startActivity(i);
                break;

            case R.id.txt_customer_list_CreditNote:
                Intent j = new Intent(getActivity(), CreditNotesActivity_Salesman.class);
                this.startActivity(j);
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
            //removeItem(v);
           /* int selectedItemPosition = recycler_view_customer_list.getChildPosition(v);
            Intent in = new Intent(context, OrderDetailsActivity_Saleesman.class);
            startActivity(in);*/
            int selectedItemPosition = recycler_view_customer_list_pending_bill.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recycler_view_customer_list_pending_bill.findViewHolderForPosition(selectedItemPosition);
            LinearLayout ly_hide_status
                    = (LinearLayout) viewHolder.itemView.findViewById(R.id.ly_hide_status);
            ly_hide_status.setVisibility(View.VISIBLE);
        }

    }
}
