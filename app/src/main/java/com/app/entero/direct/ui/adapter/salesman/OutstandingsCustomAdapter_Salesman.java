package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Outstandings;
import com.app.entero.direct.model.SummaryReport;
import com.app.entero.direct.ui.activity.salesman.OutstandingsActivity_Salesman;

public class OutstandingsCustomAdapter_Salesman extends BaseExpandableListAdapter {
    private List<SummaryReport> outstandingExpandData;
    private Context ctx;
    private int itemLayoutId;
    private int groupLayoutId;
    public OutstandingsCustomAdapter_Salesman(List<SummaryReport> outstandingExpandData, Context ctx) {
        this.itemLayoutId =  R.layout.salesman_cartview_all_outstanding_bill_child_item;
        this.groupLayoutId = R.layout.salesman_cartview_all_outstanding_bill_parent_item;
        this.outstandingExpandData = outstandingExpandData;
        this.ctx = ctx;

    }

    @Override
    public int getGroupCount() {
        /*return outstandingExpandData.size();*/
        return 12;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        /*int size = outstandingExpandData.get(groupPosition).getOrders().size();
        return size;*/
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return outstandingExpandData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return outstandingExpandData.get(groupPosition).getOrders().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        /*return outstandingExpandData.get(groupPosition).hashCode();*/
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        /*return outstandingExpandData.get(groupPosition).getOrders().get(childPosition).hashCode();*/
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //  View v = null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.salesman_cartview_all_outstanding_bill_parent_item, null);     }
        /*SummaryReport cat = outstandingExpandData.get(groupPosition);*/
        TextView tx_outstanding_cust_id = (TextView)convertView.findViewById(R.id.tx_outstanding_cust_id);
        TextView tx_outstanding_cust_name = (TextView)convertView.findViewById(R.id.tx_outstanding_cust_name);
        TextView tx_outstanding_total_amount = (TextView)convertView.findViewById(R.id.tx_outstanding_total_amount);
        de.hdodenhof.circleimageview.CircleImageView due_indicator = (de.hdodenhof.circleimageview.CircleImageView)convertView.findViewById(R.id.due_indicator);
        ImageView group_indicator = (ImageView) convertView.findViewById(R.id.group_indicator);

        if(isExpanded) {
            group_indicator.setImageResource(R.drawable.arrow_up_float);
        }else {
            group_indicator.setImageResource(R.drawable.arrow_down_float);
        }

       /* tx_outstanding_cust_id.setText(cat.getClientId());
       tx_outstanding_cust_name.setText(cat.getClientName());
        tx_outstanding_total_amount.setText(String.valueOf(cat.getTotalAmount()));*/
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.salesman_cartview_all_outstanding_bill_child_item, null);
        }
        /*Orders cat = outstandingExpandData.get(groupPosition).getOrders().get(childPosition);*/
        TextView tx_outstanding_invoice_date = (TextView)convertView.findViewById(R.id.tx_outstanding_invoice_date);
        TextView tx_outstanding_invoice_no = (TextView)convertView.findViewById(R.id.tx_outstanding_invoice_no);
        TextView tx_outstanding_stock_count = (TextView)convertView.findViewById(R.id.tx_outstanding_stock_count);
        TextView tx_outstanding_bill_amt = (TextView)convertView.findViewById(R.id.tx_outstanding_bill_amt);
        TextView tx_outstanding_received_amt = (TextView)convertView.findViewById(R.id.tx_outstanding_received_amt);
        TextView tx_outstanding_due_amount = (TextView)convertView.findViewById(R.id.tx_outstanding_due_amount);
        TextView tx_outstanding_due_day_count = (TextView)convertView.findViewById(R.id.tx_outstanding_due_day_count);
        /*tx_outstanding_invoice_date.setText(cat.getDoc_Date());
        tx_outstanding_invoice_no.setText(cat.getDOC_NO());
        tx_outstanding_bill_amt.setText(String.valueOf(cat.getAmount()));*/
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
