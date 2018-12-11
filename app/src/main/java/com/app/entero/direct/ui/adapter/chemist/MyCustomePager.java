package com.app.entero.direct.ui.adapter.chemist;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.network.ApiConstants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyCustomePager extends PagerAdapter {
    Context context;
    int images[];
    LayoutInflater layoutInflater;
    private ArrayList<OffersModel> mOffersModelList;


    public MyCustomePager(Context context, int images[],ArrayList<OffersModel> mList) {
        this.context = context;
        this.images = images;
        this.mOffersModelList = mList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mOffersModelList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.viewpager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Glide.with(context).load(ApiConstants.BASE_URL+mOffersModelList.get(position).getOffer_image()).into(imageView);
        //imageView.setImageResource(images[position]);

        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}