package com.app.entero.direct.ui.activity.salesman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.entero.direct.Helper.OutstandingsData;
import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.CreditNotesCustomAdapter_Salesman;

public class CreditNotesActivity_Salesman extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView txtToolbarHeader,text_credit_note_client_name,text_credit_note_total_amount;
    Toolbar mToolbar;
    private static RecyclerView.Adapter adapter_credit_notes_details;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recycler_credit_note_list;
    private static ArrayList<Outstandings> allcreditNotesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_credit_notes);
        initView();
        setToolbar();
        onSetText();
        onClickEvent();

        recycler_credit_note_list.setHasFixedSize(true);
        recycler_credit_note_list.setLayoutManager(layoutManager);
        recycler_credit_note_list.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < OutstandingsData.nameArray.length; i++) {
            allcreditNotesData.add(new Outstandings(
                    OutstandingsData.nameArray[i],
                    OutstandingsData.versionArray[i],
                    OutstandingsData.id_[i]
            ));

            adapter_credit_notes_details = new CreditNotesCustomAdapter_Salesman(allcreditNotesData);
            recycler_credit_note_list.setAdapter(adapter_credit_notes_details);
        }

    }

    private void onClickEvent() {

    }

    private void onSetText() {
        txtToolbarHeader.setText("Credit Note");
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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtToolbarHeader = (TextView) findViewById(R.id.txtHeader);

        text_credit_note_client_name = (TextView) findViewById(R.id.text_credit_note_client_name);
        text_credit_note_total_amount = (TextView) findViewById(R.id.text_credit_note_total_amount);
        recycler_credit_note_list = (RecyclerView) findViewById(R.id.recycler_credit_note_list);
        layoutManager = new LinearLayoutManager(this);
        allcreditNotesData = new ArrayList<Outstandings>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.add_credit_note_images :
                Intent in = new Intent(CreditNotesActivity_Salesman.this, AddCreditNotesActivity_Salesman.class);
                startActivity(in);
                break;*/
        }

    }
    // add credit note menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_pending_list, menu);
        MenuItem add_credit_note = menu.findItem(R.id.add_credit_note);
        add_credit_note.setVisible(true);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                Intent in = new Intent(CreditNotesActivity_Salesman.this, AddCreditNotesActivity_Salesman.class);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
