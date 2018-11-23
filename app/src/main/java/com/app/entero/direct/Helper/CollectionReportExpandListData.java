package com.app.entero.direct.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollectionReportExpandListData {

    static ArrayList<List<String>> stringListdata;

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> first = new ArrayList<String>();
        first.add("Zoya Medicals");
        first.add("Naveen Pharmacy");
        first.add("New Care Medicals");

        List<String> second = new ArrayList<String>();
        second.add("Shree Siddhivinayak Pharmacy");
        second.add("Zoya Pharmacy");


        List<String> thired = new ArrayList<String>();
        thired.add("Siddhivinayak Pharmacy");
        thired.add("Naveen Pharmacy");
        thired.add("Zoya Medicals");
        thired.add("New Care Pharmacy");

        List<String> date = new ArrayList<String>();
        date.add("26 Sep 2018");
        date.add("25 Sep 2018");
        date.add("24 Sep 2018");

        stringListdata = new ArrayList<List<String>>();
        stringListdata.add(first);
        stringListdata.add(second);
        stringListdata.add(thired);

        for (int i = 0; i < stringListdata.size(); i++) {
            expandableListDetail.put(date.get(i), stringListdata.get(i));
        }
       /* expandableListDetail.put(date.get(1), second);
        expandableListDetail.put(date.get(2), thired);*/

        return expandableListDetail;
    }
}
