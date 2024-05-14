import com.android.tools.r8.internal.id

plugins {
    alias(libs.plugins.androidApplication)
}

//Realm Database related
//apply plugin: "realm-android" with android studio latest version syntax
apply { plugin("realm-android") }


android {
    namespace = "com.example.expensesmanagerapp"
    compileSdk = 34

    //initiate viewBinding for initiation of .xml tags
    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.expensesmanagerapp"
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
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

        // realm database dependencies, another way of integrating Realm Database
      // implementation ("io.realm:realm-android-library:10.5.1")
      // annotationProcessor ("io.realm:realm-android-library:10.5.1")

}