package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.fragment.salesman.ProductsFragment_Salesman;

public class Products_Adapter_Salesman extends RecyclerView.Adapter<Products_Adapter_Salesman.MyViewHolder> {

    private ArrayList<ProductsModel> dataSet;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtQty;
        ImageView imgMedicine,imageCircle;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtQty = (TextView) itemView.findViewById(R.id.txtQty);
            this.imgMedicine = (ImageView) itemView.findViewById(R.id.idImage);
            this.imageCircle = (ImageView) itemView.findViewById(R.id.imageCircle);

        }
    }
    public Products_Adapter_Salesman(Context context,ArrayList<ProductsModel> data) {
        this.context=context;
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_child_products_list, parent, false);

        view.setOnClickListener(ProductsFragment_Salesman.productListOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        /*holder.text_outstanding_name.setText(dataSet.get(listPosition).getName());
        holder.text_total_outstanding_amount.setText(dataSet.get(listPosition).getVersion());*/
        holder.txtName.setText(dataSet.get(listPosition).getStrPrdct());
        holder.txtQty.setText(dataSet.get(listPosition).getStrQty());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
