package com.example.sergey.a2048j;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {
    private String login;
    private String email;
    private String number;
    private String nimsesID;
    private String password;

    private EditText loginEdit;
    private EditText emailEdit;
    private EditText numberEdit;
    private EditText nimsesIDEdit;
    private EditText passwordEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // get all Edit Text views
        loginEdit = (EditText) findViewById(R.id.editLoginText);
        emailEdit = (EditText) findViewById(R.id.editLoginText);
        numberEdit = (EditText) findViewById(R.id.editLoginText);
        nimsesIDEdit = (EditText) findViewById(R.id.editLoginText);
        passwordEdit = (EditText) findViewById(R.id.editLoginText);
    }

    private void getInfo(){
        login = String.valueOf(loginEdit.getText());
        email = String.valueOf(emailEdit.getText());
        number = String.valueOf(numberEdit.getText());
        nimsesID = String.valueOf(nimsesIDEdit.getText());
        password = String.valueOf(passwordEdit.getText());


    }
}
