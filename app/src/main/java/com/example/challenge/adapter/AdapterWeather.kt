package com.example.challenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.databinding.WeatherItemBinding
import com.example.challenge.models.Data
import java.text.SimpleDateFormat
import java.util.*


class AdapterWeather (private val context: Context, private val clickItem : (String?)->Unit): RecyclerView.Adapter<AdapterWeather.UserViewHolder>() {
    private var list: MutableList<Data> = mutableListOf()

    class UserViewHolder(private val itemRowBiding: WeatherItemBinding) : RecyclerView.ViewHolder(itemRowBiding.root) {

        fun bindData(context: Context, clickItem: (String?) -> Unit, weather: Data){
            var currentDate = getDateString(weather.dt)
            var dayName = getDateStringDay(weather.dt)
            var temperature =  Math.round(weather.temp?.day - 273.15).toString()
            itemRowBiding.txtDate.text = "Date: " + dayName + ", " + currentDate
            itemRowBiding.txtAverageTemp.text = "Average temperature: " + temperature + "°C"
            itemRowBiding.txtPressure.text = "Pressure: " + weather.pressure.toString()
            itemRowBiding.txtHumidity.text = "Humidity: " + weather.humidity.toString() + "%"
            itemRowBiding.txtDescription.text = "Description: " + weather.weather[0].description
        }

        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val simpleDateFormatDay = SimpleDateFormat("EEEE", Locale.ENGLISH)

        fun getDateString(time: Long) : String = simpleDateFormat.format(time * 1000L)

        fun getDateStringDay(time: Long) : String = simpleDateFormatDay.format(time * 1000L)

        fun getDateString(time: Int) : String = simpleDateFormat.format(time * 1000L)

        fun getDateStringDay(time: Int) : String = simpleDateFormatDay.format(time * 1000L)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list.get(position)
        holder.bindData(context, clickItem, item)
    }

    override fun getItemCount(): Int = list.size

    fun addAll(newList: List<Data>?){
        list.addAll(newList ?: listOf())
        notifyDataSetChanged()
    }

    fun clearAll(newList: List<Data>?){
        list.removeAll(newList ?: listOf())
        notifyDataSetChanged()
    }
}