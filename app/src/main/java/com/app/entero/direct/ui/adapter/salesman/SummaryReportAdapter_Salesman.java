package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.SummaryReport;

public class SummaryReportAdapter_Salesman extends BaseExpandableListAdapter {
    private List<SummaryReport> summaryExpandData;
    private Context ctx;
    private int itemLayoutId;
    private int groupLayoutId;
    public SummaryReportAdapter_Salesman(List<SummaryReport> summaryExpandData, Context ctx) {
        this.itemLayoutId =  R.layout.salesman_adapter_summary_child;
        this.groupLayoutId = R.layout.salesman_adapter_summary_parent;
        this.summaryExpandData = summaryExpandData;
        this.ctx = ctx;

    }

    @Override
    public int getGroupCount() {
        /*return summaryExpandData.size();*/
        return 8;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        /*int size = summaryExpandData.get(groupPosition).getOrders().size();
        return size;*/
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return summaryExpandData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return summaryExpandData.get(groupPosition).getOrders().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        /*return summaryExpandData.get(groupPosition).hashCode();*/
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        /*return summaryExpandData.get(groupPosition).getOrders().get(childPosition).hashCode();*/
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
            convertView = infalInflater.inflate(R.layout.salesman_adapter_summary_parent, null);     }
        /*SummaryReport cat = summaryExpandData.get(groupPosition);*/
        TextView tx_cust_name = (TextView)convertView.findViewById(R.id.tx_cust_name);
        TextView txt_cust_amt= (TextView)convertView.findViewById(R.id.tx_stock);
        ImageView imgIndicator = (ImageView)convertView.findViewById(R.id.due_indicator);
        final LinearLayout linearLayout=(LinearLayout)convertView.findViewById(R.id.lnrVisible);

        if(isExpanded) {
            linearLayout.setVisibility(View.VISIBLE);
            //  imgIndicator.s
            imgIndicator.setImageResource(R.drawable.arrow_up_float);
        }else {
            linearLayout.setVisibility(View.GONE);
            imgIndicator.setImageResource(R.drawable.arrow_down_float);
        }

       /* tx_cust_name.setText(cat.getClientName());
        txt_cust_amt.setText(String.valueOf(cat.getTotalAmount()));*/
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.salesman_adapter_summary_child, null);
        }
        /*Orders cat = summaryExpandData.get(groupPosition).getOrders().get(childPosition);*/
        TextView tx_invoicedate = (TextView)convertView.findViewById(R.id.tx_date);
        TextView tx_orderNo = (TextView)convertView.findViewById(R.id.tx_orderNo);
        TextView tx_amt = (TextView)convertView.findViewById(R.id.tx_amt);
        /*tx_invoicedate.setText(cat.getDoc_Date());
        tx_orderNo.setText(cat.getDOC_NO());
        tx_amt.setText(String.valueOf(cat.getAmount()));*/
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
