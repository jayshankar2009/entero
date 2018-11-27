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
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;


public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.HolderNavigation> {

    private Activity activity;
    private ArrayList<ProductListModel> mList;
    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public ViewCartAdapter(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<ProductListModel> mProductList) {
        this.activity = activity;
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.selected_pos = selected_pos;
        this.mList = mProductList;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewcart_adapter_chemist, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        ProductListModel mOfferModel = mList.get(position);
        //holderNavigation.des_Tv.setText(mOfferModel.getTabDes());
        holderNavigation.tab_Tv.setText(mOfferModel.getItemname());
        holderNavigation.count_Tv.setText(mOfferModel.getStock());
        /*if(mOfferModel.getColorCode().equals("#f7931e")){
            Glide.with(activity).load(R.drawable.circle_dot_yellow).fitCenter().into(holderNavigation.ic_dot);
        }else if(mOfferModel.getColorCode().equals("#22b573")){
            Glide.with(activity).load(R.drawable.circle_dot_green).into(holderNavigation.ic_dot);

        }*/ /*else if(mOfferModel.getBoxSize().equals("f7931e")){

        }else if(mOfferModel.getBoxSize().equals("f7931e")){

        }else if(mOfferModel.getBoxSize().equals("f7931e")){

        }*/


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

        public ImageView img_icon,ic_dot;
        private CustomTextView_Salesman tab_Tv,count_Tv;
        private RelativeLayout ll_main;

        public HolderNavigation(View itemView) {
            super(itemView);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            ic_dot = (ImageView) itemView.findViewById(R.id.ic_dot);
            count_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.count_Tv);
            tab_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.tab_Tv);
            //des_Tv = (TextView) itemView.findViewById(R.id.des_Tv);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
        }
    }
    public void refreshAdapter(ArrayList<ProductListModel> mProductList)
    {
        this.mList = mProductList;
        notifyDataSetChanged();
        
    }
}