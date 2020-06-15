package com.myatina.ad340

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myatina.ad340.api.DailyForecast
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("MM-dd-yyyy")

class DailyForecastVeiwHolder(
    view: View,
    private val tempDisplaySettingManager: TempDisplaySettingManager

)
    : RecyclerView.ViewHolder(view){

    private val tempText: TextView = view.findViewById(R.id.tempText)
    private val descriptionText: TextView = view.findViewById(R.id.descriptionText)
    private val dateText = view.findViewById<TextView>(R.id.dateText)

    fun bind(dailyForecast: DailyForecast) {
        tempText.text = formatTempForDisplay(dailyForecast.temp.max, tempDisplaySettingManager.getTempDisplaySetting() )
        descriptionText.text = dailyForecast.weather[0].description
        dateText.text = DATE_FORMAT.format((Date(dailyForecast.date * 1000)))

    }

}

class DailyForecastAdapter(
    private val tempDisplaySettingManager: TempDisplaySettingManager,
    private val clickHandler: (DailyForecast) -> Unit

) : ListAdapter<DailyForecast, DailyForecastVeiwHolder>(DIFF_CONFIG) {


    companion object {
        val DIFF_CONFIG = object: DiffUtil.ItemCallback<DailyForecast>(){
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem ==newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastVeiwHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastVeiwHolder(itemView, tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyForecastVeiwHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }

    }


}