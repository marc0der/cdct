# Consumer Driven Contract Tests

A simple demonstration of how to implement consumer driven contract tests
using the [pact-jvm](https://github.com/DiUS/pact-jvm) framework.

This project aims to mimic a contract between an Android weather app and a
weather API. We will be building the bare minimum of components for this to
work. This includes an HTTP Client to call our API, as well as a tiny
[Micronaut](https://micronaut.io/) app to serve hard-coded responses. We will
be using the Kotlin [Fuel](https://github.com/kittinunf/fuel) library to make
http requests to the API. This application is not feature complete, and only
serves to demonstrate consumer contracts between two applications.

This repository contains two modules, one for the _consumer_ and another for
the _provider_.

The API that we will develop should be an HTTP `GET` endpoint, taking a query
parameter `id` which is the `cityId` of the location of interest.  The API
should then respond with the following JSON response body:

```json
{
  "clouds": {
    "all": 2
  },
  "id": 1851632,
  "main": {
    "humidity": 92,
    "pressure": 1009,
    "temp": 289.92,
    "temp_max": 290.93,
    "temp_min": 288.71
  },
  "name": "Shuzenji",
  "timezone": 32400,
  "weather": {
    "description": "clear sky",
    "main": "Clear"
  },
  "wind": {
    "deg": 107.538,
    "speed": 0.47
  }
}
```


The `main` branch of this repository contains an unimplemented application
that can be developed using the following pointers:

The `solution` branch contains a working implementation for reference.

## Consumer in Kotlin

1. Add build dependencies to the consumer Gradle build file:
  
```groovy
implementation("com.github.kittinunf.fuel:fuel:2.2.1")
implementation("com.github.kittinunf.fuel:fuel-gson:2.2.1")
testImplementation("au.com.dius:pact-jvm-consumer-junit_2.11:3.5.0")
```
  
2. Create a new Kotlin test class `ConsumerPact`
3. Extend the `au.com.dius.pact.consumer.ConsumerPactTestMk2`
4. Implement the overrides for the `providerName` and `consumerName` methods.
5. Implement the `createPact` method, observing the above contract.
6. Write a `WeatherClient` class to call a remote weather API.  This class
   should be constructed with `url` and `appid` fields, and should expose a
   method with the following signature: `getWeatherByCoords(lat: Double, lon:
   Double)`. Use
   [Fuel](https://github.com/kittinunf/fuel#blocking-usage-example) to make a
   _blocking_ call to the API endpoint.
7. Implement the `runTest` method, using the `WeatherClient` to call the
   injected `mockServer`. Assert the response and confirm that it is working.

## Provider in Java with Micronaut

1. Apply the `id "au.com.dius.pact" version "4.0.0"` to the provider Gradle
   build.
2. Add the following dependencies for the Micronaut framework:

```groovy
annotationProcessor platform("io.micronaut:micronaut-bom:$micronautVersion")
annotationProcessor "io.micronaut:micronaut-inject-java"
annotationProcessor "io.micronaut:micronaut-validation"
implementation platform("io.micronaut:micronaut-bom:$micronautVersion")
implementation "io.micronaut:micronaut-inject"
implementation "io.micronaut:micronaut-validation"
implementation "io.micronaut:micronaut-runtime"
implementation "javax.annotation:javax.annotation-api"
implementation "io.micronaut:micronaut-http-server-netty"
implementation "io.micronaut:micronaut-http-client"
runtimeOnly "ch.qos.logback:logback-classic:1.2.3"
```

3. The `micronautVersion` can be set to the current stable version in the
   `settings.gradle.kts`
4. Write a `provider` configuration to point to _localhost:8080_ and reference
   the pact contract file.
5. Implement `startProviderTask` and `terminateProviderTask` hooks to
   start/stop the running server.
6. Write an `Application` bootstrap class.
7. Write a `WeatherController` class to serve back the above JSON response for
   given longitude, latitude and appid parameters.

