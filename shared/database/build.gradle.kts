plugins {
    id("easy.multiplatform.library")
    id("app.cash.sqldelight") version libs.versions.sqldelight
}

kotlin {
    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(libs.kotlinx.datetime)
                api(libs.sqldelight.coroutines)
                implementation(project(":shared:core"))
                implementation(project(":shared:model"))
            }
        }
        getByName("androidMain") {
            dependencies {
                implementation(libs.sqldelight.android)
            }
        }
        getByName("iosMain") {
            dependencies {
                implementation(libs.sqldelight.native)
            }
        }
    }
}

android {
    namespace = "com.easy.todolist.database"
}

sqldelight {
    databases {
        create("TodoListDatabase") {
            packageName.set("com.easy.todolist.database")
        }
    }
    linkSqlite.set(true)
}