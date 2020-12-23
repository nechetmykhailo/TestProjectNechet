package com.example.testprojectnechet

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.testprojectnechet.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {
    private lateinit var mBinding: FragmentTimerBinding
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_timer, container, false)

        return mBinding.root
    }

    var time = 5000L
    var isClicked = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true

        mBinding.btnAddTime.setOnClickListener {
            if (isClicked) {
                isClicked = false
                timer = object: CountDownTimer(time, 5) {
                    override fun onTick(millisUntilFinished: Long) {
                        millisUntilFinished.toInt().toString().also { mBinding.tvTimer.text = it }
                    }

                    override fun onFinish() {
                        mBinding.tvTimer.text = getString(R.string.start_timer)
                        isClicked = true
                        time = 5000L
                    }
                }
                timer.start()
            }
            else {
                timer.onTick(500)
            }
        }
    }
}