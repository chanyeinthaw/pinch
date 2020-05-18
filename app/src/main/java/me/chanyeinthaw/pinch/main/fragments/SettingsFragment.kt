package me.chanyeinthaw.pinch.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import me.chanyeinthaw.pinch.R
import me.chanyeinthaw.pinch.main.MainActivity

class SettingsFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        navController = findNavController()

        mainActivity.navControllerSettings = navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.settingsHomeFragment -> {
                    mainActivity.isSettingsAtSettingHome = true
                }
            }
        }

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}