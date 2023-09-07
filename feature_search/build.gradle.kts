plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.mys_ya.feature_search"
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

    // ViewBindingPropertyDelegate
    implementation(ViewBindingDelegate.viewBindingPropertyDelegate)

    // Lifecycle components
    implementation(Lifecycle.lifecycleExtensions)
    implementation(Lifecycle.lifecycleViewModel)
    implementation(Lifecycle.lifecycleLiveData)

    // ViewPager2
    implementation(ViewPager.viewPager)

    // Coroutines
    implementation(Coroutines.coroutinesAndroid)

    //Serialization
    implementation(Serialization.kotlinxSerializationJson)

    // Fragment
    implementation(Fragment.fragmentKtx)

    //Dagger
    implementation(Dagger.dagger)
    kapt(Dagger.daggerCompiler)

    implementation(project(":core"))
    implementation(project(":feature_news_api"))
    implementation(project(":feature_search_api"))
}
