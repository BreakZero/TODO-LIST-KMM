pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Easy-TODOList"
include(":TODOList-Android")
include(":shared:model")
include(":shared:database")
include(":shared:data")
