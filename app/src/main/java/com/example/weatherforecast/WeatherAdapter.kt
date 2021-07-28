package com.example.weatherforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.databinding.WeatherAdapterBinding
import com.example.weatherforecast.model.WeatherElement
import com.example.weatherforecast.utils.Utils
import dagger.hilt.android.qualifiers.ApplicationContext

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = WeatherAdapterBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<WeatherElement>() {
        override fun areItemsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherElement = differ.currentList[position]
        val context = holder.itemView.context
        holder.binding.apply {
            tvAvgTemp.text = String.format(context.getString(R.string.avg_temp), weatherElement.temp?.day)
            tvDate.text = String.format(context.getString(R.string.date), Utils.getDate(weatherElement.dt!!))
            tvPressure.text = String.format(context.getString(R.string.pressure), weatherElement.pressure.toString())
            tvHumidity.text = String.format(context.getString(R.string.humidity), weatherElement.humidity.toString())
            if (weatherElement.weather.isNotEmpty()) {
                tvDescription.text = String.format(context.getString(R.string.description), weatherElement.weather[0].description)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}