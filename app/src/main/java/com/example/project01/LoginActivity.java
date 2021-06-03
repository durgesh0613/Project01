package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project01.utilities.ValidationUtility;

public class LoginActivity extends AppCompatActivity {

    //UI widgets
    private Button m_btnLogin;
    private Button m_btnSignup;
    private EditText m_txtUsername;
    private EditText m_txtPassword;

    //Data Class
    private Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Wiring : Initializing UI controls.
        m_btnLogin = findViewById(R.id.btnLogin);
        m_btnSignup = findViewById(R.id.btnSignup1);
        m_txtUsername = findViewById(R.id.txtUsername);
        m_txtPassword = findViewById(R.id.txtPassword);

        // Login Button OnClick Listener
        // Validates and verifies the credentials : On login success, takes the user to the Welcome Activity
        //else a toast is display with proper message to the user
        m_btnLogin.setOnClickListener(v -> {
            String username = m_txtUsername.getText().toString();
            String password = m_txtPassword.getText().toString();
            if (!ValidationUtility.isAnyEmpty(username, password)) {
                if (this.data.CheckCredentials(username, password)) {
                    Intent welcomeIntent = new Intent(this, WelcomeActivity.class);
                    welcomeIntent.putExtra(getString(R.string.ext_login_username), username);
                    startActivity(welcomeIntent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.err_IncorrectCredentials, Toast.LENGTH_SHORT).show();
                    m_txtPassword.getText().clear();
                }

            } else {
                Toast.makeText(getApplicationContext(), R.string.err_emptyCredentials, Toast.LENGTH_SHORT).show();
            }
        });

        m_btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        });
    }
}