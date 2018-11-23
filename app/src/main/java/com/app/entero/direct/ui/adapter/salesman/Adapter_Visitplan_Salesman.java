package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Visitplanmodal;
import com.app.entero.direct.ui.activity.salesman.Customer_TastActivity_Salesman;


/**
 * Created by admin on 10/24/2018.
 */

public class Adapter_Visitplan_Salesman extends RecyclerView.Adapter<Adapter_Visitplan_Salesman.MyViewHolder> {

    private List<Visitplanmodal> mList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name, address;
        CardView card_view;


        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.txt_id);
            name = (TextView) view.findViewById(R.id.txt_name);
            address = (TextView) view.findViewById(R.id.txt_adrs);
            card_view=(CardView) view.findViewById(R.id.card_view);
        }
    }


    public Adapter_Visitplan_Salesman(Context context, List<Visitplanmodal> mList) {
        this.context= context;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_visitplan_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Visitplanmodal vistmodal = mList.get(position);
        holder.id.setText(vistmodal.getId());
        holder.name.setText(vistmodal.getName());
        holder.address.setText(vistmodal.getAddress());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Customer_TastActivity_Salesman.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
