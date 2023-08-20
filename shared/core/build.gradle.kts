plugins {
    id("easy.multiplatform.library")
}

kotlin {
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "core"
            isStatic = false
        }
    }
    sourceSets {
//        getByName("androidMain") {
//            dependencies {
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
//            }
//        }
        getByName("commonMain") {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(libs.coroutine.core)
            }
        }
    }
}

android {
    namespace = "com.easy.todolist.core"
}
dependencies {
    implementation(libs.androidx.annotation.jvm)
}
