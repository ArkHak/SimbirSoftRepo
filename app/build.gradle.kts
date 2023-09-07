plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "o.mysin.simbirsoftappjava"
    compileSdk = 33

    defaultConfig {
        applicationId = "o.mysin.simbirsoftappjava"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
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

    // Android Navigation
    implementation(Navigation.navigationFragmentKtx)
    implementation(Navigation.navigationUIKtx)
    implementation(Navigation.navigationDynamicFeaturesFragment)
    implementation(Deps.legacySupportV4)

    // ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.viewBindingPropertyDelegate)

    // Lifecycle components
    implementation(Lifecycle.lifecycleExtensions)
    implementation(Lifecycle.lifecycleViewModel)
    implementation(Lifecycle.lifecycleLiveData)

    //Gson
    implementation(Gson.gson)

    //Dagger
    implementation(Dagger.dagger)
    kapt(Dagger.daggerCompiler)

    //Module
    implementation(project(":feature_history"))
    implementation(project(":feature_authorization"))
    implementation(project(":feature_profile"))
    implementation(project(":feature_help"))
    implementation(project(":feature_help_api"))
    implementation(project(":feature_search"))
    implementation(project(":feature_news"))
    implementation(project(":feature_news_api"))
    implementation(project(":core"))
    implementation(project(":network"))
}
