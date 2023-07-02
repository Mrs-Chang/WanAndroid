import com.android.build.gradle.internal.api.ApkVariantOutputImpl

plugins {
    id("com.android.application")
    //id("org.jetbrains.kotlin.android")可以替换如下：
    kotlin("android")
}

android {
    namespace = "com.chang.android"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.chang.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    //签名配置
    signingConfigs {
        register("release") {
            keyAlias = "chang"
            keyPassword = "123456"
            storeFile = file("src/main/jks/chang.jks")
            storePassword = "123456"
        }
    }

    //编译类型
    buildTypes {
        debug {

        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    //输出类型
    android.applicationVariants.all {
        val buildType = this.buildType.name
        outputs.all {
            if (this is ApkVariantOutputImpl) {
                if (buildType == "release") {
                    this.outputFileName = "WanAndroid_V${defaultConfig.versionName}_$buildType.apk"
                }
            }
        }
    }

    //依赖操作
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //TODO 下面改为VersionCatalog
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    //okhttp-logging-interceptor
    implementation(libs.logging.interceptor)
    //mvvm
    implementation(libs.live.data.ktx)
    implementation(libs.view.model.ktx)
}