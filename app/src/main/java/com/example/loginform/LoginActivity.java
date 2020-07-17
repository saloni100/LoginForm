package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_username,edt_password;
    Button btn_login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No title bar for the activity
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_login);
        initView();
        setClickListeners();

    }

    private void initView() {
        db = new DatabaseHelper(this);
        edt_username = findViewById(R.id.edt_name);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
    }


    private void setClickListeners() {
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id =v.getId();
        switch (id){
            case R.id.btn_login : { if (!validateData()) {
                checkUserAndFetchType();
            }} break;
        }
    }


    private boolean validateData() {
        boolean failFlag = false;
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        boolean b_check = db.checkUserExist(username,password);


        if(username.length()==0 || password.length()==0)
        {
            failFlag=true;
            Toast.makeText(getApplicationContext(),"PLease fill the details",Toast.LENGTH_SHORT).show();
        }

         else if(username.length()!=0 && password.length()!=0)
        {
            boolean b = db.checkUserExist(username,password);
            if(b==false){
                Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_SHORT).show();
            failFlag = true;
            }
        }

        //Redirected to register activity if user does not exist
        else if(!b_check)
        {
            Toast.makeText(getApplicationContext(),"Username does not exist , Registertation required",Toast.LENGTH_LONG).show();
            Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(i);

        }




        return failFlag;
    }






    private void checkUserAndFetchType() {
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        boolean checkuser = db.checkUserExist(username, password);
        if (checkuser == true) {
            Cursor cursor = db.fetch();
            if (cursor.moveToFirst()) {
                do {
                    String res = cursor.getString(2);
                    startImageTypeActivity(res);
                } while (cursor.moveToNext());
            }
        }
    }


        public void startImageTypeActivity(String result) {
            if (result.equals("B")) {
                Intent i2 = new Intent(LoginActivity.this, Image_TypeB.class);
                startActivity(i2);
                finish();


            }  else if (result.equals("A")) {
                Intent i1 = new Intent(LoginActivity.this, Image_TypeA.class);
                startActivity(i1);
                finish();


            }  else if (result.equals("C")) {
                Intent i3 = new Intent(LoginActivity.this, Image_TypeC.class);
                startActivity(i3);
                finish();

            }

        }


}