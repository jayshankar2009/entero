package com.app.entero.direct.ui.fragment.chemist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.entero.direct.R;
import com.app.entero.direct.model.StockListModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.adapter.chemist.MyStockListAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyStockListFragment extends Fragment implements OnItemRecycleClickListener {

    private RecyclerView rv_stocklist;
    private ArrayList<StockListModel> mOfferList;
    private Context mContext;
    private MyStockListAdapter mStockListAdapter;
    private HomeActivity activity;
    private String TAG ="SelectStockListFragment";
    private TabLayout tabLayout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (HomeActivity) getActivity();
        activity.initObjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stocklist, container, false);
        activity = ((HomeActivity) getActivity());
        activity.initObjects();
        initview(view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        activity.isVisible(false);
        activity.isVisibleAdd(false);
        activity.isVisibleAddTocart(false);
        activity.isVisibleFilter(false);
        //activity.isVisibleSearch(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void initview(View view) {
        mOfferList = new ArrayList<>();
        mOfferList = setOfferListData();
        rv_stocklist = (RecyclerView) view.findViewById(R.id.rv_navigation);
        rv_stocklist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mStockListAdapter = new MyStockListAdapter(getActivity(),this,mOfferList);
        rv_stocklist.setAdapter(mStockListAdapter);
        rv_stocklist.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));



    }

    public void callApi(LinkedHashMap<String, String> linkedHashMap) {
        activity.mCompositeDisposable.add(activity.getApiCallService().getHomeData(ApiConstants.type, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }



    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        activity.isShowProgress(false);
    }

    private void handleResponse(Object mObject) {


    }

    @Override
    public void onItemClick(View view, int position) {


    }


    private ArrayList<StockListModel> setOfferListData() {

        StockListModel model = new StockListModel();
        model.setStockListquantitly("23");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListPrice("12354");
        model.setStockListAddress("Malad");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("5");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListPrice("1127");
        model.setStockListAddress("Agipada");
        mOfferList.add(model);


        model = new StockListModel();
        model.setStockListquantitly("33");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListPrice("1425");
        model.setStockListAddress("Agipada");
        mOfferList.add(model);


        model = new StockListModel();
        model.setStockListquantitly("41");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListPrice("3032");
        model.setStockListAddress("Goregaon");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("51");
        model.setStockListName("New Life Enterprise");
        model.setStockListPrice("1004");
        model.setStockListAddress("Malad");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("15");
        model.setStockListName("Golden Healthcare");
        model.setStockListPrice("2004");
        model.setStockListAddress("Agipada");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("23");
        model.setStockListName("New Life Enterprise");
        model.setStockListPrice("115");
        model.setStockListAddress("Goregaon");
        mOfferList.add(model);

        return mOfferList;
    }
}
