package com.stackoverflow.uknow.DesignationPredictor.Outputs;

/**
 * Created by Ayush on 05-Feb-18.
 */

public class Output {
    Results Results;

    public Results getResults() {
        return Results;
    }

    public Output() {
    }

    public void setResults(com.stackoverflow.uknow.DesignationPredictor.Outputs.Results results) {
        Results = results;
    }

    public Output(Results results) {


        Results = results;
    }
}
