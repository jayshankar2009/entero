package com.app.entero.direct.ui.adapter.chemist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.database.models.OrderDetailTable;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.List;


public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.HolderNavigation> {

    private Activity activity;
    private List<OrderDetailTable> mList;
    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public ViewCartAdapter(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener, List<OrderDetailTable> mProductList) {
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
        OrderDetailTable mOfferModel = mList.get(position);
        //holderNavigation.des_Tv.setText(mOfferModel.getTabDes());
        holderNavigation.text_take_order_product_name.setText(mOfferModel.getItemname());
        holderNavigation.text_take_order_product_unit.setText(mOfferModel.getPacksize());
        holderNavigation.text_take_order_product_ptr.setText(mOfferModel.getRate());
        holderNavigation.text_take_order_product_mrp.setText(mOfferModel.getMrp());
        holderNavigation.text_take_order_product_stock_count.setText(mOfferModel.getStock());
        holderNavigation.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_pos = position;
                onItemRecycleClickListener.onItemClick(v, position);
                notifyDataSetChanged();
            }
        });
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HolderNavigation extends RecyclerView.ViewHolder {
        public RelativeLayout viewBackground, viewForeground;
        public ImageView take_order_product_img,take_order_product_stock_status_img;
        public CustomTextView_Salesman text_take_order_product_name,text_take_order_product_unit,text_take_order_product_ptr,text_take_order_product_mrp,text_take_order_product_stock_count;
        public LinearLayout linearLayout;

        public HolderNavigation(View itemView) {
            super(itemView);
            take_order_product_img = (ImageView) itemView.findViewById(R.id.take_order_product_img);
            text_take_order_product_name = (CustomTextView_Salesman) itemView.findViewById(R.id.text_take_order_product_name);
            text_take_order_product_unit = (CustomTextView_Salesman) itemView.findViewById(R.id.text_take_order_product_unit);
            text_take_order_product_ptr = (CustomTextView_Salesman) itemView.findViewById(R.id.text_take_order_product_ptr);
            text_take_order_product_mrp = (CustomTextView_Salesman) itemView.findViewById(R.id.text_take_order_product_mrp);
            text_take_order_product_stock_count = (CustomTextView_Salesman) itemView.findViewById(R.id.text_take_order_product_stock_count);
            take_order_product_stock_status_img = (ImageView) itemView.findViewById(R.id.take_order_product_stock_status_img);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }
    public void refreshAdapter(List<OrderDetailTable> mProductList)
    {
        this.mList = mProductList;
        notifyDataSetChanged();
        
    }

    public void removeItem(int position) {
        mList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(OrderDetailTable item, int position) {
        mList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}