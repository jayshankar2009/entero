package com.app.entero.direct.ui.fragment.salesman;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.adapter.salesman.CustomerOrdersListAdapter_Salesman;

public class CustomerOrdersFragment_Salesman extends Fragment {

    private static RecyclerView.Adapter adapter_all_order;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_all_order_list;
    private static ArrayList<Outstandings> allCustomerData;
    public static View.OnClickListener customerListOnClickListener;
    View rootView;

    public CustomerOrdersFragment_Salesman() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.salesman_customer_list_tab_orders, container, false);

        initView(rootView);

        recycler_view_all_order_list.setHasFixedSize(true);
        recycler_view_all_order_list.setLayoutManager(layoutManager);
        recycler_view_all_order_list.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            allCustomerData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }

        adapter_all_order = new CustomerOrdersListAdapter_Salesman(allCustomerData);
        recycler_view_all_order_list.setAdapter(adapter_all_order);

        return rootView;
    }

    private void initView(View rootView) {
        recycler_view_all_order_list = (RecyclerView) rootView.findViewById(R.id.recycler_view_all_order_list);
        allCustomerData = new ArrayList<Outstandings>();
        customerListOnClickListener = new MyOnClickListener(getActivity());
        layoutManager = new LinearLayoutManager(getContext());
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //removeItem(v);
           /* int selectedItemPosition = recycler_view_all_order_list.getChildPosition(v);
            Intent in = new Intent(context, OrderDetailsActivity_Saleesman.class);
            startActivity(in);*/
        }

    }
}
