package com.app.entero.direct.ui.activity.chemist;

import android.app.SearchManager;
import android.app.SearchableInfo;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.OrderDetailTable;
import com.app.entero.direct.database.models.OrderDetailTableDao;
import com.app.entero.direct.database.models.OrderTableMaster;
import com.app.entero.direct.database.models.OrderTableMasterDao;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.adapter.chemist.ProductsAdapter;
import com.app.entero.direct.ui.listener.AddCartOnItemRecycleClickListener;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ProductsActivity extends BaseActivity implements View.OnClickListener, AddCartOnItemRecycleClickListener {

    private static final String TAG = ProductsActivity.class.getName();
    private Toolbar toolbar;
    private CustomTextView_Salesman tv_title;
    private RecyclerView rv_offers;
    private ProductsAdapter mProductsAdapter;
    private ArrayList<ProductListModel> mProductList;
    private Context mContext;
    private SearchView searchView;
    String screen,chmstErp,cmstId;
    TextView tab_Tv;
    ProductListModel mSelectedProductList;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout layoutBottomSheet;
    private StockistModel mStockistModel;
    OrderDetailTableDao orderListModelDao;
    OrderTableMasterDao orderTableMasterDao;
    int itemCount=1;
    Bundle bundle;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
    private ProductListModel mStockListModelData;
    String strCrntDate;
    private ImageView img_cross,img_viewcart;
    private CustomTextView_Salesman item_name_tv, product_id_tv, sale_rate_tv, mrp_tv, mfg_tv, pack_tv;

    private Button btn_take_order_add_minus,btn_take_order_add_plus;
    private CustomTextView_Salesman btn_take_order_add_to_cart;
    private EditText text_take_order_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_chemist);
        mContext = this;
        Log.i("Check4","Check4");
        initObjects();
        initView();
        setToolbar();

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Check3","Check3");
        strCrntDate = mdformat.format(calendar.getTime());
        Log.i("StrCrntDate---"+chmstErp,"---"+strCrntDate);
        if(screen.equals("Chemist")) {
            if (mStockistModel.getClientID() != null) {
                if (!isStockistAdded(mStockistModel.getClientID())) {
                    orderTableMasterDao.insert(new OrderTableMaster(null, mStockistModel.getClientID(),
                            "" + (String.valueOf(Math.random())).replace(".", ""), "test", strCrntDate, "no", "no"
                    ));
                }
            }
        }
        else if(screen.equals("Salesman")){
            if(!isStockistAdded(chmstErp)){
                orderTableMasterDao.insert(new OrderTableMaster(null, chmstErp,
                        "" + (String.valueOf(Math.random())).replace(".", ""), "test", strCrntDate, "yes", "no"));

            }

        }
    }

    private void initView() {
        bundle = getIntent().getExtras();
    //    if(bundle!=null) {
            screen = bundle.getString(Constants.screen);
            chmstErp = bundle.getString(Constants.cmstErp);
            cmstId=bundle.getString(Constants.cmstId);
   //     }
        mSelectedProductList = new ProductListModel();
        mStockistModel  = new StockistModel();
        Intent i = getIntent();
        if (i.hasExtra(Constants.STOCKISTDATA)) {
            mStockistModel = (StockistModel) i.getSerializableExtra(Constants.STOCKISTDATA);
        }
        orderListModelDao = ((EnteroApp) getApplication()).getDaoSession().getOrderDetailTableDao();
        orderTableMasterDao = ((EnteroApp) getApplication()).getDaoSession().getOrderTableMasterDao();

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
        tab_Tv=(TextView)findViewById(R.id.tab_Tv);
        item_name_tv = (CustomTextView_Salesman) findViewById(R.id.item_name_tv);
        product_id_tv = (CustomTextView_Salesman) findViewById(R.id.product_id_tv);
        sale_rate_tv = (CustomTextView_Salesman) findViewById(R.id.sale_rate_tv);
        mrp_tv = (CustomTextView_Salesman) findViewById(R.id.mrp_tv);
        mfg_tv = (CustomTextView_Salesman) findViewById(R.id.mfg_tv);
        pack_tv = (CustomTextView_Salesman) findViewById(R.id.pack_tv);
        img_cross = (ImageView) findViewById(R.id.img_cross);
        btn_take_order_add_minus = (Button) findViewById(R.id.btn_take_order_add_minus);
        text_take_order_count = (EditText) findViewById(R.id.text_take_order_count);
        btn_take_order_add_plus = (Button) findViewById(R.id.btn_take_order_add_plus);
        btn_take_order_add_to_cart = (CustomTextView_Salesman) findViewById(R.id.btn_take_order_add_to_cart);
        img_viewcart = (ImageView) findViewById(R.id.img_viewcart);
        rv_offers.setLayoutManager(new LinearLayoutManager(this));

          if(screen.equals("Salesman")) {
           mSelectedProductList= read(getApplicationContext(),Constants.PRODUCT_LIST);
           rv_offers.setVisibility(View.GONE);
             // ArrayList<ProductListModel> productList = mSelectedProductList.getProductList();
              Log.i("ProductList",""+mSelectedProductList.getProductList().size());
        }
       else if(screen.equals("Chemist")) {
            hashMap.put(ApiConstants.StockistID, mStockistModel.getClientID());
            hashMap.put(ApiConstants.legendType, mStockistModel.getClientTypeID());
            if (isNetworkAvailable()) {
                callApi(ApiConstants.GETPRODUCTLIST, hashMap);
            }
        }
        tab_Tv.setText(""+mSelectedProductList.getProductList().size());
        mProductsAdapter = new ProductsAdapter(this, this, mSelectedProductList.getProductList());
        rv_offers.setAdapter(mProductsAdapter);
        rv_offers.addItemDecoration(new SimpleDividerItemDecoration(this));

        inItListener();

    }

    private void inItListener() {
        img_viewcart.setOnClickListener(this);
        img_cross.setOnClickListener(this);
        btn_take_order_add_minus.setOnClickListener(this);
        btn_take_order_add_plus.setOnClickListener(this);
        btn_take_order_add_to_cart.setOnClickListener(this);
        double random = Math.random();
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
        if(screen.equals("Chemist")) {
            tv_title.setText(R.string.products);
        }else  if(screen.equals("Salesman")) {
            tv_title.setText("Take Order");
        }

    }

    @Override
    public void onItemClick(View view, int position,ProductListModel productListModel) {

      /*  if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

       */     mSelectedProductList = productListModel;
            item_name_tv.setText(mSelectedProductList.getItemname());
            product_id_tv.setText(mSelectedProductList.getProduct_ID());
            sale_rate_tv.setText(mSelectedProductList.getRate());
            mrp_tv.setText(mSelectedProductList.getMrp());
            mfg_tv.setText(mSelectedProductList.getMfgName());
            pack_tv.setText(mSelectedProductList.getPacksize());
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
       /* } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
       /* MenuItem action_search = menu.findItem(R.id.action_search);
        action_search.setVisible(true);*/
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();


     SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

        searchView.setSearchableInfo(searchableInfo);
        searchView.setIconifiedByDefault(true);

        EditText searchEditText = (EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.btn_color));
        searchEditText.setHintTextColor(getResources().getColor(R.color.btn_color));

        ImageView searchMagIcon = (ImageView)searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        //searchMagIcon.setVisibility(View.VISIBLE);
        searchMagIcon.setImageResource(R.drawable.ic_nav_search);
        searchView.setQueryHint("Search Products");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                //setDataToRecyclerView();
                //lnrBottom.setVisibility(View.VISIBLE);
                rv_offers.setVisibility(View.VISIBLE);
                mProductsAdapter.getFilter().filter(newText.toLowerCase());
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
                img_viewcart.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.VISIBLE);
                rv_offers.setVisibility(View.GONE);
               /* ly_indicator_arrow_take_order.setVisibility(View.VISIBLE);
                lnrBottom.setVisibility(View.GONE);
                recycler_view_take_order.setVisibility(View.GONE);*/
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // img_viewcart.setVisibility(View.GONE);
                tv_title.setVisibility(View.GONE);
               /* ly_indicator_arrow_take_order.setVisibility(View.GONE);
                ly_indicater.setVisibility(View.VISIBLE);
                recycler_view_take_order.setVisibility(View.VISIBLE);*/
            }
        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_viewcart:
                Intent mIntent = new Intent(ProductsActivity.this, ViewCartActivityChemist.class);
           mIntent.putExtra(Constants.screen,screen);
           //     Log.i("Salesman---",""+screen);
                if(screen.equals("Salesman")) {
                    Log.i("Salesman",""+chmstErp);
                    mIntent.putExtra(Constants.cmstErp, chmstErp);
                    mIntent.putExtra(Constants.cmstId,cmstId);
                }else if(screen.equals("Chemist")){
                    mIntent.putExtra(Constants.STOCKISTDATA,mStockistModel);
                }

                startActivity(mIntent);
                break;
            case R.id.img_cross:
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;

            case R.id.btn_take_order_add_minus:
                if(itemCount!=1) {
                    itemCount = Integer.parseInt(text_take_order_count.getText()+"")-1;
                    text_take_order_count.setText(itemCount+"");
                }
                break;

            case R.id.btn_take_order_add_plus:
                itemCount = Integer.parseInt(text_take_order_count.getText()+"")+1;
                text_take_order_count.setText(itemCount+"");
                break;

            case R.id.btn_take_order_add_to_cart:
                if(screen.equals("Chemist")) {
                    orderListModelDao.insert(
                            new OrderDetailTable(null, mStockistModel.getClientID(),
                                    getDocId(mStockistModel.getClientID()),
                                    mSelectedProductList.getProduct_ID(),
                                    mSelectedProductList.getItemcode(),
                                    mSelectedProductList.getItemname(), mSelectedProductList.getMrp(),
                                    mSelectedProductList.getRate(), mSelectedProductList.getStock(),
                                    mSelectedProductList.getMfgCode(), mSelectedProductList.getMfgName(),
                                    mSelectedProductList.getImage_path(), mSelectedProductList.getPacksize(),
                                    mSelectedProductList.getScheme(),
                                    mSelectedProductList.getPercentScheme(), mSelectedProductList.getLegendMode(),
                                    mSelectedProductList.getColorCode(),
                                    mSelectedProductList.getHalfScheme(), mSelectedProductList.getMinQty(),
                                    mSelectedProductList.getMaxQty(), mSelectedProductList.getBoxSize(), "" + itemCount, mSelectedProductList.getStk_id())
                    );
                }else if(screen.equals("Salesman")) {
                    orderListModelDao.insert(
                            new OrderDetailTable(null, chmstErp,
                                    getDocId(chmstErp),
                                    mSelectedProductList.getProduct_ID(),
                                    mSelectedProductList.getItemcode(),
                                    mSelectedProductList.getItemname(), mSelectedProductList.getMrp(),
                                    mSelectedProductList.getRate(), mSelectedProductList.getStock(),
                                    mSelectedProductList.getMfgCode(), mSelectedProductList.getMfgName(),
                                    mSelectedProductList.getImage_path(), mSelectedProductList.getPacksize(),
                                    mSelectedProductList.getScheme(),
                                    mSelectedProductList.getPercentScheme(), mSelectedProductList.getLegendMode(),
                                    mSelectedProductList.getColorCode(),
                                    mSelectedProductList.getHalfScheme(), mSelectedProductList.getMinQty(),
                                    mSelectedProductList.getMaxQty(), mSelectedProductList.getBoxSize(), "" + itemCount, mSelectedProductList.getStk_id()));

                }
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                text_take_order_count.setText("1");
                if (searchView != null) {
                    searchView.setQuery("", false);
                    searchView.clearFocus();
                    searchView.onActionViewCollapsed();
                }
                //int productQty = Integer.parseInt(text_take_order_count.getText()+"");
                break;
        }

    }

    public boolean isStockistAdded(String stockistID) {
        try {

        if(orderTableMasterDao.loadAll().size()>0)
        {
            QueryBuilder<OrderTableMaster> qb = orderTableMasterDao.queryBuilder();
            QueryBuilder<OrderTableMaster> where = qb.where(OrderTableMasterDao.Properties.Stockiest_id.eq(stockistID));
            if(where.list().size()>0)
                return true;
            else
                return false;
        }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }


    public String getDocId(String stockistID) {
        QueryBuilder<OrderTableMaster> qb = orderTableMasterDao.queryBuilder();
        QueryBuilder<OrderTableMaster> where = qb.where(OrderTableMasterDao.Properties.Stockiest_id.eq(stockistID));
        if(where.list().size()>0)
            return where.list().get(0).getDoc_no();
        else
            return null;
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Check","Check");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Check1","Check1");
    }
}
