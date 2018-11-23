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
import com.app.entero.direct.ui.fragment.salesman.CustomerOrdersFragment_Salesman;

public class CustomerOrdersListAdapter_Salesman extends RecyclerView.Adapter<CustomerOrdersListAdapter_Salesman.MyViewHolder> {

    private ArrayList<Outstandings> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView order_details_id;
        TextView order_details_total_amount;
        TextView order_details_date;
        TextView order_details_time;

        TextView order_details_invoice_id;
        TextView order_details_paid_amount;
        TextView order_details_paid_date;
        TextView order_details_paid_time;

        ImageView order_details_status_img;
        TextView order_details_status;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.order_details_id = (TextView) itemView.findViewById(R.id.order_details_id);
            this.order_details_total_amount = (TextView) itemView.findViewById(R.id.order_details_total_amount);
            this.order_details_date = (TextView) itemView.findViewById(R.id.order_details_date);
            this.order_details_time = (TextView) itemView.findViewById(R.id.order_details_time);

            this.order_details_invoice_id = (TextView) itemView.findViewById(R.id.order_details_invoice_id);
            this.order_details_paid_amount = (TextView) itemView.findViewById(R.id.order_details_paid_amount);
            this.order_details_paid_date = (TextView) itemView.findViewById(R.id.order_details_paid_date);
            this.order_details_paid_time = (TextView) itemView.findViewById(R.id.order_details_paid_time);

            this.order_details_status_img = (ImageView) itemView.findViewById(R.id.order_details_status_img);
            this.order_details_status = (TextView) itemView.findViewById(R.id.order_details_status);


        }
    }
    public CustomerOrdersListAdapter_Salesman(ArrayList<Outstandings> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_customer_orders_item, parent, false);

        view.setOnClickListener(CustomerOrdersFragment_Salesman.customerListOnClickListener);

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
