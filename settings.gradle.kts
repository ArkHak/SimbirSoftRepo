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
include(":feature_search")
include(":feature_news")
include(":feature_help_api")
include(":network")
include(":feature_news_api")
