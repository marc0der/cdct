package example

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class UserClient(val baseUrl: String) {
    fun getById(id: String): Result<String, FuelError> =
        "$baseUrl/users/$id"
            .httpGet()
            .responseString().third
}