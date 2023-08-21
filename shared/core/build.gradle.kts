plugins {
    id("easy.multiplatform.library")
}

kotlin {
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
