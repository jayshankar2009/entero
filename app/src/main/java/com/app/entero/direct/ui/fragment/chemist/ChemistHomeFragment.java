package com.app.entero.direct.ui.fragment.chemist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.chemist.OffersActivity;
import com.app.entero.direct.ui.activity.chemist.TakeOrderActivity_Chemist;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.activity.salesman.Customer_TastActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.DeliveryActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.TakeOrderActivity_Salesman;
import com.app.entero.direct.ui.adapter.chemist.MyCustomePager;
import com.app.entero.direct.ui.adapter.chemist.OffersAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;
import com.app.entero.direct.viewpager.WCViewPagerIndicator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ChemistHomeFragment extends Fragment implements View.OnClickListener, OnItemRecycleClickListener {

    private HomeActivity activity;
    private String TAG = "ChemistHomeFragment";
    private int image[] = {R.drawable.banner, R.drawable.banner, R.drawable.banner};
    private ArrayList<OffersModel> mOfferList = new ArrayList<>();
    private RecyclerView popular_recyclerView;
    private CustomTextView_Salesman view_all;
    private RelativeLayout toplayout;
    WCViewPagerIndicator wcViewPagerIndicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (HomeActivity) getActivity();
        activity.initObjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chemist_home, container, false);
        activity = ((HomeActivity) getActivity());
        activity.initObjects();
        initview(view);
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private ArrayList<OffersModel> setOfferListData() {

        OffersModel model = new OffersModel();
        model.setQuantitly("4");
        model.setTabName("Crocin pain relief Tablet");
        model.setTabDes("1 Strip of 10 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("5");
        model.setTabName("Uprise D30 100k Capsule");
        model.setTabDes("1 Strip of 12 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);


        model = new OffersModel();
        model.setQuantitly("3");
        model.setTabName("Disprin pain relief Tablet");
        model.setTabDes("1 Strip of 20 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);


        model = new OffersModel();
        model.setQuantitly("4");
        model.setTabName("Crocin pain relief Tablet");
        model.setTabDes("1 Strip of 30 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("8");
        model.setTabName("Dexorange Syrup");
        model.setTabDes("100 ml bottle");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("9");
        model.setTabName("Nerry syrup");
        model.setTabDes("200 ml bottle");
        model.setImg("");
        mOfferList.add(model);

        model = new OffersModel();
        model.setQuantitly("2");
        model.setTabName("Disprin pain relief Tablet");
        model.setTabDes("1 Strip of 15 tablets");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        return mOfferList;
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.setTitle("");
        activity.isVisible(true);
        activity.isVisibleAdd(false);
        activity.isVisibleSearch(false);
        activity.isVisibleAddTocart(true);
        activity.isVisibleFilter(false);
        Log.e("onResumechem","onPause");
        if(wcViewPagerIndicator!=null)
        {
            wcViewPagerIndicator.setSelectedindicator(wcViewPagerIndicator.getViewPager().getCurrentItem());
        }
        }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPausechem","onPause");
    }

    public void initview(View view) {
        wcViewPagerIndicator = (WCViewPagerIndicator) view.findViewById(R.id.wcviewpager);
        MyCustomePager viewPagerAdapter = new MyCustomePager(activity, image);
        wcViewPagerIndicator.setAdapter(viewPagerAdapter);
        wcViewPagerIndicator.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                wcViewPagerIndicator.setSelectedindicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        toplayout = (RelativeLayout) view.findViewById(R.id.toplayout);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int height = (display.getHeight());
        RelativeLayout.LayoutParams layout_description = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                (int) (height / 3));
        //toplayout.setLayoutParams(layout_description);

        popular_recyclerView = (RecyclerView) view.findViewById(R.id.popular_recyclerView);
        popular_recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        popular_recyclerView.setAdapter(new OffersAdapter(activity, this, setOfferListData()));
        popular_recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        view_all = (CustomTextView_Salesman) view.findViewById(R.id.view_all);
        activity.addToCart.setOnClickListener(this);
        inItListener();

    }

    private void inItListener() {
        view_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == view_all)
        {
            Intent mIntent = new Intent(getActivity(), OffersActivity.class);
            startActivity(mIntent);
        }
        if(view == activity.addToCart)
        {
            Intent i4 = new Intent(getActivity(),TakeOrderActivity_Chemist.class);
            startActivity(i4);
        }

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
}
