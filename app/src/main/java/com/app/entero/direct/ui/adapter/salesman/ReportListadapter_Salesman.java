package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.ui.activity.salesman.DailyCollectionReportActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.DeliveryReportActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.SummaryReportActivity_Salesman;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ReportListadapter_Salesman extends RecyclerView.Adapter<ReportListadapter_Salesman.MyViewHolder> {

    private Context mContext;
    private List<String> reportsList;
    int[] reportImages;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_report_name;
        public ImageView report_images;
        public LinearLayout report_item;


        public MyViewHolder(View view) {
            super(view);
            report_item = (LinearLayout) view.findViewById(R.id.report_item);
            text_report_name = (TextView) view.findViewById(R.id.text_report_name);
            report_images = (ImageView) view.findViewById(R.id.report_images);
        }
    }


    public ReportListadapter_Salesman(Context mContext, List<String> reportsList, int[] reportImages) {
        this.mContext = mContext;
        this.reportsList = reportsList;
        this.reportImages = reportImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_report_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.text_report_name.setText(reportsList.get(position));
        holder.report_images.setImageResource(reportImages[position]);

        // loading album cover using Glide library
        //Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.report_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0){

                    Intent in = new Intent(mContext, DailyCollectionReportActivity_Salesman.class);
                    in.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(in);
                }else if(position == 1){

                    Intent in = new Intent(mContext, DeliveryReportActivity_Salesman.class);
                    in.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(in);
                }else if(position == 2){

                    Intent in = new Intent(mContext, SummaryReportActivity_Salesman.class);
                    in.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(in);
                }else {

                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return reportsList.size();
    }
}
