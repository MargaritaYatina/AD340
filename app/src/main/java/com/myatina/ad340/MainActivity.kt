package com.myatina.ad340

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.myatina.ad340.forecast.CurrentForecastFragmentDirections
import com.myatina.ad340.location.LocationEntryFragmentDirections


class MainActivity : AppCompatActivity(),AppNavigator {

    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
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
       val action = LocationEntryFragmentDirections.actionLocationEntryFragmentToCurrentForecastFragment2()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateToLocationEntry() {
        val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

     override fun navigateToForecastDetails(forecast: DailyForecast) {
         val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(forecast.temp, forecast.description)
         findNavController(R.id.nav_host_fragment).navigate(action)

     }


}