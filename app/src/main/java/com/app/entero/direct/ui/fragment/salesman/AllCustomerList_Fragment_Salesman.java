package com.app.entero.direct.ui.fragment.salesman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.app.entero.direct.R;
import com.app.entero.direct.model.CustomerListModel;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.model.Salesman_CustomerList_Model;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.activity.salesman.CustomerListDetailsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.ui.adapter.salesman.AllCustomerList_Adapter_Salesman;
import com.app.entero.direct.ui.adapter.salesman.Products_Adapter_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllCustomerList_Fragment_Salesman extends Fragment implements OnItemRecycleClickListener {
    private MainActivity activity;
    ArrayList<CustomerListModel> arrCustomerList;
    RecyclerView recyclerView;
    BaseActivity baseActivity;

    LinkedHashMap<String, String> linkRequest;
    RecyclerView.LayoutManager layoutManager;
    String strSalesmanId, strStockisId;
    public static ArrayList<Salesman_CustomerList_Model> listCustomer;
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
        baseActivity=((BaseActivity) getActivity());
        activity.initObjects();
        initview(view);
        strStockisId = SavePref.getInstance(getContext()).getUserDetail().getSalesmanInfo().get(0).getStockistID();
        strSalesmanId = SavePref.getInstance(getContext()).getUserDetail().getSalesmanInfo().get(0).getID();
        linkRequest = new LinkedHashMap<>();
        linkRequest.put(Constants.StockistID, "1");
        linkRequest.put(Constants.SalesmanID, "2");
        if(baseActivity.isNetworkAvailable()) {
            callCustomerList(ApiConstants.Get_Salesman_CustomerList,linkRequest);
        }
     //   setOnClick();
        return view;

    }

    private void callCustomerList(String url, LinkedHashMap<String, String> linkRequest) {
        baseActivity.isShowProgress(true);
        baseActivity.mCompositeDisposable.add(baseActivity.getApiCallService().getSalesmanCustomerList(SavePref.getInstance(getContext()).getToken(), url, linkRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleCustomerResponse, this::handleCustomerError));
    }
private void handleCustomerError(Throwable throwable) {
    Log.e("CustomerList", " error: " + throwable.getMessage());
    baseActivity.isShowProgress(false);
}
    private void handleCustomerResponse(Salesman_CustomerList_Model salesman_customerList_model) {
        listCustomer = new ArrayList<>();
        baseActivity.isShowProgress(false);
        if(salesman_customerList_model.getStatus().equals("success")) {
            if(salesman_customerList_model.getMessage().equals("Record found")){
            if(salesman_customerList_model.getEntityChemistList().size()>0) {
                for(int i =0 ;i<salesman_customerList_model.getEntityChemistList().size();i++){
                    listCustomer.add(salesman_customerList_model.getEntityChemistList().get(i));

                }
                allCustomerList_adapter_salesman = new AllCustomerList_Adapter_Salesman(getActivity(),this, listCustomer);
                recyclerView.setAdapter(allCustomerList_adapter_salesman);
            }
            }
        }
    }


    private void initview(View view) {
           recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_customer_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }


    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(getContext(), CustomerListDetailsActivity_Salesman.class);
        i.putExtra("position",position);
      //  i.putExtra("custList",listCustomer.get(position));

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(i);

    }
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {

        inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        activity.imgToolbar.setVisibility(View.GONE);
        activity.txtToolbar.setVisibility(View.VISIBLE);
        activity.txtToolbar.setText("Customer List");
        MenuItem action_search = menu.findItem(R.id.action_search);
        MenuItem action_filter = menu.findItem(R.id.action_filter);
        action_search.setVisible(true);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Customer List");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

            //    adapterProducts.getFilter().filter(newText.toLowerCase());
                //setDataToRecyclerView();
                allCustomerList_adapter_salesman.getFilter().filter(newText.toLowerCase());
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


}
