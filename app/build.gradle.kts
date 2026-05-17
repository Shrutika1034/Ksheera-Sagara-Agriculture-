plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {

    namespace = "com.example.ksheerasagara"

    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.ksheerasagara"

        minSdk = 26
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        sourceCompatibility =
            JavaVersion.VERSION_11

        targetCompatibility =
            JavaVersion.VERSION_11
    }

    kotlinOptions {

        jvmTarget = "11"
    }

    buildFeatures {

        viewBinding = true
    }
}

dependencies {

    // Core Android

    implementation(
        "androidx.core:core-ktx:1.13.1"
    )

    implementation(
        "androidx.appcompat:appcompat:1.7.0"
    )

    implementation(
        "com.google.android.material:material:1.12.0"
    )

    implementation(
        "androidx.constraintlayout:constraintlayout:2.1.4"
    )

    // Lifecycle

    implementation(
        "androidx.lifecycle:lifecycle-livedata-ktx:2.8.3"
    )

    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3"
    )

    // RecyclerView

    implementation(
        "androidx.recyclerview:recyclerview:1.3.2"
    )

    // Room Database

    implementation(
        "androidx.room:room-runtime:2.6.1"
    )

    implementation(
        "androidx.room:room-ktx:2.6.1"
    )

    kapt(
        "androidx.room:room-compiler:2.6.1"
    )

    // Coroutines

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1"
    )

    // PDF Generator

    implementation(
        "com.itextpdf:itextg:5.5.10"
    )
    implementation("com.google.android.material:material:1.11.0")

    implementation("com.itextpdf:itextg:5.5.10")

    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation("androidx.work:work-runtime-ktx:2.9.0")

    // Unit Testing

    testImplementation(
        "junit:junit:4.13.2"
    )

    // Android Testing

    androidTestImplementation(
        "androidx.test.ext:junit:1.2.1"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.6.1"
    )
}