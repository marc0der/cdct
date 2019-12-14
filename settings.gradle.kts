pluginManagement {
    repositories {
        jcenter()
        gradlePluginPortal()
    }
}

gradle.allprojects {
    buildscript {
        repositories { jcenter() }
    }
    repositories { jcenter() }
}

rootProject.name = "cdct"
include("consumer", "provider")
