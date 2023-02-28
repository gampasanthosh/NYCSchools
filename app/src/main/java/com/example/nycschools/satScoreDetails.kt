package com.example.nycschools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nycschools.databinding.ActivityItemDetailsBinding

class satScoreDetails : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title ="SAT Scores"

        val name = intent.getStringExtra("name")
        val numberOfSatTestTakers = intent.getStringExtra("numberOfSatTestTakers")
        val satMathAvgScore = intent.getStringExtra("satMathAvgScore")
        val satCriticalReadingAvgScore = intent.getStringExtra("satCriticalReadingAvgScore")
        val satWritingAvgScore = intent.getStringExtra("satWritingAvgScore")

        binding.apply {
            vlName.text = name
            vlNumOfSatTestTakers.text = numberOfSatTestTakers
            vlSatMathAvgScore.text = satMathAvgScore
            vlSatCriticalReadingAvgScore.text = satCriticalReadingAvgScore
            vlSatWritingAvgScore.text = satWritingAvgScore
        }
    }
}