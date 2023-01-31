package com.example.cleanarchslvglass.presentation.products

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.ActivityStageOfProductionBinding
import com.example.cleanarchslvglass.presentation.adapters.StageOfProductionAdapter
import com.example.cleanarchslvglass.presentation.viewmodel.StageViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StageOfProductionActivity : FragmentActivity() {

    private lateinit var binding: ActivityStageOfProductionBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: StageOfProductionAdapter
    private lateinit var toolbar: MaterialToolbar
    private lateinit var viewModel: StageViewModel

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStageOfProductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StageViewModel::class.java]
        val getLanguage = resources.configuration.locale.language
        viewModel.checkLanguage(getLanguage)

        viewModel.getStageGlass()
        viewModel.getStageSodiumLiquidGlass()
        viewModel.getStageSodiumSilicate()

        val iconListGlass = listOf<Drawable>(
            resources.getDrawable(R.drawable.ic_glass_stage_1),
            resources.getDrawable(R.drawable.ic_glass_stage_2),
            resources.getDrawable(R.drawable.ic_glass_stage_3),
            resources.getDrawable(R.drawable.ic_glass_stage_4),
            resources.getDrawable(R.drawable.ic_glass_stage_5),
            resources.getDrawable(R.drawable.ic_glass_stage_6)
        )

        val iconListLiquidGlass = listOf<Drawable>(
            resources.getDrawable(R.drawable.ic_liquid_glass_stage_1),
            resources.getDrawable(R.drawable.ic_liquid_glass_stage_2),
            resources.getDrawable(R.drawable.ic_liquid_glass_stage_3),
            resources.getDrawable(R.drawable.ic_liquid_glass_stage_4),
            resources.getDrawable(R.drawable.ic_liquid_glass_stage_5)
        )

        val iconListSilicate = listOf<Drawable>(
            resources.getDrawable(R.drawable.ic_silicate_stage_1),
            resources.getDrawable(R.drawable.ic_silicate_stage_2),
            resources.getDrawable(R.drawable.ic_silicate_stage_3),
            resources.getDrawable(R.drawable.ic_silicate_stage_4),
            resources.getDrawable(R.drawable.ic_silicate_stage_5)
        )

        binding.appBarStages.bringToFront()
        toolbar = binding.toolbarStage

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        adapter = StageOfProductionAdapter(this)
        viewPager.adapter = adapter

        val stageGlass = intent.extras?.getString("stageGlass")
        val stageLiquidGlass = intent.extras?.getString("stageLiquidGlass")
        val stageSilicate = intent.extras?.getString("stageSilicate")

        if (!stageGlass.isNullOrEmpty()){

            viewModel.stageGlass.observe(this) { glassList ->

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.icon = iconListGlass[position]
                }.attach()

                val titleList = mutableListOf<String>()
                val imgList = mutableListOf<String>()
                for (i in glassList.iterator()){
                    titleList.add(i.title)
                    imgList.add(i.img)
                    adapter.setText(titleList, imgList)
                }
            }
        } else if (!stageLiquidGlass.isNullOrEmpty()){

            viewModel.stageGlassSodiumLiquidGlass.observe(this) { glassLiquidList ->

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.icon = iconListLiquidGlass[position]
                }.attach()

                val titleList = mutableListOf<String>()
                val imgList = mutableListOf<String>()
                for (i in glassLiquidList.iterator()){
                    titleList.add(i.title)
                    imgList.add(i.img)
                    adapter.setText(titleList, imgList)
                }
            }

        } else if (!stageSilicate.isNullOrEmpty()){

            viewModel.stageGlassSodiumSilicate.observe(this) { sodiumSilicate ->

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.icon = iconListSilicate[position]
                }.attach()

                val titleList = mutableListOf<String>()
                val imgList = mutableListOf<String>()
                for (i in sodiumSilicate.iterator()){
                    titleList.add(i.title)
                    imgList.add(i.img)
                    adapter.setText(titleList, imgList)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        toolbar.setNavigationOnClickListener {
            val intent = Intent(applicationContext, StageActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stageList.removeObservers(this)
        viewModel.stageGlass.removeObservers(this)
        viewModel.stageGlassSodiumSilicate.removeObservers(this)
        viewModel.stageGlassSodiumLiquidGlass.removeObservers(this)
    }
}