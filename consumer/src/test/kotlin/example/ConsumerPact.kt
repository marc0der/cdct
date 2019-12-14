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

    override fun providerName(): String = "user-data-provider"

    override fun consumerName(): String = "user-data-consumer"

    fun String.toUUID(): UUID = UUID.fromString(this)

    fun body(
        ssn: UUID,
        firstName: String,
        lastName: String,
        age: Int
    ): PactDslJsonBody =
        PactDslJsonBody()
            .uuid("ssn", ssn)
            .stringType("firstName", firstName)
            .stringType("lastName", lastName)
            .numberType("age", age)

    override fun createPact(builder: PactDslWithProvider): RequestResponsePact =
        builder.uponReceiving("can get user data from user data provider")
            .matchPath(
                "/users/(.*)",
                "/users/32fadd88-4d61-402d-8d80-2679f12b5c66"
            )
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(
                body(
                    "042534a4-e998-4ac0-98da-91f4af65cc94".toUUID(),
                    "John",
                    "West",
                    49
                )
            )
            .toPact()

    override fun runTest(mockServer: MockServer) {

        val client = UserClient(mockServer.getUrl())

        val result = client.getById("32fadd88-4d61-402d-8d80-2679f12b5c66")

        when (result) {
            is Result.Failure ->
                fail(result.getException().message)
            is Result.Success ->
                println(result.get())
        }
    }
}