package example

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class WeatherClient(val baseUrl: String) {
    fun getById(id: Int): Result<String, FuelError> =
        "$baseUrl/weather/$id"
            .httpGet()
            .responseString()
            .third
}