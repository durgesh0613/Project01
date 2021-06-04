package com.example.project01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class SignUpActivity extends AppCompatActivity {

    //declarations
    private EditText textUsername;
    private EditText textPassword;
    private EditText textPassword2;
    private Button btnSignUp;
    Context context;
    AwesomeValidation awesomeValidation = new AwesomeValidation(COLORATION);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initializations
        textUsername = findViewById(R.id.editTextUsername);
        textPassword = findViewById(R.id.editTextPassword);
        textPassword2 = findViewById(R.id.editTextPassword2);
        btnSignUp = findViewById(R.id.btnSignUp);
        context = getApplicationContext();

        /*
        Adding validations
        addValidation(activity, pattern, error_string)
        */
        awesomeValidation.addValidation(this, R.id.editTextUsername, getString(R.string.regex_uname), R.string.err_invalid_username);
        awesomeValidation.addValidation(this, R.id.editTextPassword, getString(R.string.regex_password), R.string.err_invalid_password);
        awesomeValidation.addValidation(this, R.id.editTextPassword2, getString(R.string.regex_password), R.string.err_invalid_password);
        awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.err_invalid_email);
        awesomeValidation.addValidation(this, R.id.editTextPhone, RegexTemplate.TELEPHONE, R.string.err_invalid_phone);


        //Initialize the helper Data class
        Data data = new Data();

        //listeners
        btnSignUp.setOnClickListener(view -> {
            String username, password, password2;
            StringBuffer errorString = new StringBuffer();
            username = textUsername.getText().toString();
            password = textPassword.getText().toString();
            password2 = textPassword2.getText().toString();

            //check username uniqueness
            if (data.CheckUsername(username)) {
                errorString.append(getString(R.string.toast_username_exists)).append("\n");
            }

            //check password match
            if (!password.equals(password2)) {
                errorString.append(getString(R.string.toast_password_mismatch)).append("\n");
            }

            if (!awesomeValidation.validate()) {
                errorString = new StringBuffer();
                errorString.append("Please fix the highlighted errors");
            }

            if (!errorString.toString().isEmpty()) {
                Toast.makeText(context, errorString.toString().trim(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, R.string.toast_signup_success, Toast.LENGTH_SHORT).show();
                data.AddCredential(username, password);
                Intent loginIntent = new Intent(context, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}