package com.app.entero.direct.ui.adapter.salesman;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.app.entero.direct.R;
import com.app.entero.direct.model.AllOrderModel;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;

public class AllOrderCustomAdapter_Salesman extends RecyclerView.Adapter<AllOrderCustomAdapter_Salesman.MyViewHolder> {

    private ArrayList<AllOrderModel> dataSet;
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
    OnItemRecycleClickListener onItemRecycleClickListener;
    Date date = null;
    String formattedDate;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_outstanding_name;
        TextView text_total_outstanding_amount;
        TextView text_order_id,text_order_status;
        TextView text_order_date;
        TextView text_order_count, text_total_order_amount;
        CardView card_view;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.text_outstanding_name = (TextView) itemView.findViewById(R.id.text_order_name);
            this.text_total_outstanding_amount = (TextView) itemView.findViewById(R.id.text_total_outstanding_amount);
            this.text_order_id = (TextView) itemView.findViewById(R.id.text_order_id);
            this.text_order_date = (TextView) itemView.findViewById(R.id.text_order_date);
            this.text_order_count = (TextView) itemView.findViewById(R.id.text_order_count);
            this.text_total_order_amount = (TextView) itemView.findViewById(R.id.text_total_order_amount);
            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
            this.text_order_status=(TextView)itemView.findViewById(R.id.text_order_status);
        }
    }

    public AllOrderCustomAdapter_Salesman(OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<AllOrderModel> data) {
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_all_order_item, parent, false);

        view.setOnClickListener(AllOrderActivity_Salesman.allOrderOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        if (dataSet.get(listPosition).getTransactionNo() == null) {
            holder.text_order_id.setText("Order Id: 0");
        } else {
            holder.text_order_id.setText("Order Id: " + dataSet.get(listPosition).getTransactionNo());
        }
        holder.text_outstanding_name.setText(dataSet.get(listPosition).getChemistLegalName());
        holder.text_total_order_amount.setText("Rs." + dataSet.get(listPosition).getAmount());
        holder.text_order_count.setText(dataSet.get(listPosition).getItems() + " Items");
        try {
            date = inputFormat.parse(dataSet.get(listPosition).getDocDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formattedDate = outputFormat.format(date);
        holder.text_order_date.setText("Date : " + formattedDate);
        holder.text_order_status.setText(dataSet.get(listPosition).getStatusTitle());
        holder.text_order_status.setTextColor(Color.parseColor(dataSet.get(listPosition).getColorCode()));
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemRecycleClickListener.onItemClick(view, listPosition);
            }
        });
        // date = inputFormat.
        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/
           //holder.text_order_date.setText(date.toString());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    dataSet = dataSet;
                } else {

                    ArrayList<AllOrderModel> filteredList = new ArrayList<>();

                    for (AllOrderModel allOrderModel : dataSet) {

                        if ((allOrderModel.getChemistLegalName().toLowerCase().trim().contains(charString)) || (allOrderModel.getTransactionNo().trim().contains(charSequence))) {

                            filteredList.add(allOrderModel);
                        }
                    }

                    dataSet = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataSet;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataSet = (ArrayList<AllOrderModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
