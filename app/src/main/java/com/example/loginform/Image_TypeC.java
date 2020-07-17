package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class Image_TypeC extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_image__type_c);
    }


//    @Override
//    public void onBackPressed() {
//
//        startActivity(new Intent(this,MainActivity.class));
//        this.finish();
//    }




}