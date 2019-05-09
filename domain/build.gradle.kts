plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    id(Plugin.androidJunit5)
}

android {

    compileSdkVersion(AppConfig.Sdk.targetVersion)

    defaultConfig {
        minSdkVersion(AppConfig.Sdk.minVersion)
        targetSdkVersion(AppConfig.Sdk.targetVersion)
        versionCode = AppConfig.Version.code
        versionName = AppConfig.Version.name
        testInstrumentationRunner = AppConfig.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

    implementation(project(":util"))
    implementation(Dependency.Kotlin.stblib)
    implementation(Dependency.Okhttp3.okhttp)
    implementation(Dependency.Okhttp3.logging)
    implementation(Dependency.Retrofit2.retrofit)
    implementation(Dependency.Retrofit2.coroutinesAdapter)
    implementation(Dependency.Retrofit2.gsonConvertor)
    implementation(Dependency.Koin.core)
    implementation(Dependency.Koin.coreExt)

    testImplementation(Dependency.KotlinTest.kotlinTest)

    androidTestImplementation(Dependency.AndroidTest.runner)
    androidTestImplementation(Dependency.AndroidTest.espressoCore)
}
