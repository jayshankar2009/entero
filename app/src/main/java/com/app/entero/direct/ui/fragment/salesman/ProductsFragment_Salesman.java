package com.app.entero.direct.ui.fragment.salesman;

import android.app.Dialog;
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

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.ui.activity.salesman.Product_Search_Activity_Salesman;
import com.app.entero.direct.ui.activity.salesman.TakeOrderActivity_Salesman;
import com.app.entero.direct.ui.adapter.salesman.Products_Adapter_Salesman;

public class ProductsFragment_Salesman extends Fragment {
    private MainActivity activity;
    ArrayList<ProductsModel> arrProduct;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Products_Adapter_Salesman adapterProducts;
    SearchView searchView;
    public static View.OnClickListener productListOnClickListener;
    BottomSheetBehavior behavior;
    ImageView closeProductDiscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        activity.initObjects();

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
        activity.imgToolbar.setVisibility(View.GONE);
        activity.txtToolbar.setVisibility(View.VISIBLE);
        activity.txtToolbar.setText("Products");
        activity.imgFilter.setVisibility(View.VISIBLE);
        activity.imgSearch.setVisibility(View.VISIBLE);
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
        activity.imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_Filter_dialog();
            }
        });
        closeProductDiscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

    }

    private void initview(View view) {
        arrProduct = new ArrayList<>();
        arrProduct.add(new ProductsModel("Sheical - 500mg Tablet", "8"));
        arrProduct.add(new ProductsModel("Accu Check Active Strip", "3"));
        arrProduct.add(new ProductsModel("Uprise D3 60K Capsule", "4"));
        arrProduct.add(new ProductsModel("Hairbless Tablet", "10"));
        arrProduct.add(new ProductsModel("Folvite 5mg Tablet", "82"));
        arrProduct.add(new ProductsModel("New Follihair Tablet", "33"));
        arrProduct.add(new ProductsModel("Zincovit Tablet", "10"));
        arrProduct.add(new ProductsModel("A to Z NS Tablet", "10"));
        recyclerView = (RecyclerView) view.findViewById(R.id.recyAllProducts);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterProducts = new Products_Adapter_Salesman(getActivity(), arrProduct);
        recyclerView.setAdapter(adapterProducts);
        closeProductDiscription = (ImageView) view.findViewById(R.id.ingCross);


    }



    private void show_Filter_dialog() {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View dialogview = inflater.inflate(R.layout.salesman_product_filter, null);
        final Dialog infoDialog = new Dialog(getActivity());//builder.create();
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infoDialog.setContentView(dialogview);
        TextView txtProduct = (TextView)dialogview.findViewById(R.id.btnSrchPrdct);
        TextView txtManuhacture = (TextView)dialogview.findViewById(R.id.btnSrchMnfact);
        TextView txtClear = (TextView)dialogview.findViewById(R.id.txtClear);
        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Product_Search_Activity_Salesman.class);
                i.putExtra("search","Product");
                infoDialog.dismiss();
                startActivity(i);
            }
        });
        txtManuhacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Product_Search_Activity_Salesman.class);
                i.putExtra("search","Manufacture");
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
        TypedArray a = getActivity().obtainStyledAttributes(typedValue.data, textSizeAttr);
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
        }

    }
}
