package com.app.entero.direct.ui.activity.chemist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.adapter.chemist.TakeOrderAdapter;
import com.app.entero.direct.ui.listener.AddCartOnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class TakeOrderActivity_Chemist extends BaseActivity implements View.OnClickListener, AddCartOnItemRecycleClickListener {
    Toolbar mToolbar;
    SearchView searchView;
    RelativeLayout rltMain;
    LinearLayout lnrBottom;
    ProductListModel mProductListModel;
    private ProductListModel productModel;
    LinearLayout ly_indicater,ly_indicator_arrow_take_order;
    RelativeLayout ry_cart_take_order;
    TextView txtHeader,text_products_count_take_order,text_name_take_order,text_cart_count_take_order,
            text_take_order_medicine_name;
    EditText text_take_order_count;
    private static TakeOrderAdapter adapter_take_order;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_take_order;
    private static ArrayList<ProductListModel> takeOrderData;
    public static View.OnClickListener takeOrderOnClickListener;
    CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout layoutBottomSheet;
    ImageView take_order_drawer_close_img;
    Button btn_take_order_add_minus,btn_take_order_add_plus,btn_take_order_add_to_cart;
    int itemCount=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeorder_chemist);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();
        recycler_view_take_order.setHasFixedSize(true);
        recycler_view_take_order.setLayoutManager(layoutManager);
        recycler_view_take_order.setItemAnimator(new DefaultItemAnimator());

        View bottomSheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        ViewTreeObserver vto = rltMain.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rltMain.getWindowVisibleDisplayFrame(r);
                int screenHeight = rltMain.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                } else {
                }
            }
        });

    }

    private void onClickEvent() {
        ry_cart_take_order.setOnClickListener(this);
        takeOrderOnClickListener = new MyOnClickListener(this);
        btn_take_order_add_minus.setOnClickListener(this);
        btn_take_order_add_plus.setOnClickListener(this);
        btn_take_order_add_to_cart.setOnClickListener(this);
        take_order_drawer_close_img.setOnClickListener(this);

    }
    private void onSetText() {
        txtHeader.setText("Take Order");
    }

    private void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        mProductListModel = new ProductListModel();
        productModel = new ProductListModel();
        if(isFilePresent(this, Constants.PRODUCT_LIST))
            mProductListModel = read(this, Constants.PRODUCT_LIST);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        ly_indicater = (LinearLayout) findViewById(R.id.ly_indicater);
        ry_cart_take_order = (RelativeLayout) findViewById(R.id.ry_cart_take_order);
        ly_indicator_arrow_take_order = (LinearLayout) findViewById(R.id.ly_indicator_arrow_take_order);
        text_products_count_take_order = (TextView) findViewById(R.id.text_products_count_take_order);
        lnrBottom=(LinearLayout)findViewById(R.id.lnrBottom);
        rltMain=(RelativeLayout)findViewById(R.id.rltMain);
        text_name_take_order = (TextView) findViewById(R.id.text_name_take_order);
        text_cart_count_take_order = (TextView) findViewById(R.id.text_cart_count_take_order);
        recycler_view_take_order = (RecyclerView) findViewById(R.id.recycler_view_take_order);
        layoutManager = new LinearLayoutManager(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        text_take_order_medicine_name = (TextView) findViewById(R.id.text_take_order_medicine_name);
        btn_take_order_add_minus = (Button) findViewById(R.id.btn_take_order_add_minus);
        text_take_order_count = (EditText) findViewById(R.id.text_take_order_count);
        btn_take_order_add_plus = (Button) findViewById(R.id.btn_take_order_add_plus);
        btn_take_order_add_to_cart = (Button) findViewById(R.id.btn_take_order_add_to_cart);
        take_order_drawer_close_img = (ImageView) findViewById(R.id.take_order_drawer_close_img);
        setDataToRecyclerView();
    }

    private void setDataToRecyclerView() {
        //isFilePresent(this, Constants.PRODUCT_LIST);
        //read(this,Constants.PRODUCT_LIST);
        adapter_take_order = new TakeOrderAdapter(this,this,mProductListModel.getProductList());
        recycler_view_take_order.setAdapter(adapter_take_order);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ry_cart_take_order:
                if(text_products_count_take_order.getVisibility()== View.VISIBLE) {
                    Intent in = new Intent(this, AllOrderActivity_Salesman.class);
                    startActivity(in);
                }
                break;
            case R.id.take_order_drawer_close_img:
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
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                int productQty = Integer.parseInt(text_take_order_count.getText()+"");
                Log.d("productModel",""+productModel);
                break;

        }
    }

    // search and filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Products");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                //setDataToRecyclerView();
                //lnrBottom.setVisibility(View.VISIBLE);
                adapter_take_order.getFilter().filter(newText);
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
                ly_indicator_arrow_take_order.setVisibility(View.VISIBLE);
                lnrBottom.setVisibility(View.GONE);
                recycler_view_take_order.setVisibility(View.GONE);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ly_indicator_arrow_take_order.setVisibility(View.GONE);
                ly_indicater.setVisibility(View.VISIBLE);
                recycler_view_take_order.setVisibility(View.VISIBLE);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(View view, int position,ProductListModel productListModel) {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            this.productModel = productListModel;
            text_take_order_medicine_name.setText(productListModel.getItemname());
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

    }


    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //removeItem(v);
            int selectedItemPosition = recycler_view_take_order.getChildPosition(v);
            if(text_products_count_take_order.getVisibility() == View.GONE) {
                text_products_count_take_order.setVisibility(View.VISIBLE);
            }
            text_take_order_count.setText("1");
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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

    /*private String read(Context context, String fileName) {
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
    }*/

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
