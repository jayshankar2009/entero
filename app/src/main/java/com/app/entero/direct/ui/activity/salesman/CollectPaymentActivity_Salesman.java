package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Paymentmodel;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.Adapter_payment_Salesman;
import com.app.entero.direct.utils.LocationTrack;
import com.app.entero.direct.utils.getLocation;

import net.igenius.customcheckbox.CustomCheckBox;


public class CollectPaymentActivity_Salesman extends BaseActivity implements View.OnClickListener {
    Toolbar mToolbar;
    RecyclerView recyclerView;
    Adapter_payment_Salesman mAdapter;
    public static TextView txtPdc,txtCreditNote,txt_balance_amount;
    SearchView searchView;
    LocationTrack locationTrack;
    public static Button btn_border_details_pending,btn_order_details_invoiced,btn_order_details_filter,btn_clear_filter;
    TextView txt_start_date,txt_end_date;
    private List<Paymentmodel> mList = new ArrayList<>();
    public static Button btn_makePayment;
    TextView txtHeader;
    int paidAmnt=0;
    private int mYear, mMonth, mDay;
    RecyclerView.LayoutManager mLayoutManager;
    String date = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Date filter_start_date, filter_end_date;
    public static View.OnClickListener allCollectPaymentOnClickListener;
    public static LinearLayout ly_balance_account_amount;
    public static int balanceAmount;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_collect_payment);
        initView();
        new getLocation(this).checkLocation(this);
        setToolbar();
        onSetText();
        onClickEvent();

        allCollectPaymentOnClickListener = new MyOnClickListener(this);
        mAdapter = new Adapter_payment_Salesman(mList);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        if(paidAmnt>0) {
            txt_balance_amount.setText(String.valueOf(paidAmnt));
            ly_balance_account_amount.setVisibility(View.VISIBLE);
        }else {
            ly_balance_account_amount.setVisibility(View.GONE);
        }

        prepareMovieData();
    }

    private void onSetText() {
        txtHeader.setText("Collect Payment");
    }

    private void setToolbar() {
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

    private void onClickEvent() {
        txtPdc.setOnClickListener(this);
        txtCreditNote.setOnClickListener(this);
        btn_makePayment.setOnClickListener(this);
    }

    private void initView() {
        locationTrack = new LocationTrack(getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        txtPdc = (TextView)findViewById(R.id.txtPdc);
        txtCreditNote=(TextView)findViewById(R.id.txtCreditNote);
        txt_balance_amount=(TextView)findViewById(R.id.txt_balance_amount);

        ly_balance_account_amount = (LinearLayout) findViewById(R.id.ly_balance_account_amount);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        btn_makePayment = (Button) findViewById(R.id.btn_makePayment);
    }

    private void prepareMovieData() {


        Paymentmodel modal_visitplan = new Paymentmodel("U0018336961", "02/08/2018", "50 days","20","1926","28","1898","Partially Paid","1873");
        mList.add(modal_visitplan);


          modal_visitplan = new Paymentmodel("U0018336962", "30/10/2018", "70 days","30","2000","99","4000","UnPaid","5000");
        mList.add(modal_visitplan);


       /* modal_visitplan = new Paymentmodel("55792", "Yash medical Gen Stores, Kids & Family", "3,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);

        modal_visitplan = new Paymentmodel("55793", "Vashi medical Gen Stores", "4,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);

        modal_visitplan = new Paymentmodel("55794", "Thane medical Gen Stores", "5,Kaweli wadi,R.B.s Bole Rd");
        mList.add(modal_visitplan);*/



          mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.txtPdc :
                Intent i = new Intent(getApplicationContext(),PDCPaymentActivity_Salesman.class);
                startActivity(i);
                break;
            case R.id.txtCreditNote:
                Intent i2= new Intent(getApplicationContext(),CreditNotesActivity_Salesman.class);
                startActivity(i2);
                break;
            case R.id.btn_makePayment :
                if(locationTrack.get_location()) {
                    if (btn_makePayment.getText().toString().equalsIgnoreCase("Make Payment")) {
                        makePaymentDialog();
                    } else {
                        Intent in = new Intent(CollectPaymentActivity_Salesman.this, Complete_PaymentActivity_Salesman.class);
                        startActivity(in);
                    }
                }else {

                }

                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        MenuItem action_search = menu.findItem(R.id.action_search);
        MenuItem action_filter = menu.findItem(R.id.action_filter);
        action_filter.setVisible(true);
        action_search.setVisible(true);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Collect Payment");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_filter:
                show_Filter_dialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void show_Filter_dialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogview = inflater.inflate(R.layout.salesman_dialog_order_details_list_filter, null);
        final Dialog infoDialog = new Dialog(this);//builder.create();
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infoDialog.setContentView(dialogview);

        btn_border_details_pending = (Button) dialogview.findViewById(R.id.btn_border_details_pending);
        btn_order_details_invoiced = (Button) dialogview.findViewById(R.id.btn_order_details_invoiced);
        txt_start_date = (TextView) dialogview.findViewById(R.id.txt_start_date);
        txt_end_date = (TextView) dialogview.findViewById(R.id.txt_end_date);


        if (filter_start_date != null) {
            txt_start_date.setText(sdf.format(filter_start_date));
        }
        if (filter_end_date != null) {
            txt_end_date.setText(sdf.format(filter_end_date));
        }


        txt_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = "from";
                pickUpDate();
            }


        });


        txt_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = "to";
                pickUpDate();
            }


        });

        dialogview.findViewById(R.id.btn_order_details_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //filter_dialog_conditions(filter_start_date, filter_end_date, chk_pending.isChecked(), chk_completed.isChecked());
                infoDialog.dismiss();
            }
        });

        dialogview.findViewById(R.id.btn_clear_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //fill_orderlist(posts);
                infoDialog.dismiss();
            }
        });
        set_attributes(infoDialog);
        infoDialog.show();
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

                        if(date.equalsIgnoreCase("from")) {

                            txt_start_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }else if(date.equalsIgnoreCase("to")){

                            txt_end_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void set_attributes(Dialog dlg) {

        Window window = dlg.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        int[] textSizeAttr = new int[]{android.R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedValue typedValue = new TypedValue();
        TypedArray a = this.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionbarsize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        int maxX = mdispSize.x;
        wlp.gravity = Gravity.TOP | Gravity.LEFT;
        wlp.x = maxX;   //x position
        wlp.y = actionbarsize - 20;   //y position
        window.setAttributes(wlp);

    }
    private void makePaymentDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.salesman_dialog_make_payment);
        dialog.setTitle("Title...");

        // set the custom dialog components - text and image

        TextView edtAmnt = (TextView) dialog.findViewById(R.id.edtAmnt);
        ImageView lnrCancel = (ImageView) dialog.findViewById(R.id.lnrCancel);
        lnrCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button btn_proceed_invoices = (Button) dialog.findViewById(R.id.btn_proceed_invoices);
        btn_proceed_invoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paidAmnt = Integer.parseInt(edtAmnt.getText().toString());
                if (paidAmnt > 0) {
                    ly_balance_account_amount.setVisibility(View.VISIBLE);
                    txt_balance_amount.setText("Rs. "+String.valueOf(paidAmnt));
                    dialog.dismiss();
                }
            }
        });

        /*// if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //removeItem(v);
            int selectedItemPosition = recyclerView.getChildPosition(v);

            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForPosition(selectedItemPosition);
            CustomCheckBox checkbox_status = (CustomCheckBox) viewHolder.itemView.findViewById(R.id.checkbox_status);
            TextView txt_bill_balanceAmt = (TextView) viewHolder.itemView.findViewById(R.id.txt_balAmt);
//TextView txtBalanceAmnt=(TextView)viewHolder.itemView.findViewById(R.id.txt_balAmt);
           /* if(checkbox_status.isChecked()){
                checkbox_status.setChecked(false);
                balanceAmount = balanceAmount - Integer.parseInt(txt_bill_balanceAmt.getText()+"");
                txt_balance_amount.setText("Rs. "+ balanceAmount);
                if(txt_balance_amount.getText().toString().equalsIgnoreCase("Rs. 0")){
                //    ly_balance_account_amount.setVisibility(View.GONE);
                    btn_makePayment.setText("Make Payment");
                    btn_makePayment.setBackgroundResource(R.drawable.curd_rectangle);
                }
            }else {


                checkbox_status.setChecked(true);
                btn_makePayment.setText("Complete Payment");
                btn_makePayment.setTextSize(12);
                btn_makePayment.setBackgroundResource(R.drawable.curve_blue_background);
                ly_balance_account_amount.setVisibility(View.VISIBLE);
                balanceAmount = balanceAmount + Integer.parseInt(txt_bill_balanceAmt.getText()+"");
                //txt_bill_balanceAmt.setText("0");
                txt_balance_amount.setText("Rs. "+ balanceAmount);
            }*/
           if(checkbox_status.isChecked()) {
               checkbox_status.setChecked(false);

           }else {
             //  checkbox_status.setChecked(true);
               if(paidAmnt>0) {

                checkbox_status.setChecked(true);
                  // int blnceAmnt;
                   paidAmnt= paidAmnt-Integer.parseInt(txt_bill_balanceAmt.getText().toString());
                  if(paidAmnt>=Integer.parseInt(txt_bill_balanceAmt.getText().toString())){
                      txt_bill_balanceAmt.setText(String.valueOf(0));
                      if(txt_bill_balanceAmt.getText().toString().equals("0")) {

                      }

                  }else {
                      txt_bill_balanceAmt.setText(String.valueOf(Math.abs(paidAmnt)));
                  }
               }else {
                   checkbox_status.setChecked(false);
                Toast.makeText(getApplicationContext(),"Balance Not Available",Toast.LENGTH_SHORT).show();
               }
           }

            //mAdapter.notifyDataSetChanged();
        }
    }


}
