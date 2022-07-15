package com.example.baseapp.views.fragments.rewards

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.baseapp.R
import com.example.baseapp.databinding.FragmentRewardsBinding
import com.example.baseapp.utils.ScratchUtils.ScratchView
import com.example.baseapp.utils.ViewUtils.toast
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardsFragment : Fragment() {

    private lateinit var binding: FragmentRewardsBinding

    private lateinit var contentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!::contentView.isInitialized) {
            binding = FragmentRewardsBinding.inflate(inflater, container, false)
            contentView = binding.root
            setPieChartData()
            binding.apply {
                scratchView.setStrokeWidth(15)
                scratchView.setRevealListener(object : ScratchView.IRevealListener {
                    override fun onRevealed(scratchView: ScratchView?) {
                        toast("You won the reward !!!")
                    }

                    override fun onRevealPercentChangedListener(
                        scratchView: ScratchView?,
                        percent: Float
                    ) {
                        Log.d("TAG", "Reveal Percent: $percent")
                    }

                })
            }
        }

        return contentView
    }

    private fun setPieChartData() {
        binding.apply {

            rewardsPieChart.animateY(1400, Easing.EaseInOutQuad)
            rewardsPieChart.description.isEnabled = false;
            rewardsPieChart.isDrawHoleEnabled = true;
            rewardsPieChart.holeRadius = 60f;
            rewardsPieChart.transparentCircleRadius = 60f;
            rewardsPieChart.isRotationEnabled = false

            val pieEntries = arrayListOf<PieEntry>()
            pieEntries.add(PieEntry(60F))
            pieEntries.add(PieEntry(30F))
            pieEntries.add(PieEntry(10F))
            val pieEntryColorsList = arrayListOf<Int>()
            pieEntryColorsList.add(ColorTemplate.rgb("#063FB2"))
            pieEntryColorsList.add(ColorTemplate.rgb("#CF227B"))
            pieEntryColorsList.add(ColorTemplate.rgb("#F2AE17"))

            val pieDataSet = PieDataSet(pieEntries, "")
            pieDataSet.colors = pieEntryColorsList
            pieDataSet.sliceSpace = 1f
            pieDataSet.setDrawValues(false)

            val pieData = PieData(pieDataSet)

            rewardsPieChart.data = pieData

            rewardsPieChart.invalidate()
        }
    }

    companion object {
    }
}