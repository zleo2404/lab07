plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "0.39.0"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("it.unibo.mvc.DrawNumberApp")
}

spotbugs {
    omitVisitors.set(listOf("FindReturnRef", "DumbMethods"))
}

tasks.javadoc {
    isFailOnError = false
}
