package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.CustomerListModel;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.model.Salesman_CustomerList_Model;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.CustomTaskDeliveryDetailsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.CustomerListDetailsActivity_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.CustomerListInterface;
import com.app.entero.direct.utils.SavePref;

public class AllCustomerList_Adapter_Salesman  extends RecyclerView.Adapter<AllCustomerList_Adapter_Salesman.MyViewHolder> {

private ArrayList<Salesman_CustomerList_Model> dataSet;
    Context context;
    SavePref savePref;
    private OnItemRecycleClickListener onItemRecycleClickListener;
public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tx_cust_id;
    TextView tx_cust_name;
    TextView txAmount;
    LinearLayout heading_layout;




    public MyViewHolder(View itemView) {
        super(itemView);
        this.tx_cust_id = (TextView) itemView.findViewById(R.id.tx_cust_id);
        this.tx_cust_name = (TextView) itemView.findViewById(R.id.tx_cust_name);
        this.txAmount = (TextView) itemView.findViewById(R.id.txAmount);
        this.heading_layout=(LinearLayout)itemView.findViewById(R.id.heading_layout);


    }
}
    public AllCustomerList_Adapter_Salesman(Context context, OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<Salesman_CustomerList_Model> data) {
    this.context=context;
        this.dataSet = data;
        savePref = new SavePref();
        this.onItemRecycleClickListener = onItemRecycleClickListener;


    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_cartview_customer_list_item, parent, false);

        view.setOnClickListener(AllOrderActivity_Salesman.allOrderOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

holder.tx_cust_id.setText(dataSet.get(listPosition).getChemistID());
        holder.tx_cust_name.setText(dataSet.get(listPosition).getChemistLegalName());
        holder.txAmount.setText("RS: "+dataSet.get(listPosition).getOutstandingBal());

        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       // customerListInterface= (CustomerListInterface)context;
       /* Intent i = new Intent(context, CustomerListDetailsActivity_Salesman.class);
        i.putExtra("custList",dataSet.get(listPosition));

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/
       onItemRecycleClickListener.onItemClick(view,listPosition);

    }
});
    }
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    dataSet = dataSet;
                } else {

                    ArrayList<Salesman_CustomerList_Model> filteredList = new ArrayList<>();

                    for (Salesman_CustomerList_Model productsModel : dataSet) {

                        if (productsModel.getChemistLegalName().toLowerCase().contains(charString)||productsModel.getChemistID().contains(charString)) {

                            filteredList.add(productsModel);
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
                dataSet = (ArrayList<Salesman_CustomerList_Model>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
