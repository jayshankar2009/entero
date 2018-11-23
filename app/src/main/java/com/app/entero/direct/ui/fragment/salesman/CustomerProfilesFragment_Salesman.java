package com.app.entero.direct.ui.fragment.salesman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.entero.direct.R;

public class CustomerProfilesFragment_Salesman extends Fragment implements View.OnClickListener {

    TextView txt_customer_name,txt_customer_address,text_Customer_code,text_customer_phone_no,txt_customer_email,txt_customer_outstanding_bill;
    Button btn_customer_update_location;
    View rootView;
    public CustomerProfilesFragment_Salesman() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.salesman_customer_list_tab_profile, container, false);

        initView();
        //onSetText();

        return rootView;
    }

    private void initView() {
        txt_customer_name = (TextView) rootView.findViewById(R.id.txt_customer_name);
        txt_customer_address = (TextView) rootView.findViewById(R.id.txt_customer_address);
        text_Customer_code = (TextView) rootView.findViewById(R.id.text_Customer_code);
        text_customer_phone_no = (TextView) rootView.findViewById(R.id.text_customer_phone_no);
        txt_customer_email = (TextView) rootView.findViewById(R.id.txt_customer_email);
        txt_customer_outstanding_bill = (TextView) rootView.findViewById(R.id.txt_customer_outstanding_bill);
        btn_customer_update_location = (Button) rootView.findViewById(R.id.btn_customer_update_location);
        btn_customer_update_location.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_customer_update_location:

                break;
        }

    }
}
