package com.app.entero.direct.ui.listener;

import android.view.View;

import com.app.entero.direct.model.PDCModel;
import com.app.entero.direct.model.ProductListModel;

import java.util.ArrayList;

public interface PDCListInterface {
    public void onItemClick(View view,
                            int position,
                            ArrayList<PDCModel> pdcList);
}
