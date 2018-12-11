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
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;


public class MyStockListAdapter extends RecyclerView.Adapter<MyStockListAdapter.HolderNavigation> {

    private Activity activity;
    private ArrayList<StockistModel> mList;
    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public MyStockListAdapter(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<StockistModel> mOfferList) {
        this.activity = activity;
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.selected_pos = selected_pos;
        this.mList = mOfferList;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_stocklist, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        StockistModel mStockListModel = mList.get(position);
        if(mStockListModel.getIs_Locked().equals("Unlocked"))
        {
            holderNavigation.stocklistId_Tv.setText(mStockListModel.getAccepted_id());
            holderNavigation.stocklistName_Tv.setText(mStockListModel.getClient_LegalName());
            holderNavigation.count_Tv.setText(mStockListModel.getClientID());
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

        private CustomTextView_Salesman stocklistId_Tv,stocklistName_Tv,count_Tv;
        private RelativeLayout ll_main;
        private ImageView img_icon;

        public HolderNavigation(View itemView) {
            super(itemView);
            count_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.count_Tv);
            stocklistId_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.stocklistId_Tv);
            stocklistName_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.stocklistName_Tv);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
            img_icon  = (ImageView) itemView.findViewById(R.id.img_icon);
        }
    }

    public void refreshData(ArrayList<StockistModel> mOfferList) {
        this.mList = mOfferList;
        notifyDataSetChanged();
    }
}