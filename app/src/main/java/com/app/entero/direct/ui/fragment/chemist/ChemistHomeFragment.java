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
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.SchemeListModel;
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
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;
import com.app.entero.direct.utils.Utils;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;
import com.app.entero.direct.viewpager.WCViewPagerIndicator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ChemistHomeFragment extends Fragment implements View.OnClickListener, OnItemRecycleClickListener {

    private HomeActivity activity;
    private String TAG = "ChemistHomeFragment";
    private int image[] = {R.drawable.banner, R.drawable.banner, R.drawable.banner};
    private ArrayList<SchemeListModel> mSchemeList = new ArrayList<>();
    private RecyclerView popular_recyclerView;
    private CustomTextView_Salesman view_all;
    private RelativeLayout toplayout;
    WCViewPagerIndicator wcViewPagerIndicator;
    private LinkedHashMap<String, String> hashMap;
    private LinkedHashMap<String, String> hashMapOffer;
    private SchemeListModel mModel;
    private OffersModel mOffersModel;
    private OffersAdapter mAdapterScheme;
    ArrayList<OffersModel> mOffersModelList;

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
        hashMap = new LinkedHashMap<>();
        hashMapOffer = new LinkedHashMap<>();
        mOffersModelList = new ArrayList<>();

        hashMap.put(ApiConstants.Chemist_ID,"1115");
        // hashMap.put(ApiConstants.ClientID, SavePref.getInstance(getActivity()).getUserId());
        if(activity.isNetworkAvailable())
        {
            callApi(hashMap);
        }

        hashMapOffer.put(ApiConstants.PINCODE,"400001");
        hashMapOffer.put(ApiConstants.CLIENTTYPE,""+Constants.Salesman);

        if(activity.isNetworkAvailable())
        {
            callApiForSlider(hashMapOffer);
        }

        wcViewPagerIndicator = (WCViewPagerIndicator) view.findViewById(R.id.wcviewpager);
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
        mAdapterScheme = new OffersAdapter(activity, this, mSchemeList);
        popular_recyclerView.setAdapter(mAdapterScheme);
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
        activity.mCompositeDisposable.add(activity.getApiCallService().getAPP_SchemeforChemist(SavePref.getInstance(getContext()).getToken(),ApiConstants.APP_SCHEME_FOR_CHEMIST, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    public void callApiForSlider(LinkedHashMap<String, String> linkedHashMap) {
        activity.mCompositeDisposable.add(activity.getApiCallService().getOfferList(SavePref.getInstance(getContext()).getToken(),ApiConstants.GETOFFERLIST, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleError));
    }

    private void handleResponseSlider(OffersModel offersModel) {
        Log.e(TAG, " error: " + offersModel);
        activity.isShowProgress(false);
        this.mOffersModel = offersModel;
        if(mOffersModel.getStatus().equals("success"))
        {
            MyCustomePager viewPagerAdapter = new MyCustomePager(activity, image,offersModel.getEntityOfferList());
            wcViewPagerIndicator.setAdapter(viewPagerAdapter);
        }
    }


    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        activity.isShowProgress(false);
    }

    private void handleResponse(SchemeListModel mSchemeListModel) {
        Log.e(TAG, " error: " + mSchemeListModel);
        activity.isShowProgress(false);
        this.mModel = mSchemeListModel;
        if(mModel.getStatus().equals("success"))
        {
            if(mModel.getschemeList()!=null &&mModel.getschemeList().size()>0)
                mSchemeList = mModel.getschemeList();
                mAdapterScheme.refreshadapter(mModel.getschemeList());
            create(getActivity(), Constants.SCHEME_LIST,mSchemeListModel);
        }
        else
        {
            Toast.makeText(getActivity(),mModel.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public SchemeListModel read(Context context, String fileName) {
        try {

            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            SchemeListModel mProductListModel  = (SchemeListModel) ois.readObject();
            ois.close();

            return mProductListModel;
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public boolean create(Context context, String fileName, SchemeListModel jsonString){
        try {
            File file = null;
            file = new File(context.getCacheDir(), "MyCache"); // Pass getFilesDir() and "MyFile" to read file
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);

            if (jsonString != null) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(jsonString);
                oos.close();
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }



    @Override
    public void onItemClick(View view, int position) {

    }
}
