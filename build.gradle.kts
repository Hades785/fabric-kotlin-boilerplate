import java.util.Properties

plugins {
    kotlin("jvm") version "1.4.21"
    id("fabric-loom") version "0.5.12"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// load properties
val props = rootDir.resolve("gradle.properties").bufferedReader().use {
    Properties().apply {
        load(it)
    }
}

val minecraft: String by props
val fabricLoader: String by props
val yarnMappings: String by props
val fabricApi: String by props
val fabricKotlin: String by props
val modId: String by props
val modVersion: String by props
val group: String by props

base {
    archivesBaseName = modId
}


project.group = group
version = modVersion


repositories {
    maven(url = "http://maven.fabricmc.net/") {
        name = "Fabric"
    }
    maven(url = "https://kotlin.bintray.com/kotlinx") {
        name = "Kotlinx"
    }
    mavenLocal()
    mavenCentral()
    jcenter()
}

minecraft {

}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = minecraft)
    mappings(group = "net.fabricmc", name = "yarn", version = "$minecraft+$yarnMappings", classifier = "v2")

    modImplementation("net.fabricmc:fabric-loader:$fabricLoader")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApi")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlin")
}

//val publishToMavenLocal = rootProject.tasks.getByName<Task>("publishToMavenLocal")

//val remapJar = tasks.getByName<RemapJarTask>("remapJar") {
//    (this as Task).dependsOn(publishToMavenLocal)
//}

val fabricApiVersion = ""
val kotlinVersion = ""

tasks.getByName<ProcessResources>("processResources") {
    filesMatching("fabric.mod.json") {
        expand(
            mutableMapOf(
                "modid" to modId,
                "version" to modVersion,
                "kotlinVersion" to kotlinVersion,
                "fabricApiVersion" to fabricApiVersion
            )
        )
    }
}