package com.app.entero.direct.ui.activity.salesman;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.CustomerVisitTable;
import com.app.entero.direct.database.models.CustomerVisitTableDao;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.ui.activity.chemist.ProductsActivity;
import com.app.entero.direct.ui.activity.chemist.TakeOrderActivity_Chemist;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.LocationTrack;
import com.app.entero.direct.utils.SavePref;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer_TastActivity_Salesman extends BaseActivity implements View.OnClickListener {

    RelativeLayout btn_takeOrder,btn_collectpayment,btn_take_delivery;
    TextView textView_profile;
    Toolbar mToolbar;
    Bundle bundle;
    String chemistId;
    List<CustomerVisitTable> listProfile;
    CustomerVisitTableDao customerDao;
    LocationTrack locationTrack;
   // CustomerVisitTable listSalesmanData;
    TextView txtHeader,txtCustId,txtDistrName,txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_customer__tast);
        checkLocation();
        initLayout();
        setToolbar();
//onVisiblityMode();
        onSetText();
        onClickEvent();

  //      Toast.makeText(getApplicationContext(),"---"+listSalesmanData,Toast.LENGTH_SHORT).show();
        }

    private void onSetText() {
        txtHeader.setText("Customer Task");
        txtCustId.setText(listProfile.get(0).getChemistERPCode());
        txtAddress.setText(listProfile.get(0).getAddress());
        txtDistrName.setText(listProfile.get(0).getChemist_Legal_Name());
       /* txtAddress.setText(listSalesmanData.getChemistAddress());
        txtDistrName.setText(listSalesmanData.getChemistLegalName());*/
    }

    private void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void onClickEvent() {
        textView_profile.setOnClickListener(this);
        btn_collectpayment.setOnClickListener(this);
        btn_takeOrder.setOnClickListener(this);
        btn_take_delivery.setOnClickListener(this);
    }

    private void initLayout() {
        bundle = getIntent().getExtras();
        chemistId=bundle.getString("chemistId");
      //  Log.i("Chemist Id",""+chemistId);
        customerDao = ((EnteroApp) getApplication()).getDaoSession().getCustomerVisitTableDao();
        listProfile = getProfileData(chemistId);
        //Log.i("ListProfile",""+listProfile.get(0).getChemistERPCode());
        locationTrack = new LocationTrack(this);
         mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        btn_takeOrder = (RelativeLayout) findViewById(R.id.btn_take_order);
        btn_collectpayment = (RelativeLayout) findViewById(R.id.btn_collectpayment);
        textView_profile = (TextView) findViewById(R.id.btn_view_profile);
        btn_take_delivery=(RelativeLayout)findViewById(R.id.btn_take_delivery);
        txtCustId = (TextView) findViewById(R.id.txtCustId);
        txtDistrName = (TextView) findViewById(R.id.txtDistrName);
        txtAddress= (TextView) findViewById(R.id.txtAddress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_profile :

                    Intent i = new Intent(Customer_TastActivity_Salesman.this, ProfileActivity_Salesman.class);
                   // i.putExtra("array_list", (Serializable) listSalesmanData);
                i.putExtra("chemistId",listProfile.get(0).getChemistID());
                    startActivity(i);

                break;

            case R.id.btn_collectpayment:
             //   Toast.makeText(getApplicationContext(),"Coll",Toast.LENGTH_LONG).show();
               // if(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getRoleID().equals(Constants.Salesman)) {
                if(locationTrack.get_location()) {
                    Intent i2 = new Intent(Customer_TastActivity_Salesman.this, CollectPaymentActivity_Salesman.class);
                    startActivity(i2);
                }else {
                    Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();

                }
             /*   }else {
                    Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();
                }*/
                break;

            case R.id.btn_take_order :
                if(locationTrack.get_location()) {
                    Intent i3 = new Intent(Customer_TastActivity_Salesman.this, ProductsActivity.class);
                    i3.putExtra(Constants.screen,"Salesman");
                    i3.putExtra(Constants.cmstErp,listProfile.get(0).getChemistERPCode());
                    startActivity(i3);
                }else {
                    Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.btn_take_delivery :
                if(locationTrack.get_location()) {
                    Intent i4 = new Intent(Customer_TastActivity_Salesman.this, DeliveryActivity_Salesman.class);
                    startActivity(i4);
                }else {
                    Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();

                }
                break;
        }

    }

    public void checkLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 1);
        }
    }
    /*private void onVisiblityMode() {

        if(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getRoleID().equals(String.valueOf(Constants.Deliveryboy))) {
              btn_collectpayment.setVisibility(View.GONE);
              btn_takeOrder.setVisibility(View.GONE);
              btn_take_delivery.setVisibility(View.VISIBLE);
        }
        else if(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getRoleID().equals(String.valueOf(Constants.CollectionAgent))){
            btn_take_delivery.setVisibility(View.GONE);
            btn_takeOrder.setVisibility(View.GONE);
            btn_collectpayment.setVisibility(View.VISIBLE);


        }
    }*/
    public List<CustomerVisitTable> getProfileData(String chemistId) {
        QueryBuilder<CustomerVisitTable> qb = customerDao.queryBuilder();
       /* qb.where(qb.and(Properties.ChemistID.eq(clientId)));
        QueryBuilder<MasterPlacedOrder> qb = this.queryBuilder();*/
        qb.where(CustomerVisitTableDao.Properties.ChemistID.eq(chemistId));
        return qb.list();
    }
}
