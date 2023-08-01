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
            baseName = "data"
            isStatic = true
        }
    }
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