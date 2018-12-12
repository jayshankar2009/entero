package com.app.entero.direct.ui.activity.chemist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.DaoSession;
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

import org.greenrobot.greendao.query.DeleteQuery;
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
    String screen,chmstErp,cmstId;
    Bundle bundle;
    TextView text_cart_count_take_order;


    private StockistModel mStockistModel;
    private OrderDetailTableDao orderDetailTableDao;
    private OrderTableMasterDao orderTableMasterDao;
    private List<OrderDetailTable> productListModelDaos;
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
        bundle = getIntent().getExtras();
        screen = bundle.getString(Constants.screen);
        mStockistModel = new StockistModel();
        productListModelDaos = new ArrayList<>();
        orderDetailTableDao = ((EnteroApp) getApplication()).getDaoSession().getOrderDetailTableDao();
        orderTableMasterDao = ((EnteroApp) getApplication()).getDaoSession().getOrderTableMasterDao();
        mStockistModel = new StockistModel();
        Intent i = getIntent();
        if(screen.equals("Chemist")) {
            if (i.hasExtra(Constants.STOCKISTDATA)) {
                mStockistModel = (StockistModel) i.getSerializableExtra(Constants.STOCKISTDATA);
            }
            productListModelDaos = cartItems(getDocId(mStockistModel.getClientID()));
        } else if (screen.equals("Salesman")) {
            chmstErp= bundle.getString(Constants.cmstErp);
            cmstId=bundle.getString(Constants.cmstId);
            productListModelDaos=cartItems(getDocId(chmstErp));
        }
        confirm_linear = (LinearLayout) findViewById(R.id.confirm_linear);
        cancel_linear = (LinearLayout) findViewById(R.id.cancel_linear);
        mProductList = new ArrayList<>();
        text_cart_count_take_order=(TextView)findViewById(R.id.text_cart_count_take_order);
        rv_offers = (RecyclerView) findViewById(R.id.rv_navigation);
        text_name_take_order = (CustomTextView_Salesman) findViewById(R.id.text_name_take_order);
        relativemain = (RelativeLayout) findViewById(R.id.relative);
        text_name_take_order.setText(mStockistModel.getClient_LegalName());
        rv_offers.setLayoutManager(new LinearLayoutManager(this));
        if(productListModelDaos!=null) {
            text_cart_count_take_order.setText(String.valueOf(productListModelDaos.size()));
            mViewCartAdapter = new ViewCartAdapter(this, this, productListModelDaos);

        } else
        {
            productListModelDaos = new ArrayList<>();
            text_cart_count_take_order.setText("0");
            mViewCartAdapter = new ViewCartAdapter(this, this, productListModelDaos);
        }
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
      //  rv_offers.notify();
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
            deleteCartItem(productListModelDaos.get(position).getId());
            mViewCartAdapter.removeItem(viewHolder.getAdapterPosition());
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
            simpleAlert();
        }
        if(view == confirm_linear)
        {
            if(productListModelDaos!=null && productListModelDaos.size()>0)
            {
                Intent mIntent = new Intent(ViewCartActivityChemist.this, ConfirmOrderActivity.class);
              mIntent.putExtra(Constants.screen,screen);
                if(screen.equals("Salesman")) {
                    Log.i("Salesman",""+chmstErp);
                    mIntent.putExtra(Constants.cmstErp, chmstErp);
                    mIntent.putExtra(Constants.cmstId,cmstId);
                }else if(screen.equals("Chemist")){
                    mIntent.putExtra(Constants.STOCKISTDATA,mStockistModel);
                }

                mIntent.putExtra(Constants.STOCKISTDATA,mStockistModel);
                startActivity(mIntent);
                finish();
            }

        }

    }

    public void deleteCartItem(Long stockistID) {
        orderDetailTableDao.deleteByKey(stockistID);
    }

    public void addItem(OrderDetailTable deletedItem) {
        orderDetailTableDao.insert( new  OrderDetailTable(deletedItem.getId(),
                mStockistModel.getClientID(),
                deletedItem.getDoc_no(),
                deletedItem.getProduct_ID(),
                deletedItem.getItemcode(),
                deletedItem.getItemname(), deletedItem.getMrp(),
                deletedItem.getRate(), deletedItem.getStock(),
                deletedItem.getMfgCode(),  deletedItem.getMfgName(),
                deletedItem.getImage_path(), deletedItem.getPacksize(),
                deletedItem.getScheme(),
                deletedItem.getPercentScheme(), deletedItem.getLegendMode(),
                deletedItem.getColorCode(),
                deletedItem.getHalfScheme(), deletedItem.getMinQty(),
                deletedItem.getMaxQty(), deletedItem.getBoxSize(),deletedItem.getQuantity(),deletedItem.getStk_id()));

    }


    public void simpleAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete items");
        builder.setMessage("Are you realy want to clear the cart!");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(screen.equals("Chemist")) {
                            deleteDataFromDb(getId(mStockistModel.getClientID()));
                        }else if(screen.equals("Salesman")){
                            Log.i("Salesman",""+chmstErp);
                            deleteDataFromDb(getId(chmstErp));
                        }
                        deletecartItems(productListModelDaos);
                        dialog.dismiss();
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void deleteDataFromDb(Long stockistID)
    {
        Log.i("Salesman1",""+stockistID);
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


    public List<OrderDetailTable> cartItems(String docno) {
        if(orderDetailTableDao.loadAll().size()>0)
        {
            QueryBuilder<OrderDetailTable> qb = orderDetailTableDao.queryBuilder();
            QueryBuilder<OrderDetailTable> where = qb.where(OrderDetailTableDao.Properties.Doc_no.eq(docno));
            if(where.list().size()>0)
                return where.list();
            else
                return null;
        }
        return null;

    }
    public void deletecartItems(List<OrderDetailTable> productListModelDaos) {
        if(orderDetailTableDao.loadAll().size()>0)
        {
            orderDetailTableDao.deleteInTx(productListModelDaos);
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
     /*  public void orderMaster(String stokist) {
           DaoSession daoSession;

           final DeleteQuery<OrderTableMaster> tableDeleteQuery = daoSession.queryBuilder(Table.class)
                   .where(TableDao.Properties.Name.eq("Value"))
                   .buildDelete();
           tableDeleteQuery.executeDeleteWithoutDetachingEntities();
           daoSession.clear();
         }*/

}