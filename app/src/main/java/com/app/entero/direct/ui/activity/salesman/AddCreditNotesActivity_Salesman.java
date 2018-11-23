package com.app.entero.direct.ui.activity.salesman;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.entero.direct.R;
import com.app.entero.direct.ui.activity.main.BaseActivity;


public class AddCreditNotesActivity_Salesman extends BaseActivity implements View.OnClickListener {

    Toolbar mToolbar;
    TextView txtToolbarHeader,text_add_credit_note_name;
    EditText txt_add_credit_note_select_bill_id,txt_add_credit_note_amount,txt_add_credit_notes;
    Button btn_add_credit_notes_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_add_credit_note);
           initView();
           setToolbar();
           onSetText();
           onClickEvent();
     }

    private void onClickEvent() {
        btn_add_credit_notes_submit.setOnClickListener(this);
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
        text_add_credit_note_name = (TextView) findViewById(R.id.text_add_credit_note_name);
        txt_add_credit_note_select_bill_id = (EditText) findViewById(R.id.txt_add_credit_note_select_bill_id);
        txt_add_credit_note_amount = (EditText) findViewById(R.id.txt_add_credit_note_amount);
        txt_add_credit_notes = (EditText) findViewById(R.id.txt_add_credit_notes);
        btn_add_credit_notes_submit = (Button) findViewById(R.id.btn_add_credit_notes_submit);

    }

    private void submitStatusDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.salesman_dialog_submit_message);
        dialog.setTitle("Title...");

        // set the custom dialog components - text and image

        ImageView order_dialog_credit_note_images = (ImageView) dialog.findViewById(R.id.order_dialog_credit_note_images);

        TextView text_dialog_credit_note_submit = (TextView) dialog.findViewById(R.id.text_dialog_credit_note_submit);

        /*// if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_credit_notes_submit :
                submitStatusDialog();
                break;
        }

    }
}
