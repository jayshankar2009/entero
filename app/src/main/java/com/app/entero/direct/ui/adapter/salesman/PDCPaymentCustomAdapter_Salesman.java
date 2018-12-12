package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.model.PDCModel;
import com.app.entero.direct.ui.activity.salesman.PDCPaymentActivity_Salesman;
import com.app.entero.direct.ui.listener.PDCListInterface;

public class PDCPaymentCustomAdapter_Salesman extends RecyclerView.Adapter<PDCPaymentCustomAdapter_Salesman.MyViewHolder>{

    private ArrayList<PDCModel> dataSet;
PDCListInterface pdcListInterface;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
CardView card_view;
        TextView text_pdc_cheque_no;
        TextView text_pdc_status;
        TextView text_pdc_amount;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.text_pdc_cheque_no = (TextView) itemView.findViewById(R.id.text_pdc_cheque_no);
            this.text_pdc_status = (TextView) itemView.findViewById(R.id.text_pdc_status);
            this.text_pdc_amount = (TextView) itemView.findViewById(R.id.text_pdc_amount);
this.card_view=(CardView)itemView.findViewById(R.id.card_view);
        }
    }

    public PDCPaymentCustomAdapter_Salesman(PDCListInterface pdcListInterface,ArrayList<PDCModel> dataSet) {
        this.dataSet =  dataSet;
       this.pdcListInterface = pdcListInterface;
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
holder.text_pdc_cheque_no.setText(dataSet.get(listPosition).getChequeNo());
holder.text_pdc_amount.setText("Rs. "+dataSet.get(listPosition).getAmount());
holder.text_pdc_status.setText(dataSet.get(listPosition).getStatus() );
holder.card_view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        pdcListInterface.onItemClick(view,listPosition,dataSet);
    }
});
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
