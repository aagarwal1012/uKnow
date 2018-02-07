package com.stackoverflow.uknow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.stackoverflow.uknow.Classes.Dashboard;

public class MainActivity extends AppCompatActivity {

    Button start;
    TextView dashboard;
    TextView x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (TextView) findViewById(R.id.textView);
        x.setMovementMethod(new ScrollingMovementMethod());

        dashboard = (TextView) findViewById(R.id.textView5);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Dashboard.class));
            }
        });

        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FillDetails.class));
            }
        });

    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.putExtra("logout", true);
            startActivity(intent);
            finish();
        }
    }
}
