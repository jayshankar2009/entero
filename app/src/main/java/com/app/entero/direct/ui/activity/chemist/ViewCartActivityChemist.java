package com.app.entero.direct.ui.activity.chemist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.OrderDetailTable;
import com.app.entero.direct.database.models.OrderDetailTableDao;
import com.app.entero.direct.database.models.OrderTableMaster;
import com.app.entero.direct.database.models.OrderTableMasterDao;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.chemist.ViewCartAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.ui.listener.RecyclerItemTouchHelper;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ViewCartActivityChemist extends BaseActivity implements OnItemRecycleClickListener,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener,View.OnClickListener {

    private static final String TAG = ViewCartActivityChemist.class.getName();
    private Toolbar toolbar;
    private CustomTextView_Salesman tv_title, text_name_take_order;
    private RecyclerView rv_offers;
    private ViewCartAdapter mViewCartAdapter;
    private ArrayList<ProductListModel> mProductList;
    private Context mContext;
    private StockistModel mStockistModel;
    private ProductListModel mStockListModelData;
    OrderDetailTableDao orderDetailTableDao;
    List<OrderDetailTable> productListModelDaos;
    private  RelativeLayout relativemain;
    private  LinearLayout confirm_linear, cancel_linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcart_chemist);
        mContext = this;
        initObjects();
        setToolbar();
        initView();
    }

    private void initView() {
        mStockistModel = new StockistModel();
        productListModelDaos = new ArrayList<>();
        orderDetailTableDao = ((EnteroApp) getApplication()).getDaoSession().getOrderDetailTableDao();
        productListModelDaos = orderDetailTableDao.loadAll();
        Log.d("productListModelDaos",""+productListModelDaos);
        mStockistModel = new StockistModel();
        Intent i = getIntent();
        if (i.hasExtra(Constants.STOCKISTDATA)) {
            mStockistModel = (StockistModel) i.getSerializableExtra(Constants.STOCKISTDATA);
        }
        confirm_linear = (LinearLayout) findViewById(R.id.confirm_linear);
        cancel_linear = (LinearLayout) findViewById(R.id.cancel_linear);
        mProductList = new ArrayList<>();
        rv_offers = (RecyclerView) findViewById(R.id.rv_navigation);
        text_name_take_order = (CustomTextView_Salesman) findViewById(R.id.text_name_take_order);
        relativemain = (RelativeLayout) findViewById(R.id.relative);
        text_name_take_order.setText(mStockistModel.getClient_LegalName());
        rv_offers.setLayoutManager(new LinearLayoutManager(this));
        mViewCartAdapter = new ViewCartAdapter(this, this, productListModelDaos);
        rv_offers.setAdapter(mViewCartAdapter);
        rv_offers.addItemDecoration(new SimpleDividerItemDecoration(this));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv_offers);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
                // remove it from adapter
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(rv_offers);
       /* hashMap.put(ApiConstants.StockistID, mStockistModel.getClientID());
        hashMap.put(ApiConstants.legendType, mStockistModel.getClientTypeID());
        if (isNetworkAvailable()) {
            callApi(ApiConstants.GETPRODUCTLIST, hashMap);
        }*/

       initListener();

    }

    private void initListener() {
        confirm_linear.setOnClickListener(this);
        cancel_linear.setOnClickListener(this);
    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (CustomTextView_Salesman) findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.backbutton);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("");
        tv_title.setText("View Cart");

    }

    @Override
    public void onItemClick(View view, int position) {


    }

   /* public void callApi(String url, LinkedHashMap<String, String> linkedHashMap) {
        isShowProgress(true);
        mCompositeDisposable.add(getApiCallService().getProductlist(SavePref.getInstance(this).getToken(), url, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }*/


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ViewCartAdapter.HolderNavigation) {
            // get the removed item name to display it in snack bar
            String name = productListModelDaos.get(viewHolder.getAdapterPosition()).getItemname();

            // backup of removed item for undo purpose
            final OrderDetailTable deletedItem = productListModelDaos.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mViewCartAdapter.removeItem(viewHolder.getAdapterPosition());
            deleteCartItem(productListModelDaos.get(position).getId());
            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(relativemain, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mViewCartAdapter.restoreItem(deletedItem, deletedIndex);
                    addItem(deletedItem);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public void onClick(View view) {

        if(view == cancel_linear)
        {

        }
        if(view == confirm_linear)
        {

        }

    }

    public void deleteCartItem(Long stockistID) {
        orderDetailTableDao.deleteByKey(stockistID);
    }


    public void addItem(OrderDetailTable deletedItem) {

    }

}
