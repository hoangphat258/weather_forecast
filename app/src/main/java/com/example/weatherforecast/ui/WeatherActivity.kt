package com.example.weatherforecast.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.WeatherAdapter
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.utils.Constant
import com.example.weatherforecast.utils.DeviceUtils
import com.example.weatherforecast.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : BaseActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (DeviceUtils.isDeviceRooted()) {
            binding.tvErrorMessage.text = getString(R.string.rooted_device)
            binding.tvErrorMessage.visibility = View.VISIBLE
            disableView(binding.btnGetWeather)
            disableView(binding.edtSearch)
        } else {
            binding.tvErrorMessage.visibility = View.GONE
            enableView(binding.btnGetWeather)
            enableView(binding.btnGetWeather)
            initView()
            viewModel.getWeather(Constant.DEFAULT_CITY)
            setupObserver()
        }

    }

    private fun initView() {
        binding.rcvListWeather.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this@WeatherActivity)
        }

        binding.btnGetWeather.setOnClickListener {
            searchCity()
        }

        binding.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchCity()
            }
            true
        }
    }

    private fun setupObserver() {
        viewModel.weatherResponse.observe(this, {
            if (it != null && it.isNotEmpty()) {
                weatherAdapter.differ.submitList(it)
                binding.rcvListWeather.visibility = View.VISIBLE
                binding.tvErrorMessage.visibility = View.GONE
            } else {
                binding.rcvListWeather.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.VISIBLE
            }
        })

        viewModel.isLoading.observe(this, { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.errorMessage.observe(this, { message ->
            binding.tvErrorMessage.text = message
        })
    }

    private fun searchCity() {
        if (binding.edtSearch.text.length >= 3) {
            viewModel.getWeather(binding.edtSearch.text.toString())
        } else if (binding.edtSearch.text.isEmpty()) {
            viewModel.getWeather(Constant.DEFAULT_CITY)
        } else {
            Toast.makeText(this, getString(R.string.city_name_must_be_from_3), Toast.LENGTH_SHORT)
                .show()
        }

        hideSoftKeyboard()
    }
}