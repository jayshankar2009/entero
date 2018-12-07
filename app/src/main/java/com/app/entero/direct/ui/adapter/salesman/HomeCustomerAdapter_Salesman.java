package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.database.models.CustomerVisitTable;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeCustomerAdapter_Salesman extends RecyclerView.Adapter<HomeCustomerAdapter_Salesman.MyViewHolder> {
ArrayList<CustomerVisitTable> salesmanDashBoardModel;
Context context;
OnItemRecycleClickListener onItemRecycleClickListener;

    public HomeCustomerAdapter_Salesman(Context context,OnItemRecycleClickListener onItemRecycleClickListener, ArrayList<CustomerVisitTable> salesmanDashBoardModel) {
    this.context=context;
   this.salesmanDashBoardModel=salesmanDashBoardModel;
   this.onItemRecycleClickListener = onItemRecycleClickListener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salesman_home_customer_adapter, parent, false);

     //   view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtLocation.setText(salesmanDashBoardModel.get(position).getCity());
        holder.txtPharma.setText(salesmanDashBoardModel.get(position).getChemist_Legal_Name());
        holder.lnrClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemRecycleClickListener.onItemClick(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(salesmanDashBoardModel.size()>5) {
            return 5;
        }else {
            return salesmanDashBoardModel.size();
        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtPharma,txtLocation;
        LinearLayout lnrClick;
        CircleImageView imgCircle;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtPharma=(TextView)itemView.findViewById(R.id.txtPharma);
            this.txtLocation=(TextView)itemView.findViewById(R.id.txtLocation);
            this.imgCircle=(CircleImageView)itemView.findViewById(R.id.img);
            this.lnrClick=(LinearLayout)itemView.findViewById(R.id.lnrClick);


        }
    }
}
