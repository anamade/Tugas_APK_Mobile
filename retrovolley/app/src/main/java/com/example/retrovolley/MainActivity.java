package com.example.retrovolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void actionRetrofit(View view) {
        Intent retrofit = new Intent(this, RetrofitActivity.class);
        startActivity(retrofit);
    }

    public void actionVolley(View view) {
        Intent volley = new Intent(this, VolleyActivity.class);
        startActivity(volley);
    }
}