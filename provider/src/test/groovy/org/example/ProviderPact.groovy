package org.example

import au.com.dius.pact.core.model.*
import au.com.dius.pact.provider.*
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class ProviderPact extends Specification {

    @Inject
    EmbeddedServer server

    @Inject
    @Client("/")
    HttpClient client

    @Shared
    Pact<RequestResponseInteraction> consumerPact

    def setupSpec() {
        consumerPact = DefaultPactReader.INSTANCE.loadPact(
                "../consumer/target/pacts/user-data-consumer-user-data-provider.json")
    }

    def "verify consumer pact against provider"() {
        given:
        ProviderInfo provider = new ProviderInfo(
                name: "Micronaut App",
                protocol: "http",
                host: server.host,
                port: server.port,
                path: "/")

        ConsumerInfo consumer = new ConsumerInfo(name: "user-data-consumer")

        ProviderVerifier verifier = new ProviderVerifier()
        verifier.initialiseReporters(provider)
        verifier.reportVerificationForConsumer(
                consumer,
                provider,
                new UrlSource("http://org.example.pact"))

        Interaction interaction = consumerPact.interactions.first()
        verifier.reportInteractionDescription(interaction)

        ProviderClient client = new ProviderClient(provider, new HttpClientFactory())
        Map<String, Object> failures = [:]

        when:
        verifier.verifyResponseFromProvider(
                provider,
                interaction,
                interaction.getDescription(),
                failures,
                client
        )

        and:
        verifier.displayFailures(failures)

        then:
        !failures
    }


}

