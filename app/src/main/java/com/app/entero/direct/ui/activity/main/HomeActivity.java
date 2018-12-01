package com.app.entero.direct.ui.activity.main;

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
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.model.LoginModel;
import com.app.entero.direct.ui.activity.chemist.NotificationActivity;
import com.app.entero.direct.ui.activity.chemist.ProfileActivity;
import com.app.entero.direct.ui.adapter.chemist.NavigationAdapter;
import com.app.entero.direct.ui.fragment.chemist.ChemistHomeFragment;
import com.app.entero.direct.ui.fragment.chemist.OrderlistFragment;
import com.app.entero.direct.ui.fragment.chemist.OutStandingFragment;
import com.app.entero.direct.ui.fragment.chemist.SelectStockListFragment;
import com.app.entero.direct.ui.fragment.chemist.StockListFragment;
import com.app.entero.direct.ui.listener.OnItemRecycleClickListener;
import com.app.entero.direct.utils.custom.CustomTextView_Salesman;


public class HomeActivity extends BaseActivity implements OnItemRecycleClickListener {

    private Toolbar toolbar;
    private CustomTextView_Salesman tv_title;
    private RecyclerView rv_navigation;
    private LoginModel loginModel;
    private static final String TAG = HomeActivity.class.getSimpleName();
    NavigationView navigationView;
    private NavigationAdapter mNavigationAdapter;
    private BottomNavigationView mBottomNavigationView;
    public ImageView img_title,img_add_order,searchImg,addToCart,imgFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initObjects();
        setToolbar();
        setNavigationDrawer();
        setupBottomNavigation();
        initView();
        Fragment selectedFragment = new ChemistHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        selectedFragment.setArguments(bundle);
        replaceFragment(selectedFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    private void initView() {
        rv_navigation = (RecyclerView) findViewById(R.id.rv_navigation);
        img_title = (ImageView) findViewById(R.id.img_title);
        img_add_order = (ImageView) findViewById(R.id.img_add_order);
        addToCart = (ImageView) findViewById(R.id.addToCart);
        searchImg = (ImageView) findViewById(R.id.search);
        imgFilter = (ImageView) findViewById(R.id.imgFilter);
        mNavigationAdapter = new NavigationAdapter(this, this);
        rv_navigation.setAdapter(mNavigationAdapter);
        rv_navigation.setLayoutManager(new LinearLayoutManager(this));
        mNavigationAdapter.refreshadapter(0);
        setProfileData();


    }

    public void isVisible(Boolean b) {
        if (b) {
            img_title.setVisibility(View.VISIBLE);
        } else {
            img_title.setVisibility(View.GONE);
        }
    }

    public void isVisibleAdd(Boolean b) {
        if (b) {
            img_add_order.setVisibility(View.VISIBLE);
        } else {
            img_add_order.setVisibility(View.GONE);
        }
    }

    public void isVisibleSearch(Boolean b) {
        if (b) {
            searchImg.setVisibility(View.VISIBLE);
        } else {
            searchImg.setVisibility(View.GONE);
        }
    }

    public void isVisibleAddTocart(Boolean b) {
        if (b) {
            addToCart.setVisibility(View.VISIBLE);
        } else {
            addToCart.setVisibility(View.GONE);
        }
    }
    public void isVisibleFilter(Boolean b) {
        if (b) {
            imgFilter.setVisibility(View.VISIBLE);
        } else {
            imgFilter.setVisibility(View.GONE);
        }
    }





    private void setNavigationDrawer() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menubutton);
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
    }

    private void setupBottomNavigation() {

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment ;
                Bundle bundle;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //loadHomeFragment();
                        selectedFragment = new ChemistHomeFragment();
                        bundle = new Bundle();
                        bundle.putInt("type", 1);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        return true;
                    case R.id.action_product:
                        //loadProfileFragment();
                        selectedFragment = new SelectStockListFragment();
                        bundle = new Bundle();
                        bundle.putInt("type", 1);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        return true;
                    case R.id.action_stocklist:
                        //loadSettingsFragment();

                        selectedFragment = new StockListFragment();
                        bundle = new Bundle();
                        bundle.putInt("type", 1);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        return true;
                    case R.id.action_orders:
                        selectedFragment = new OrderlistFragment();
                        bundle = new Bundle();
                        bundle.putInt("type", 1);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        //loadHomeFragment();
                        return true;

                    case R.id.action_outstanding:
                        selectedFragment = new OutStandingFragment();
                        bundle = new Bundle();
                        bundle.putInt("type", 1);
                        selectedFragment.setArguments(bundle);
                        replaceFragment(selectedFragment);
                        //loadHomeFragment();
                        return true;

                }
                return false;
            }
        });
    }



    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (CustomTextView_Salesman) findViewById(R.id.tv_title);
        img_title = (ImageView) findViewById(R.id.img_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1){
                finish();
            }
            else {

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

        Bundle bundle = null;
        Fragment selectedFragment = null;
        if (position == 0) {
            mBottomNavigationView.setSelectedItemId(R.id.action_home);
        } else if (position == 1) {
            mBottomNavigationView.setSelectedItemId(R.id.action_product);
        } else if (position == 2) {
            mBottomNavigationView.setSelectedItemId(R.id.action_stocklist);
        } else if (position == 3) {
            mBottomNavigationView.setSelectedItemId(R.id.action_order);
        } else if (position == 4) {
            mBottomNavigationView.setSelectedItemId(R.id.action_outstanding);
        }else if (position == 5) {
            Intent mIntent = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(mIntent);
        }else if (position == 6) {
            Intent mIntent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(mIntent);

        }else if (position == 7) {
            Intent intent = new Intent()
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            System.exit(0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

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
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
