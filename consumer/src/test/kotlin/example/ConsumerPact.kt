package example

import au.com.dius.pact.consumer.ConsumerPactTestMk2
import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.RequestResponsePact
import com.github.kittinunf.result.Result
import org.junit.Assert.fail
import java.util.*

class ConsumerPact : ConsumerPactTestMk2() {

    override fun providerName(): String = "weather-provider"

    override fun consumerName(): String = "weather-consumer"

    fun String.toUUID(): UUID = UUID.fromString(this)

    val cityId = 1851632

    fun buildBody(id: Int): PactDslJsonBody =
        PactDslJsonBody()
            .numberType("id", id)
            .stringType("name", "Shuzenji")
            .numberType("timezone", 32400)
            .`object`("wind").numberType("speed", 0.47).numberType("deg", 107.538).closeObject()
            .`object`("clouds").numberType("all", 2).closeObject()
            .`object`("main").numberType("temp", 289.92).numberType("pressure", 1009).numberType(
                "humidity",
                92
            ).numberType("temp_min", 288.71).numberType("temp_max", 290.93).closeObject()
            .`object`("weather").stringType("main", "Clear").stringType("description", "clear sky").closeObject()
            .asBody()

    override fun createPact(builder: PactDslWithProvider): RequestResponsePact =
        builder.uponReceiving("can get weather data from weather data provider by city id")
            .matchPath(
                "/weather/(.*)",
                "/weather/1851632"
            )
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(buildBody(cityId))
            .toPact()

    override fun runTest(mockServer: MockServer) {
        val client = WeatherClient(mockServer.getUrl())
        when (val result = client.getById(cityId)) {
            is Result.Failure ->
                fail(result.getException().message)
            is Result.Success ->
                println(result.get())
        }
    }
}