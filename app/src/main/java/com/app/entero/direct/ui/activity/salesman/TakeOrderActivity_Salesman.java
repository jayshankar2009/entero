package com.app.entero.direct.ui.activity.salesman;

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

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.TakeOrderCustomAdapter_Salesman;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;


public class TakeOrderActivity_Salesman extends BaseActivity implements View.OnClickListener {
    Toolbar mToolbar;
    SearchView searchView;
    RelativeLayout rltMain;
    LinearLayout lnrBottom;
    LinearLayout ly_indicater,ly_indicator_arrow_take_order;
    RelativeLayout ry_cart_take_order;
    TextView txtHeader,text_products_count_take_order,text_name_take_order,text_cart_count_take_order,
            text_take_order_medicine_name;
    EditText text_take_order_count;
    private static RecyclerView.Adapter adapter_take_order;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_take_order;
    private static ArrayList<Outstandings> takeOrderData;
    public static View.OnClickListener takeOrderOnClickListener;
    CoordinatorLayout coordinatorLayout;
    BottomSheetBehavior behavior;
    ImageView take_order_drawer_close_img;
    Button btn_take_order_add_minus,btn_take_order_add_plus,btn_take_order_add_to_cart;
    int itemCount=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeorder_chemist);

        //Toolbar with search and filter
        initView();
        setToolbar();
        onSetText();
           onClickEvent();
          // outstandings bill list
        recycler_view_take_order.setHasFixedSize(true);

        recycler_view_take_order.setLayoutManager(layoutManager);
        recycler_view_take_order.setItemAnimator(new DefaultItemAnimator());




        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

        ViewTreeObserver vto = rltMain.getViewTreeObserver();
        //   final View content = activity.findViewById(android.R.id.content).getRootView();
        //get_stockist_legends();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rltMain.getWindowVisibleDisplayFrame(r);
                int screenHeight = rltMain.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                //  Log.d(TAG, "keypadHeight = " + keypadHeight);

                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                //    lnrBottom.setVisibility(View.GONE);
                } else {
                    // keyboard is closed
                   // lnrBottom.setVisibility(View.VISIBLE);
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

        // Back to previous activity
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
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

    }

    private void setDataToRecyclerView() {
        takeOrderData = new ArrayList<Outstandings>();
        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            takeOrderData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }
        adapter_take_order = new TakeOrderCustomAdapter_Salesman(takeOrderData);
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
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                //int productQty = Integer.parseInt(text_take_order_count.getText()+"");
                break;

        }
    }

    // search and filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        MenuItem action_search = menu.findItem(R.id.action_search);
        action_search.setVisible(true);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Order ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                setDataToRecyclerView();
                lnrBottom.setVisibility(View.VISIBLE);
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
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }

}
