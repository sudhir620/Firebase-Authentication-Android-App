package com.skcoder.intellifytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {

    TextView uName, uCity, uAge, uGender;
    String name,age,city,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        this.setTitle("User Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uName = findViewById(R.id.uName);
        uCity = findViewById(R.id.uCity);
        uAge = findViewById(R.id.uAge);
        uGender = findViewById(R.id.uGender);

        name = getIntent().getStringExtra("Name");
        uName.setText(name);

        city = getIntent().getStringExtra("City");
        uCity.setText(city);

        age = getIntent().getStringExtra("Age");
        uAge.setText(age);

        gender = getIntent().getStringExtra("Gender");
        uGender.setText(gender);

    }
}