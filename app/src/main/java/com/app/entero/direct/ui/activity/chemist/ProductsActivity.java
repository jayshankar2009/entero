package com.app.entero.direct.ui.activity.chemist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.chemist.ProductsAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductsActivity extends BaseActivity implements OnItemRecycleClickListener {

    private static final String TAG = ProductsActivity.class.getName();
    private Toolbar toolbar;
    private CustomTextView_Salesman tv_title;
    private RecyclerView rv_offers;
    private ProductsAdapter mProductsAdapter;
    private ArrayList<ProductListModel> mProductList;
    private Context mContext;
    private SearchView searchView;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout layoutBottomSheet;
    private StockistModel mStockistModel;
    private ProductListModel mStockListModelData;
    private ImageView img_cross;
    private CustomTextView_Salesman item_name_tv, product_id_tv, sale_rate_tv, mrp_tv, mfg_tv, pack_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_chemist);
        mContext = this;
        initObjects();
        setToolbar();
        initView();
    }

    private void initView() {
        mStockistModel = new StockistModel();
        Intent i = getIntent();
        if (i.hasExtra(Constants.STOCKISTDATA)) {
            mStockistModel = (StockistModel) i.getSerializableExtra(Constants.STOCKISTDATA);
        }
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        mProductList = new ArrayList<>();
        //mOfferList = setOfferListData();
        rv_offers = (RecyclerView) findViewById(R.id.rv_navigation);

        item_name_tv = (CustomTextView_Salesman) findViewById(R.id.item_name_tv);
        product_id_tv = (CustomTextView_Salesman) findViewById(R.id.product_id_tv);
        sale_rate_tv = (CustomTextView_Salesman) findViewById(R.id.sale_rate_tv);
        mrp_tv = (CustomTextView_Salesman) findViewById(R.id.mrp_tv);
        mfg_tv = (CustomTextView_Salesman) findViewById(R.id.mfg_tv);
        pack_tv = (CustomTextView_Salesman) findViewById(R.id.pack_tv);
        img_cross = (ImageView) findViewById(R.id.img_cross);

        rv_offers.setLayoutManager(new LinearLayoutManager(this));
        mProductsAdapter = new ProductsAdapter(this, this, mProductList);
        rv_offers.setAdapter(mProductsAdapter);
        rv_offers.addItemDecoration(new SimpleDividerItemDecoration(this));


        hashMap.put(ApiConstants.StockistID, mStockistModel.getClientID());
        hashMap.put(ApiConstants.legendType, mStockistModel.getClientTypeID());
        if (isNetworkAvailable()) {
            callApi(ApiConstants.GETPRODUCTLIST, hashMap);
        }

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
        tv_title.setText(R.string.products);

    }

    @Override
    public void onItemClick(View view, int position) {

        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

            item_name_tv.setText(mStockListModelData.getProductList().get(position).getItemname());
            product_id_tv.setText(mStockListModelData.getProductList().get(position).getProduct_ID());
            sale_rate_tv.setText(mStockListModelData.getProductList().get(position).getRate());
            mrp_tv.setText(mStockListModelData.getProductList().get(position).getMrp());
            mfg_tv.setText(mStockListModelData.getProductList().get(position).getMfgName());
            pack_tv.setText(mStockListModelData.getProductList().get(position).getPacksize());
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        //searchView.setQueryHint("Outstandings List");
       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        });*/
        return true;
    }

    public void callApi(String url, LinkedHashMap<String, String> linkedHashMap) {
        isShowProgress(true);
        mCompositeDisposable.add(getApiCallService().getProductlist(SavePref.getInstance(this).getToken(), url, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void handleResponse(ProductListModel mStockListModel) {
        Log.e(TAG, " res: " + mStockListModel);
        isShowProgress(false);
        this.mStockListModelData = mStockListModel;
        if (mStockListModelData.getStatus().equals("success")) {
            if (mStockListModelData.getProductList() != null && mStockListModelData.getProductList().size() > 0) {
                mProductsAdapter.refreshAdapter(mStockListModelData.getProductList());
            }
        } else {
            Toast.makeText(this, mStockListModelData.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




    private String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    private boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }

}
