plugins {
    id("easy.multiplatform.library")
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../TODOList-iOS/Podfile")
        framework {
            baseName = "core"
            isStatic = true
        }
    }
    sourceSets {
        getByName("androidMain") {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
            }
        }
    }
}

android {
    namespace = "com.easy.todolist.core"
}
