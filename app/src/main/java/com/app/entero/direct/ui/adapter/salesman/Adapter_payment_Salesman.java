package com.app.entero.direct.ui.adapter.salesman;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Paymentmodel;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.CollectPaymentActivity_Salesman;

import net.igenius.customcheckbox.CustomCheckBox;

/**
 * Created by admin on 10/30/2018.
 */

public class Adapter_payment_Salesman extends RecyclerView.Adapter<Adapter_payment_Salesman.MyViewHolder> {

    private List<Paymentmodel> mList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_bill_id,txt_bill_date,txt_pendingday,txt_bill_items,txt_bill_grndtotal,txt_bill_discount,
                txt_bill_finalAmt,txt_status,txt_bill_balanceAmt;

        public CustomCheckBox checkbox_status;
        public MyViewHolder(View view) {
            super(view);
           /* id = (TextView) view.findViewById(R.id.txt_id);
            name = (TextView) view.findViewById(R.id.txt_name);
            address = (TextView) view.findViewById(R.id.txt_adrs);*/

            txt_bill_id = (TextView) view.findViewById(R.id.txt_bill_id);
            txt_bill_date = (TextView) view.findViewById(R.id.txt_billdate);
            txt_pendingday = (TextView) view.findViewById(R.id.txt_pending_amnt);
            txt_bill_items = (TextView) view.findViewById(R.id.txt_items);
            txt_bill_grndtotal = (TextView) view.findViewById(R.id.txt_grandtotal);
            txt_bill_discount = (TextView) view.findViewById(R.id.txt_discount);
            txt_bill_finalAmt = (TextView) view.findViewById(R.id.txt_finalamt);
            txt_status = (TextView) view.findViewById(R.id.txt_status);
            txt_bill_balanceAmt = (TextView) view.findViewById(R.id.txt_balAmt);
            checkbox_status = (CustomCheckBox) view.findViewById(R.id.checkbox_status);

        }
    }


    public Adapter_payment_Salesman(List<Paymentmodel> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_collect_payment_item, parent, false);

        //visitplan_list_row
        itemView.setOnClickListener(CollectPaymentActivity_Salesman.allCollectPaymentOnClickListener);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Paymentmodel vistmodal = mList.get(position);
        /*holder.id.setText(vistmodal.getId());
        holder.name.setText(vistmodal.getName());
        holder.address.setText(vistmodal.getAddress());*/

        holder.txt_bill_id.setText(vistmodal.getBillid());
        holder.txt_bill_date.setText(vistmodal.getBilldate());
        holder.txt_pendingday.setText(vistmodal.getPendingdays());
        holder.txt_bill_items.setText(vistmodal.getBillitems());
        holder.txt_bill_grndtotal.setText(vistmodal.getGrandTotal());
        holder.txt_bill_discount.setText(vistmodal.getDiscount());
        holder.txt_bill_finalAmt.setText(vistmodal.getFinalamount());
        holder.txt_status.setText(vistmodal.getStatus());
        holder.txt_bill_balanceAmt.setText(vistmodal.getBalAmount());



        if(vistmodal.getStatus().equals("Partially Paid")){

            holder.txt_status.setTextColor(Color.parseColor("#03A59A"));

        }else if(vistmodal.getStatus().equals("UnPaid")){

            holder.txt_status.setTextColor(Color.parseColor("#FF4081"));
        }
        holder.checkbox_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.checkbox_status.setChecked(false);
                if(holder.checkbox_status.isChecked()){
                    holder.checkbox_status.setChecked(true);
                 CollectPaymentActivity_Salesman.balanceAmount = CollectPaymentActivity_Salesman.balanceAmount - Integer.parseInt(holder.txt_bill_balanceAmt.getText()+"");
                    CollectPaymentActivity_Salesman.txt_balance_amount.setText("Rs. "+ CollectPaymentActivity_Salesman.balanceAmount);
                    if(CollectPaymentActivity_Salesman.txt_balance_amount.getText().toString().equalsIgnoreCase("Rs. 0")){
                        CollectPaymentActivity_Salesman.ly_balance_account_amount.setVisibility(View.GONE);
                        CollectPaymentActivity_Salesman.btn_makePayment.setText("Make Payment");
                        CollectPaymentActivity_Salesman.btn_makePayment.setBackgroundResource(R.drawable.curd_rectangle);
                    }
                }else {
                    holder.checkbox_status.setChecked(false);
                  CollectPaymentActivity_Salesman.btn_makePayment.setText("Complete Payment");
                    CollectPaymentActivity_Salesman.btn_makePayment.setBackgroundResource(R.drawable.curve_blue_background);
                    CollectPaymentActivity_Salesman.ly_balance_account_amount.setVisibility(View.VISIBLE);
                    CollectPaymentActivity_Salesman.balanceAmount = CollectPaymentActivity_Salesman.balanceAmount + Integer.parseInt(holder.txt_bill_balanceAmt.getText()+"");
                    CollectPaymentActivity_Salesman.txt_balance_amount.setText("Rs. "+ CollectPaymentActivity_Salesman.balanceAmount);
              }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
