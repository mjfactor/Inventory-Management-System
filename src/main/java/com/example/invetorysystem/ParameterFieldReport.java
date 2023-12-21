package com.example.invetorysystem;

import java.util.List;

public class ParameterFieldReport {
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<historyData> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<historyData> fieldList) {
        this.fieldList = fieldList;
    }

    public ParameterFieldReport(String total, List<historyData> fieldList) {
        this.total = total;
        this.fieldList = fieldList;
    }

    String   total;
    List<historyData> fieldList;
}
