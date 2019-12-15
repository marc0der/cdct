plugins {
    `java-library`
    id("org.jetbrains.kotlin.jvm").version("1.3.61")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.slf4j:slf4j-simple:1.7.29")

    testImplementation("junit:junit:4.12")
}
