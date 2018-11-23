package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Sales;

public class SalesDataCustomAdapter_Salesman extends RecyclerView.Adapter<SalesDataCustomAdapter_Salesman.MyViewHolder> {

    private ArrayList<Sales> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_customer_sales_qty;
        TextView text_customer_sales_amount;
        TextView text_sales_return_qty;
        TextView text_sales_return_amount;
        TextView text_sales_total_amount;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.text_customer_sales_qty = (TextView) itemView.findViewById(R.id.text_customer_sales_qty);
            this.text_customer_sales_amount = (TextView) itemView.findViewById(R.id.text_customer_sales_amount);
            this.text_sales_return_qty = (TextView) itemView.findViewById(R.id.text_sales_return_qty);
            this.text_sales_return_amount = (TextView) itemView.findViewById(R.id.text_sales_return_amount);
            this.text_sales_total_amount = (TextView) itemView.findViewById(R.id.text_sales_total_amount);

        }
    }

    public SalesDataCustomAdapter_Salesman(ArrayList<Sales> allSalesData) {
        this.dataSet = allSalesData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_sales_summary_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.text_customer_sales_qty.setText(dataSet.get(listPosition).getCustomer_sales_qty());
        holder.text_customer_sales_amount.setText(dataSet.get(listPosition).getCustomer_sales_amount());
        holder.text_sales_return_qty.setText(dataSet.get(listPosition).getSales_return_qty());
        holder.text_sales_return_amount.setText(dataSet.get(listPosition).getSales_return_amount());
        holder.text_sales_total_amount.setText(dataSet.get(listPosition).getToral_amount());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
        //return 3;
    }
}
