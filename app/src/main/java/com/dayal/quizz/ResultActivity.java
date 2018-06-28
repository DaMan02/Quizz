package com.dayal.quizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar1);

        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        ratingBar.setRating(score);
        ratingBar.setEnabled(false);
        ratingBar.setVisibility(View.INVISIBLE);
        scoreTxtView.setText(String.valueOf(score));

   }
}
