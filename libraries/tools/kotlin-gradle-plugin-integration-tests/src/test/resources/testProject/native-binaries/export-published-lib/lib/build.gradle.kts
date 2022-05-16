plugins {
    kotlin("multiplatform").version("<pluginMarkerVersion>")
    `maven-publish`
}

repositories {
    mavenLocal()
    maven("../repo")
    mavenCentral()
}

group = "com.example"
version = "1.0"

kotlin {
    jvm()
    linuxX64()
    iosX64()
    iosArm64()
}

publishing {
    repositories {
        maven("../repo")
    }
}
