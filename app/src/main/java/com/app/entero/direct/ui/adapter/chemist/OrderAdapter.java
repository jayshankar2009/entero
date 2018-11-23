package com.app.entero.direct.ui.adapter.chemist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OrdersModel;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.HolderNavigation> {

    private Activity activity;
    private ArrayList<OrdersModel> mList;
    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public OrderAdapter(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<OrdersModel> mOfferList) {
        this.activity = activity;
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.selected_pos = selected_pos;
        this.mList = mOfferList;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_adapter, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        OrdersModel mOfferModel = mList.get(position);
        holderNavigation.chemistName.setText(mOfferModel.getChemistName());
        if(mOfferModel.getStatus().equals("Pending"))
        {
            holderNavigation.deliveryStatus.setTextColor(activity.getResources().getColor(R.color.red_text_color));
        }
        else
        {
            holderNavigation.deliveryStatus.setTextColor(activity.getResources().getColor(R.color.primaryBlack));

        }
        holderNavigation.deliveryStatus.setText(mOfferModel.getStatus());
        holderNavigation.order_id_Tv.setText(mOfferModel.getId());
        holderNavigation.invoice_id_tv.setText(mOfferModel.getInvoicId());
        holderNavigation.invoice_amount_tv.setText("Rs." + mOfferModel.getInvoiceAmount());
        holderNavigation.order_amount_tv.setText("Rs." + mOfferModel.getOrderAmount());

        String[] split = mOfferModel.getOrderDateTime().split(" \n ");
        holderNavigation.date_order_tv.setText(split[0]);
        holderNavigation.time_order_tv.setText(split[1]);
        String[] splitinvoiceDate = mOfferModel.getInvoiceDateTime().split(" \n ");
        holderNavigation.date_invoice_tv.setText(splitinvoiceDate[0]);
        holderNavigation.time_invoice_tv.setText(splitinvoiceDate[1]);


        holderNavigation.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_pos = position;
                onItemRecycleClickListener.onItemClick(v, position);
                notifyDataSetChanged();
//                ((HomeActivity) activity).onNavItemClick(v, position);
            }
        });
    }


    public void refreshadapter(int menu_position) {
        // TODO Auto-generated method stub
        this.selected_pos = menu_position;
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HolderNavigation extends RecyclerView.ViewHolder {

        private CustomTextView_Salesman chemistName,deliveryStatus,order_id_Tv,date_order_tv,time_order_tv,
                order_amount_tv,invoice_id_tv,date_invoice_tv,time_invoice_tv,invoice_amount_tv;
        private RelativeLayout ll_main;

        public HolderNavigation(View itemView) {
            super(itemView);
            order_id_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.order_id_Tv);
            chemistName = (CustomTextView_Salesman) itemView.findViewById(R.id.chemistName);
            deliveryStatus = (CustomTextView_Salesman) itemView.findViewById(R.id.deliveryStatus);

            date_order_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.date_order_tv);
            time_order_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.time_order_tv);
            order_amount_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.order_amount_tv);

            invoice_id_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.invoice_id_tv);
            date_invoice_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.date_invoice_tv);

            time_invoice_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.time_invoice_tv);
            invoice_amount_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.invoice_amount_tv);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
        }
    }
}