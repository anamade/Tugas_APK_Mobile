package com.rekoso.intent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button explicit;
    Button implicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explicit = (Button)findViewById(R.id.explicitb);
        explicit.setOnClickListener(this);
        implicit = (Button)findViewById(R.id.implicitb);
        implicit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.explicitb:
                Intent explicit = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(explicit);
                break;
            case R.id.implicitb:
                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(implicit);
                break;
            default:
                break;
        }
    }
}