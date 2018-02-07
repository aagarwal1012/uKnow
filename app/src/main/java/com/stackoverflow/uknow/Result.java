package com.stackoverflow.uknow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stackoverflow.uknow.Classes.IncomeCalculator;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.value;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {

    public static final String desugnation_url =
            "https://ussouthcentral.services.azureml.net/workspaces/d5a3961180be4de8850d83ef70d39f6d/services/a1c3394af8ce4e04bfbf9c3a5df46b55/";
    public static final String designation_apiKey =
            "Bearer aAg/ISK01rLNadQN8CV411o80bqhxAsweP7g5KrCHfXazM3PZeQ5shgaCNIgO+goMnCI1Nm3fX/JuEHkcq9SzA==";

    public static final String city_url =
            "https://ussouthcentral.services.azureml.net/workspaces/d5a3961180be4de8850d83ef70d39f6d/services/bc508eeb3b7a45fba845afb9203d2d15/";
    public static final String city_apiKey =
            "Bearer +p+ZYUfF39hz1mHPO/inQwQDLMo0TksFpBjyHRKh6WBDdZco6tVDhUEzhXut+7SgtUPIsr3gnCI88RRGPHdtpA==";

    String timestamp;
    String branch;
    private String clg;
    private double cg,ten,twelwe;
    double English_marks = 0, Logic_marks = 0, Basic_cp_marks = 0, Personality_marks = 0, Branch_specific_marks = 0, TOTAL_SCORE = 0;
    int agreeableness = 0, conscientiousness = 0, extraversion = 0, nueroticism = 0, openess_to_experience = 0;

    TextView english_text_view, logic_text_view, Basic_text_view, Personality_text_view, branch_text_view,
            agreeableness_text_view, conscientiousness_text_view, extraversion_text_view, nueroticism_text_view, openess_to_experience_text_view;

    PieChart city_piechart, designation_piechart;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    value city_value, designation_value;

    TextView income, city, designation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        TOTAL_SCORE = Double.parseDouble(getIntent().getExtras().getString("Total"));

        cg = Double.parseDouble(getIntent().getExtras().getString("CG"));
        ten = Double.parseDouble(getIntent().getExtras().getString("Ten"));
        twelwe = Double.parseDouble(getIntent().getExtras().getString("Twelve"));

        branch = getIntent().getExtras().getString("Branch");
        clg = getIntent().getExtras().getString("College");

        timestamp = getIntent().getExtras().getString("TimeStamp");

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

        city_piechart = (PieChart) findViewById(R.id.city_piechart);
        city_piechart.setUsePercentValues(true);

        designation_piechart = (PieChart) findViewById(R.id.designation_piechart);
        designation_piechart.setUsePercentValues(true);

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

        income = (TextView) findViewById(R.id.income_text_view);
        city = (TextView) findViewById(R.id.city);
        designation = (TextView) findViewById(R.id.designation);

        double percentage = ((English_marks+Logic_marks+Basic_cp_marks+Personality_marks+Branch_specific_marks)/45)*100;

        IncomeCalculator incomeCalculator = new IncomeCalculator(cg,percentage, clg );
        Double income_x = ((int)(incomeCalculator.getP()*100))/100.0;
        income.setText(""+income_x+" Lacs per Year");


        databaseReference.child("City_Designation_Predicted").child(timestamp).child("City").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                city_value = dataSnapshot.getValue(value.class);
                if (city_value != null){
                    value predictedValues = city_value;
                    List<String> probabilities = predictedValues.getValues().get(0);
                    List<Entry> yvalues = new ArrayList<>();
                    List<String> xVals = new ArrayList<String>();
                    int count = 0;
                    for (int i = 17; i<=30 ; i++){
                        if(Float.parseFloat(probabilities.get(i))>=0.05){
                            yvalues.add(new Entry(Float.parseFloat(probabilities.get(i)), count));
                            xVals.add(getCityName(predictedValues.getColumnNames().get(i)));
                            count++;
                        }
                    }
                    PieDataSet dataSet = new PieDataSet(yvalues, "");


                    PieData data = new PieData(xVals,dataSet);

                    data.setValueFormatter(new PercentFormatter());

                    //Disable Hole in the Pie Chart
                    city_piechart.setDrawHoleEnabled(false);
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    city_piechart.setDrawHoleEnabled(true);
                    city_piechart.setTransparentCircleRadius(30f);
                    city_piechart.setHoleRadius(30f);

                    city_piechart.setDescription("City Predictor");

                    data.setValueTextSize(13f);
                    data.setValueTextColor(Color.DKGRAY);

                    city_piechart.setData(data);

                    city.setText(probabilities.get(probabilities.size()-1));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("City_Designation_Predicted").child(timestamp).child("Designation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                designation_value = dataSnapshot.getValue(value.class);
                if (designation_value != null){
                    value predictedValues = designation_value;
                    List<String> probabilities = predictedValues.getValues().get(0);
                    List<Entry> yvalues = new ArrayList<>();
                    List<String> xVals = new ArrayList<String>();
                    int count = 0;
                    for (int i = 17; i<=48 ; i++){
                        if(Float.parseFloat(probabilities.get(i))>=0.05){
                            yvalues.add(new Entry(Float.parseFloat(probabilities.get(i)), count));
                            xVals.add(getDesignationName(predictedValues.getColumnNames().get(i)));
                            count++;
                        }
                    }
                    PieDataSet dataSet = new PieDataSet(yvalues, "");


                    PieData data = new PieData(xVals,dataSet);

                    data.setValueFormatter(new PercentFormatter());

                    //Disable Hole in the Pie Chart
                    designation_piechart.setDrawHoleEnabled(false);
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    designation_piechart.setDrawHoleEnabled(true);
                    designation_piechart.setTransparentCircleRadius(30f);
                    designation_piechart.setHoleRadius(30f);

                    designation_piechart.setDescription("Designation Predictor");

                    data.setValueTextSize(13f);
                    data.setValueTextColor(Color.DKGRAY);

                    designation_piechart.setData(data);

                    designation.setText(probabilities.get(probabilities.size()-1));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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


    String getCityName(String name){
        if (name.equals("Scored Probabilities for Class \"Ahmedabad\""))
            return "Ahmedabad";
        else if (name.equals("Scored Probabilities for Class \"Bangalore\""))
            return "Bangalore";
        else if (name.equals("Scored Probabilities for Class \"Bhopal\""))
            return "Bhopal";
        else if (name.equals("Scored Probabilities for Class \"Chandigarh\""))
            return "Chandigarh";
        else if (name.equals("Scored Probabilities for Class \"Chennai\""))
            return "Chennai";
        else if (name.equals("Scored Probabilities for Class \"Delhi\""))
            return "Delhi";
        else if (name.equals("Scored Probabilities for Class \"Ghaziabad\""))
            return "Ghaziabad";
        else if (name.equals("Scored Probabilities for Class \"Gurgaon\""))
            return "Gurgaon";
        else if (name.equals("Scored Probabilities for Class \"Hyderabad\""))
            return "Hyderabad";
        else if (name.equals("Scored Probabilities for Class \"Kolkata\""))
            return "Kolkata";
        else if (name.equals("Scored Probabilities for Class \"Mumbai\""))
            return "Mumbai";
        else if (name.equals("Scored Probabilities for Class \"Noida\""))
            return "Noida";
        else if (name.equals("Scored Probabilities for Class \"Pune\""))
            return "Pune";
        else if (name.equals("Scored Probabilities for Class \"Bhubaneshwar\""))
            return "Bhubaneshwar";
        else
            return null;

    }

    String getDesignationName(String name){
        if(name.equals("Scored Probabilities for Class \".Net Developer\""))
            return ".Net Developer";
        else if(name.equals("Scored Probabilities for Class \"Application Developer\""))
            return "Application Developer";
        else if(name.equals("Scored Probabilities for Class \"asp.Net Developer\""))
            return "asp.Net Developer";
        else if(name.equals("Scored Probabilities for Class \"Automation Engineer\""))
            return "Automation Engineer";
        else if(name.equals("Scored Probabilities for Class \"Business Analyst\""))
            return "Business Analyst";
        else if(name.equals("Scored Probabilities for Class \"Customer Service\""))
            return "Customer Service";
        else if(name.equals("Scored Probabilities for Class \"Data Analyst\""))
            return "Data Analyst";
        else if(name.equals("Scored Probabilities for Class \"Designer\""))
            return "Designer";
        else if(name.equals("Scored Probabilities for Class \"Desktop Engineer\""))
            return "Desktop Engineer";
        else if(name.equals("Scored Probabilities for Class \"Electrical Engineer\""))
            return "Electrical Engineer";
        else if(name.equals("Scored Probabilities for Class \"Game Developer\""))
            return "Game Developer";
        else if(name.equals("Scored Probabilities for Class \"Information Security\""))
            return "Information Security";
        else if(name.equals("Scored Probabilities for Class \"Mobile Developer\""))
            return "Mobile Developer";
        else if(name.equals("Scored Probabilities for Class \"Network Engineer\""))
            return "Network Engineer";
        else if(name.equals("Scored Probabilities for Class \"Operations Analyst\""))
            return "Operations Analyst";
        else if(name.equals("Scored Probabilities for Class \"Product Engineer\""))
            return "Product Engineer";
        else if(name.equals("Scored Probabilities for Class \"Programmer\""))
            return "Programmer";
        else if(name.equals("Scored Probabilities for Class \"Project Engineer\""))
            return "Project Engineer";
        else if(name.equals("Scored Probabilities for Class \"Quality Analyst\""))
            return "Quality Analyst";
        else if(name.equals("Scored Probabilities for Class \"Quality Engineer\""))
            return "Quality Engineer";
        else if(name.equals("Scored Probabilities for Class \"Research Engineer\""))
            return "Research Engineer";
        else if(name.equals("Scored Probabilities for Class \"Sales Engineer\""))
            return "Sales Engineer";
        else if(name.equals("Scored Probabilities for Class \"Software Developer\""))
            return "Software Developer";
        else if(name.equals("Scored Probabilities for Class \"Software Engineer\""))
            return "Software Engineer";
        else if(name.equals("Scored Probabilities for Class \"System Engineer\""))
            return "System Engineer";
        else if(name.equals("Scored Probabilities for Class \"Systems Analyst\""))
            return "Systems Analyst";
        else if(name.equals("Scored Probabilities for Class \"Systems Engineer\""))
            return "Systems Engineer";
        else if(name.equals("Scored Probabilities for Class \"Technical Engineer\""))
            return "Technical Engineer";
        else if(name.equals("Scored Probabilities for Class \"Telecom Engineer\""))
            return "Telecom Engineer";
        else if(name.equals("Scored Probabilities for Class \"Test Engineer\""))
            return "Test Engineer";
        else if(name.equals("Scored Probabilities for Class \"Web Developer\""))
            return "Web Developer";
        else if(name.equals("Scored Probabilities for Class \"Database Developer\""))
            return "Database Developer";
        else
            return null;

    }

}
