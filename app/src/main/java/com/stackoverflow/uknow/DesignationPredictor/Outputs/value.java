package com.stackoverflow.uknow.DesignationPredictor.Outputs;

import java.util.ArrayList;
import java.util.List;

public class value {
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
