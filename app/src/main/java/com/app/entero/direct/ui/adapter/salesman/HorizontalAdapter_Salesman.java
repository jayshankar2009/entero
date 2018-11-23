package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Selfie;


public class HorizontalAdapter_Salesman extends RecyclerView.Adapter<HorizontalAdapter_Salesman.MyViewHolder> {


    List<Selfie> horizontalList = Collections.emptyList();
    Context context;


    public HorizontalAdapter_Salesman(Context context, List<Selfie> horizontalList ) {
        this.context = context;
        this.horizontalList = horizontalList;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView take_selfie_signature;

        public MyViewHolder(View view) {
            super(view);
            take_selfie_signature=(ImageView) view.findViewById(R.id.take_selfie_signature);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.salesman_cartview_selfie_signature_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.take_selfie_signature.setImageBitmap(horizontalList.get(position).imageId);

    }


    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}
