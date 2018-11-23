package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;

public class AllOrderCustomAdapter_Salesman extends RecyclerView.Adapter<AllOrderCustomAdapter_Salesman.MyViewHolder> {

    private ArrayList<Outstandings> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_outstanding_name;
        TextView text_total_outstanding_amount;
        TextView text_order_id;
        TextView text_order_date;
        TextView text_order_count;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.text_outstanding_name = (TextView) itemView.findViewById(R.id.text_outstanding_name);
            this.text_total_outstanding_amount = (TextView) itemView.findViewById(R.id.text_total_outstanding_amount);
            this.text_order_id = (TextView) itemView.findViewById(R.id.text_order_id);
            this.text_order_date = (TextView) itemView.findViewById(R.id.text_order_date);
            this.text_order_count = (TextView) itemView.findViewById(R.id.text_order_count);

        }
    }
    public AllOrderCustomAdapter_Salesman(ArrayList<Outstandings> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_all_order_item, parent, false);

        view.setOnClickListener(AllOrderActivity_Salesman.allOrderOnClickListener);

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
