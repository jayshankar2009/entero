package com.app.entero.direct.ui.adapter.chemist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.ui.listener.AddCartOnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.HolderNavigation> {

    private Activity activity;
    private ArrayList<ProductListModel> mList;
    private ArrayList<ProductListModel> mFilterData;
    int selected_pos = 0;
    private AddCartOnItemRecycleClickListener AddCartOnItemRecycleClickListener;

    public ProductsAdapter(Activity activity, AddCartOnItemRecycleClickListener AddCartOnItemRecycleClickListener, ArrayList<ProductListModel> mProductList) {
        this.activity = activity;
        this.AddCartOnItemRecycleClickListener = AddCartOnItemRecycleClickListener;
        this.selected_pos = selected_pos;
        this.mList = mProductList;
        this.mFilterData = mProductList;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_adapter_chemist, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        ProductListModel mOfferModel = mFilterData.get(position);
        //holderNavigation.des_Tv.setText(mOfferModel.getTabDes());
        holderNavigation.tab_Tv.setText(mOfferModel.getItemname());
        holderNavigation.count_Tv.setText(mOfferModel.getStock());
        //Glide.with(activity).load(R.drawable.circle_dot_yellow).into(holderNavigation.ic_dot);

        /*
        if(mOfferModel.getColorCode().equals("#f7931e")){
            Glide.with(activity).load(R.drawable.circle_dot_yellow).into(holderNavigation.ic_dot);
        }else if(mOfferModel.getColorCode().equals("#22b573")){
            Glide.with(activity).load(R.drawable.circle_dot_green).into(holderNavigation.ic_dot);

        }
        else
        {
            Glide.with(activity).load(R.drawable.black_dot).into(holderNavigation.ic_dot);

        }*/


      //  Glide.with(activity).load(mOfferModel.getImg()).into(holderNavigation.img_icon);
        holderNavigation.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_pos = position;
                AddCartOnItemRecycleClickListener.onItemClick(v, position, mFilterData.get(position));
                notifyDataSetChanged();
//                ((HomeActivity) activity).onNavItemClick(v, position);
            }
        });
    }


    public void refreshadapter(int menu_position) {
        // TODO Auto-generated method stub
        this.selected_pos = menu_position;
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return mFilterData.size();
    }

    public class HolderNavigation extends RecyclerView.ViewHolder {

        public ImageView img_icon,ic_dot;
        private CustomTextView_Salesman tab_Tv,count_Tv;
        private RelativeLayout ll_main;

        public HolderNavigation(View itemView) {
            super(itemView);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            ic_dot = (ImageView) itemView.findViewById(R.id.ic_dot);
            count_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.count_Tv);
            tab_Tv = (CustomTextView_Salesman) itemView.findViewById(R.id.tab_Tv);
            //des_Tv = (TextView) itemView.findViewById(R.id.des_Tv);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
        }
    }
    public void refreshAdapter(ArrayList<ProductListModel> mProductList)
    {
        this.mList = mProductList;
        this.mFilterData = mProductList;
        notifyDataSetChanged();
        
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilterData = mList;
                } else {
                    ArrayList<ProductListModel> filteredList = new ArrayList<>();
                    for (ProductListModel row : mList) {

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