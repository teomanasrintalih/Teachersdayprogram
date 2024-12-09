plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.cloffystudio.ogretmenim"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cloffystudio.ogretmenim"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.work:work-runtime:2.8.0")  // WorkManager bağımlılığı
    implementation(libs.appcompat)                       // AppCompat
    implementation(libs.material)                        // Material Design
    implementation(libs.activity)                        // Activity için yardımcı sınıflar
    implementation(libs.constraintlayout)               // ConstraintLayout
    testImplementation(libs.junit)                      // Testler için JUnit
    androidTestImplementation(libs.ext.junit)           // Android testleri için JUnit
    androidTestImplementation(libs.espresso.core)       // UI testleri için Espresso
    implementation("com.airbnb.android:lottie:3.4.0")    // Animasyonlar için Lottie
    // WorkManager için ekstra bağımlılıklar
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")  // Yaşam döngüsü bağımlılığı
}