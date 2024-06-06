plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //id("kotlin-kapt")
    //kotlin("jvm") version "2.0.0"
}

android {
    namespace = "com.example.tfg"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.tfg"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    //compila y revisa que jdk-19 quiere: 'C:\Program Files\Java\jdk-19' (Windows Registry) used for java installations does not exist
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    java{
        toolchain{
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Jdbc que conecta con la base de datos
    //implementation(files("jdbc"))
    //implementation("androidx.appcompat:appcompat:1.7.0")
    //Modificación y comentarios de https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=es-419#kts
    val lifecycle_version = "2.8.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    implementation("androidx.compose.runtime:runtime-livedata:1.4.3")

    //Conector Base de datos
    //implementation("mysql:mysql-connector-java:8.0.33")
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    //Conexión Base de datos con Volley referencia: https://www.youtube.com/watch?v=I33dlFY3qWU
    //implementation("com.android.volley:volley:1.2.1")

    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    //kapt ("androidx.room:room-compiler:$room_version")

    implementation("androidx.compose.material:material-icons-extended")

    //referecnia : https://www.youtube.com/watch?v=eNuaMn4ukdo
    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.0.0")
    implementation ("androidx.activity:activity-compose:1.5.0")
    implementation("androidx.compose.ui:ui-android:1.6.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}