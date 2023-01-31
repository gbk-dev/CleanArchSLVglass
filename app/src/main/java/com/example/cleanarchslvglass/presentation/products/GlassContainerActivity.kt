package com.example.cleanarchslvglass.presentation.products

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.ActivityGlassContainerBinding
import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.presentation.MainActivity
import com.example.cleanarchslvglass.presentation.adapters.GlassContainerAdapter
import com.example.cleanarchslvglass.presentation.viewmodel.BasketViewModel
import com.example.cleanarchslvglass.presentation.viewmodel.ProductsViewModel
import com.example.cleanarchslvglass.presentation.viewmodel.UserViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class GlassContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlassContainerBinding
    private lateinit var viewModelProducts: ProductsViewModel
    private lateinit var viewModelBasket: BasketViewModel
    private lateinit var viewModelUser: UserViewModel
    private lateinit var adapter: GlassContainerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelProducts = ViewModelProvider(this)[ProductsViewModel::class.java]
        viewModelBasket = ViewModelProvider(this)[BasketViewModel::class.java]
        viewModelUser = ViewModelProvider(this)[UserViewModel::class.java]
        viewModelUser.getUser()

        val getLanguage = resources.configuration.locale.language
        viewModelProducts.checkLanguage(getLanguage)

        val toolbar = binding.glassConToolbar
        toolbar.title = resources.getString(R.string.glassCon_title)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = binding.glassConGridView
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        adapter = GlassContainerAdapter(this)
        recyclerView.adapter = adapter

        viewModelProducts.getGlassCon()
        viewModelProducts.glassConList.observe(this){ glassCon ->
            adapter.setGlassConList(glassCon)
        }


    }

    override fun onResume() {
        super.onResume()

        adapter.setOnItemClickListener(object : GlassContainerAdapter.onGlassConClickListener{
            @SuppressLint("InflateParams", "SetTextI18n")

            override fun onItemCLick(position: Int) {

                val dialog = LayoutInflater
                    .from(this@GlassContainerActivity)
                    .inflate(R.layout.glass_con_dialog, null)

                val builder = MaterialAlertDialogBuilder(this@GlassContainerActivity, R.style.AlertDialogCustom)
                    .setView(dialog)

                val alertDialog = builder.show()

                val articleTextView = dialog.findViewById<TextView>(R.id.dialogArt)
                val titleTextView = dialog.findViewById<TextView>(R.id.dialogTitle)
                val capacityTextView = dialog.findViewById<TextView>(R.id.dialogCapacity)
                val corollaTextView = dialog.findViewById<TextView>(R.id.dialogCorolla)
                val imgView = dialog.findViewById<ImageView>(R.id.dialogImg)

                val addToBasket = dialog.findViewById<MaterialButton>(R.id.addToBasket)

                var basket = Basket(
                    id = 0,
                    article = "article",
                    title = "title",
                    capacity = "capacity",
                    collarType = "collar",
                    img = "img",
                    count = 1,
                    userId = ""
                )

                viewModelProducts.glassConList.observe(this@GlassContainerActivity){ glassCon ->

                    viewModelUser.userList.observe(this@GlassContainerActivity){ user ->

                        val id = glassCon.get(position).id
                        val article = glassCon.get(position).article
                        val title = glassCon.get(position).title
                        val capacity = glassCon.get(position).capacity
                        val collar = glassCon.get(position).collarType
                        val img = glassCon.get(position).img

                        val articleRes = resources.getString(R.string.article)
                        val collarRes = resources.getString(R.string.type_of_corolla)

                        articleTextView.text = "$articleRes - $article"
                        titleTextView.text = title
                        capacityTextView.text = capacity
                        corollaTextView.text = "$collarRes - $collar"
                        Glide.with(this@GlassContainerActivity).load(img).into(imgView)

                        basket = Basket(
                            id = 0,
                            article = article,
                            title = title,
                            capacity = capacity,
                            collarType = collar,
                            img = img,
                            count = 1,
                            userId = user.uid!!
                        )

                    }

                }

                addToBasket.setOnClickListener {
                    lifecycleScope.launch {
                        viewModelBasket.insert(basket)
                    }
                    Toast.makeText(this@GlassContainerActivity, resources.getString(R.string.added_to_basket), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelProducts.glassConList.removeObservers(this)
        viewModelUser.userList.removeObservers(this)
    }
}