package com.app.entero.direct.ui.activity.chemist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.model.SchemeListModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.chemist.OffersAdapter;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.SimpleDividerItemDecoration;

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

public class OffersActivity extends BaseActivity implements OnItemRecycleClickListener {

    private static final String TAG = "OffersActivity";
    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView rv_offers;
    private OffersAdapter mOffersAdapter;
    private ArrayList<SchemeListModel> mSchemeList;
    private Context mContext;
    private SchemeListModel mSchemeListModel;
    private SchemeListModel mModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        mContext = this;
        initObjects();
        setToolbar();
        initView();
    }

    private void initView() {
        mSchemeList = new ArrayList<>();
        mModel = new SchemeListModel();
        if(isFilePresent(this, Constants.SCHEME_LIST))
            mSchemeListModel = read(this, Constants.SCHEME_LIST);
        else
        {
            hashMap = new LinkedHashMap<>();
            hashMap.put(ApiConstants.Chemist_ID,"1115");
            // hashMap.put(ApiConstants.ClientID, SavePref.getInstance(getActivity()).getUserId());
            if(isNetworkAvailable())
            {
                callApi(hashMap);
            }
        }
        if(mSchemeListModel!=null)
        {
            mSchemeList = mSchemeListModel.getschemeList();
        }
        rv_offers = (RecyclerView) findViewById(R.id.rv_navigation);
        rv_offers.setLayoutManager(new LinearLayoutManager(this));
        mOffersAdapter = new OffersAdapter(this,this,mSchemeList);
        rv_offers.setAdapter(mOffersAdapter);
        rv_offers.addItemDecoration(new SimpleDividerItemDecoration(this));

    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (TextView) findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.backbutton);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("");
        tv_title.setText(R.string.offers);

    }

    @Override
    public void onItemClick(View view, int position) {

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

    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }


    public void callApi(LinkedHashMap<String, String> linkedHashMap) {
        mCompositeDisposable.add(getApiCallService().getAPP_SchemeforChemist(SavePref.getInstance(this).getToken(),ApiConstants.APP_SCHEME_FOR_CHEMIST, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }


    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void handleResponse(SchemeListModel mSchemeListModel) {
        Log.e(TAG, " error: " + mSchemeListModel);
        isShowProgress(false);
        this.mModel = mSchemeListModel;
        if(mModel.getStatus().equals("success"))
        {
            if(mModel.getschemeList()!=null &&mModel.getschemeList().size()>0)
                mSchemeList = mModel.getschemeList();
            mOffersAdapter.refreshadapter(mModel.getschemeList());
        }
        else
        {
            Toast.makeText(this,mModel.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
