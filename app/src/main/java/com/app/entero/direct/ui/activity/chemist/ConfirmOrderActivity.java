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
import android.widget.Toast;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.OrderDetailTable;
import com.app.entero.direct.database.models.OrderDetailTableDao;
import com.app.entero.direct.database.models.OrderTableMaster;
import com.app.entero.direct.database.models.OrderTableMasterDao;
import com.app.entero.direct.model.DataModel;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.activity.main.ProfessionalDetailActivity;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.app.entero.direct.utils.Utils.create;

public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "ConfirmOrderActivity";
    private  Toolbar mToolbar;
    private SearchView searchView;
    private Context mContext;
    private CustomTextView_Salesman doc_id_tv,save_Tv,txtHeader;
    EditText comment_editText;
    private StockistModel mStockistModel;
    private OrderDetailTableDao orderDetailTableDao;
    private List<OrderDetailTable> productListModelDaos;
    private OrderTableMasterDao orderTableMasterDao;
    private OrderTableMaster mOrderTableMaster;

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
        mOrderTableMaster = new OrderTableMaster();
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
        mOrderTableMaster = getStockistData(mStockistModel.getClientID());
        doc_id_tv = (CustomTextView_Salesman) findViewById(R.id.doc_id_tv);
        save_Tv = (CustomTextView_Salesman) findViewById(R.id.save_Tv);
        comment_editText = (EditText) findViewById(R.id.comment_editText);
        inItListener();
        if(mOrderTableMaster!=null)
        doc_id_tv.setText(mOrderTableMaster.getDoc_no());
    }

    private void inItListener() {
        save_Tv.setOnClickListener(this);
    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);
        txtHeader=(CustomTextView_Salesman)findViewById(R.id.txtHeader);
        txtHeader.setText("Confirm Order");
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


    @Override
    public void onClick(View v) {
        if(v == save_Tv)
        {
            placeOrder();
        }
    }
    public String getDocId(String stockistID) {
        QueryBuilder<OrderTableMaster> qb = orderTableMasterDao.queryBuilder();
        QueryBuilder<OrderTableMaster> where = qb.where(OrderTableMasterDao.Properties.Stockiest_id.eq(stockistID));
        if(where.list().size()>0)
            return where.list().get(0).getDoc_no();
        else
            return null;
    }

    public OrderTableMaster getStockistData(String stockistID) {
        QueryBuilder<OrderTableMaster> qb = orderTableMasterDao.queryBuilder();
        QueryBuilder<OrderTableMaster> where = qb.where(OrderTableMasterDao.Properties.Stockiest_id.eq(stockistID));
        if(where.list().size()>0)
            return where.list().get(0);
        else
            return null;
    }

    private void placeOrder ()
    {



        LinkedHashMap<String, Object> mPostMapData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> mPostData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> mOrderData = new LinkedHashMap<>();
        JsonObject postJsonData = new JsonObject();
        JsonObject postData = new JsonObject();
        JsonArray mOrderDeatils = new JsonArray();
        try {
            postData.addProperty("StockistID", mStockistModel.getClientID());
            postData.addProperty("ChemistID", "9");
            postData.addProperty("Doc_no", mOrderTableMaster.getDoc_no());
            postData.addProperty("ERP_Code", "1100");
            postData.addProperty("CreatedBy", "9");
            postData.addProperty("Role_ID", Constants.CollectionAgent);
            postData.addProperty("Description", "");
            postData.addProperty("Comments", comment_editText.getText().toString());
            postData.addProperty("Deliveryoption", "demo Deliveryoption");

            for(int i =0; i < productListModelDaos.size();i++ )
            {
                JsonObject orderDetail = new JsonObject();
                orderDetail.addProperty("StockistID", mStockistModel.getClientID());
                orderDetail.addProperty("ChemistID", "9");
                orderDetail.addProperty("Doc_no", mOrderTableMaster.getDoc_no());
                orderDetail.addProperty("Product_StockistID", productListModelDaos.get(i).getStk_id());
                orderDetail.addProperty("Product_ID",  productListModelDaos.get(i).getProduct_ID());
                orderDetail.addProperty("line_item",  productListModelDaos.get(i).getLegendMode());
                orderDetail.addProperty("Qty",  productListModelDaos.get(i).getQuantity());
                orderDetail.addProperty("Rate",  productListModelDaos.get(i).getRate());
                orderDetail.addProperty("Price", ""+Double.parseDouble(productListModelDaos.get(i).getQuantity())*Double.parseDouble(productListModelDaos.get(i).getRate()));
                mOrderDeatils.add(orderDetail);
            }
            postData.add("OrderDeatils",mOrderDeatils);
            postJsonData.add("Orders",postData);
            //Log.d("postJsonData",""+postJsonData);
           // mOrderData.put("Orders",postData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postJsonData.toString());
        callPlaceOrder(mOrderData,body);

    }
    private void callPlaceOrder(LinkedHashMap<String, Object> mPostMapData,RequestBody mPostData) {
        isShowProgress(true);
        //"application/json"
        mCompositeDisposable.add(getApiCallService().app_place_order(SavePref.getInstance(getApplicationContext()).getToken(), ApiConstants.PLACE_ORDER, mPostData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void handleResponse(DataModel mObject) {
        Log.e(TAG, " res: " + mObject);
        isShowProgress(false);
        //Gson gson = new Gson();
       // String jsonOutput = mObject.toString();
        //JSONParser parser = new JSONParser();
        //JSONObject json = (JSONObject) parser.parse(stringToParse);
       /* Type listType = new TypeToken<DataModel>() {
        }.getType();*/
      //  DataModel responseData = gson.fromJson(jsonOutput, DataModel.class);

        if(mObject.getStatus().equals("success"))
        {
            deleteDataFromDb(getId(mStockistModel.getClientID()));
            deletecartItems(productListModelDaos);
            Intent login =new Intent(ConfirmOrderActivity.this,HomeActivity.class);
            startActivity(login);
        }
        else
        {
            Toast.makeText(this, mObject.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    private void deleteDataFromDb(Long stockistID)
    {
        orderTableMasterDao.deleteByKey(stockistID);
        //orderDetailTableDao.deleteAll();
    }

    public Long getId(String stockistID) {
        QueryBuilder<OrderTableMaster> qb = orderTableMasterDao.queryBuilder();
        QueryBuilder<OrderTableMaster> where = qb.where(OrderTableMasterDao.Properties.Stockiest_id.eq(stockistID));
        if(where.list().size()>0)
            return where.list().get(0).getId();
        else
            return null;
    }
    public void deletecartItems(List<OrderDetailTable> productListModelDaos) {
        if(orderDetailTableDao.loadAll().size()>0)
        {
            orderDetailTableDao.deleteInTx(productListModelDaos);
        }
    }
}
