package com.stackoverflow.uknow.EmotionApi.Output;

/**
 * Created by Ayush on 05-Feb-18.
 */

public class FaceRectangle {
    String left, top, width, height;

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public FaceRectangle() {

    }

    public FaceRectangle(String left, String top, String width, String height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }
}
