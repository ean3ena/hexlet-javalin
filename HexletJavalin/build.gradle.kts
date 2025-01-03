import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("application")
}

application {
    mainClass.set("org.example.hexlet.App")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("io.javalin:javalin:6.2.0")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("io.javalin:javalin-rendering:6.1.6")
    implementation("gg.jte:jte:3.1.15")
    implementation("net.datafaker:datafaker:2.0.1")
    implementation("org.projectlombok:lombok:1.18.36")
    implementation("org.apache.commons:commons-text:1.13.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.h2database:h2:2.2.220")
    implementation("com.zaxxer:HikariCP:5.0.1")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("com.konghq:unirest-java:3.13.0")
}

tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}