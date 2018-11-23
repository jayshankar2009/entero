package com.app.entero.direct.ui.adapter.chemist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.entero.direct.R;
import com.app.entero.direct.model.NotificationModel;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.HolderNavigation> {

    private Activity activity;
    private ArrayList<NotificationModel> mList;
    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public NotificationAdapter(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<NotificationModel> mOfferList) {
        this.activity = activity;
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.selected_pos = selected_pos;
        this.mList = mOfferList;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_adapter_chemist, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        NotificationModel mOfferModel = mList.get(position);
        if(mOfferModel.getDate()!=null && !mOfferModel.getDate().equals(""))
        {
            holderNavigation.date_Tv.setVisibility(View.VISIBLE);
            holderNavigation.date_Tv.setText(mOfferModel.getDate().toString());
        }
        else
        {
            holderNavigation.date_Tv.setVisibility(View.GONE);
        }
        /*if(position==1 || position==3 || position==5 || position==7)
        {
            Glide.with(activity).load(R.drawable.nav_products).into(holderNavigation.img_icon);
        }
        else {
            Glide.with(activity).load(R.drawable.nav_orders).into(holderNavigation.img_icon);
        }*/
        holderNavigation.chemist_Tv.setText(Html.fromHtml(mOfferModel.getChemistName()));
        holderNavigation.time_tv.setText(mOfferModel.getTime());
      //  Glide.with(activity).load(mOfferModel.getImg()).into(holderNavigation.img_icon);
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

        public ImageView img_icon;
        private CustomTextView_Salesman order_Tv,chemist_Tv,time_tv,date_Tv;
        private RelativeLayout ll_main;

        public HolderNavigation(View itemView) {
            super(itemView);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            order_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.tab_Tv);
            time_tv = (CustomTextView_Salesman) itemView.findViewById(R.id.time_tv);
            chemist_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.chemist_Tv);
            date_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.date_Tv);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
        }
    }
}