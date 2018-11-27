package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.fragment.salesman.ProductsFragment_Salesman;

public class Products_Adapter_Salesman extends RecyclerView.Adapter<Products_Adapter_Salesman.MyViewHolder> {

    public static ArrayList<ProductsModel>  data;
    //Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtQty;
        ImageView imgMedicine;
        de.hdodenhof.circleimageview.CircleImageView imageCircle;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtQty = (TextView) itemView.findViewById(R.id.txtQty);
            this.imgMedicine = (ImageView) itemView.findViewById(R.id.idImage);
            this.imageCircle = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.imageCircle);

        }
    }
    public Products_Adapter_Salesman(ArrayList<ProductsModel> data) {
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_child_products_list, parent, false);

        view.setOnClickListener(ProductsFragment_Salesman.productListOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    data = data;
                } else {

                    ArrayList<ProductsModel> filteredList = new ArrayList<>();

                    for (ProductsModel productsModel : data) {

                        if (productsModel.getItemname().toLowerCase().contains(charString)) {

                            filteredList.add(productsModel);
                        }
                    }

                    data = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (ArrayList<ProductsModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.txtName.setText(data.get(listPosition).getItemname());
        holder.txtQty.setText(data.get(listPosition).getStock());
        int color = Color.parseColor(data.get(listPosition).getColorCode());
        holder.imageCircle.setColorFilter(color);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
