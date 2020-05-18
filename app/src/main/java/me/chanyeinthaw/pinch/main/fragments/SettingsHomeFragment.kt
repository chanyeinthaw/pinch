package me.chanyeinthaw.pinch.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.chanyeinthaw.pinch.R

class SettingsHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("SF", "${findNavController().currentDestination?.id}")

        return inflater.inflate(R.layout.fragment_settings_home, container, false)
    }
}