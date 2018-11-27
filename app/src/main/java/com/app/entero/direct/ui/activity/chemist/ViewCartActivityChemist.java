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
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.chemist.ViewCartAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ViewCartActivityChemist extends BaseActivity implements OnItemRecycleClickListener {

    private static final String TAG = ViewCartActivityChemist.class.getName();
    private Toolbar toolbar;
    private CustomTextView_Salesman tv_title;
    private RecyclerView rv_offers;
    private ViewCartAdapter mViewCartAdapter;
    private ArrayList<ProductListModel> mProductList;
    private Context mContext;
    private StockistModel mStockistModel;
    private ProductListModel mStockListModelData;


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
        Intent i = getIntent();
        if (i.hasExtra(Constants.STOCKISTDATA)) {
            mStockistModel = (StockistModel) i.getSerializableExtra(Constants.STOCKISTDATA);
        }
        mProductList = new ArrayList<>();
        rv_offers = (RecyclerView) findViewById(R.id.rv_navigation);
        rv_offers.setLayoutManager(new LinearLayoutManager(this));
        mViewCartAdapter = new ViewCartAdapter(this, this, mProductList);
        rv_offers.setAdapter(mViewCartAdapter);
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
        tv_title.setText("View Cart");

    }

    @Override
    public void onItemClick(View view, int position) {


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
                mViewCartAdapter.refreshAdapter(mStockListModelData.getProductList());
            }
            if(create(this,Constants.PRODUCT_LIST,mStockListModel))
            {
                read(this,Constants.PRODUCT_LIST);
            }
        } else {
            Toast.makeText(this, mStockListModelData.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private ProductListModel read(Context context, String fileName) {
        try {

            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ProductListModel mProductListModel  = (ProductListModel) ois.readObject();
            ois.close();

          /*  FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();*/
            return mProductListModel;
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private boolean create(Context context, String fileName, ProductListModel jsonString){
        try {
            File file = null;
            file = new File(getCacheDir(), "MyCache"); // Pass getFilesDir() and "MyFile" to read file
            FileOutputStream fos = openFileOutput(fileName,Context.MODE_PRIVATE);

            if (jsonString != null) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(jsonString);
                oos.close();
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
