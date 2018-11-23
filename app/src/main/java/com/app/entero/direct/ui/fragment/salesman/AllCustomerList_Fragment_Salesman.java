package com.app.entero.direct.ui.fragment.salesman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.app.entero.direct.R;
import com.app.entero.direct.model.CustomerListModel;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.ui.adapter.salesman.AllCustomerList_Adapter_Salesman;
import com.app.entero.direct.ui.adapter.salesman.Products_Adapter_Salesman;

public class AllCustomerList_Fragment_Salesman extends Fragment {
    private MainActivity activity;
    ArrayList<CustomerListModel> arrCustomerList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AllCustomerList_Adapter_Salesman allCustomerList_adapter_salesman;
    SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        activity.initObjects();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salesman_fragment_customer_list, container, false);
        setHasOptionsMenu(true);
        //       Toast.makeText(getContext(), "Toast", Toast.LENGTH_LONG).show();
        activity = ((MainActivity) getActivity());
        activity.initObjects();
        initview(view);
        activity.imgToolbar.setVisibility(View.GONE);
        activity.txtToolbar.setVisibility(View.VISIBLE);
        activity.txtToolbar.setText("Customer List");
        activity.imgFilter.setVisibility(View.GONE);
        activity.imgSearch.setVisibility(View.VISIBLE);
     //   setOnClick();
        return view;

    }



    private void initview(View view) {
        arrCustomerList = new ArrayList<>();
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        arrCustomerList.add(new CustomerListModel("5142", "Yash Medical Stores","Rs. 30779"));
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_customer_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        allCustomerList_adapter_salesman = new AllCustomerList_Adapter_Salesman(getActivity(), arrCustomerList);
        recyclerView.setAdapter(allCustomerList_adapter_salesman);


    }



}
