package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private static final String USER_NAME = "USER_NAME"; // Intent Extraas Key.
    private TextView m_tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Getting the intent (From the Login Activity) and extraas.
        Intent intent = getIntent();
        m_tvWelcome = findViewById(R.id.tv_welcome); //Wiring
        m_tvWelcome.setText("Welcome " + intent.getStringExtra(USER_NAME) + "!");
    }
}