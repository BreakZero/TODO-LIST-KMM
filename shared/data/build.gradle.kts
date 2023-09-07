plugins {
    id("easy.multiplatform.library")
}

kotlin {
    cocoapods {
        framework {
            export(project(":shared:core"))
        }
    }
    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(project(":shared:model"))
                implementation(project(":shared:database"))
                api(project(":shared:core"))
            }
        }
    }
}

android {
    namespace = "com.easy.todolist.data"
}