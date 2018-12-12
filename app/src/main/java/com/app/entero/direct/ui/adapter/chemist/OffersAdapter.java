package com.app.entero.direct.ui.adapter.chemist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.entero.direct.R;
import com.app.entero.direct.model.SchemeListModel;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.HolderNavigation> {

    private Activity activity;
    private ArrayList<SchemeListModel> mList;
    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public OffersAdapter(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<SchemeListModel> mOfferList) {
        this.activity = activity;
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.selected_pos = selected_pos;
        this.mList = mOfferList;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_adapter, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        SchemeListModel mOfferModel = mList.get(position);
        holderNavigation.tab_Tv.setText(mOfferModel.getProdcutName());
        holderNavigation.distributor_name.setText(mOfferModel.getStcokistName());
        holderNavigation.count_Tv.setText(mOfferModel.getPacksize());
      //  Glide.with(activity).load(mOfferModel.getImg()).into(holderNavigation.img_icon);
        holderNavigation.des_Tv.setText(mOfferModel.getScheme());


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


    public void refreshadapter(ArrayList<SchemeListModel> mOfferList) {
        this.mList = mOfferList;
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HolderNavigation extends RecyclerView.ViewHolder {

        public ImageView img_icon;
        private CustomTextView_Salesman tab_Tv,des_Tv,count_Tv,distributor_name;
        private RelativeLayout ll_main;

        public HolderNavigation(View itemView) {
            super(itemView);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            count_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.count_Tv);
            tab_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.tab_Tv);
            des_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.des_Tv);
            distributor_name = (CustomTextView_Salesman) itemView.findViewById(R.id.distributor_name);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
        }
    }
}