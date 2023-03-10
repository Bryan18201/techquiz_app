package com.sandipbhattacharya.techquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    // Declare TextView object references for showing points and personal best.
    TextView tvPoints, tvPersonalBest;
    // Declare a SharedPreferences object reference since we are going to store
    // the highest score or personal best using Android SharedPreferences class.
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        // Receive the extra data from Intent that is sent from StartGame Activity.
        int points = getIntent().getExtras().getInt("points");
        // Get the handles of the TextViews for points and personal best
        tvPoints = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);
        // Set tvPoints with the value of points
        tvPoints.setText("" + points);
        // Instantiate the SharedPreferences object reference
        sharedPreferences = getSharedPreferences("pref", 0);

        int pointsSP = sharedPreferences.getInt("pointsSP", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Check, if points is greater than pointsSP
        if(points > pointsSP){
            // If true, store points in pointsSP
            pointsSP = points;
            // Put the value in editor with the key "pointsSP"
            editor.putInt("pointsSP", pointsSP);
            // This will overwrite existing value of the key "pointsSP".
            // To save the changes call commit() on editor.
            editor.commit();
        }
        // Set tvPersonalBest with the value of pointsSP
        tvPersonalBest.setText("" + pointsSP);
    }

    public void restart(View view) {
        // Create an Intent object to launch StartGame Activity
        Intent intent = new Intent(GameOver.this, StartGame.class);
        startActivity(intent);
        // Finish GameOver Activity
        finish();
    }

    public void exit(View view) {
        // Call finish() method to finish GameOver Activity
        finish();
    }
}
