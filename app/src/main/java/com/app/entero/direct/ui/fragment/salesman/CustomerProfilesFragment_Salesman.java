package com.app.entero.direct.ui.fragment.salesman;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.utils.LocationTrack;
import com.app.entero.direct.utils.getLocation;

public class CustomerProfilesFragment_Salesman extends Fragment implements View.OnClickListener {

    TextView txt_customer_name,txt_customer_address,text_Customer_code,text_customer_phone_no,txt_customer_email,txt_customer_outstanding_bill;
    Button btn_customer_update_location;
    View rootView;
    Bundle b;
    LocationTrack locationTrack;
    int position;
    public CustomerProfilesFragment_Salesman() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.salesman_customer_list_tab_profile, container, false);
         new getLocation(getActivity()).checkLocation(getActivity());
        initView();
        onSetText();
       b=getArguments();
        if (b != null) {
           position = b.getInt("position", 2);
        }
        Toast.makeText(getContext(),"---"+position,Toast.LENGTH_SHORT).show();
        return rootView;
    }

    private void onSetText() {
        txt_customer_address.setText(AllCustomerList_Fragment_Salesman.listCustomer.get(position).getAddress());
        text_Customer_code.setText(AllCustomerList_Fragment_Salesman.listCustomer.get(position).getERPCode());
        txt_customer_name.setText(AllCustomerList_Fragment_Salesman.listCustomer.get(position).getChemistLegalName());
        txt_customer_email.setText(AllCustomerList_Fragment_Salesman.listCustomer.get(position).getEmail());
        text_customer_phone_no.setText(AllCustomerList_Fragment_Salesman.listCustomer.get(position).getMobileNo());
    txt_customer_outstanding_bill.setText(AllCustomerList_Fragment_Salesman.listCustomer.get(position).getOutstandingBal());
    }

    private void initView() {
        locationTrack = new LocationTrack(getActivity());
        txt_customer_name = (TextView) rootView.findViewById(R.id.txt_customer_name);
        txt_customer_address = (TextView) rootView.findViewById(R.id.txt_customer_address);
        text_Customer_code = (TextView) rootView.findViewById(R.id.text_Customer_code);
        text_customer_phone_no = (TextView) rootView.findViewById(R.id.text_customer_phone_no);
        txt_customer_email = (TextView) rootView.findViewById(R.id.txt_customer_email);
        txt_customer_outstanding_bill = (TextView) rootView.findViewById(R.id.txt_customer_outstanding_bill);
        btn_customer_update_location = (Button) rootView.findViewById(R.id.btn_customer_update_location);
if(locationTrack.get_location()) {
    btn_customer_update_location.setOnClickListener(this);
}else {

}
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_customer_update_location:

                break;
        }

    }
}
