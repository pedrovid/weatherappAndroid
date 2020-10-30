package mx.uach.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val TAG = "CITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnCuu = findViewById<Button>(R.id.btnCuu)

        btnCuu.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra(TAG, "Chihuahua")
            startActivity(intent)
        })

        val btnCdmx = findViewById<Button>(R.id.btnCdmx)

        btnCdmx.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra(TAG, "Ciudad de México")
            startActivity(intent)
        })
    }
}