package com.app.entero.direct.ui.activity.chemist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.OrderDetailTable;
import com.app.entero.direct.database.models.OrderDetailTableDao;
import com.app.entero.direct.database.models.OrderTableMasterDao;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener{

    private  Toolbar mToolbar;
    private SearchView searchView;
    private Context mContext;
    private CustomTextView_Salesman doc_id_tv,save_Tv,txtHeader;
    EditText comment_editText;
    private StockistModel mStockistModel;
    private OrderDetailTableDao orderDetailTableDao;
    private List<OrderDetailTable> productListModelDaos;
    private OrderTableMasterDao orderTableMasterDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
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
        orderTableMasterDao = ((EnteroApp) getApplication()).getDaoSession().getOrderTableMasterDao();
        Log.d("productListModelDaos",""+productListModelDaos);
        mStockistModel = new StockistModel();
        Intent i = getIntent();
        if (i.hasExtra(Constants.STOCKISTDATA)) {
            mStockistModel = (StockistModel) i.getSerializableExtra(Constants.STOCKISTDATA);
        }
        doc_id_tv = (CustomTextView_Salesman) findViewById(R.id.doc_id_tv);
        save_Tv = (CustomTextView_Salesman) findViewById(R.id.save_Tv);
        comment_editText = (EditText) findViewById(R.id.comment_editText);
        inItListener();
    }

    private void inItListener() {
        save_Tv.setOnClickListener(this);
    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);
        txtHeader=(CustomTextView_Salesman)findViewById(R.id.txtHeader);
        txtHeader.setText("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Back to previous activity
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    // search and filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Outstandings List");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return true;
    }

    @Override
    public void onClick(View v) {

        if(v == comment_editText)
        {

        }

    }
}
