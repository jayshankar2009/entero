package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.Helper.ReportData;
import com.app.entero.direct.R;

public class AllDeliveryReportAdapter_Salesman extends RecyclerView.Adapter<AllDeliveryReportAdapter_Salesman.MyViewHolder> {

    private ArrayList<ReportData> dataSet;
    Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_delivered_name;
        ImageView status_images;
        TextView text_delivery_status;
        TextView text_delivery_time;
        TextView text_delivery_location;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.text_delivered_name = (TextView) itemView.findViewById(R.id.text_delivered_name);
            this.status_images = (ImageView) itemView.findViewById(R.id.status_images);
            this.text_delivery_status = (TextView) itemView.findViewById(R.id.text_delivery_status);
            this.text_delivery_time = (TextView) itemView.findViewById(R.id.text_delivery_time);
            this.text_delivery_location = (TextView) itemView.findViewById(R.id.text_delivery_location);

        }
    }

    public AllDeliveryReportAdapter_Salesman(Context context, ArrayList<ReportData> data) {
        this.mContext = context;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_delivery_report_list_item, parent, false);

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
        //return dataSet.size();
        return 5;
    }
}
