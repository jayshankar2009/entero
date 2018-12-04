package com.app.entero.direct.ui.activity.salesman;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.CustomerVisitTable;
import com.app.entero.direct.database.models.CustomerVisitTableDao;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity_Salesman extends BaseActivity {
    TextView txtHeader;
    TextView txtcustName,txtCustAddrs,txtCustPhone,txtCustCode,txtEmail,txtPending;
    Toolbar mToolbar;
    Bundle bundle;
    String chemistId;
    CustomerVisitTableDao customerVisitTableDao;
    List<CustomerVisitTable> listCustomerVisit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_profile);
        initView();
        setToolbar();
        onSetText();

     //   position = bundle.getInt("position");
       // onClickEvent();

    }

    private void onSetText() {
        txtHeader.setText("Customer Profile");
        txtcustName.setText(listCustomerVisit.get(0).getChemist_Legal_Name());
        txtCustAddrs.setText(listCustomerVisit.get(0).getAddress());
        txtCustPhone.setText(listCustomerVisit.get(0).getMobileNo());
        txtCustCode.setText(listCustomerVisit.get(0).getChemistERPCode());
        txtEmail.setText(listCustomerVisit.get(0).getEmail());
        txtPending.setText(listCustomerVisit.get(0).getOutstandingAmt());

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

    private void initView() {
        bundle=getIntent().getExtras();
        chemistId=bundle.getString("chemistId");
        listCustomerVisit = new ArrayList<>();
        customerVisitTableDao =((EnteroApp) getApplication()).getDaoSession().getCustomerVisitTableDao();
          listCustomerVisit=getProfileData(chemistId);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        txtcustName =(TextView)findViewById(R.id.txtCustName);
        txtCustAddrs=(TextView)findViewById(R.id.txtCustAddrs);
        txtCustCode=(TextView)findViewById(R.id.text_Custcode);
        txtEmail=(TextView)findViewById(R.id.text_emilId);

        txtCustPhone=(TextView)findViewById(R.id.text_phno);
        txtPending=(TextView)findViewById(R.id.text_pendingamt);

    } public List<CustomerVisitTable> getProfileData(String chemistId) {
        QueryBuilder<CustomerVisitTable> qb = customerVisitTableDao.queryBuilder();
       /* qb.where(qb.and(Properties.ChemistID.eq(clientId)));
        QueryBuilder<MasterPlacedOrder> qb = this.queryBuilder();*/
        qb.where(CustomerVisitTableDao.Properties.ChemistID.eq(chemistId));
        return qb.list();
    }

}
