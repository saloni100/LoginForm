package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar for the activity
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        initView();
        setClickListeners();

    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    private void setClickListeners() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id =v.getId();
        switch (id){
            case R.id.btn_login : startLoginActivity(); break;
            case R.id.btn_register : startRegisterActivity(); break;
        }

    }

    private void startLoginActivity() {
        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent1);
        //finish();
    }

    private void startRegisterActivity() {
        Intent intent2 = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent2);
      //  finish();
    }


}