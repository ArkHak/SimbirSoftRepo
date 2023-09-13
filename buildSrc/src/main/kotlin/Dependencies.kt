internal object Version {

    //Libs
    const val desugarJdk = "2.0.3"

    //Deps
    const val coreKtx = "1.9.0"
    const val appCompat = "1.6.1"
    const val material = "1.9.0"
    const val constraintlayout = "2.1.4"
    const val legacySupportV4 = "1.0.0"

    //Testing
    const val jUnit = "4.13.2"
    const val extJInit = "1.1.5"
    const val espressoCore = "3.5.1"

    //Fragment
    const val fragmentKtx = "1.6.1"

    //Dagger
    const val dagger = "2.47"

    //Serialization
    const val kotlinxSerializationJson = "1.5.1"

    //GSON
    const val gson = "2.10"

    //Lifecycle components
    const val lifecycleViewModel = "2.6.1"
    const val lifecycleExtensions = "2.2.0"
    const val lifecycleLiveData = "2.6.1"

    // ViewBindingPropertyDelegate by kirich1409
    const val viewBindingPropertyDelegate = "1.5.3"

    // Android Navigation
    const val navigationFragmentKtx = "2.6.0"
    const val navigationUIKtx = "2.6.0"
    const val navigationDynamicFeaturesFragment = "2.6.0"

    //ViewPager2
    const val viewPager = "1.0.0"

    //Coroutines
    const val coroutinesAndroid = "1.6.4"

    //RxJava3
    const val rxJavaCore = "3.1.3"
    const val rxAndroid = "3.0.0"
    const val rxBinding = "4.0.0"
    const val rxBindingMaterial = "4.0.0"

    //Room
    const val roomRuntime = "2.5.2"
    const val roomKtx = "2.5.2"
    const val roomCompiler = "2.5.2"

    //Retrofit2
    const val retrofitCore = "2.9.0"
    const val retrofitAdapterRxJava = "2.9.0"
    const val retrofitConverterGson = "2.9.0"

    //OkHttp3
    const val okhttpLoggingInterceptor = "4.9.1"

    // Kotlinx datetime
    const val datetimeKotlinx = "0.4.0"

    //Coil
    const val coilCore = "1.4.0"

    //Compose
    const val composeBom = "2023.04.00"

}

object Libs {
    const val desugarJdk = "com.android.tools:desugar_jdk_libs:${Version.desugarJdk}"
}

object Deps {
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintlayout}"
    const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Version.legacySupportV4}"
}

object Testing {
    const val jUnit = "junit:junit:${Version.jUnit}"
    const val extJInit = "androidx.test.ext:junit:${Version.extJInit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
}

object Fragment {
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtx}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Version.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
}

object Serialization {
    const val kotlinxSerializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinxSerializationJson}"
}

object Gson {
    const val gson = "com.google.code.gson:gson:${Version.gson}"
}

object Lifecycle {
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleViewModel}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleExtensions}"
    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycleLiveData}"
}

object ViewBindingDelegate {
    const val viewBindingPropertyDelegate =
        "com.github.kirich1409:viewbindingpropertydelegate:${Version.viewBindingPropertyDelegate}"
}

object Navigation {
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigationFragmentKtx}"
    const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigationUIKtx}"
    const val navigationDynamicFeaturesFragment =
        "androidx.navigation:navigation-dynamic-features-fragment:${Version.navigationDynamicFeaturesFragment}"
}

// ViewPager2
object ViewPager {
    const val viewPager = "androidx.viewpager2:viewpager2:${Version.viewPager}"
}

//Coroutines
object Coroutines {
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}"
}

//RXJava
object RxJava {
    const val core = "io.reactivex.rxjava3:rxjava:${Version.rxJavaCore}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Version.rxAndroid}"
    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${Version.rxBinding}"
    const val rxBindingMaterial = "com.jakewharton.rxbinding4:rxbinding-material:${Version.rxBindingMaterial}"
}

//Database
object Database {
    const val roomRuntime = "androidx.room:room-runtime:${Version.roomRuntime}"
    const val roomKtx = "androidx.room:room-ktx:${Version.roomKtx}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.roomCompiler}"
}

//Network
object Network {

    //Retrofit2
    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Version.retrofitCore}"
        const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava3:${Version.retrofitAdapterRxJava}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofitConverterGson}"
    }

    //OkHttp
    object OkHttp{
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLoggingInterceptor}"
    }
}

//Kotlinx datetime
object Datetime {
    const val datetimeKotlinx = "org.jetbrains.kotlinx:kotlinx-datetime:${Version.datetimeKotlinx}"
}

//ImageLoader
object Coil{
    const val core = "io.coil-kt:coil:${Version.coilCore}"
}

//Compouse
object Compose{
    const val composeBom = "androidx.compose:compose-bom:${Version.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeMaterial = "androidx.compose.material:material"
    const val composeMaterialIcons = "androidx.compose.material:material-icons-extended"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
}


