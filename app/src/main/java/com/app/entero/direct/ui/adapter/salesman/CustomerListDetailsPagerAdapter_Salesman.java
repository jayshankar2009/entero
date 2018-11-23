package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.entero.direct.R;
import com.app.entero.direct.ui.fragment.salesman.CustomerOrdersFragment_Salesman;
import com.app.entero.direct.ui.fragment.salesman.CustomerPendingBillFragment_Salesman;
import com.app.entero.direct.ui.fragment.salesman.CustomerProfilesFragment_Salesman;

public class CustomerListDetailsPagerAdapter_Salesman extends FragmentPagerAdapter {

    private Context context;

    public CustomerListDetailsPagerAdapter_Salesman(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CustomerProfilesFragment_Salesman();
        } else if (position == 1){
            return new CustomerOrdersFragment_Salesman();
        } else {
            return new CustomerPendingBillFragment_Salesman();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return context.getString(R.string.customer_profile);
            case 1:
                return context.getString(R.string.customer_orders);
            case 2:
                return context.getString(R.string.customer_pending_bill);
            default:
                return null;
        }
    }

}
