// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application")  version "7.4.2" apply false
//    id("com.android.library") version "7.4.2" apply false
//    id("org.jetbrains.kotlin.android")  version "1.8.0" apply false
//}
/**
 * Gradle 7.0 VersionCatalog 简单使用
 * https://juejin.cn/post/7173865686182133768
 * */
//gradle bug :https://github.com/gradle/gradle/issues/20131
@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.hilt.plugin) apply false
}