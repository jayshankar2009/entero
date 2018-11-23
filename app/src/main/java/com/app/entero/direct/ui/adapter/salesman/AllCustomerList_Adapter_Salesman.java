package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.CustomerListModel;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.CustomTaskDeliveryDetailsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.CustomerListDetailsActivity_Salesman;

public class AllCustomerList_Adapter_Salesman  extends RecyclerView.Adapter<AllCustomerList_Adapter_Salesman.MyViewHolder> {

private ArrayList<CustomerListModel> dataSet;
    Context context;

public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tx_cust_id;
    TextView tx_cust_name;
    TextView txAmount;
    LinearLayout heading_layout;




    public MyViewHolder(View itemView) {
        super(itemView);
        this.tx_cust_id = (TextView) itemView.findViewById(R.id.tx_cust_id);
        this.tx_cust_name = (TextView) itemView.findViewById(R.id.tx_cust_name);
        this.txAmount = (TextView) itemView.findViewById(R.id.txAmount);
        this.heading_layout=(LinearLayout)itemView.findViewById(R.id.heading_layout);


    }
}
    public AllCustomerList_Adapter_Salesman(Context context,ArrayList<CustomerListModel> data) {
    this.context=context;
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_customer_list_item, parent, false);

        view.setOnClickListener(AllOrderActivity_Salesman.allOrderOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
holder.tx_cust_id.setText(dataSet.get(listPosition).getInvoiceNo());
        holder.tx_cust_name.setText(dataSet.get(listPosition).getProduct());
        holder.txAmount.setText("RS: "+dataSet.get(listPosition).getAmount());
        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(context, CustomerListDetailsActivity_Salesman.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
});
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
