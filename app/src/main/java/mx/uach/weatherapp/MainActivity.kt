package mx.uach.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.uach.weatherapp.models.City
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {
    val TAG = "CITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)
        var url = "https://mp-weather-app-2.herokuapp.com/"

        val stringRequest =  StringRequest(
            Request.Method.GET, url,
            Response.Listener<String>{ response ->
                Log.i("DATA", response.toString())
                val listOfMyClassObject: Type =
                    object : TypeToken<ArrayList<City?>?>() {}.type

                val cities : List<City> = Gson().fromJson(response.toString(), listOfMyClassObject)
                val layout = findViewById<LinearLayout>(R.id.lytScreen)

                for (city : City in cities){
                    val newButton : Button = Button(this)
                    newButton.text = city.name
                    newButton.setOnClickListener(View.OnClickListener {
                        val intent = Intent(this, WeatherActivity::class.java)
                        intent.putExtra(TAG, city.city?.toUpperCase())
                        startActivity(intent)
                    })
                    layout.addView(newButton)
                }


            },
            Response.ErrorListener { error ->
                Log.i("DATA", error.toString())
            })

        queue.add(stringRequest)
    }
}