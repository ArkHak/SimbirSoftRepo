pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SimbirSoftAppJava"
include(":app")
include(":core")
include(":feature_history")
include(":feature_authorization")
include(":feature_profile")
include(":feature_help")
