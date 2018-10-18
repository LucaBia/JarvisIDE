@file:Suppress("UNUSED_EXPRESSION")

plugins {
    java
}

group = "gt.edu.uvg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testCompile("junit", "junit", "4.12")

    testCompile("net.sf.phat", "sphinx4-core", "5prealpha")
    testCompile("net.sf.phat", "sphinx4-data", "5prealpha")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}