package com.stackoverflow.uknow.EmotionApi.Output;

import java.util.List;

/**
 * Created by Ayush on 05-Feb-18.
 */

public class FaceApiResult {
    FaceRectangle faceRectangle;
    Scores scores;

    public FaceRectangle getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(FaceRectangle faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public Scores getScores() {
        return scores;
    }

    public void setScores(Scores scores) {
        this.scores = scores;
    }

    public FaceApiResult() {

    }

    public FaceApiResult(FaceRectangle faceRectangle, Scores scores) {

        this.faceRectangle = faceRectangle;
        this.scores = scores;
    }
}
