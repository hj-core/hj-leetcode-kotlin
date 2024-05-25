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
    gradleVersion = "8.8-rc-1"
    distributionType = Wrapper.DistributionType.ALL
}

kotlin {
    jvmToolchain(21)
}