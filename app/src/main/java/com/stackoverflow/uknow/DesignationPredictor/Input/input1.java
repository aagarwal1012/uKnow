package com.stackoverflow.uknow.DesignationPredictor.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayush on 05-Feb-18.
 */

public class input1 {
    List<String> ColumnNames;
    List<List<String>> Values = new ArrayList<>();

    public input1(List<String> values) {
        Values.add(values);
        ColumnNames = new ArrayList<>();
        ColumnNames.add("10percentage");
        ColumnNames.add("12percentage");
        ColumnNames.add("collegeGPA");
        ColumnNames.add("English");
        ColumnNames.add("Logical");
        ColumnNames.add("Quant");
        ColumnNames.add("ComputerProgramming");
        ColumnNames.add("ElectronicsAndSemicon");
        ColumnNames.add("ComputerScience");
        ColumnNames.add("MechanicalEngg");
        ColumnNames.add("ElectricalEngg");
        ColumnNames.add("CivilEngg");
        ColumnNames.add("conscientiousness");
        ColumnNames.add("agreeableness");
        ColumnNames.add("extraversion");
        ColumnNames.add("nueroticism");
        ColumnNames.add("openess_to_experience");
    }

    public List<String> getColumnNames() {
        return ColumnNames;
    }

    public List<List<String>> getValues() {
        return Values;
    }
}
