@file:Suppress("unused")

object Versions {

    const val kotlin = "1.3.21"

    // Android libraries
    const val appCompat = "28.0.0"
    const val constraintLayout = "1.1.3"

    // Third party libraries
    const val dagger = "2.21"
    const val retrofit = "2.5.0"
    const val okhttp = "3.12.1"
    const val rxjava = "2.2.7"
    const val rxAndroid = "2.1.1"

    // Unit Testing
    const val junit = "4.12"
    const val mockito = "2.24.5"
    const val mockitoKotlin = "1.6.0"

    // Acceptance Testing
    const val espresso = "3.0.2"

    // Image loading
    const val picasso = "2.71828"

    // Arch components
    const val archComponents = "1.1.1"

    // Android tools
    const val androidTools = "26.3.1"

    // Coroutines
    const val coroutinesVersion = "1.1.1"
}

object MainApplicationDependencies {
    const val kotlin                            = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val appCompat                         = "com.android.support:appcompat-v7:${Versions.appCompat}"
    const val constraintLayout                  = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"
    const val recyclerView                      = "com.android.support:recyclerview-v7:${Versions.appCompat}"
    const val design                            = "com.android.support:design:${Versions.appCompat}"
    const val androidAnnotations                = "com.android.support:support-annotations:${Versions.appCompat}"
    const val supportV4                         = "com.android.support:support-v4:${Versions.appCompat}"
    const val daggerCompiler                    = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger                            = "com.google.dagger:dagger:${Versions.dagger}"
    const val retrofit                          = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson                      = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxAdapter                 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttpLoggingInterceptor          = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp                            = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val picasso                           = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val rxjava2                           = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxandroid                         = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val archComponentLifeCycleExtensions  = "android.arch.lifecycle:extensions:${Versions.archComponents}"
    const val archComponentLifeCycleCompiler    = "android.arch.lifecycle:compiler:${Versions.archComponents}"
    const val coroutines    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
}

object UnitTestingDependencies {
    const val kotlin                    = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinTest                = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val mockitoKotlin             = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    const val junit                     = "junit:junit:${Versions.junit}"
    const val mockito                   = "org.mockito:mockito-inline:${Versions.mockito}"
    const val archComponentCoreTesting  = "android.arch.core:core-testing:${Versions.archComponents}"
}

object AndroidTestingDependencies {
    const val espressoCore          = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoIntents       = "com.android.support.test.espresso:espresso-intents:${Versions.espresso}"
    const val espressoContrib       = "com.android.support.test.espresso:espresso-contrib:${Versions.espresso}"
}