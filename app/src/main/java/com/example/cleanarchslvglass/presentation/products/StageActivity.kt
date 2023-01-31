package com.example.cleanarchslvglass.presentation.products

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchslvglass.databinding.ActivityStageBinding
import com.example.cleanarchslvglass.presentation.adapters.StageAdapter
import com.example.cleanarchslvglass.presentation.viewmodel.StageViewModel

class StageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStageBinding
    private lateinit var viewModel: StageViewModel
    private lateinit var adapter: StageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[StageViewModel::class.java]

        val getLanguage = resources.configuration.locale.language
        viewModel.checkLanguage(getLanguage)

        val recycler = binding.stageRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = StageAdapter(this)
        recycler.adapter = adapter

        val toolbar = binding.stageToolbar
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, GlassActivity::class.java)
            startActivity(intent)
        }

        viewModel.getStage()
        viewModel.stageList.observe(this){
            adapter.setStageList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setOnItemClickListener(object  : StageAdapter.onStageClickListener{
            override fun onItemCLick(position: Int) {

                when(position) {
                    0 -> {
                        val intent = Intent(this@StageActivity, StageOfProductionActivity::class.java)
                        intent.putExtra("stageGlass", "stageGlass")
                        startActivity(intent)
                    }
                    1 -> {
                        val intent = Intent(this@StageActivity, StageOfProductionActivity::class.java)
                        intent.putExtra("stageLiquidGlass", "stageLiquidGlass")
                        startActivity(intent)
                    }
                    2 -> {
                        val intent = Intent(this@StageActivity, StageOfProductionActivity::class.java)
                        intent.putExtra("stageSilicate", "stageSilicate")
                        startActivity(intent)
                    }
                }
            }
        })
    }
}