package com.app.entero.direct.ui.listener;

import android.view.View;

import com.app.entero.direct.model.ProductListModel;


public interface AddCartOnItemRecycleClickListener {
    public void onItemClick(View view,
                            int position,
                            ProductListModel productListModel);
}
