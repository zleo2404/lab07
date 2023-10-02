plugins {
    java
    id("org.danilopianini.gradle-java-qa") version "1.18.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
    testLogging.showStandardStreams = true
}

spotbugs {
    omitVisitors.set(listOf("ComparatorIdiom"))
}

tasks.javadoc {
    isFailOnError = false
}
