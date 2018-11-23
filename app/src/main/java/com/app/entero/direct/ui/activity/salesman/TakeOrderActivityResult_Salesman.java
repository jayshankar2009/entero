package com.app.entero.direct.ui.activity.salesman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.ui.activity.main.BaseActivity;

import java.util.ArrayList;

public class TakeOrderActivityResult_Salesman extends BaseActivity implements View.OnClickListener {
    Toolbar mToolbar;
    TextView txtHeader,text_take_order_id,txt_stores_name;
    EditText edit_txt_comment;
    Button btn_save_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_takeorder_result);

        initView();
        setToolbar();
        onSetText();

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
        text_take_order_id = (TextView) findViewById(R.id.text_take_order_id);
        edit_txt_comment = (EditText) findViewById(R.id.edit_txt_comment);
        btn_save_comment = (Button) findViewById(R.id.btn_save_comment);
        btn_save_comment.setOnClickListener(this);
        txt_stores_name = (TextView) findViewById(R.id.txt_stores_name);
    }

    private void onSetText() {
        txtHeader.setText("Take Order");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_save_comment:
                finish();
                break;
        }
    }
}
