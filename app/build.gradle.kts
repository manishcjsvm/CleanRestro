plugins {
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "com.assignment.cleanrestro"
    compileSdk = Integer.parseInt(libs.versions.compileSDK.get())

    defaultConfig {
        applicationId = "com.assignment.cleanrestro"
        minSdk = Integer.parseInt(libs.versions.minSDK.get())
        targetSdk = Integer.parseInt(libs.versions.targetSDK.get())
        versionCode = Integer.parseInt(libs.versions.versionCode.get())
        versionName = libs.versions.versionName.get()

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))


}