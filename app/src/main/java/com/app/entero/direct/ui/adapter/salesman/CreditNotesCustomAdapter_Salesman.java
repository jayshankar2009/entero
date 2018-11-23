package com.app.entero.direct.ui.adapter.salesman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;


public class CreditNotesCustomAdapter_Salesman extends RecyclerView.Adapter<CreditNotesCustomAdapter_Salesman.MyViewHolder> {
    private ArrayList<Outstandings> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_credit_note_no;
        TextView txt_credit_note;
        TextView txt_credit_note_amount;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txt_credit_note_no = (TextView) itemView.findViewById(R.id.txt_credit_note_no);
            this.txt_credit_note = (TextView) itemView.findViewById(R.id.txt_credit_note);
            this.txt_credit_note_amount = (TextView) itemView.findViewById(R.id.txt_credit_note_amount);

        }
    }
    public CreditNotesCustomAdapter_Salesman(ArrayList<Outstandings> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_credit_notes_item, parent, false);

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

