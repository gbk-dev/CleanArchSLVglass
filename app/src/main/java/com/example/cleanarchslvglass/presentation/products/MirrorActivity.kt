package com.example.cleanarchslvglass.presentation.products

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.ActivityMirrorBinding
import com.example.cleanarchslvglass.presentation.MainActivity
import com.example.cleanarchslvglass.presentation.fragments.ContactsFragment
import com.example.cleanarchslvglass.presentation.viewmodel.MirrorViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class MirrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMirrorBinding
    private lateinit var viewModel: MirrorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMirrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MirrorViewModel::class.java]

        val getLanguage = resources.configuration.locale.language
        viewModel.checkLanguage(getLanguage)

        val toolbar = binding.mirrorToolbar

        viewModel.getMirror()
        viewModel.mirror.observe(this){ mirror ->
            binding.titleMirror.text = mirror.title
            binding.descriptionMirror.text = mirror.description
            binding.colorsMirror.text = mirror.colors
            binding.sizeMirror.text = mirror.size
            binding.thicknessMirror.text = mirror.thickness

            toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
            toolbar.title = mirror.title
            setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }

            val coordinator = binding.coordinatorMirror
            Glide.with(this).load(mirror.img).into(object: SimpleTarget<Drawable>(){
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    coordinator.background = resource
                }
            })
        }

        val appBarLayout: AppBarLayout = binding.mirrorAppBar
        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalRange = appBarLayout.totalScrollRange
            val difference = totalRange - toolbar.height

            if (abs(verticalOffset) >= difference){
                toolbar.background = ColorDrawable(resources.getColor(R.color.dark_blue))
            } else {
                toolbar.background = ColorDrawable(Color.TRANSPARENT)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        binding.openWebSiteMirror.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.salstek.ru/?locale=ru"))
            startActivity(intent)
        }

        binding.managerButtonMirror.setOnClickListener {
            val contactsFragment = ContactsFragment()
            supportFragmentManager.beginTransaction().replace(R.id.frameMirror, contactsFragment).commitAllowingStateLoss()
            binding.coordinatorMirror.visibility = View.INVISIBLE
            binding.frameMirror.background = resources.getDrawable(R.drawable.ic_main)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.mirror.removeObservers(this)
    }
}