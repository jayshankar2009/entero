package com.app.entero.direct.ui.adapter.salesman;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;


public class NavigationAdapter_Salesman extends RecyclerView.Adapter<NavigationAdapter_Salesman.HolderNavigation> {

    private Activity activity;
    private String nav_title[];
    private TypedArray nav_icon;

    int selected_pos = 0;
    private OnItemRecycleClickListener onItemRecycleClickListener;

    public NavigationAdapter_Salesman(Activity activity, OnItemRecycleClickListener onItemRecycleClickListener) {
        this.activity = activity;
        nav_title = activity.getResources().getStringArray(R.array.sales_nav_title);
        nav_icon = activity.getResources().obtainTypedArray(R.array.sales_nav_icons);
        this.onItemRecycleClickListener = onItemRecycleClickListener;
        this.selected_pos = selected_pos;
    }

    @Override
    public HolderNavigation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slaesman_adapter_nav, parent, false);
        HolderNavigation holderNavigation = new HolderNavigation(view);
        return holderNavigation;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNavigation holderNavigation, int position) {
        holderNavigation.nav_titles.setText(nav_title[position]);
        holderNavigation.nav_icons.setImageResource(nav_icon.getResourceId(position, 0));
        if (position == selected_pos) {
            holderNavigation.nav_titles.setPressed(true);
            holderNavigation.nav_icons.setPressed(true);
            // holder.ll_main.setBackgroundResource(R.drawable.list_hover);
        } else {
            holderNavigation.nav_titles.setPressed(false);
            holderNavigation.nav_icons.setPressed(false);
            holderNavigation.ll_main.setBackgroundColor(Color.TRANSPARENT);
        }

        holderNavigation.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_pos = position;
                onItemRecycleClickListener.onItemClick(v, position);
                notifyDataSetChanged();
            // ((MainActivity) activity).onNavItemClick(v, position);
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
        return nav_title.length;
    }

    public class HolderNavigation extends RecyclerView.ViewHolder {

        public ImageView nav_icons;
        private TextView nav_titles;
        private RelativeLayout ll_main;

        public HolderNavigation(View itemView) {
            super(itemView);
            nav_icons = (ImageView) itemView.findViewById(R.id.nav_icon);
            nav_titles = (TextView) itemView.findViewById(R.id.nav_title);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_nav);
        }
    }
}