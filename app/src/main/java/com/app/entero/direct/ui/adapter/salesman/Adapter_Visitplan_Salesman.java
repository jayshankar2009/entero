package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.model.Visitplanmodal;
import com.app.entero.direct.ui.activity.salesman.Customer_TastActivity_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;


/**
 * Created by admin on 10/24/2018.
 */

public class Adapter_Visitplan_Salesman extends RecyclerView.Adapter<Adapter_Visitplan_Salesman.MyViewHolder> {

    private List<SalesmanDashBoardModel> mList;
    Context context;
    OnItemRecycleClickListener onItemRecycleClickListener;

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


    public Adapter_Visitplan_Salesman(Context context,OnItemRecycleClickListener onItemRecycleClickListener, List<SalesmanDashBoardModel> mList) {
        this.context= context;
        this.mList = mList;
        this.onItemRecycleClickListener = onItemRecycleClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_visitplan_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

     holder.id.setText(mList.get(position).getChemistErpCode());
        holder.name.setText(mList.get(position).getChemistLegalName());
        holder.address.setText(mList.get(position).getChemistAddress());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemRecycleClickListener.onItemClick(v,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mList = mList;
                } else {

                    ArrayList<SalesmanDashBoardModel> filteredList = new ArrayList<>();

                    for (SalesmanDashBoardModel dashBoardModel : mList) {

                        if ((dashBoardModel.getChemistLegalName().toLowerCase().trim().contains(charString)) || (dashBoardModel.getChemistErpCode().trim().contains(charSequence))) {

                            filteredList.add(dashBoardModel);
                        }
                    }

                    mList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mList = (ArrayList<SalesmanDashBoardModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
