package com.example.denis.networkpractice2.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.denis.networkpractice.R;
import com.example.denis.networkpractice2.models.entities.Address;
import com.example.denis.networkpractice2.models.entities.Company;
import com.example.denis.networkpractice2.models.entities.Geo;
import com.example.denis.networkpractice2.models.entities.User;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_detail_view);

        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra("userData");

        bindUserData(user);
    }

    @SuppressLint("DefaultLocale")
    private void bindUserData(User user){

        TextView userName = findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView userEmail = findViewById(R.id.userEmail);
        userEmail.setText(user.getEmail());

        TextView userWebsite = findViewById(R.id.userWebsite);
        userWebsite.setText(user.getWebsite());

        TextView userPhone = findViewById(R.id.userPhone);
        userPhone.setText(user.getPhone());


        Address address = user.getAddress();

        TextView street = findViewById(R.id.streetName);
        street.setText(address.getStreet());

        TextView suite = findViewById(R.id.suite);
        suite.setText(address.getSuite());

        TextView city = findViewById(R.id.city);
        city.setText(address.getCity());

        TextView zipcode = findViewById(R.id.zipcode);
        zipcode.setText(address.getZipcode());


        Geo geo = address.getGeo();

        TextView geoLat = findViewById(R.id.geoLat);
        geoLat.setText(String.format("%.2f", geo.getLat()));

        TextView geoLon = findViewById(R.id.geoLon);
        geoLon.setText(String.format("%.2f", geo.getLng()));


        Company company = user.getCompany();

        TextView companyName = findViewById(R.id.companyName);
        companyName.setText(company.getName());

        TextView companyPhrase = findViewById(R.id.companyCatchPhrase);
        companyPhrase.setText(company.getCatchPhrase());

        TextView companyBs = findViewById(R.id.companyBs);
        companyBs.setText(company.getBs());
    }
}
