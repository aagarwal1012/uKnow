package com.stackoverflow.uknow.Classes;

/**
 * Created by ayush on 7/2/18.
 */

public class InterviewResult {

    Double percentage_marks, income;

    String name, imageUrl, dataId;

    public InterviewResult(Double percentage_marks, Double income, String name, String imageUrl, String dataId) {
        this.percentage_marks = percentage_marks;
        this.income = income;
        this.name = name;
        this.imageUrl = imageUrl;
        this.dataId = dataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Double getPercentage_marks() {
        return percentage_marks;
    }

    public void setPercentage_marks(Double percentage_marks) {
        this.percentage_marks = percentage_marks;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }


}
