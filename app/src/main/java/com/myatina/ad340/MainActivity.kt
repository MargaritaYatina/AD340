package com.myatina.ad340

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myatina.ad340.details.ForecastDetailsActivity
import com.myatina.ad340.forecast.CurrentForecastFragment
import com.myatina.ad340.location.LocationEntryFragment


class MainActivity : AppCompatActivity(),AppNavigator {

    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //handle item selection
        return when (item.itemId) {
            R.id.tempDisplaySetting -> {
                showTempDisplaySettingDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun showTempDisplaySettingDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("Chose Display Units")
            .setMessage("Choose which temperature unit to use for temperature display")
            .setPositiveButton("F°") { _, _ ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
            }
            .setNeutralButton("C°" ){ _, _ ->
                tempDisplaySettingManager.updateSetting((TempDisplaySetting.Celsius))
            }

            .setOnDismissListener {
                Toast.makeText(this, "Setting will take effect on app restart", Toast.LENGTH_SHORT).show()

            }
        dialogBuilder.show()
    }


    override fun navigateToCurrentForecast(zipcode: String) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragmentContainer, CurrentForecastFragment.newInstance(zipcode))
//            .commit()
    }

    override fun navigateToLocationEntry() {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragmentContainer, LocationEntryFragment())
//            .commit()
    }




}