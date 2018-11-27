package com.app.entero.direct.ui.adapter.chemist;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.ui.activity.salesman.TakeOrderActivity_Salesman;
import com.app.entero.direct.ui.listener.AddCartOnItemRecycleClickListener;

import java.util.ArrayList;

public class TakeOrderAdapter extends RecyclerView.Adapter<TakeOrderAdapter.MyViewHolder> {
    private Activity activity;
    private ArrayList<ProductListModel> dataSet;
    private ArrayList<ProductListModel> mFilterData;
    private AddCartOnItemRecycleClickListener AddCartOnItemRecycleClickListener;

    public TakeOrderAdapter(Activity activity, AddCartOnItemRecycleClickListener AddCartOnItemRecycleClickListener,ArrayList<ProductListModel> data) {
        this.activity = activity;
        this.AddCartOnItemRecycleClickListener = AddCartOnItemRecycleClickListener;
        this.dataSet = data;
        this.mFilterData =data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView take_order_product_img,take_order_product_stock_status_img;
        TextView text_take_order_product_name,text_take_order_product_unit,text_take_order_product_ptr,text_take_order_product_mrp,text_take_order_product_stock_count;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.take_order_product_img = (ImageView) itemView.findViewById(R.id.take_order_product_img);
            this.text_take_order_product_name = (TextView) itemView.findViewById(R.id.text_take_order_product_name);
            this.text_take_order_product_unit = (TextView) itemView.findViewById(R.id.text_take_order_product_unit);
            this.text_take_order_product_ptr = (TextView) itemView.findViewById(R.id.text_take_order_product_ptr);
            this.text_take_order_product_mrp = (TextView) itemView.findViewById(R.id.text_take_order_product_mrp);
            this.text_take_order_product_stock_count = (TextView) itemView.findViewById(R.id.text_take_order_product_stock_count);
            this.take_order_product_stock_status_img = (ImageView) itemView.findViewById(R.id.take_order_product_stock_status_img);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.take_order_adapter, parent, false);

        view.setOnClickListener(TakeOrderActivity_Salesman.takeOrderOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.text_take_order_product_name.setText(mFilterData.get(listPosition).getItemname());
        holder.text_take_order_product_unit.setText(mFilterData.get(listPosition).getStock());
        holder.text_take_order_product_ptr.setText(mFilterData.get(listPosition).getRate());
        holder.text_take_order_product_mrp.setText(mFilterData.get(listPosition).getMrp());
        holder.text_take_order_product_stock_count.setText(mFilterData.get(listPosition).getStock());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCartOnItemRecycleClickListener.onItemClick(v, listPosition,mFilterData.get(listPosition));
                notifyDataSetChanged();
//                ((HomeActivity) activity).onNavItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mFilterData!=null && mFilterData.size()>0)
        return mFilterData.size();
        else
            return 0;
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilterData = dataSet;
                } else {
                    ArrayList<ProductListModel> filteredList = new ArrayList<>();
                    for (ProductListModel row : dataSet) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getItemname().toLowerCase().contains(charString.toLowerCase()) || row.getItemname().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mFilterData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterData = (ArrayList<ProductListModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



}
