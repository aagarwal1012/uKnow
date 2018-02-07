package com.stackoverflow.uknow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button logout, start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = (Button) findViewById(R.id.logout_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
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
