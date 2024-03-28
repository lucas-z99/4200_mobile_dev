plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.a4200_group_project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.a4200_group_project"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation ("com.squareup.picasso:picasso:2.8")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    //V1
//    implementation(files("libs/postgresql-42.2.27.jre6.jar")) // JDBC driver (for postgreSQL)
//
//    val work_version = "2.9.0" // WorkManager
//    implementation("androidx.work:work-runtime:$work_version") // java
//    implementation("androidx.work:work-runtime-ktx:$work_version") // kotlin + coroutines

    //V2
//    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

    //V3
    implementation ("com.squareup.picasso:picasso:2.71828") // picasso


}