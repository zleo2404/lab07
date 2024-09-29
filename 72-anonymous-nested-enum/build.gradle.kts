plugins {
    java
    id("org.danilopianini.gradle-java-qa") version "1.67.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.1")
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
