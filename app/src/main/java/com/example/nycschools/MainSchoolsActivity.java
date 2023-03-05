package com.example.nycschools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nycschools.adapter.RVAdapter;
import com.example.nycschools.adapter.onItemClickedInterface;
import com.example.nycschools.api.JsonAPI;
import com.example.nycschools.api.RetroFitHelper;
import com.example.nycschools.databinding.ActivityMainBinding;
import com.example.nycschools.models.SchoolSatScores;
import com.example.nycschools.repository.JsonRepository;
import com.example.nycschools.viewmodels.MainViewModel;
import com.example.nycschools.viewmodels.ViewModelProviderFactory;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainSchoolsActivity extends AppCompatActivity implements onItemClickedInterface {

    public MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    public RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        this.getSupportActionBar().setTitle("NYC Schools");

        JsonAPI jsonApi = (JsonAPI) RetroFitHelper.INSTANCE.getInstance().create(JsonAPI.class);
        JsonRepository repository = new JsonRepository(jsonApi);
        mainViewModel = new ViewModelProvider(this, new ViewModelProviderFactory(repository)).get(MainViewModel.class);

        mainViewModel.getJsonDataSatScores().observe(this, list-> {
            setUpRecyclerView(list);
        });
    }

    private void setUpRecyclerView(List list){
        binding.rvRecycler.setVisibility(View.VISIBLE);
        rvAdapter = new RVAdapter(this, list, this);
        binding.rvRecycler.setAdapter(rvAdapter);
        binding.rvRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(SchoolSatScores listItem) {
        Log.d("ITEM CLICKED", listItem.getSchool_name());
        Intent intent = new Intent((Context)this, SatScoreDetailsActivity.class);
        intent.putExtra("name", listItem.getSchool_name());
        intent.putExtra("numberOfSatTestTakers", listItem.getNum_of_sat_test_takers());
        intent.putExtra("satMathAvgScore", listItem.getSat_math_avg_score());
        intent.putExtra("satCriticalReadingAvgScore", listItem.getSat_critical_reading_avg_score());
        intent.putExtra("satWritingAvgScore", listItem.getSat_writing_avg_score());
        this.startActivity(intent);
    }
}
