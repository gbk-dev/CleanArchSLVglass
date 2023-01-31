package com.example.cleanarchslvglass.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.ActivityMainBinding
import com.example.cleanarchslvglass.presentation.fragments.BasketFragment
import com.example.cleanarchslvglass.presentation.fragments.HomeFragment
import com.example.cleanarchslvglass.presentation.fragments.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var activeFragment: Fragment
    private val home = HomeFragment()
    private val basket = BasketFragment()
    private val settings = SettingsFragment()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.materialToolbar.title = ""
        setSupportActionBar(binding.materialToolbar)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.frameLayout, home)
            add(R.id.frameLayout, basket).hide(basket)
            add(R.id.frameLayout, settings).hide(settings)
        }.commit()

        activeFragment = home

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(home).commit()
                    activeFragment = home
                    true
                }
                R.id.basket -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(basket).commit()
                    activeFragment = basket
                    true
                }
                R.id.settings -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(settings).commit()
                    activeFragment = settings
                    true
                }

                else -> false

            }
            true
        }
    }
}