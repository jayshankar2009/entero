package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.salesman.TakeOrderActivity_Salesman;

public class TakeOrderCustomAdapter_Salesman extends RecyclerView.Adapter<TakeOrderCustomAdapter_Salesman.MyViewHolder> {

    private ArrayList<Outstandings> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView take_order_product_img,take_order_product_stock_status_img;
        TextView text_take_order_product_name,text_take_order_product_unit,text_take_order_product_ptr,text_take_order_product_mrp,text_take_order_product_stock_count;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.take_order_product_img = (ImageView) itemView.findViewById(R.id.take_order_product_img);
            this.text_take_order_product_name = (TextView) itemView.findViewById(R.id.text_take_order_product_name);
            this.text_take_order_product_unit = (TextView) itemView.findViewById(R.id.text_take_order_product_unit);
            this.text_take_order_product_ptr = (TextView) itemView.findViewById(R.id.text_take_order_product_ptr);
            this.text_take_order_product_mrp = (TextView) itemView.findViewById(R.id.text_take_order_product_mrp);
            this.text_take_order_product_stock_count = (TextView) itemView.findViewById(R.id.text_take_order_product_stock_count);
            this.take_order_product_stock_status_img = (ImageView) itemView.findViewById(R.id.take_order_product_stock_status_img);

        }
    }
    public TakeOrderCustomAdapter_Salesman(ArrayList<Outstandings> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_take_order_products_list_item, parent, false);

        view.setOnClickListener(TakeOrderActivity_Salesman.takeOrderOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
