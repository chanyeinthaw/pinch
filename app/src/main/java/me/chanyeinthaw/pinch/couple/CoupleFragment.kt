package me.chanyeinthaw.pinch.couple

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.chanyeinthaw.pinch.databinding.FragmentCoupleBinding

class CoupleFragment : Fragment() {
    private lateinit var binding: FragmentCoupleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoupleBinding.inflate(inflater, container, false)

        initializeValues()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initializeValues() {
        binding.textViewDaysNumber.text = "200"
        binding.textViewDaysBeforeNumber.text = "90"
        binding.textViewDate.text = "October 20, 2020 (Tue)"
        binding.textViewTotalDays.text = "6M 20D"
        binding.textViewFooter.text = "October 20, 2020"
        binding.textViewHoney.text = "Chan"
        binding.textViewBabe.text = "Nyein"
    }
}