package com.app.entero.direct.ui.activity.salesman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.model.Salesman_Product_Search_Model;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.Adapter_Manufacture_Search_Salesman;
import com.app.entero.direct.ui.adapter.salesman.Adapter_Product_Search_Salesman;
import com.app.entero.direct.ui.adapter.salesman.Products_Adapter_Salesman;

public class Product_Search_Activity_Salesman extends BaseActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    RadioButton rdbProduct,rdbManufacture;
    String setRdb;
    Bundle bundle;
    ArrayList<ProductsModel> allProductList;
    ArrayList<Salesman_Product_Search_Model> arrProductSearch;
    RecyclerView.LayoutManager layoutManager;
    Adapter_Product_Search_Salesman adapter_product_search_salesman;
    Adapter_Manufacture_Search_Salesman adapter_manufacture_search_salesman;
    Toolbar mToolbar;
   ;
    TextView txtHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_product_search_activity);
        initView();
        setToolbar();
        onClickEvent();
        if(setRdb.equalsIgnoreCase("Product")) {
            rdbProduct.setChecked(true);
            rdbProduct.setTextColor(getResources().getColor(R.color.colorPrimary1));
            rdbManufacture.setChecked(false);
            rdbManufacture.setTextColor(Color.parseColor("#000000"));
            adapter_product_search_salesman = new Adapter_Product_Search_Salesman(getApplicationContext(), arrProductSearch);
            recyclerView.setAdapter(adapter_product_search_salesman);
        }
        else if(setRdb.equalsIgnoreCase("Manufacture")) {
            rdbProduct.setChecked(false);
            rdbManufacture.setTextColor(getResources().getColor(R.color.colorPrimary1));
            rdbProduct.setTextColor(Color.parseColor("#000000"));
            rdbManufacture.setChecked(true);
            adapter_manufacture_search_salesman = new Adapter_Manufacture_Search_Salesman(getApplicationContext(), arrProductSearch);
            recyclerView.setAdapter(adapter_manufacture_search_salesman);
        }

    }

    private void onClickEvent() {
        rdbProduct.setOnClickListener(this);
        rdbManufacture.setOnClickListener(this);
    }


    private void setToolbar() {
            txtHeader.setText("Products Catalogue");
            mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);
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


    private void initView() {
        bundle = getIntent().getExtras();
       // allProductList= (ArrayList<ProductsModel>) bundle.getSerializable("productSearch");
        Toast.makeText(getApplicationContext(),"List"+allProductList.size(),Toast.LENGTH_SHORT).show();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        arrProductSearch = new ArrayList<>();
        arrProductSearch.add(new Salesman_Product_Search_Model("Crocine Advance", "Cipla Inc","10","980","1000"));
        arrProductSearch.add(new Salesman_Product_Search_Model("Crocine Advance", "Cipla Inc","10","980","1000"));
        arrProductSearch.add(new Salesman_Product_Search_Model("Baby Shop", "Origo Pharma","10","980","1000"));
        arrProductSearch.add(new Salesman_Product_Search_Model("Baby Shop", "Origo Pharma","10","980","1000"));
        arrProductSearch.add(new Salesman_Product_Search_Model("Baby Shop", "Origo Pharma","10","980","1000"));
        arrProductSearch.add(new Salesman_Product_Search_Model("Baby Shop", "Origo Pharma","10","980","1000"));
        arrProductSearch.add(new Salesman_Product_Search_Model("Baby Shop", "Origo Pharma","10","980","1000"));
        arrProductSearch.add(new Salesman_Product_Search_Model("Baby Shop", "Origo Pharma","10","980","1000"));
        setRdb= getIntent().getStringExtra("search");
        recyclerView = (RecyclerView)findViewById(R.id.delivRecy);
        rdbProduct=(RadioButton)findViewById(R.id.rdbProduct);
        rdbManufacture=(RadioButton)findViewById(R.id.rdbManufacture);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter_product_search_salesman = new Adapter_Product_Search_Salesman(getApplicationContext(), arrProductSearch);
        recyclerView.setAdapter(adapter_product_search_salesman);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rdbProduct :
                rdbProduct.setChecked(true);
                rdbProduct.setTextColor(getResources().getColor(R.color.colorPrimary1));
                rdbManufacture.setChecked(false);
                rdbManufacture.setTextColor(Color.parseColor("#000000"));
                adapter_product_search_salesman = new Adapter_Product_Search_Salesman(getApplicationContext(), arrProductSearch);
                recyclerView.setAdapter(adapter_product_search_salesman);
                break;
            case R.id.rdbManufacture :
                rdbProduct.setChecked(false);
                rdbManufacture.setTextColor(getResources().getColor(R.color.colorPrimary1));
                rdbProduct.setTextColor(Color.parseColor("#000000"));
                rdbManufacture.setChecked(true);
                adapter_manufacture_search_salesman = new Adapter_Manufacture_Search_Salesman(getApplicationContext(), arrProductSearch);
                recyclerView.setAdapter(adapter_manufacture_search_salesman);
                break;
        }
    }
}
