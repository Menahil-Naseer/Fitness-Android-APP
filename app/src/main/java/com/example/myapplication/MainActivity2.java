package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void signup(View view) {
        Intent in= new Intent(MainActivity2.this,MainActivity.class);
        startActivity(in);
    }

    public void signin(View view) {
        Intent in= new Intent(MainActivity2.this,SignInActivity.class);
        startActivity(in);
    }

}