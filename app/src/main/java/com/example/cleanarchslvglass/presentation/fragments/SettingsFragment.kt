package com.example.cleanarchslvglass.presentation.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleanarchslvglass.R
import com.example.cleanarchslvglass.databinding.FragmentSettingsBinding
import com.example.cleanarchslvglass.domain.models.Settings
import com.example.cleanarchslvglass.presentation.MainActivity
import com.example.cleanarchslvglass.presentation.auth.SignInActivity
import com.example.cleanarchslvglass.presentation.viewmodel.SettingsViewModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val settingsViewModel: SettingsViewModel by viewModels()
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val ordersFragment = OrdersFragment()
    private val contactsFragment = ContactsFragment()
    private val aboutAppFragment = AboutAppFragment()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater)
        binding.scrollSettings.visibility = View.VISIBLE

        settingsViewModel.getUser()
        settingsViewModel.userList.observe(viewLifecycleOwner){ user ->
            val firstName = user.firstName.toString()
            val lastName = user.lastName.toString()
            val email = user.email.toString()

            binding.name.text = "$firstName $lastName"
            binding.settingsFirstNEdit.hint = firstName
            binding.settingsLastNEdit.hint = lastName
            binding.settingsEmail.text = email
        }
        val settings = Settings(
            id = 0,
            notificationState = 0
        )
        settingsViewModel.getSettings()
        settingsViewModel.settingsList.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                settingsViewModel.insert(settings)
            }
        }

        val switch = binding.switchNoti
        settingsViewModel.settingsList.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                val state = it[0].notificationState
                if (state == 1){
                    switch.isChecked = true
                } else if (state == 0){
                    switch.isChecked = false
                }
            }
        }

        return binding.root
    }

    override fun onResume() {

        binding.settingsFirstNEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val firstLayout = binding.settingsFirstNLayout
                if (p3 > 0){
                    firstLayout.isEndIconVisible = true
                    firstLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
                    firstLayout.setEndIconDrawable(R.drawable.ic_check)
                    firstLayout.setEndIconOnClickListener {
                        val newFirstN = binding.settingsFirstNEdit.text.toString()
                        val newFirstNMap = mapOf(
                            "firstName" to newFirstN
                        )
                        settingsViewModel.updateUser(newFirstNMap)
                        binding.settingsFirstNEdit.text = null
                        Toast.makeText(view?.context, settingsViewModel.updateUser(newFirstNMap), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    firstLayout.isEndIconVisible = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.settingsLastNEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val lastLayout = binding.settingsLastNLayout
                if (p3 > 0){
                    lastLayout.isEndIconVisible = true
                    lastLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
                    lastLayout.setEndIconDrawable(R.drawable.ic_check)
                    lastLayout.setEndIconOnClickListener {
                        val newLastN = binding.settingsLastNEdit.text.toString()
                        val newLastNMap = mapOf(
                            "lastName" to newLastN
                        )
                        settingsViewModel.updateUser(newLastNMap)
                        binding.settingsLastNEdit.text = null
                        Toast.makeText(view?.context, settingsViewModel.updateUser(newLastNMap), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    lastLayout.isEndIconVisible = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.switchNoti.setOnCheckedChangeListener { _, p1 ->
            val state = if (p1) {
                1
            } else {
                0
            }
            settingsViewModel.updateNotificationState(state = state)

            when (p1) {
                true -> {
                    Firebase.messaging.subscribeToTopic("news")
                }
                false -> {
                    Firebase.messaging.unsubscribeFromTopic("news")
                }
            }
        }


        binding.settingsAbout.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameSettings, aboutAppFragment).commitAllowingStateLoss()
            binding.scrollSettings.visibility = View.INVISIBLE
        }

        binding.settingsContacts.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameSettings, contactsFragment).commitAllowingStateLoss()
            binding.scrollSettings.visibility = View.INVISIBLE
        }

        binding.myOrders.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.frameSettings, ordersFragment).commitAllowingStateLoss()
            binding.scrollSettings.visibility = View.INVISIBLE
        }

        binding.language.setOnClickListener {
            val getLanguage = resources.configuration.locale.language
            val ru = Locale("ru").language
            val config : Configuration = resources.configuration
            if (getLanguage == ru){
                config.setLocale(Locale("en"))
                resources.updateConfiguration(config, resources.displayMetrics)
            } else {
                config.setLocale(Locale("ru"))
                resources.updateConfiguration(config, resources.displayMetrics)
            }

            activity?.finish()

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.logOut.setOnClickListener {
            settingsViewModel.logOut()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
        }

        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        settingsViewModel.userList.removeObservers(viewLifecycleOwner)
        settingsViewModel.settingsList.removeObservers(viewLifecycleOwner)
        _binding = null
    }

}