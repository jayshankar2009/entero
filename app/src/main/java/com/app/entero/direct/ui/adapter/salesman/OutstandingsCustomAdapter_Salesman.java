package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.salesman.OutstandingsActivity_Salesman;

public class OutstandingsCustomAdapter_Salesman extends RecyclerView.Adapter<OutstandingsCustomAdapter_Salesman.MyViewHolder> {

    private ArrayList<Outstandings> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_outstanding_name;
        TextView text_total_outstanding_amount;
        TextView text_outstanding_order_id;
        TextView text_outstanding_order_date;
        TextView text_outstanding_order_count;
        TextView text_outstanding_order_due_date;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.text_outstanding_name = (TextView) itemView.findViewById(R.id.text_outstanding_name);
            this.text_total_outstanding_amount = (TextView) itemView.findViewById(R.id.text_total_outstanding_amount);
            this.text_outstanding_order_id = (TextView) itemView.findViewById(R.id.text_outstanding_order_id);
            this.text_outstanding_order_date = (TextView) itemView.findViewById(R.id.text_outstanding_order_date);
            this.text_outstanding_order_count = (TextView) itemView.findViewById(R.id.text_outstanding_order_count);
            this.text_outstanding_order_due_date = (TextView) itemView.findViewById(R.id.text_outstanding_order_due_date);

        }
    }
    public OutstandingsCustomAdapter_Salesman(ArrayList<Outstandings> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_outstandings_item, parent, false);

        view.setOnClickListener(OutstandingsActivity_Salesman.myOnClickListener);

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
