package com.example.testprojectnechet

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testprojectnechet.databinding.FragmentHelloworldBinding
import java.util.*


class HelloWorldFragment : Fragment() {
    private lateinit var mBinding: FragmentHelloworldBinding
    private var counter = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_helloworld, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true

        mBinding.btnGoToTimer.setOnClickListener {
            val action = HelloWorldFragmentDirections.actionHelloWorldFragmentToTimerFragment()
            findNavController().navigate(action)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()

        when (counter) {
            1 -> setLocale(requireActivity(), "en")
            2 -> setLocale(requireActivity(), "ar")
            3 -> setLocale(requireActivity(), "ru")
            else -> {
                counter = 1
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale("")
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onStop() {
        super.onStop()
        counter++
    }
}