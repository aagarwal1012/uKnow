package com.stackoverflow.uknow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.stackoverflow.uknow.Classes.Questions.GetQuestions;
import com.stackoverflow.uknow.Classes.Questions.Questions;

public class QuestionsActivity extends AppCompatActivity {

    TextView ques_no, ques_desc;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3, option4;
    Questions current_ques;
    Button next;
    int qid = 0;
    GetQuestions obj = new GetQuestions();
    String branch;
    private String clg;
    private double cg,ten,twelwe;
    double English_marks = 0, Logic_marks = 0, Basic_cp_marks = 0, Personality_marks = 0, Branch_specific_marks = 0, TOTAL_SCORE = 0;
    int agreeableness = 0, conscientiousness = 0, extraversion = 0, nueroticism = 0, openess_to_experience = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ques_desc = (TextView) findViewById(R.id.question_des);
        ques_no = (TextView) findViewById(R.id.question_no);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        option1 = (RadioButton) findViewById(R.id.option_1);
        option2 = (RadioButton) findViewById(R.id.option_2);
        option3 = (RadioButton) findViewById(R.id.option_3);
        option4 = (RadioButton) findViewById(R.id.option_4);
        next = (Button) findViewById(R.id.next);

        cg = Double.parseDouble(getIntent().getExtras().getString("CG"));
        ten = Double.parseDouble(getIntent().getExtras().getString("Ten"));
        twelwe = Double.parseDouble(getIntent().getExtras().getString("Twelve"));
        branch = getIntent().getExtras().getString("Branch");
        clg = getIntent().getExtras().getString("College");

        current_ques = obj.getEnglish_single_correct().get(qid);
        set_Question_View();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup =(RadioGroup)findViewById(R.id.radio_group);
                String response = getResponse(radioGroup.getCheckedRadioButtonId());
                //RadioButton answer = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                if (qid<2){
                    if (response.equals(current_ques.getAnswers())== true){
                        English_marks=English_marks+3;
                    }
                }
                if(qid>2 && qid<=5){
                    if(response.equals(current_ques.getAnswers())==true){
                        Logic_marks=Logic_marks+3;
                    }
                }
                if (qid>5 && qid<=8){
                    if (response.equals(current_ques.getAnswers())==true){
                        Basic_cp_marks=Basic_cp_marks+3;
                    }
                }
                if(qid==9){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+0.5;
                        openess_to_experience=openess_to_experience+3;
                    }else{
                        Personality_marks=Personality_marks+1;
                        openess_to_experience=openess_to_experience+4;
                    }
                }
                if(qid==10){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+2;
                        extraversion=extraversion+5;
                    }else{
                        Personality_marks=Personality_marks+0.5;
                        extraversion=extraversion+2;
                    }
                }
                if(qid==11){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+0;
                        nueroticism=nueroticism-2;
                    }else{
                        Personality_marks=Personality_marks+1;
                        nueroticism=nueroticism+1;
                    }
                }
                if(qid==12){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+2;
                        conscientiousness=conscientiousness+3;
                    }else{
                        conscientiousness=conscientiousness-4;
                    }
                }
                if(qid==13){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+1;
                        agreeableness=agreeableness+2;
                    }else{
                        agreeableness=agreeableness+5;
                        Personality_marks=Personality_marks+2;
                    }
                }
                if (qid > 13 && qid <= 16) {
                    if (response.equals(current_ques.getAnswers())==true){
                        Branch_specific_marks=Branch_specific_marks+3;
                    }
                }
                TOTAL_SCORE=Logic_marks+English_marks+Branch_specific_marks+Basic_cp_marks+Personality_marks;
                radioGroup.clearCheck();
                if(qid>16){
                    Intent intent1 = new Intent(QuestionsActivity.this,Result.class);
                    intent1.putExtra("Total", Double.toString(TOTAL_SCORE));
                    intent1.putExtra("CG", Double.toString(cg));
                    intent1.putExtra("Ten", Double.toString(ten));
                    intent1.putExtra("Twelve", Double.toString(twelwe));
                    intent1.putExtra("Branch", branch);
                    intent1.putExtra("College", clg);
                    intent1.putExtra("Eng",Double.toString(English_marks));
                    intent1.putExtra("Log",Double.toString(Logic_marks));
                    intent1.putExtra("Pers",Double.toString(Personality_marks));
                    intent1.putExtra("Basic",Double.toString(Basic_cp_marks));
                    intent1.putExtra("Brnch",Double.toString(Branch_specific_marks));
                    intent1.putExtra("Agreebleness",Double.toString(agreeableness));
                    intent1.putExtra("Conscientiousness",Double.toString(conscientiousness));
                    intent1.putExtra("Extraversion",Double.toString(extraversion));
                    intent1.putExtra("Nueroticism",Double.toString(nueroticism));
                    intent1.putExtra("Openess to experience",Double.toString(openess_to_experience));
                    startActivity(intent1);
                }

                if(qid<=2){
                    current_ques=obj.english_single_correct.get(qid);
                    set_Question_View();
                }else if(qid>2 && qid<=5){
                    current_ques=obj.logic_single_correct.get(qid-2);
                    set_Question_View();
                }else if(qid>5 && qid<=8){
                    current_ques=obj.Basic_C_single_correct.get(qid-5);
                    set_Question_View();
                }else if (qid>8 && qid<=13){
                    current_ques=obj.personality_single_correct.get(qid-9);
                    set_Question_View();
                }else if(qid>13 && qid<=16){
                    if(branch.equals("CSE")){
                        current_ques=obj.CSE_single_correct.get(qid-13);
                        set_Question_View();
                    }else if(branch.equals("EE")){
                        current_ques=obj.electrical_single_correct.get(qid-13);
                        set_Question_View();
                    }else if(branch.equals("ECE")){
                        current_ques=obj.electronics_single_correct.get(qid-13);
                        set_Question_View();
                    }else if(branch.equals("MECH")){
                        current_ques=obj.mechanical_single_correct.get(qid-13);
                        set_Question_View();
                    }else if(branch.equals("CIVIL")){
                        current_ques=obj.civil_single_correct.get(qid-13);
                        set_Question_View();
                    }
                }
            }
        });

    }

    private void set_Question_View(){
        ques_desc.setText(current_ques.getQues_desc());
        option1.setText(current_ques.getOption1());
        option2.setText(current_ques.getOption2());
        option3.setText(current_ques.getOption3());
        option4.setText(current_ques.getOption4());
        qid++;
        ques_no.setText("Q"+qid+".");
    }

    private String getResponse(int id){
        if (id == R.id.option_1)
            return "A";
        else if(id == R.id.option_2)
            return "B";
        else if (id == R.id.option_3)
            return "C";
        else
            return "D";
    }
}
