package com.app.entero.direct.ui.activity.salesman;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.OutstandingsCustomAdapter_Salesman;

public class OutstandingsActivity_Salesman extends BaseActivity {
    Toolbar mToolbar;
    SearchView searchView;

    private static RecyclerView.Adapter adapter_outstandings;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_view_outstandings;
    private static ArrayList<Outstandings> allOutstandingsBillData;
    public static View.OnClickListener myOnClickListener;
TextView txtHeader;
    private Date current_date = Calendar.getInstance().getTime();
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdYear = new SimpleDateFormat("yyyy");
    private SimpleDateFormat sdMonth = new SimpleDateFormat("MM");
    private SimpleDateFormat sdDay = new SimpleDateFormat("dd");
    private int mYear, mMonth, mDay;
    private Date filter_start_date, filter_end_date;
    String date = null;
    TextView txt_start_date,txt_end_date;
    CheckBox chk_pending_invoices,chk_undelivered_invoices;
    Button btn_filter,btn_clear;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_outstandings);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();
        //Toolbar with search and filter
           
          //  mToolbar.setTitle(getString(R.string.outstangings));
        
        // for remove RecyclerView item
        myOnClickListener = new MyOnClickListener(this);

        // outstandings bill list
               recycler_view_outstandings.setHasFixedSize(true);

      
        recycler_view_outstandings.setLayoutManager(layoutManager);
        recycler_view_outstandings.setItemAnimator(new DefaultItemAnimator());

        /*LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.item_animation_fall_down);
        recycler_view_outstandings.setLayoutAnimation(animation);*/

       
        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            allOutstandingsBillData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));
        }


        adapter_outstandings = new OutstandingsCustomAdapter_Salesman(allOutstandingsBillData);
        recycler_view_outstandings.setAdapter(adapter_outstandings);

    }

    private void onClickEvent() {
    }

    private void onSetText() {
        txtHeader.setText(getString(R.string.outstangings));
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

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHeader=(TextView)findViewById(R.id.txtHeader);
        recycler_view_outstandings = (RecyclerView) findViewById(R.id.recycler_view_outstandings);
        layoutManager = new LinearLayoutManager(this);
        allOutstandingsBillData = new ArrayList<Outstandings>();
    }

    // search and filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Outstandings List");
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
        final View dialogview = inflater.inflate(R.layout.salesman_dialog_outstandings_list_filter, null);
        final Dialog infoDialog = new Dialog(this);//builder.create();
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infoDialog.setContentView(dialogview);


        txt_start_date = (TextView) dialogview.findViewById(R.id.txt_start_date);
        txt_end_date = (TextView) dialogview.findViewById(R.id.txt_end_date);
        chk_pending_invoices = (CheckBox) dialogview.findViewById(R.id.chk_pending_invoices);
        chk_undelivered_invoices = (CheckBox) dialogview.findViewById(R.id.chk_undelivered_invoices);



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

        dialogview.findViewById(R.id.btn_outstandings_filter).setOnClickListener(new View.OnClickListener() {
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

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //removeItem(v);

        }
    }

}
