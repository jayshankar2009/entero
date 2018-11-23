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

public class CustTaskDeliveryDetailsAdapter_Salesman extends RecyclerView.Adapter<CustTaskDeliveryDetailsAdapter_Salesman.MyViewHolder>{

    private ArrayList<Outstandings> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView customer_task_delivery_details_product_images;
        TextView text_customer_task_delivery_details_name;
        TextView text_customer_task_delivery_details_discription;
        TextView text_customer_task_delivery_details_qty;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.customer_task_delivery_details_product_images = (ImageView) itemView.findViewById(R.id.customer_task_delivery_details_product_images);
            this.text_customer_task_delivery_details_name = (TextView) itemView.findViewById(R.id.text_customer_task_delivery_details_name);
            this.text_customer_task_delivery_details_discription = (TextView) itemView.findViewById(R.id.text_customer_task_delivery_details_discription);
            this.text_customer_task_delivery_details_qty = (TextView) itemView.findViewById(R.id.text_customer_task_delivery_details_qty);

        }
    }

    public CustTaskDeliveryDetailsAdapter_Salesman(ArrayList<Outstandings> custTaskDeliveryDetailsData) {
        this.dataSet = custTaskDeliveryDetailsData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_customer_task_delivery_details_item, parent, false);

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
