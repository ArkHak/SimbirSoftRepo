plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.mys_ya.feature_help"
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

    //ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.viewBindingPropertyDelegate)

    //Fragment
    implementation(Fragment.fragmentKtx)

    //Lifecycle components
    implementation(Lifecycle.lifecycleExtensions)
    implementation(Lifecycle.lifecycleViewModel)
    implementation(Lifecycle.lifecycleLiveData)

    //Dagger
    implementation(Dagger.dagger)
    kapt(Dagger.daggerCompiler)

    //Coil
    implementation(Coil.core)

    //Gson
    implementation(Gson.gson)

    //Module
    implementation(project(":core"))
    implementation(project(":feature_help_api"))
    implementation(project(":network"))
}
