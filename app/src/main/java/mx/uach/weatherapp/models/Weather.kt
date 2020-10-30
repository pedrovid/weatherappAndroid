package mx.uach.weatherapp.models

data class Weather(var city : String? = null,
                   var name : String? = null,
                   var weather : String? = null,
                   var temperature : String? = null) {
}