package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.AllOrderSecondModel;
import com.app.entero.direct.model.Outstandings;

public class OrderDetailsCustomAdapter_Salesman extends RecyclerView.Adapter<OrderDetailsCustomAdapter_Salesman.MyViewHolder>{

    private ArrayList<AllOrderSecondModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView order_detail_product_images;
        TextView text_order_details_name;
        TextView text_order_details_discription;
        TextView text_order_details_qty;
        TextView text_order_details_amount;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.order_detail_product_images = (ImageView) itemView.findViewById(R.id.order_detail_product_images);
            this.text_order_details_name = (TextView) itemView.findViewById(R.id.text_order_details_name);
            this.text_order_details_discription = (TextView) itemView.findViewById(R.id.text_order_details_discription);
            this.text_order_details_qty = (TextView) itemView.findViewById(R.id.text_order_details_qty);
            this.text_order_details_amount = (TextView) itemView.findViewById(R.id.text_order_details_amount);

        }
    }

    public OrderDetailsCustomAdapter_Salesman(ArrayList<AllOrderSecondModel> allOrderDetailsData) {
        this.dataSet = allOrderDetailsData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_all_order_details_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/
        holder.text_order_details_name.setText(dataSet.get(listPosition).getProductDesc());
        holder.text_order_details_amount.setText("Rs. "+dataSet.get(listPosition).getPrice());
        holder.text_order_details_discription.setText(dataSet.get(listPosition).getPacksize());
        holder.text_order_details_qty.setText("X"+dataSet.get(listPosition).getQty());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
