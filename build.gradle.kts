import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    application
    kotlin("jvm") version "1.5.0"
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.beryx.jlink") version "2.23.8"
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
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.5.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    //components { withModule<ModularKotlinRule>(kotlin("stdlib")) }
}

application {
    applicationName =  "KtDesktopCoroutines"
    mainModule.set("kt.desktop.coroutines")
    mainClass.set("org.rainday.kt.LauncherKt")
}

jlink{
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    mergedModuleName.set("org.merged.module")
    addExtraDependencies("javafx")
    mergedModule {
        additive = false
        //uses("java.sql.Driver")
        //provides("java.sql.Driver").with("org.hsqldb.jdbc.JDBCDriver")
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

println(":sssss: "+JavaVersionCurrent.majorVersion)

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
