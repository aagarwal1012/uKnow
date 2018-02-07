package com.stackoverflow.uknow.Classes.Questions;

public class Questions {

    int ques_no;
    String ques_desc, answers , Option1, Option2, Option3, Option4;

    public Questions(int ques_no, String ques_desc, String answers, String option1, String option2, String option3, String option4) {
        this.ques_no = ques_no;
        this.ques_desc = ques_desc;
        this.answers = answers;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
    }

    public int getQues_no() {
        return ques_no;
    }

    public void setQues_no(int ques_no) {
        this.ques_no = ques_no;
    }

    public String getQues_desc() {
        return ques_desc;
    }

    public void setQues_desc(String ques_desc) {
        this.ques_desc = ques_desc;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getOption4() {
        return Option4;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }
}
