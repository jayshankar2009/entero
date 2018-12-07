package com.app.entero.direct.ui.fragment.salesman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.CustomerVisitTable;
import com.app.entero.direct.database.models.CustomerVisitTableDao;
import com.app.entero.direct.database.models.OrderTableMaster;
import com.app.entero.direct.database.models.OrderTableMasterDao;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.activity.salesman.AllOrderActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.Customer_TastActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.MainActivity;
import com.app.entero.direct.ui.activity.salesman.OutstandingsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.ReportsActivity_Salesman;
import com.app.entero.direct.ui.activity.salesman.Visit_PlanActivity_Salesman;
import com.app.entero.direct.ui.adapter.salesman.HomeCustomerAdapter_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.LocationTrack;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.getLocation;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment_Salesman extends Fragment implements View.OnClickListener,OnItemRecycleClickListener {
    private RecyclerView.LayoutManager layoutManager;
    private MainActivity activity;
    BaseActivity baseActivity;
    LinearLayout lnrCustomer;
    LocationTrack locationTrack;
    SavePref savePref;
    ArrayList<ProductsModel> allProductList = new ArrayList<ProductsModel>();
    CustomerVisitTableDao customerVisitTableDao;
    ArrayList<CustomerVisitTable> listCustomerVisit;
    ArrayList<SalesmanDashBoardModel> listDashBoard;
    TextView txtDlvryNo, txtPymntCol, txtCstmrVisit;
    LinkedHashMap<String, String> linkRequest;
    RecyclerView recyclerView;
    String strSalesmanId, strStockisId;
    LinkedHashMap<String, String> hashMap;
    private String TAG = "HomeFragment_Salesman";
    CardView btnOutSta, btnAllOrder, btnViewRe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        baseActivity = (BaseActivity) getActivity();
        activity.initObjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salesman_fragment_home, container, false);
        //Toast.makeText(getContext(), "Toast", Toast.LENGTH_LONG).show();
        activity = ((MainActivity) getActivity());
        activity.initObjects();

        initview(view);

        new getLocation(getActivity()).checkLocation(getActivity());
       // savePref = new SavePref();
       strStockisId = SavePref.getInstance(getContext()).getUserDetail().getSalesmanInfo().get(0).getStockistID();
        strSalesmanId = SavePref.getInstance(getContext()).getUserDetail().getSalesmanInfo().get(0).getID();
        linkRequest = new LinkedHashMap<>();
        linkRequest.put(Constants.StockistID, "1");
        linkRequest.put(Constants.SalesmanID, "2");
        hashMap = new LinkedHashMap<>();
        hashMap.put(Constants.StockistID, "1");
        hashMap.put(Constants.legendType, "2");
        if(baseActivity.isNetworkAvailable()) {
            callProductListApi(ApiConstants.Get_productList, hashMap);
            if (customerVisitTableDao.loadAll().size() <= 0) {
                callSalesmanDashBoard(ApiConstants.Get_SalesmanDashboard, linkRequest);
            } else {
                setOnText();
                fetchData();
            }
        }
        activity.imgToolbar.setVisibility(View.VISIBLE);
        activity.txtToolbar.setVisibility(View.GONE);

        setOnClick();
        return view;

    }

    private void setOnText() {
        if(SavePref.getInstance(getContext()).getStringValue(Constants.homeDelivery,"")==null){
            txtDlvryNo.setText("0");
                    }else {
            txtDlvryNo.setText(SavePref.getInstance(getContext()).getStringValue(Constants.homeDelivery,""));

        }
        if(SavePref.getInstance(getContext()).getStringValue(Constants.homeCstmrVisit,"")==null){
            txtCstmrVisit.setText("0");
                   }else {
            txtCstmrVisit.setText(SavePref.getInstance(getContext()).getStringValue(Constants.homeCstmrVisit,""));

        }
        if(SavePref.getInstance(getContext()).getStringValue(Constants.homePaymenClc,"")==null){
            txtPymntCol.setText("0");
        }else {
            txtPymntCol.setText(SavePref.getInstance(getContext()).getStringValue(Constants.homePaymenClc,""));

        }
    }

    private void setOnClick() {
        btnOutSta.setOnClickListener(this);
        btnAllOrder.setOnClickListener(this);
        btnViewRe.setOnClickListener(this);
        lnrCustomer.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void initview(View view) {
         listCustomerVisit = new ArrayList<>();
        customerVisitTableDao =((EnteroApp) activity.getApplication()).getDaoSession().getCustomerVisitTableDao();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btnOutSta = (CardView) view.findViewById(R.id.btnOutSta);
        btnAllOrder = (CardView) view.findViewById(R.id.btnAllOrder);
        btnViewRe = (CardView) view.findViewById(R.id.btnViewRe);
        lnrCustomer = (LinearLayout) view.findViewById(R.id.lnrCustomer);
        txtCstmrVisit = (TextView) view.findViewById(R.id.txtCstmrVisit);
        txtDlvryNo = (TextView) view.findViewById(R.id.txtDlvryNo);
        txtPymntCol = (TextView) view.findViewById(R.id.txtPymntCol);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        locationTrack = new LocationTrack(getActivity());
       // fetchData();
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOutSta:
                Intent i = new Intent(getActivity(), OutstandingsActivity_Salesman.class);
                this.startActivity(i);
                break;
            case R.id.btnAllOrder:
                Intent i1 = new Intent(getActivity(), AllOrderActivity_Salesman.class);
                this.startActivity(i1);
                break;
            case R.id.btnViewRe:
                Intent i2 = new Intent(getActivity(), ReportsActivity_Salesman.class);
                this.startActivity(i2);
                break;

            case R.id.lnrCustomer:
              //  if(listDashBoard.size()>0) {
                    Intent i3 = new Intent(getActivity(), Visit_PlanActivity_Salesman.class);
                  //  i3.putExtra("array_list", listDashBoard);
                    this.startActivity(i3);
             //   }
                break;

        }


    }


   private void callSalesmanDashBoard(String url, LinkedHashMap<String, String> linkedHashMap) {
        baseActivity.isShowProgress(true);
        baseActivity.mCompositeDisposable.add(baseActivity.getApiCallService().getSalesmanDashBoard(SavePref.getInstance(getContext()).getToken(), url, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }
    private void callProductListApi(String url, LinkedHashMap<String, String> hashMap) {

      //  baseActivity.isShowProgress(true);
        baseActivity.mCompositeDisposable.add(baseActivity.getApiCallService().getProductList(SavePref.getInstance(getContext()).getToken(), url, hashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleProductResponse, this::handleError));


    }


    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        baseActivity.isShowProgress(false);
    }

    private void handleResponse(SalesmanDashBoardModel mSalesmanDashBoard) {
        baseActivity.isShowProgress(false);
        if (mSalesmanDashBoard.getStatus().equals("success")) {
            if (mSalesmanDashBoard.getEntityCountSalesmanData().size() > 0) {
                SavePref.getInstance(getContext()).setValue(Constants.homeCstmrVisit,mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getAssignedcustomerCount());
                SavePref.getInstance(getContext()).setValue(Constants.homeDelivery,mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getAssignedDeliveryCount());
                SavePref.getInstance(getContext()).setValue(Constants.homePaymenClc,mSalesmanDashBoard.getEntityCountSalesmanData().get(0).getTotalPaymentCollection());


            }else {
                Toast.makeText(getContext(),"Delivery , Customer Visit , Payment Collection Records are not found",Toast.LENGTH_SHORT).show();
            }
            if(mSalesmanDashBoard.getEntityChemListData().size()>0){
            //    listDashBoard = new ArrayList<>();
                for (int i = 0;i<mSalesmanDashBoard.getEntityChemListData().size();i++) {
                  //  listDashBoard.add(mSalesmanDashBoard.getEntityChemListData().get(i));
                   if(!isCustomerAdded(mSalesmanDashBoard.getEntityChemListData().get(i).getEcid())) {
                       customerVisitTableDao.insert(new CustomerVisitTable(null, mSalesmanDashBoard.getEntityChemListData().get(i).getEcid(), mSalesmanDashBoard.getEntityChemListData().get(i).getStokistID(), mSalesmanDashBoard.getEntityChemListData().get(i).getUserID(),
                               mSalesmanDashBoard.getEntityChemListData().get(i).getRouteID(), mSalesmanDashBoard.getEntityChemListData().get(i).getChemistLegalName(),
                               mSalesmanDashBoard.getEntityChemListData().get(i).getChemistAddress(), mSalesmanDashBoard.getEntityChemListData().get(i).getMobileNo(),
                               mSalesmanDashBoard.getEntityChemListData().get(i).getEmail(), mSalesmanDashBoard.getEntityChemListData().get(i).getChemistCity(),
                               mSalesmanDashBoard.getEntityChemListData().get(i).getChemistErpCode(), mSalesmanDashBoard.getEntityChemListData().get(i).getTotalAmnt(), mSalesmanDashBoard.getEntityChemListData().get(i).getOutStanding()));
                   }else {

                   }

                }
                setOnText();
             fetchData();

            }else {
                Toast.makeText(getContext(), mSalesmanDashBoard.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }
    }
    private void handleProductResponse(ProductListModel productsModel) {

      //  baseActivity.isShowProgress(false);
        if (productsModel.getStatus().equals("success")) {
            if (productsModel.getMessage().equals("Record found")) {
               /* for (int i = 0; i < productsModel.getProductList().size(); i++) {
                    allProductList.add(productsModel.getProductList().get(i));

                }*/

                if  (create(activity,Constants.PRODUCT_LIST,productsModel)){
                //    SavePref.getInstance(getContext()).setValue("productSave","1");
                    //    read(activity,Constants.Salesman_PRODUCT_LIST);

                }

              //  fetchProductList();
              /*  txtProductCount.setText(allProductList.size() + "");
                Toast.makeText(mContext, allProductList.size() + "Products", Toast.LENGTH_LONG).show();
*/            } else {

               // Toast.makeText(mContext, productsModel.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void fetchData() {
        listCustomerVisit= (ArrayList<CustomerVisitTable>) customerVisitTableDao.loadAll();
        Log.i("ListCustomerV","---"+listCustomerVisit.size());
        if(listCustomerVisit.size()>0) {
            HomeCustomerAdapter_Salesman adapter = new HomeCustomerAdapter_Salesman(getContext(), this, listCustomerVisit);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        }
    }



    @Override
    public void onItemClick(View view, int position) {
        if(locationTrack.get_location()) {
            Intent i = new Intent(getContext(), Customer_TastActivity_Salesman.class);
            i.putExtra("chemistId",listCustomerVisit.get(position).getChemistID());
            startActivity(i);
        }else {
            Toast.makeText(getContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();

        }

    }
    private boolean isCustomerAdded(String chemistID) {
        QueryBuilder<CustomerVisitTable> qb = customerVisitTableDao.queryBuilder();
        QueryBuilder<CustomerVisitTable> where = qb.where(CustomerVisitTableDao.Properties.ChemistID.eq(chemistID));
        if(where.list().size()>0)
            return true;
        else
            return false;
    }

    private boolean create(Context context, String fileName, ProductListModel jsonString){
        try {
            //File file = null;
            //   File file = new File(Environment.getExternalStorageDirectory()+"/", "MyCache"); // Pass getFilesDir() and "MyFile" to read file
            FileOutputStream fos = activity.openFileOutput(fileName,Context.MODE_PRIVATE);

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
}
