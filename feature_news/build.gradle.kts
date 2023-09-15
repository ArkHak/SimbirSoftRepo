plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "ru.mys_ya.feature_news"
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    coreLibraryDesugaring(Libs.desugarJdk)

    //Compose
    implementation(platform(Compose.composeBom))
    implementation(Compose.composeUi)
    implementation(Compose.composeMaterial)
    implementation(Compose.composeMaterialIcons)
    implementation(Compose.composeUiTooling)
    implementation(Compose.composeUiToolingPreview)
    implementation(Compose.composeLiveData)

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintlayout)
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.extJInit)
    androidTestImplementation(Testing.espressoCore)

    //Android Navigation
    implementation(Navigation.navigationFragmentKtx)
    implementation(Navigation.navigationUIKtx)
    implementation(Navigation.navigationDynamicFeaturesFragment)
    implementation(Deps.legacySupportV4)

    //ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.viewBindingPropertyDelegate)

    //Lifecycle components
    implementation(Lifecycle.lifecycleExtensions)
    implementation(Lifecycle.lifecycleViewModel)
    implementation(Lifecycle.lifecycleLiveData)

    //Coil
    implementation(Coil.core)

    //Coroutines
    implementation(Coroutines.coroutinesAndroid)

    //Dagger
    implementation(Dagger.dagger)
    kapt(Dagger.daggerCompiler)

    //Gson
    implementation(Gson.gson)

    //Fragment
    implementation(Fragment.fragmentKtx)

    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":feature_news_api"))
    implementation(project(":feature_help_api"))
}
