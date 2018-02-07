package com.stackoverflow.uknow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FillDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText cgpa, tenth,twelth, college, branch;
    private String clg,bra;
    private double cg,ten,twelwe;
    String text;

    Button submitBtn;
    Spinner spin1,spin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_details);

        //both the spinners

        String []colleges = new String[]{
                "IITs",
                "NITs",
                "IIITs",
                "BITS Pilani",
                "Others"
        };

        spin1 = findViewById(R.id.spin1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,R.layout.spinner_style,colleges);
        //ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.colleges, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(this);

        String []branch = new String[]{
                "Computer Science",
                "Electronics and Communication",
                "Electrical",
                "Mechanical",
                "Civil"
        };

        spin2 = findViewById(R.id.spin2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.spinner_style,branch);
        //ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.branch, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter1);
        spin2.setOnItemSelectedListener(this);

        //all other text view and buttons
        cgpa= (EditText) findViewById(R.id.cgpa);
        tenth= (EditText) findViewById(R.id.tenth);
        twelth= (EditText) findViewById(R.id.twelth);
        submitBtn= (Button) findViewById(R.id.submitbtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(); //submitting the values
            }
        });
    }

    public void submit(){

        if(!(cgpa.getText().toString().isEmpty() || tenth.getText().toString().isEmpty() || twelth.getText().toString().isEmpty())){
            initialize();

            if(!validate())
                Toast.makeText(this, "input is inappropriate", Toast.LENGTH_SHORT).show();
            else
                submitsuccess();
        }
        else {
            Toast.makeText(this, "please input values", Toast.LENGTH_SHORT).show();
        }

    }

    public void submitsuccess(){
        Intent intent = new Intent(FillDetails.this, QuestionsActivity.class);
        intent.putExtra("CG", Double.toString(cg));
        intent.putExtra("Ten", Double.toString(ten));
        intent.putExtra("Twelve", Double.toString(twelwe));
        intent.putExtra("Branch", getBranch(bra));
        intent.putExtra("College", clg);
        startActivity(intent);

    }

    public boolean validate(){
        boolean valid = true;

        if(cg<5 || cg >10.0 || cgpa.getText().toString().equals("")){
            Toast.makeText(this, "Input invalid ", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if(ten<=35 || ten>100 || tenth.getText().toString().equals("")){
            Toast.makeText(this, "Input Invalid", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if(twelwe<=35 || twelwe>100 || twelth.getText().toString().equals("")){
            Toast.makeText(this, "Input Invalid", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    public void initialize(){
        cg = Double.valueOf(cgpa.getText().toString());
        ten = Double.valueOf(tenth.getText().toString());
        twelwe = Double.valueOf(twelth.getText().toString());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.spin1)
        {
            clg = parent.getItemAtPosition(i).toString();
        }
        if(spin2.getId() == R.id.spin2)
        {
            bra = parent.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(),"please select a option",Toast.LENGTH_SHORT).show();
    }

    String getBranch(String branch){
        if (branch.equals("Computer Science"))
            return "CSE";
        else if(branch.equals("Electronics and Communication"))
            return "ECE";
        else if(branch.equals("Electrical"))
            return "EE";
        else if(branch.equals("Mechanical"))
            return "MECH";
        else
            return "CIVIL";
    }
}
