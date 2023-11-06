plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    namespace = "ru.mys_ya.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

dependencies {
    coreLibraryDesugaring(Libs.desugarJdk)

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintlayout)
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.extJInit)
    androidTestImplementation(Testing.espressoCore)

    //Room
    implementation(Database.roomRuntime)
    implementation(Database.roomKtx)
    kapt(Database.roomCompiler)

    //Serialization
    implementation(Serialization.kotlinxSerializationJson)

    // Kotlinx datetime
    implementation(Datetime.datetimeKotlinx)

    //Gson
    implementation(Gson.gson)

    //Dagger
    implementation(Dagger.dagger)
    kapt(Dagger.daggerCompiler)
}
