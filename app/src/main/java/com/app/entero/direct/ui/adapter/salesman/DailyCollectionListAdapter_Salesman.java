package com.app.entero.direct.ui.adapter.salesman;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.DailyCollection_Report_Model;

public class DailyCollectionListAdapter_Salesman extends BaseExpandableListAdapter {

    private Context context;
   // private String expandableListTitle;
    private List<DailyCollection_Report_Model> expandableListDetail;

    public DailyCollectionListAdapter_Salesman(Context context,
                                               List<DailyCollection_Report_Model> expandableListDetail) {
        this.context = context;
     //   this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
       /* return this.expandableListDetail.get(this.expandableListDetail.get(listPosition))
                .get(expandedListPosition);*/
       return expandableListDetail.get(listPosition).getPaymentDetails().get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandableListDetail.get(listPosition).getPaymentDetails().get(expandedListPosition).hashCode();
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        //final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.salesman_child_expand_daily_collections_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.text_daily_collection_name);
        TextView text_daily_collection_time = (TextView) convertView
                .findViewById(R.id.text_daily_collection_time);
        TextView text_daily_collection_amount = (TextView) convertView
                .findViewById(R.id.text_daily_collection_amount);
        expandedListTextView.setText(expandableListDetail.get(listPosition).getPaymentDetails().get(expandedListPosition).getChemistLegalName());
        text_daily_collection_time.setText(expandableListDetail.get(listPosition).getPaymentDetails().get(expandedListPosition).getPaymentTime());
        text_daily_collection_amount.setText(expandableListDetail.get(listPosition).getAmount());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        int size = expandableListDetail.get(listPosition).getPaymentDetails().size();
        return size;
    }

    @Override
    public Object getGroup(int listPosition) {
        return expandableListDetail.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return expandableListDetail.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
       // String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.salesman_expand_daily_collections_item, null);
        }
        TextView daily_collection_date = (TextView) convertView
                .findViewById(R.id.daily_collection_date);
        daily_collection_date.setTypeface(null, Typeface.BOLD);
        daily_collection_date.setText(expandableListDetail.get(listPosition).getPaymentDate());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
