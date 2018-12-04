package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.DeliveryModel;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.CustomTaskDeliveryDetailsActivity_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeliveryActivity_Adpter_Salesman extends RecyclerView.Adapter<DeliveryActivity_Adpter_Salesman.MyViewHolder> {

    private ArrayList<DeliveryModel> data;
    Context context;
    OnItemRecycleClickListener onItemRecycleClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtInvoiceNo;
        TextView txtItems;
        TextView txtPackets;
CardView card_view;

        TextView txtBoxes;
        TextView txtDlvryStatus,txtInvoiceStatus;
        CircleImageView imgCircle,imgRupee;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtInvoiceNo = (TextView) itemView.findViewById(R.id.txtInvoiceNo);
            this.txtItems = (TextView) itemView.findViewById(R.id.txtItems);
            this.txtPackets = (TextView) itemView.findViewById(R.id.txtPackets);
            this.txtBoxes = (TextView) itemView.findViewById(R.id.txtBoxes);
            this.imgCircle = (CircleImageView)itemView.findViewById(R.id.imgCircle);
            this.imgRupee = (CircleImageView)itemView.findViewById(R.id.imgRupee);
            this.txtInvoiceStatus = (TextView)itemView.findViewById(R.id.txtInvoiceStatus);
          this.txtDlvryStatus = (TextView) itemView.findViewById(R.id.txtDlvryStatus);
          this.card_view=(CardView)itemView.findViewById(R.id.card_view);

        }
    }
    public DeliveryActivity_Adpter_Salesman(Context context, OnItemRecycleClickListener onItemRecycleClickListener,ArrayList<DeliveryModel> data) {
        this.data = data;
        this.context=context;
        this.onItemRecycleClickListener=onItemRecycleClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_child_delivery_list, parent, false);

        view.setOnClickListener(AllOrderActivity_Salesman.allOrderOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/
        holder.txtInvoiceNo.setText(data.get(listPosition).getStrInvoiceNo());
        holder.txtItems.setText(data.get(listPosition).getItems());
        holder.txtBoxes.setText(data.get(listPosition).getBoxes());
        holder.txtPackets.setText(data.get(listPosition).getPackets());
        if(listPosition%2==0) {
          holder.txtDlvryStatus.setText("Undeliverd");
            holder.txtDlvryStatus.setTextColor(Color.parseColor("#ff0000"));

           holder.txtInvoiceStatus.setVisibility(View.GONE);
           holder.imgRupee.setVisibility(View.GONE);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                holder.imgCircle.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_list_undeliver) );
            } else {
                holder.imgCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_list_undeliver));
            }



        }else {
            holder.txtDlvryStatus.setText("Waiting to Delivery");
           // holder.txtInvoiceStatus.setTextColor(Color.parseColor("#ff0000"));
            holder.txtInvoiceStatus.setVisibility(View.VISIBLE);
            holder.imgRupee.setVisibility(View.VISIBLE);
            holder.txtDlvryStatus.setTextColor(context.getResources().getColor(R.color.waitColor));
           // holder.imgCircle.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drawable_blank_circle) );
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                holder.imgCircle.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.drawable_blank_circle) );
            } else {
                holder.imgCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.drawable_blank_circle));
            }
        }
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemRecycleClickListener.onItemClick(v,listPosition);
             /*  Intent i = new Intent(context, CustomTaskDeliveryDetailsActivity_Salesman.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
