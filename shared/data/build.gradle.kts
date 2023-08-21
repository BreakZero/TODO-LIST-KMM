plugins {
    id("easy.multiplatform.library")
}

kotlin {
    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(project(":shared:model"))
                implementation(project(":shared:database"))
                implementation(project(":shared:core"))
            }
        }
    }
}

android {
    namespace = "com.easy.todolist.data"
}