package com.example.project01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    //declarations
    private EditText textUsername;
    private EditText textPassword;
    private EditText textPassword2;
    private EditText textEmail;
    private EditText textPhone;
    private Button btnSignUp;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initializations
        textUsername = findViewById(R.id.editTextUsername);
        textPassword = findViewById(R.id.editTextPassword);
        textPassword2 = findViewById(R.id.editTextPassword2);
        textEmail = findViewById(R.id.editTextEmail);
        textPhone = findViewById(R.id.editTextPhone);
        btnSignUp = findViewById(R.id.btnSignUp);
        context = getApplicationContext();

        Intent intent = getIntent();
        Data d = (Data)intent.getSerializableExtra("Data");

        //listeners
        btnSignUp.setOnClickListener(view -> {
            String username, password, password2, email, phone;
            StringBuffer errorString = new StringBuffer("");
            username = textUsername.getText().toString();
            password = textPassword.getText().toString();
            password2 = textPassword2.getText().toString();
            email = textEmail.getText().toString();
            phone = textPhone.getText().toString();

            //check username uniqueness
            if(username.isEmpty()){
                errorString.append(getString(R.string.toast_empty_username)).append("\n");
            }
            else if(d.CheckUsername(username)){
                errorString.append(getString(R.string.toast_username_exists)).append("\n");
            }

            //check password match
            if(!password.equals(password2)){
                errorString.append(getString(R.string.toast_password_mismatch)).append("\n");
            }

            //check valid email
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if(email.isEmpty() || !email.matches(emailPattern)){
                errorString.append(getString(R.string.toast_invalid_email)).append("\n");
            }

            //check valid phone
            String phonePattern = "^\\+[0-9]{10,13}$";
            if(phone.isEmpty() || !phone.matches(phonePattern)){
                errorString.append(getString(R.string.toast_invalid_phone)).append("\n");
            }

            if(!errorString.toString().isEmpty()){
                Toast.makeText(context, errorString.toString().trim(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, R.string.toast_signup_success, Toast.LENGTH_SHORT).show();
                d.AddCredential(username, password);
                Intent loginIntent = new Intent(context, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}