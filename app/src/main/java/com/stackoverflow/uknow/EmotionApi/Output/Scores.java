package com.stackoverflow.uknow.EmotionApi.Output;

/**
 * Created by Ayush on 05-Feb-18.
 */

public class Scores {

    String anger, contempt, disgust, fear, happiness, neutral, sadness, surprise;

    public String getAnger() {
        return anger;
    }

    public void setAnger(String anger) {
        this.anger = anger;
    }

    public String getContempt() {
        return contempt;
    }

    public void setContempt(String contempt) {
        this.contempt = contempt;
    }

    public String getDisgust() {
        return disgust;
    }

    public void setDisgust(String disgust) {
        this.disgust = disgust;
    }

    public String getFear() {
        return fear;
    }

    public void setFear(String fear) {
        this.fear = fear;
    }

    public String getHappiness() {
        return happiness;
    }

    public void setHappiness(String happiness) {
        this.happiness = happiness;
    }

    public String getNeutral() {
        return neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    public String getSadness() {
        return sadness;
    }

    public void setSadness(String sadness) {
        this.sadness = sadness;
    }

    public String getSurprise() {
        return surprise;
    }

    public void setSurprise(String surprise) {
        this.surprise = surprise;
    }

    public Scores() {

    }

    public Scores(String anger, String contempt, String disgust, String fear, String happiness, String neutral, String sadness, String surprise) {

        this.anger = anger;
        this.contempt = contempt;
        this.disgust = disgust;
        this.fear = fear;
        this.happiness = happiness;
        this.neutral = neutral;
        this.sadness = sadness;
        this.surprise = surprise;
    }
}
