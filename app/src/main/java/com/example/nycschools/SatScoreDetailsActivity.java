package com.example.nycschools;

import android.os.Bundle;

import com.example.nycschools.databinding.ActivityItemDetailsBinding;

import androidx.appcompat.app.AppCompatActivity;

public class SatScoreDetailsActivity extends AppCompatActivity {

    private ActivityItemDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityItemDetailsBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        this.getSupportActionBar().setTitle("SAT Scores");

        String name = this.getIntent().getStringExtra("name");
        String numberOfSatTestTakers = this.getIntent().getStringExtra("numberOfSatTestTakers");
        String satMathAvgScore = this.getIntent().getStringExtra("satMathAvgScore");
        String satCriticalReadingAvgScore = this.getIntent().getStringExtra("satCriticalReadingAvgScore");
        String satWritingAvgScore = this.getIntent().getStringExtra("satWritingAvgScore");

        binding.vlName.setText(name);
        binding.vlNumOfSatTestTakers.setText(numberOfSatTestTakers);
        binding.vlSatMathAvgScore.setText(satMathAvgScore);
        binding.vlSatCriticalReadingAvgScore.setText(satCriticalReadingAvgScore);
        binding.vlSatWritingAvgScore.setText(satWritingAvgScore);
    }
}
