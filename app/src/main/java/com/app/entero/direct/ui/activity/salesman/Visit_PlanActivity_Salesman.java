package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.CustomerVisitTable;
import com.app.entero.direct.database.models.CustomerVisitTableDao;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.Adapter_Visitplan_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.LocationTrack;
import com.app.entero.direct.utils.getLocation;

public class Visit_PlanActivity_Salesman extends BaseActivity implements View.OnClickListener,OnItemRecycleClickListener {

    RecyclerView recycler_vie;
    Adapter_Visitplan_Salesman mAdapter;
    Toolbar mToolbar;
    TextView txtHeader;
   LocationManager mLocationManager;
    Bundle bundle;
    LocationTrack locationTrack;
    public static   ArrayList<SalesmanDashBoardModel> listChemist;
    CustomerVisitTableDao customerVisitTableDao;
    ArrayList<CustomerVisitTable> listCustomerVisit;
   // private List<Visitplanmodal> mList;
    TextView textView_date;
    RelativeLayout baseLayout;
    SearchView searchView;

    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,dd LLL yyyy");
    Date filter_start_date = Calendar.getInstance().getTime();
    private Date current_date = Calendar.getInstance().getTime();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private int mYear, mMonth, mDay;

    String date = null;
    private SimpleDateFormat sdYear = new SimpleDateFormat("yyyy");
    private SimpleDateFormat sdMonth = new SimpleDateFormat("MM");
    private SimpleDateFormat sdDay = new SimpleDateFormat("dd");

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_visit__plan);
         new getLocation(this).checkLocation(this);
        initView();
        setToolBar();
        onClickEvent();
        onSetText();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_vie.setLayoutManager(mLayoutManager);
        recycler_vie.setItemAnimator(new DefaultItemAnimator());
        fetchVisitPlan();
        // prepareMovieData();

    }

    private void fetchVisitPlan() {
        mAdapter = new Adapter_Visitplan_Salesman(this,this,listCustomerVisit);
        recycler_vie.setAdapter(mAdapter);
    }

    private void onSetText() {
        txtHeader.setText(getResources().getString(R.string.visit_paln));
        textView_date.setText(dateFormat.format(filter_start_date));
    }

    private void onClickEvent() {
        textView_date.setOnClickListener(this);
        baseLayout.setOnClickListener(this);
    }

    private void setToolBar() {
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Back to previous activity
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        listCustomerVisit = new ArrayList<>();
        customerVisitTableDao =((EnteroApp) getApplication()).getDaoSession().getCustomerVisitTableDao();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        bundle = getIntent().getExtras();
       // listChemist= (ArrayList<SalesmanDashBoardModel>) bundle.getSerializable("array_list");
        listCustomerVisit = (ArrayList<CustomerVisitTable>) customerVisitTableDao.loadAll();
locationTrack = new LocationTrack(this);
       // mList = new ArrayList<>();
        recycler_vie = (RecyclerView) findViewById(R.id.recycler_view);
        textView_date = (TextView) findViewById(R.id.txt_date);
        baseLayout=(RelativeLayout)findViewById(R.id.baseLayout);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem action_search = menu.findItem(R.id.action_search);
        action_search.setVisible(true);
        searchView.setQueryHint("Customer List");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.trim().isEmpty()) {
                    fetchVisitPlan();
                    return false;
                }
        mAdapter.getFilter().filter(newText.toLowerCase());
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
        return true;
    }

    private void fetchProductList() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_date:
                break;

            case R.id.baseLayout:
            //    pickUpDate();
                break;

        }
    }
    private void pickUpDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
String strDate =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        Date date1 = null;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date1 = format.parse(strDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //  textView_date.setText();
textView_date.setText(dateFormat.format(date1));

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onItemClick(View view, int position) {
        // if(new getLocation(this).get_location())
        if (locationTrack.get_location()) {
            Intent i = new Intent(getApplicationContext(), Customer_TastActivity_Salesman.class);
            //  Log.i("ListDash",""+listChemist.get(position));
             i.putExtra("chemistId",listCustomerVisit.get(position).getChemistID());
         //   i.putExtra("array_list", listChemist.get(position));
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(),"You have not a permmision to this job",Toast.LENGTH_SHORT).show();

        }

    }


    /*public void checkLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 1);
        }
    }*/


}
