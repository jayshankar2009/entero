package com.app.entero.direct.ui.activity.salesman;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.EnteroApp;
import com.app.entero.direct.R;
import com.app.entero.direct.database.models.CustomerVisitTable;
import com.app.entero.direct.database.models.CustomerVisitTableDao;
import com.app.entero.direct.model.LoginModel;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.network.ApiConstants;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.NavigationAdapter_Salesman;
import com.app.entero.direct.ui.fragment.salesman.AllCustomerList_Fragment_Salesman;
import com.app.entero.direct.ui.fragment.salesman.HomeFragment_Salesman;
import com.app.entero.direct.ui.fragment.salesman.ProductsFragment_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.Constants;
import com.app.entero.direct.utils.SavePref;
import com.app.entero.direct.utils.getLocation;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity implements OnItemRecycleClickListener {

    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView rv_navigation;
    private LoginModel loginModel;
    public static BottomNavigationView bottomNavigationView;
    private static final String TAG = MainActivity.class.getSimpleName();
    NavigationView navigationView;
    CustomerVisitTableDao customerVisitTableDao;
    ArrayList<CustomerVisitTable> listCustomerVisit;
    LinkedHashMap<String, String> linkRequest;
   public static ImageView imgToolbar;
    public static TextView txtToolbar;
    TextView txtName,txtImeiNo,txtEmail,txtMobile;
    DrawerLayout drawer;
    String strSalesmanId, strStockisId;
    // public static ImageView imgFilter,imgSearch;
    ActionBarDrawerToggle toggle;
    private NavigationAdapter_Salesman mNavigationAdapter;
    Fragment selectedFragment;
    private static final int REQUEST_WRITE_PERMISSION = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_salesman_home);


        initObjects();
        setToolbar();
        initView();
        setupBottomNavigation();
        // setNavigationDrawer();

        selectedFragment = new HomeFragment_Salesman();

        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        selectedFragment.setArguments(bundle);
        replaceFragment(selectedFragment);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment;
                Bundle bundle;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        selectedFragment = new HomeFragment_Salesman();
                        bundle = new Bundle();
                        bundle.putInt("type", 1);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        return true;

                    case R.id.action_product:
                        selectedFragment = new ProductsFragment_Salesman();
                        bundle = new Bundle();
                        bundle.putInt("type", 2);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        return true;
                    case R.id.action_customer:
                        selectedFragment = new AllCustomerList_Fragment_Salesman();
                        bundle = new Bundle();
                        bundle.putInt("type", 3);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        return true;

                    case R.id.action_order:
                        Intent i = new Intent(getApplicationContext(), AllOrderActivity_Salesman.class);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void initView() {
        listCustomerVisit = new ArrayList<>();
        customerVisitTableDao =((EnteroApp)getApplication()).getDaoSession().getCustomerVisitTableDao();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        rv_navigation = (RecyclerView) findViewById(R.id.rv_navigation);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        strStockisId = SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getStockistID();
        strSalesmanId = SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getID();
        txtName=(TextView)findViewById(R.id.txtName);
        txtMobile=(TextView)findViewById(R.id.txtMobile);
        txtEmail=(TextView)findViewById(R.id.txtEmail);
        txtImeiNo=(TextView)findViewById(R.id.txtImeiNo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        setUpDrawerToggle();
        //drawer.setDrawerListener(this);
        mNavigationAdapter = new NavigationAdapter_Salesman(this, this);
        rv_navigation.setAdapter(mNavigationAdapter);
        rv_navigation.setLayoutManager(new LinearLayoutManager(this));
        mNavigationAdapter.refreshadapter(0);

        setProfileData();

    }

    private void setUpDrawerToggle() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);      //This is necessary to change the icon of the Drawer Toggle upon state change.
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.ordergenie_gray));

        toggle.syncState();
    }

   /* private void setNavigationDrawer() {
       // navigationView = (NavigationView) findViewById(R.id.nav_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }*/

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       imgToolbar = (ImageView) findViewById(R.id.imgLogo);
        txtToolbar = (TextView) findViewById(R.id.txtHeader);

        //  tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);
        setSupportActionBar(toolbar);


        // getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          //  finish();
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setTitle("Exit");
            builder.setMessage("Are you sure want to Exit.");
            builder.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                     dialog.dismiss();                        }
                    });
            builder.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                           finish();                        }
                    });
           
           /* if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {

            }*/
        }
    }


    public void setTitle(String title) {
        tv_title.setText(title);
    }


    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onItemClick(View view, int position) {

        Bundle bundle;
        Fragment selectedFragment;
        if (position == 0) {
            selectedFragment = new HomeFragment_Salesman();
            bottomNavigationView.setSelectedItemId(R.id.action_home);

        } else if (position == 1) {
            bottomNavigationView.setSelectedItemId(R.id.action_product);
        } else if (position == 2) {
            bottomNavigationView.setSelectedItemId(R.id.action_customer);
        } else if (position == 3) {

            Intent i = new Intent(getApplicationContext(), AllOrderActivity_Salesman.class);
            startActivity(i);
        } else if (position == 4) {

        }
        closeDrawer();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void doLogOut() {

    }

    public void setProfileData() {
        txtImeiNo.setText("Imei:          "+SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getIMEI());
        txtName.setText(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getFullName());
        txtEmail.setText("Email Id:    "+SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getEmail());
        txtMobile.setText(SavePref.getInstance(getApplicationContext()).getUserDetail().getSalesmanInfo().get(0).getMobileNO());
    }

    public void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
       /* boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped){ //fragment not in back stack, create it.
         */
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.container, fragment);
        //   ft.addToBackStack(backStateName);
        ft.commit();
       /* }else {
            Toast.makeText(getApplicationContext(),"Fragment 2"+backStateName,Toast.LENGTH_LONG).show();

        }
*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        txtToolbar.setVisibility(View.GONE);
     imgToolbar.setVisibility(View.VISIBLE);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_filter:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {

        }
    }


}
