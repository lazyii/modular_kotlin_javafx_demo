plugins {
    application
    kotlin("jvm") version "1.5.20"
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.beryx.jlink") version "2.24.0"
}

//val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
//val compileJava: JavaCompile by tasks
//compileJava.destinationDir = compileKotlin.destinationDir

allprojects {
    repositories {
        mavenLocal()
        maven("https://repo.huaweicloud.com/repository/maven/")
        mavenCentral()
    }
    buildscript {
        repositories {
            maven("https://repo.huaweicloud.com/repository/maven/")
            mavenCentral()
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.5.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

// because of this bug(https://youtrack.jetbrains.com/issue/KTIJ-18424),pls use gradlew run in console,instead of execute it in idea.
// step 1: set JAVA_HOME="C:\Program Files\Java\jdk-16.0.1+9"
// step 2: gradlew run
application {
    mainModule.set("kt.desktop.coroutines")
    mainClass.set("org.rainday.kt.LauncherKt")
    applicationName =  "KtDesktopCoroutines"
    applicationDefaultJvmArgs = listOf("--add-reads", "kotlin.stdlib=kotlinx.coroutines.core.jvm")
}

jlink{
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    mergedModuleName.set("org.merged.module")
    addExtraDependencies("javafx")
    mergedModule {
        additive = false
    }
    launcher {
        name = "hello"
        noConsole = true
    }
    jpackage {
        installerOptions = listOf("--resource-dir", "src/main/resources", "--vendor", "Rainday")
        if (org.gradle.internal.os.OperatingSystem.current().isWindows) {
            installerOptions = installerOptions + listOf("--win-per-user-install", "--win-dir-chooser", "--win-menu")
        } else if (org.gradle.internal.os.OperatingSystem.current().isWindows) {
        } else if (org.gradle.internal.os.OperatingSystem.current().isWindows) {
        }
        installerName = project.application.applicationName
        imageName = project.application.applicationName
        //imageOptions = listOf("--icon", "src/main/resources/assets/app.ico", "--win-console") //去掉win-console，在启动时才不会出现cmd窗口
        imageOptions = listOf("--icon", "src/main/resources/assets/app.ico")
        appVersion = project.version.toString()
    }
}

javafx {
    modules("javafx.controls", "javafx.fxml", "javafx.web")
}

val JavaVersionCurrent = JavaVersion.VERSION_16
tasks {
    compileJava {
        dependsOn(compileKotlin)
        sourceCompatibility = JavaVersionCurrent.majorVersion
        targetCompatibility = JavaVersionCurrent.majorVersion
        //modularity.inferModulePath.set(true)
    }
    compileKotlin {
        kotlinOptions {
            sourceCompatibility = JavaVersionCurrent.majorVersion
            targetCompatibility = JavaVersionCurrent.majorVersion
            jvmTarget = JavaVersionCurrent.majorVersion
            apiVersion = "1.5" // kotlin api version
            languageVersion = "1.5" // kotlin language version
        }
    }
    test {
        useJUnitPlatform()
    }
}


/*
java {

}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_16.majorVersion
    targetCompatibility = JavaVersion.VERSION_16.majorVersion
}*/
/*tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_16.majorVersion
        apiVersion = "1.5" // kotlin api version
        languageVersion = "1.5" // kotlin language version
    }
}*/
