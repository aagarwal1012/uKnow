package com.stackoverflow.uknow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stackoverflow.uknow.CityPredictor.City_retrofitInterface;
import com.stackoverflow.uknow.Classes.Questions.GetQuestions;
import com.stackoverflow.uknow.Classes.Questions.Questions;
import com.stackoverflow.uknow.DesignationPredictor.Designation_retrofitInterface;
import com.stackoverflow.uknow.DesignationPredictor.Input.Inputs;
import com.stackoverflow.uknow.DesignationPredictor.Input.Sendinput;
import com.stackoverflow.uknow.DesignationPredictor.Input.input1;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.Output;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.output1;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsActivity extends AppCompatActivity {

    public static final String desugnation_url =
            "https://ussouthcentral.services.azureml.net/workspaces/d5a3961180be4de8850d83ef70d39f6d/services/a1c3394af8ce4e04bfbf9c3a5df46b55/";
    public static final String designation_apiKey =
            "Bearer aAg/ISK01rLNadQN8CV411o80bqhxAsweP7g5KrCHfXazM3PZeQ5shgaCNIgO+goMnCI1Nm3fX/JuEHkcq9SzA==";

    public static final String city_url =
            "https://ussouthcentral.services.azureml.net/workspaces/d5a3961180be4de8850d83ef70d39f6d/services/bc508eeb3b7a45fba845afb9203d2d15/";
    public static final String city_apiKey =
            "Bearer +p+ZYUfF39hz1mHPO/inQwQDLMo0TksFpBjyHRKh6WBDdZco6tVDhUEzhXut+7SgtUPIsr3gnCI88RRGPHdtpA==";

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
    double English_marks = 0, Logic_marks = 0, Basic_cp_marks = -3.0, Personality_marks = 0, Branch_specific_marks = -3.0, TOTAL_SCORE = 0;
    int agreeableness = 0, conscientiousness = 0, extraversion = 0, nueroticism = 0, openess_to_experience = 0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    long millis;
    String timestamp;

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

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitting...");

        millis = System.currentTimeMillis();
        timestamp = "" + millis;

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

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
                if (qid<=2){
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
                    }else if (response.equals("B")){
                        Personality_marks=Personality_marks+1;
                        openess_to_experience=openess_to_experience+4;
                    }else {
                        Personality_marks=Personality_marks+0;
                        openess_to_experience=openess_to_experience+0;
                    }
                }
                if(qid==10){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+2;
                        extraversion=extraversion+5;
                    }else if (response.equals("B")){
                        Personality_marks=Personality_marks+0.5;
                        extraversion=extraversion+2;
                    }else {
                        Personality_marks=Personality_marks+0;
                        extraversion=extraversion+0;
                    }
                }
                if(qid==11){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+0;
                        nueroticism=nueroticism-2;
                    }else if (response.equals("B")){
                        Personality_marks=Personality_marks+1;
                        nueroticism=nueroticism+1;
                    }else {
                        Personality_marks=Personality_marks+0;
                        nueroticism=nueroticism+0;
                    }
                }
                if(qid==12){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+2;
                        conscientiousness=conscientiousness+3;
                    }else if(response.equals("B")){
                        conscientiousness=conscientiousness-4;
                    }else {
                        conscientiousness=conscientiousness-0;
                    }

                }
                if(qid==13){
                    if(response.equals("A")==true){
                        Personality_marks=Personality_marks+1;
                        agreeableness=agreeableness+2;
                    }else if (response.equals("B")){
                        agreeableness=agreeableness+5;
                        Personality_marks=Personality_marks+2;
                    }
                    else{
                        agreeableness=agreeableness+0;
                        Personality_marks=Personality_marks+0;
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
                    progressDialog.show();
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
                    intent1.putExtra("TimeStamp", timestamp);

                    predictCity();
                    predictDesignation();
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 3000);
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

    public void predictDesignation(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", designation_apiKey)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(desugnation_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        List<String> values = getValues();
        input1 input1 = new input1(values);
        Inputs inputs = new Inputs(input1);
        Sendinput sendinput = new Sendinput(inputs);

        Designation_retrofitInterface retro = retrofit.create(Designation_retrofitInterface.class);

        Gson gson = new Gson();
        Type type = new TypeToken<Sendinput>() {}.getType();
        String json = gson.toJson(sendinput, type);
        Log.d("json request",json);

        Call<Output> call = retro.getResult("2.0", true,sendinput);
        call.enqueue(new Callback<Output>() {
            @Override
            public void onResponse(Call<Output> call, retrofit2.Response<Output> response) {
                Log.d("response",String.valueOf(response.code()));
                output1 output1 = response.body().getResults().getOutput1();
                Log.d("Message", "message received "+ output1.getType());
                databaseReference.child("City_Designation_Predicted").child(timestamp.toString()).child("Designation").setValue(output1.getValue());
            }

            @Override
            public void onFailure(Call<Output> call, Throwable t) {
                Log.d("Error", "Not Received");
            }
        });


    }

    public void predictCity(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", city_apiKey)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(city_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        List<String> values = getValues();
        input1 input1 = new input1(values);
        Inputs inputs = new Inputs(input1);
        Sendinput sendinput = new Sendinput(inputs);

        City_retrofitInterface retro = retrofit.create(City_retrofitInterface.class);

        Gson gson = new Gson();
        Type type = new TypeToken<Sendinput>() {}.getType();
        String json = gson.toJson(sendinput, type);
        Log.d("json request",json);

        Call<Output> call = retro.getResult("2.0", true,sendinput);
        call.enqueue(new Callback<Output>() {
            @Override
            public void onResponse(Call<Output> call, retrofit2.Response<Output> response) {
                Log.d("response",String.valueOf(response.code()));
                output1 output1 = response.body().getResults().getOutput1();
                Log.d("Message", "message received "+ output1.getType());
                Gson gson = new Gson();
                Type type = new TypeToken<output1>() {}.getType();
                String json = gson.toJson(output1, type);
                Log.d("json got",json);
                databaseReference.child("City_Designation_Predicted").child(timestamp.toString()).child("City").setValue(output1.getValue());
            }

            @Override
            public void onFailure(Call<Output> call, Throwable t) {
                Log.d("Error", "Not Received");
            }
        });


    }

    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        values.add(""+ten);
        values.add(""+twelwe);
        values.add(""+cg);
        values.add(""+English_marks);
        values.add(""+Logic_marks);
        values.add(""+Personality_marks);
        values.add(""+Basic_cp_marks);
        if (branch.equals("CSE")){
            values.add("0");
            values.add(""+Branch_specific_marks);
            values.add("0");
            values.add("0");
            values.add("0");
        }
        else if (branch.equals("ECE")){
            values.add(""+Branch_specific_marks);
            values.add("0");
            values.add("0");
            values.add("0");
            values.add("0");
        }
        else if (branch.equals("EE")){
            values.add("0");
            values.add("0");
            values.add("0");
            values.add(""+Branch_specific_marks);
            values.add("0");
        }
        else if (branch.equals("MECH")){
            values.add("0");
            values.add("0");
            values.add(""+Branch_specific_marks);
            values.add("0");
            values.add("0");
        }
        else if (branch.equals("CIVIL")){
            values.add("0");
            values.add("0");
            values.add("0");
            values.add("0");
            values.add(""+Branch_specific_marks);
        }
        values.add(""+conscientiousness);
        values.add(""+agreeableness);
        values.add(""+extraversion);
        values.add(""+nueroticism);
        values.add(""+openess_to_experience);
        return values;
    }

}
