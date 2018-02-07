package com.stackoverflow.uknow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stackoverflow.uknow.CityPredictor.City_retrofitInterface;
import com.stackoverflow.uknow.DesignationPredictor.Designation_retrofitInterface;
import com.stackoverflow.uknow.DesignationPredictor.Input.Inputs;
import com.stackoverflow.uknow.DesignationPredictor.Input.Sendinput;
import com.stackoverflow.uknow.DesignationPredictor.Input.input1;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.Output;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.output1;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.value;

import java.io.IOException;
import java.lang.reflect.Type;
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

public class Result extends AppCompatActivity {

    public static final String desugnation_url =
            "https://ussouthcentral.services.azureml.net/workspaces/d5a3961180be4de8850d83ef70d39f6d/services/a1c3394af8ce4e04bfbf9c3a5df46b55/";
    public static final String designation_apiKey =
            "Bearer aAg/ISK01rLNadQN8CV411o80bqhxAsweP7g5KrCHfXazM3PZeQ5shgaCNIgO+goMnCI1Nm3fX/JuEHkcq9SzA==";

    public static final String city_url =
            "https://ussouthcentral.services.azureml.net/workspaces/d5a3961180be4de8850d83ef70d39f6d/services/bc508eeb3b7a45fba845afb9203d2d15/";
    public static final String city_apiKey =
            "Bearer +p+ZYUfF39hz1mHPO/inQwQDLMo0TksFpBjyHRKh6WBDdZco6tVDhUEzhXut+7SgtUPIsr3gnCI88RRGPHdtpA==";

    String branch;
    private String clg;
    private double cg,ten,twelwe;
    double English_marks = 0, Logic_marks = 0, Basic_cp_marks = 0, Personality_marks = 0, Branch_specific_marks = 0, TOTAL_SCORE = 0;
    int agreeableness = 0, conscientiousness = 0, extraversion = 0, nueroticism = 0, openess_to_experience = 0;

    TextView english_text_view, logic_text_view, Basic_text_view, Personality_text_view, branch_text_view,
            agreeableness_text_view, conscientiousness_text_view, extraversion_text_view, nueroticism_text_view, openess_to_experience_text_view;

    PieChart city_piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TOTAL_SCORE = Double.parseDouble(getIntent().getExtras().getString("Total"));

        cg = Double.parseDouble(getIntent().getExtras().getString("CG"));
        ten = Double.parseDouble(getIntent().getExtras().getString("Ten"));
        twelwe = Double.parseDouble(getIntent().getExtras().getString("Twelve"));

        branch = getIntent().getExtras().getString("Branch");
        clg = getIntent().getExtras().getString("College");

        English_marks = Double.parseDouble(getIntent().getExtras().getString("Eng"));
        Logic_marks = Double.parseDouble(getIntent().getExtras().getString("Log"));
        Basic_cp_marks = Double.parseDouble(getIntent().getExtras().getString("Basic"));
        Personality_marks = Double.parseDouble(getIntent().getExtras().getString("Pers"));
        Branch_specific_marks = Double.parseDouble(getIntent().getExtras().getString("Basic"));

        agreeableness =(int) Double.parseDouble(getIntent().getExtras().getString("Agreebleness"));
        conscientiousness = (int) Double.parseDouble(getIntent().getExtras().getString("Conscientiousness"));
        extraversion = (int) Double.parseDouble(getIntent().getExtras().getString("Extraversion"));
        nueroticism = (int) Double.parseDouble(getIntent().getExtras().getString("Nueroticism"));
        openess_to_experience = (int) Double.parseDouble(getIntent().getExtras().getString("Openess to experience"));


        english_text_view = (TextView) findViewById(R.id.english_marks);
        logic_text_view = (TextView) findViewById(R.id.logic_marks);
        Basic_text_view = (TextView) findViewById(R.id.basic_programming_marks);
        Personality_text_view = (TextView) findViewById(R.id.personality_marks);
        branch_text_view = (TextView) findViewById(R.id.branch_specific_marks);
        agreeableness_text_view = (TextView) findViewById(R.id.q5);
        conscientiousness_text_view = (TextView) findViewById(R.id.q4);
        extraversion_text_view = (TextView) findViewById(R.id.q2);
        nueroticism_text_view = (TextView) findViewById(R.id.q3);
        openess_to_experience_text_view = (TextView) findViewById(R.id.q1);

        city_piechart = (PieChart) findViewById(R.id.city_piechart);
        city_piechart.setUsePercentValues(true);

        english_text_view.setText(""+English_marks);
        logic_text_view.setText(""+Logic_marks);
        Basic_text_view.setText("" + Basic_cp_marks);
        Personality_text_view.setText(""+Personality_marks);
        branch_text_view.setText(""+Branch_specific_marks);

        agreeableness_text_view.setText(""+agreeableness);
        conscientiousness_text_view.setText(""+conscientiousness);
        extraversion_text_view.setText(""+extraversion);
        nueroticism_text_view.setText(""+ nueroticism);
        openess_to_experience_text_view.setText(""+openess_to_experience);

        predictCity();

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
                setupCityPiechart(output1);
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
        else if (branch.equals("Civil")){
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

    public void setupCityPiechart(output1 output1){
        value predictedValues = output1.getValue();
        List<String> probabilities = predictedValues.getValues().get(0);
        List<PieEntry> yvalues = new ArrayList<>();
        int count = 0;
        for (int i = 17; i<=30 ; i++){
            yvalues.add(new PieEntry(Float.parseFloat(probabilities.get(i)), predictedValues.getColumnNames().get(i)));
            count++;
        }
        PieDataSet dataSet = new PieDataSet(yvalues, "Probabilities of Cities");

        List<String> xVals = new ArrayList<String>();
        xVals.add("Ahmedabad");
        xVals.add("Bangalore");
        xVals.add("Bhopal");
        xVals.add("Bhubaneshwar");
        xVals.add("Chandigarh");
        xVals.add("Chennai");
        xVals.add("Delhi");
        xVals.add("Ghaziabad");
        xVals.add("Gurgaon");
        xVals.add("Hyderabad");
        xVals.add("Kolkata");
        xVals.add("Mumbai");
        xVals.add("Noida");
        xVals.add("Pune");

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        city_piechart.setData(data);

        //Disable Hole in the Pie Chart
        city_piechart.setDrawHoleEnabled(false);

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
    }

}
