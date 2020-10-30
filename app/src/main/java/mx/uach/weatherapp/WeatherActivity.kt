package mx.uach.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import mx.uach.weatherapp.models.Weather

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val city = intent.getStringExtra("CITY")
        val txtCity = findViewById<TextView>(R.id.txtCity)
        val txtTemperature = findViewById<TextView>(R.id.txtTemperature)
        val txtWeather = findViewById<TextView>(R.id.txtWeather)

        val queue = Volley.newRequestQueue(this)
        Log.i("Data", city.toString())
        var url = "https://mp-weather-app-2.herokuapp.com/$city"

        val stringRequest =  StringRequest(Request.Method.GET, url,
            Response.Listener<String>{respose ->
                val clima = Gson().fromJson(respose.toString(), Weather :: class.java)
                txtCity.text = clima.name
                txtWeather.text = clima.weather
                txtTemperature.text = clima.temperature
            },
            Response.ErrorListener { error ->
                Log.i("DATA", error.toString())
            })

        queue.add(stringRequest)
    }
}