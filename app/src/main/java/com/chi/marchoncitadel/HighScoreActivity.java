package com.chi.marchoncitadel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class HighScoreActivity extends AppCompatActivity implements View.OnClickListener{
    TextView score1, score2, score3, score4;
    SharedPreferences sharedPreferences;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_high_score);

        score1 = (TextView)findViewById(R.id.score1);
        score2 = (TextView)findViewById(R.id.score2);
        score3 = (TextView)findViewById(R.id.score3);
        score4 = (TextView)findViewById(R.id.score4);

        sharedPreferences = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        //set values to textViews
        score1.setText("1."+ sharedPreferences.getInt("score1", 0));
        score1.setText("2."+ sharedPreferences.getInt("score2", 0));
        score1.setText("3."+ sharedPreferences.getInt("score3", 0));
        score1.setText("4."+ sharedPreferences.getInt("score4", 0));

        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
