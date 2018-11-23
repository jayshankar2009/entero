package com.app.entero.direct.ui.fragment.chemist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.entero.direct.R;
import com.app.entero.direct.model.StockListModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.adapter.chemist.StockListAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StockListFragment extends Fragment implements OnItemRecycleClickListener {

    private RecyclerView rv_stocklist;
    private ArrayList<StockListModel> mOfferList;
    private Context mContext;
    private StockListAdapter mStockListAdapter;
    private HomeActivity activity;
    private String TAG ="SelectStockistFragment";
    public ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (HomeActivity) getActivity();
        activity.initObjects();
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.isVisible(false);
        activity.isVisibleAdd(false);
        activity.isVisibleSearch(true);
        activity.isVisibleAddTocart(false);
        activity.isVisibleFilter(false);
        activity.setTitle("Stockist List");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stocklist, container, false);
        activity = ((HomeActivity) getActivity());
        activity.initObjects();
        initview(view);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void initview(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
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



    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());
        MyStockListFragment fragment = new MyStockListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "My Stockists");
        OtherStockListFragment fragment1 = new OtherStockListFragment();
        bundle = new Bundle();
        bundle.putInt("type", 2);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment1, "Other Stockists");
        viewPager.setAdapter(adapter);
    }


    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    private ArrayList<StockListModel> setOfferListData() {

        StockListModel model = new StockListModel();
        model.setStockListquantitly("23");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListId("12354");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("5");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListId("1127");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);


        model = new StockListModel();
        model.setStockListquantitly("33");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListId("1425");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);


        model = new StockListModel();
        model.setStockListquantitly("41");
        model.setStockListName("Visu Pharma Enterprise");
        model.setStockListId("3032");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("51");
        model.setStockListName("Dexorange Syrup");
        model.setStockListId("1004");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("15");
        model.setStockListName("Nerry syrup");
        model.setStockListId("2004");
        model.setImg("");
        mOfferList.add(model);

        model = new StockListModel();
        model.setStockListquantitly("23");
        model.setStockListName("Disprin pain relief Tablet");
        model.setStockListId("115");
        model.setImg("Crocin pain relief Tablet");
        mOfferList.add(model);

        return mOfferList;
    }


}
