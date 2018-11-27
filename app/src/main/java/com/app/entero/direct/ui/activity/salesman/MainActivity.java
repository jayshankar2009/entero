package com.app.entero.direct.ui.activity.salesman;

import android.content.Context;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.model.LoginModel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.NavigationAdapter_Salesman;
import com.app.entero.direct.ui.fragment.salesman.AllCustomerList_Fragment_Salesman;
import com.app.entero.direct.ui.fragment.salesman.HomeFragment_Salesman;
import com.app.entero.direct.ui.fragment.salesman.ProductsFragment_Salesman;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;


public class MainActivity extends BaseActivity implements OnItemRecycleClickListener {

    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView rv_navigation;
    private LoginModel loginModel;
    BottomNavigationView bottomNavigationView;
    private static final String TAG = MainActivity.class.getSimpleName();
    NavigationView navigationView;
    public static ImageView imgToolbar;
    public static TextView txtToolbar;
    DrawerLayout drawer;

    // public static ImageView imgFilter,imgSearch;
    ActionBarDrawerToggle toggle;
    private NavigationAdapter_Salesman mNavigationAdapter;
    Fragment selectedFragment;

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
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        rv_navigation = (RecyclerView) findViewById(R.id.rv_navigation);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

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
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {

            }
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

}
