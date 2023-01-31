package com.example.cleanarchslvglass.presentation.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.ActivityGlassBinding
import com.example.cleanarchslvglass.domain.models.Glass
import com.example.cleanarchslvglass.presentation.MainActivity
import com.example.cleanarchslvglass.presentation.adapters.GlassAdapter
import com.example.cleanarchslvglass.presentation.fragments.GlassFragment
import com.example.cleanarchslvglass.presentation.viewmodel.ProductsViewModel

class GlassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlassBinding
    private lateinit var viewModel: ProductsViewModel
    private lateinit var adapter: GlassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

        val getLanguage = resources.configuration.locale.language
        viewModel.checkLanguage(getLanguage)

        val toolbar = binding.glassToolbar
        toolbar.title = resources.getString(R.string.glass_title)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = binding.glassRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GlassAdapter(this)
        recyclerView.adapter = adapter

        viewModel.getGlass()
        viewModel.glassList.observe(this){ glass ->
            adapter.setGlassList(glass)
        }

    }

    override fun onResume() {
        super.onResume()

        binding.stages.setOnClickListener {
            val intent = Intent(applicationContext, StageActivity::class.java)
            startActivity(intent)
        }

        adapter.setOnItemClickListener(object : GlassAdapter.onGlassClickListener{
            override fun onItemCLick(position: Int) {
                val glassFragment = GlassFragment()
                supportFragmentManager.beginTransaction().replace(R.id.glassFrame, glassFragment).commit()
                when(position){
                    0 -> {
                        val bundle = Bundle()
                        val glass = Glass(
                            title = getGlass(0).title,
                            img = getGlass(0).img2,
                            description = getGlass(0).description,
                            size = getGlass(0).size,
                            thickness = getGlass(0).thickness,
                            usage = getGlass(0).usage
                        )
                        bundle.putString("title", glass.title)
                        bundle.putString("img", glass.img)
                        bundle.putString("description", glass.description)
                        bundle.putString("size", glass.size)
                        bundle.putString("thickness", glass.thickness)
                        bundle.putString("usage", glass.usage)
                        glassFragment.arguments = bundle
                    }

                    1 -> {
                        val bundle = Bundle()
                        val glass = Glass(
                            title = getGlass(1).title,
                            img = getGlass(1).img2,
                            description = getGlass(1).description,
                            size = getGlass(1).size,
                            thickness = getGlass(1).thickness,
                            usage = getGlass(1).usage
                        )
                        bundle.putString("title", glass.title)
                        bundle.putString("img", glass.img)
                        bundle.putString("description", glass.description)
                        bundle.putString("size", glass.size)
                        bundle.putString("thickness", glass.thickness)
                        bundle.putString("usage", glass.usage)
                        glassFragment.arguments = bundle
                    }

                    2 -> {
                        val bundle = Bundle()
                        val glass = Glass(
                            title = getGlass(1).title,
                            img = getGlass(1).img2,
                            description = getGlass(1).description,
                            size = getGlass(1).size,
                            thickness = getGlass(1).thickness,
                            usage = getGlass(1).usage
                        )
                        bundle.putString("title", glass.title)
                        bundle.putString("img", glass.img)
                        bundle.putString("description", glass.description)
                        bundle.putString("size", glass.size)
                        bundle.putString("thickness", glass.thickness)
                        bundle.putString("usage", glass.usage)
                        glassFragment.arguments = bundle
                    }

                    3 -> {
                        val bundle = Bundle()
                        val glass = Glass(
                            title = getGlass(3).title,
                            img = getGlass(3).img2,
                            description = getGlass(3).description,
                            size = getGlass(3).size,
                            thickness = getGlass(3).thickness,
                            usage = getGlass(3).usage
                        )
                        bundle.putString("title", glass.title)
                        bundle.putString("img", glass.img)
                        bundle.putString("description", glass.description)
                        bundle.putString("size", glass.size)
                        bundle.putString("thickness", glass.thickness)
                        bundle.putString("usage", glass.usage)
                        glassFragment.arguments = bundle
                    }

                    4 -> {
                        val bundle = Bundle()
                        val glass = Glass(
                            title = getGlass(4).title,
                            img = getGlass(4).img2,
                            description = getGlass(4).description,
                            size = getGlass(4).size,
                            thickness = getGlass(4).thickness,
                            usage = getGlass(4).usage
                        )
                        bundle.putString("title", glass.title)
                        bundle.putString("img", glass.img)
                        bundle.putString("description", glass.description)
                        bundle.putString("size", glass.size)
                        bundle.putString("thickness", glass.thickness)
                        bundle.putString("usage", glass.usage)
                        glassFragment.arguments = bundle
                    }
                }
            }

        })
    }

    private fun getGlass(position: Int) : Glass {
        var glass = Glass()
        viewModel.glassList.observe(this@GlassActivity){glassList ->
            glass = Glass(
                title = glassList[position].title,
                img = glassList[position].img,
                img2 = glassList[position].img2,
                size = glassList[position].size,
                thickness = glassList[position].thickness,
                usage = glassList[position].usage,
                description = glassList[position].description,
            )
        }
        return glass
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.glassList.removeObservers(this)
    }
}