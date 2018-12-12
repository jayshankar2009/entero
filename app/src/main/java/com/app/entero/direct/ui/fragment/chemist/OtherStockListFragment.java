package com.app.entero.direct.ui.fragment.chemist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.StockListModel;
import com.app.entero.direct.model.StockistModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.HomeActivity;
import com.app.entero.direct.ui.adapter.chemist.OtherStockListAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OtherStockListFragment extends Fragment implements OnItemRecycleClickListener {

    private RecyclerView rv_stocklist;
    private ArrayList<StockListModel> mOfferList;
    private Context mContext;
    private OtherStockListAdapter mStockListAdapter;
    private HomeActivity activity;
    private String TAG ="SelectStockListFragment";
    private TabLayout tabLayout;
    BottomSheetBehavior sheetBehavior;
    LinearLayout layoutBottomSheet;
    private StockistModel mModel;
    private ArrayList<StockistModel> stocklist;
    private LinkedHashMap<String, String> hashMap;


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
        //activity.isVisibleSearch(false);
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
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void initview(View view) {
        stocklist = new ArrayList<>();
        hashMap = new LinkedHashMap<>();
        hashMap.put(ApiConstants.ClientID,"9");
        if(activity.isNetworkAvailable())
        {
            callApi(ApiConstants.GETSTOCKISTLIST,hashMap);
        }

        rv_stocklist = (RecyclerView) view.findViewById(R.id.rv_navigation);
        rv_stocklist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mStockListAdapter = new OtherStockListAdapter(getActivity(),this,stocklist);
        rv_stocklist.setAdapter(mStockListAdapter);
        rv_stocklist.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        layoutBottomSheet = view.findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void callApi(String url, LinkedHashMap<String, String> linkedHashMap) {
        activity.isShowProgress(true);
        activity.mCompositeDisposable.add(activity.getApiCallService().getStockistList(SavePref.getInstance(getActivity()).getToken(),url, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        activity.isShowProgress(false);
    }

    private void handleResponse(StockistModel mStockListModel) {
        Log.e(TAG, " res: " + mStockListModel);
        activity.isShowProgress(false);
        this.mModel = mStockListModel;
        if(mModel.getStatus().equals("success"))
        {
            if(mModel.getEntityStockistList()!=null &&mModel.getEntityStockistList().size()>0)
                mStockListAdapter.refreshData(getmOfferList(mModel.getEntityStockistList()));

        }
        else
        {
            Toast.makeText(getActivity(),mModel.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

    }

    private  ArrayList<StockistModel> getmOfferList(ArrayList<StockistModel> mList)
    {
        ArrayList<StockistModel> filteredList = new ArrayList<>();
        for (StockistModel row : mList) {
            //.getIs_Locked().equals("Locked")
            if (row.getIs_Locked().equalsIgnoreCase("Locked")) {
                filteredList.add(row);
            }
        }
        return filteredList;
    }

}
