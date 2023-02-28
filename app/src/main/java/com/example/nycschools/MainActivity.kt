package com.example.nycschools

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.adapter.RVAdapter
import com.example.nycschools.adapter.onItemClickedInterface
import com.example.nycschools.api.JsonAPI
import com.example.nycschools.api.RetroFitHelper
import com.example.nycschools.databinding.ActivityMainBinding
import com.example.nycschools.models.schoolSatScores
import com.example.nycschools.repository.jsonRepository
import com.example.nycschools.viewmodels.MainViewModel
import com.example.nycschools.viewmodels.ViewModelProviderFactory

class MainActivity : AppCompatActivity(), onItemClickedInterface {

    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title ="NYC Schools"

        val jsonApi = RetroFitHelper.getInstance().create(JsonAPI::class.java)
        val repository = jsonRepository(jsonApi)
        mainViewModel = ViewModelProvider(this, ViewModelProviderFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.jsonDataSatScores.observe(this, Observer { list->
            setUpRecyclerView(list)
        })

        mainViewModel.showProgress.observe(this, Observer{
            binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })

        mainViewModel.errorMessage.observe(this, Observer{
            if(!TextUtils.isEmpty(it)) {
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.tvErrorMessage.text = mainViewModel.errorMessage.toString()
            } else {
                binding.tvErrorMessage.visibility = View.GONE
            }
        })
    }

    private fun setUpRecyclerView(list: List<schoolSatScores>){
        binding.rvRecycler.visibility = View.VISIBLE
        rvAdapter = RVAdapter(this,list,this)
        binding.rvRecycler.adapter =rvAdapter
        binding.rvRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun onItemClick(listItems: schoolSatScores) {
        Log.d("ITEM CLICKED", listItems.school_name.toString())
        val intent = Intent(this, satScoreDetails::class.java)
        intent.putExtra("name",listItems.school_name)
        intent.putExtra("numberOfSatTestTakers",listItems.num_of_sat_test_takers)
        intent.putExtra("satMathAvgScore", listItems.sat_math_avg_score)
        intent.putExtra("satCriticalReadingAvgScore", listItems.sat_critical_reading_avg_score)
        intent.putExtra("satWritingAvgScore", listItems.sat_writing_avg_score)

        startActivity(intent)
    }
}