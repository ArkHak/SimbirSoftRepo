plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.mys_ya.feature_authorization"
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

    buildFeatures {
        viewBinding = true
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

    //Android Navigation
    implementation(Navigation.navigationDynamicFeaturesFragment)
    implementation(Deps.legacySupportV4)

    //ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.viewBindingPropertyDelegate)

    //RxJava
    implementation(RxJava.core)
    implementation(RxJava.rxAndroid)
    implementation(RxJava.rxBinding)
    implementation(RxJava.rxBindingMaterial)

    //Module
    implementation(project(":core"))
}
