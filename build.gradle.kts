plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.hj"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.wrapper {
    gradleVersion = "8.14.2"
    distributionType = Wrapper.DistributionType.ALL
}

kotlin {
    jvmToolchain(23)
}
