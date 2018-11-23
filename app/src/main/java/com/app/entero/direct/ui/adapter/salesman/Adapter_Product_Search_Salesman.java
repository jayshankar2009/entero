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
import com.app.entero.direct.model.Salesman_Product_Search_Model;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.CustomTaskDeliveryDetailsActivity_Salesman;
import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Product_Search_Salesman extends RecyclerView.Adapter<Adapter_Product_Search_Salesman.MyViewHolder> {

    private ArrayList<Salesman_Product_Search_Model> data;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtPrdctName;
        TextView txtManfcr;
        TextView txtPackSize;
        CardView card_view;
        TextView txtPtr;
        TextView txtMRP;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtPrdctName = (TextView) itemView.findViewById(R.id.txtPrdctName);
            this.txtManfcr = (TextView) itemView.findViewById(R.id.txtManfcr);
            this.txtPackSize = (TextView) itemView.findViewById(R.id.txtPackSize);
            this.txtPtr = (TextView) itemView.findViewById(R.id.txtPtr);

            this.txtMRP = (TextView)itemView.findViewById(R.id.txtMRP);

           // this.card_view=(CardView)itemView.findViewById(R.id.card_view);

        }
    }
    public Adapter_Product_Search_Salesman(Context context, ArrayList<Salesman_Product_Search_Model> data) {
        this.data = data;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_child_search_by_product, parent, false);

        view.setOnClickListener(AllOrderActivity_Salesman.allOrderOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/
        holder.txtPrdctName.setText(data.get(listPosition).getProductName());
        holder.txtManfcr.setText(data.get(listPosition).getMnfctName());
        holder.txtPackSize.setText("Pack Size: "+data.get(listPosition).getPckSize());
        holder.txtPtr.setText("PTR: "+data.get(listPosition).getPtr());
       holder.txtMRP.setText("MRP: "+data.get(listPosition).getMrp());
       /* holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CustomTaskDeliveryDetailsActivity_Salesman.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}