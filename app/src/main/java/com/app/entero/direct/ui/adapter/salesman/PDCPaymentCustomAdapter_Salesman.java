package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.salesman.PDCPaymentActivity_Salesman;

public class PDCPaymentCustomAdapter_Salesman extends RecyclerView.Adapter<PDCPaymentCustomAdapter_Salesman.MyViewHolder>{

    private ArrayList<Outstandings> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_pdc_cheque_no;
        TextView text_pdc_status;
        TextView text_pdc_amount;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.text_pdc_cheque_no = (TextView) itemView.findViewById(R.id.text_pdc_cheque_no);
            this.text_pdc_status = (TextView) itemView.findViewById(R.id.text_pdc_status);
            this.text_pdc_amount = (TextView) itemView.findViewById(R.id.text_pdc_amount);

        }
    }

    public PDCPaymentCustomAdapter_Salesman(ArrayList<Outstandings> allOrderDetailsData) {
        this.dataSet = allOrderDetailsData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_pdc_payment_item, parent, false);

        view.setOnClickListener(PDCPaymentActivity_Salesman.pdcPaymentOnClickListener);

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
