package com.stackoverflow.uknow.DesignationPredictor.Outputs;

import java.util.ArrayList;
import java.util.List;

public class value {
    public void setColumnNames(List<String> columnNames) {
        ColumnNames = columnNames;
    }

    public void setColumnTypes(List<String> columnTypes) {
        ColumnTypes = columnTypes;
    }

    public void setValues(List<List<String>> values) {
        Values = values;
    }

    List<String> ColumnNames = new ArrayList<>();
    List<String> ColumnTypes = new ArrayList<>();
    List<List<String>> Values = new ArrayList<>();

    public value() {

    }

    public List<String> getColumnNames() {
        return ColumnNames;
    }

    public List<String> getColumnTypes() {
        return ColumnTypes;
    }

    public List<List<String>> getValues() {
        return Values;
    }
}
