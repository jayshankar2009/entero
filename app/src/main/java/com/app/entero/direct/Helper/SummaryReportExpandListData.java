package com.app.entero.direct.Helper;

import java.util.ArrayList;
import java.util.List;

import com.app.entero.direct.model.SummaryReport;

public class SummaryReportExpandListData {
    static List<SummaryReport> summaryExpandData;
    public static List<SummaryReport> getData() {

        summaryExpandData = new ArrayList<SummaryReport>();

        /*GsonBuilder builder = new GsonBuilder();
        Gson mGson = builder.create();
        Type listType = new TypeToken<List<summary_order>>() {
        }.getType();
        summaryExpandData = mGson.fromJson(json, listType);*/

        return summaryExpandData;
    }
}
