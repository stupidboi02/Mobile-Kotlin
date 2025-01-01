plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // Đảm bảo sử dụng id đúng cách
}

android {
    namespace = "com.example.studentmanager_room"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.studentmanager_room"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}
kapt {
    correctErrorTypes = true
}

dependencies {
    // Các dependency khác
    val roomVersion = "2.5.2"

    // Room dependencies
    implementation("androidx.room:room-runtime:2.5.2")  // Kiểm tra xem phiên bản này có đúng không
    kapt("androidx.room:room-compiler:2.5.2")  // Cũng cần kiểm tra phiên bản của KAPT
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.room:room-ktx:2.5.2") // Đảm bảo rằng tất cả phiên bản thư viện là chính xác
    // Các dependency test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
