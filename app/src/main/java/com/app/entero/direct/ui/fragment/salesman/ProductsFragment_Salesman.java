package com.app.entero.direct.ui.fragment.salesman;

import android.app.Dialog;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.activity.main.ChemistLoginActivity;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.activity.main.SplashActivity;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.ui.activity.salesman.Product_Search_Activity_Salesman;
import com.app.entero.direct.ui.activity.salesman.TakeOrderActivity_Salesman;
import com.app.entero.direct.ui.adapter.salesman.Products_Adapter_Salesman;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class ProductsFragment_Salesman extends Fragment {
    private MainActivity activity;
    private BaseActivity baseActivity;
    ArrayList<ProductsModel> allProductList = new ArrayList<ProductsModel>();
    public static Products_Adapter_Salesman adapterProducts;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    SearchView searchView;
    public static View.OnClickListener productListOnClickListener;
    BottomSheetBehavior behavior;
    ImageView closeProductDiscription;
    TextView txtPrdctName, txtPrdctId, txtPact, txtMfg, txtProductMrp, txtSaleRate, txtProductCount;
    LinkedHashMap<String, String> hashMap;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        activity.initObjects();
        baseActivity = (BaseActivity) getActivity();
        setHasOptionsMenu(true);
        mContext = getActivity();
        //baseActivity.initProductObjects();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salesman_fragment_products, container, false);
        setHasOptionsMenu(true);
        //       Toast.makeText(getContext(), "Toast", Toast.LENGTH_LONG).show();
        activity = ((MainActivity) getActivity());
        activity.initObjects();
        initview(view);

       /* activity.imgFilter.setVisibility(View.VISIBLE);
        activity.imgSearch.setVisibility(View.VISIBLE);*/
        setOnClick();


        View bottomSheet = view.findViewById(R.id.lnrBehavior);
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

        return view;

    }

    private void setOnClick() {
        productListOnClickListener = new MyOnClickListener(this);
        /*activity.imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_Filter_dialog();
            }
        });*/
        closeProductDiscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

    }

    private void initview(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyAllProducts);
        txtProductCount = (TextView) view.findViewById(R.id.txtProductCount);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        hashMap = new LinkedHashMap<>();
        hashMap.put(Constants.StockistID, "1");
        hashMap.put(Constants.legendType, "2");

        if (baseActivity.isNetworkAvailable()) {

            baseActivity.isShowProgress(true);

                callProductListApi(ApiConstants.Get_productList, hashMap);

        } else {
            Toast.makeText(mContext, "No internet connection available", Toast.LENGTH_SHORT).show();
        }

        //Product Description View
        closeProductDiscription = (ImageView) view.findViewById(R.id.ingCross);
        txtPrdctName = (TextView) view.findViewById(R.id.txtPrdctName);
        txtPrdctId = (TextView) view.findViewById(R.id.txtPrdctId);
        txtPact = (TextView) view.findViewById(R.id.txtPact);
        txtMfg = (TextView) view.findViewById(R.id.txtMfg);
        txtProductMrp = (TextView) view.findViewById(R.id.txtProductMrp);
        txtSaleRate = (TextView) view.findViewById(R.id.txtSaleRate);

    }

    private void callProductListApi(String url, LinkedHashMap<String, String> hashMap) {

        baseActivity.isShowProgress(true);
        baseActivity.mCompositeDisposable.add(baseActivity.getApiCallService().getProductList(SavePref.getInstance(getContext()).getToken(), url, hashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));


    }

    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        baseActivity.isShowProgress(false);
    }

    private void handleResponse(ProductsModel productsModel) {

        baseActivity.isShowProgress(false);
        if (productsModel.getStatus().equals("success")) {
            for (int i = 0; i < productsModel.getProductList().size(); i++) {
                allProductList.add(productsModel.getProductList().get(i));
            }

            fetchProductList();
            txtProductCount.setText(allProductList.size()+"");

            Toast.makeText(mContext, allProductList.size()+ "Products", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(mContext, productsModel.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }




    // search and filter menu

    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {

        inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        activity.imgToolbar.setVisibility(View.GONE);
        activity.txtToolbar.setVisibility(View.VISIBLE);
        activity.txtToolbar.setText("Products");
        MenuItem action_search = menu.findItem(R.id.action_search);
        MenuItem action_filter = menu.findItem(R.id.action_filter);
        action_search.setVisible(true);
        action_filter.setVisible(true);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Product");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fetchProductList();
                    return false;
                }
                adapterProducts.getFilter().filter(newText.toLowerCase());
                //setDataToRecyclerView();
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
        action_filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                show_Filter_dialog();
                return false;
            }
        });
    }

    private void fetchProductList() {
        adapterProducts = new Products_Adapter_Salesman(allProductList);
        recyclerView.setAdapter(adapterProducts);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:

                return true;

            case R.id.action_filter:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void show_Filter_dialog() {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View dialogview = inflater.inflate(R.layout.salesman_product_filter, null);
        final Dialog infoDialog = new Dialog(mContext);//builder.create();
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infoDialog.setContentView(dialogview);
        TextView txtProduct = (TextView) dialogview.findViewById(R.id.btnSrchPrdct);
        TextView txtManuhacture = (TextView) dialogview.findViewById(R.id.btnSrchMnfact);
        TextView txtClear = (TextView) dialogview.findViewById(R.id.txtClear);
        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, Product_Search_Activity_Salesman.class);
                i.putExtra("search", "Product");
                i.putExtra("productSearch",allProductList);
                infoDialog.dismiss();
                startActivity(i);
            }
        });
        txtManuhacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, Product_Search_Activity_Salesman.class);
                i.putExtra("search", "Manufacture");
                infoDialog.dismiss();
                startActivity(i);
            }
        });

        txtClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog.dismiss();
            }
        });


        set_attributes(infoDialog);
        infoDialog.show();
    }

    private void set_attributes(Dialog dlg) {

        Window window = dlg.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        Display mdisp = getActivity().getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);

        int[] textSizeAttr = new int[]{android.R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedValue typedValue = new TypedValue();
        TypedArray a = mContext.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionbarsize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        int maxX = mdispSize.x;
        wlp.gravity = Gravity.TOP | Gravity.LEFT;
        wlp.x = maxX;   //x position
        wlp.y = actionbarsize - 20;   //y position
        window.setAttributes(wlp);

    }

    private class MyOnClickListener implements View.OnClickListener {

        private final ProductsFragment_Salesman context;

        private MyOnClickListener(ProductsFragment_Salesman context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //removeItem(v);
            int selectedItemPosition = recyclerView.getChildPosition(v);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            txtPrdctName.setText(Products_Adapter_Salesman.data.get(selectedItemPosition).getItemname());
            txtPrdctId.setText(Products_Adapter_Salesman.data.get(selectedItemPosition).getProductID());
            txtPact.setText(Products_Adapter_Salesman.data.get(selectedItemPosition).getPacksize());
            txtMfg.setText(Products_Adapter_Salesman.data.get(selectedItemPosition).getMfgName());
            txtProductMrp.setText(Products_Adapter_Salesman.data.get(selectedItemPosition).getMrp() + "");
            txtSaleRate.setText(Products_Adapter_Salesman.data.get(selectedItemPosition).getRate() + "");

        }

    }
}
