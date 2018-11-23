package com.app.entero.direct.ui.adapter.chemist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OutstandingModel;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;


public class OutStandingAdapter extends RecyclerView.Adapter<OutStandingAdapter.HolderNavigation> {

    private Activity activity;
    private ArrayList<OutstandingModel> mList;
    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public OutStandingAdapter(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<OutstandingModel> mOfferList) {
        this.activity = activity;
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.selected_pos = selected_pos;
        this.mList = mOfferList;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outstanding_adapter, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        OutstandingModel mOfferModel = mList.get(position);
        holderNavigation.chemistName.setText(mOfferModel.getChemistName());
        holderNavigation.bill_id_tv.setText(mOfferModel.getBillID());
        holderNavigation.bill_items_tv.setText(mOfferModel.getBillItems());
        holderNavigation.discount_tv.setText("Rs." + mOfferModel.getDiscount());
        holderNavigation.final_cost_tv.setText("Rs." + mOfferModel.getFinalAmount());
        holderNavigation.grand_total_tv.setText("Rs." + mOfferModel.getGrandTotal());
        holderNavigation.pending_days_tv.setText(mOfferModel.getPendingDays());
        String[] split = mOfferModel.getBillDate().split(" \n ");
        holderNavigation.bill_date_tv.setText(split[0]);


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

        private CustomTextView_Salesman chemistName,bill_id_tv,bill_date_tv,pending_days_tv,bill_items_tv,
                grand_total_tv,discount_tv,final_cost_tv;
        private RelativeLayout ll_main;

        public HolderNavigation(View itemView) {
            super(itemView);
            chemistName = (CustomTextView_Salesman) itemView.findViewById(R.id.chemistName);
            bill_id_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.bill_id_tv);
            bill_date_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.bill_date_tv);
            pending_days_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.pending_days_tv);
            bill_items_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.bill_items_tv);
            grand_total_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.grand_total_tv);
            discount_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.discount_tv);
            final_cost_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.final_cost_tv);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
        }
    }
}