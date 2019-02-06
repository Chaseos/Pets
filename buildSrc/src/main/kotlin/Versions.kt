import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val android_arch_lifecycle_extensions: String = "1.1.1" 

    const val android_arch_navigation: String = "1.0.0-beta01"

    const val appcompat: String = "1.0.2" 

    const val constraintlayout: String = "2.0.0-alpha3" 

    const val paging_runtime: String = "2.1.0" 

    const val recyclerview: String = "1.0.0" 

    const val androidx_test_core: String = "1.1.0" 

    const val com_android_tools_build_gradle: String = "3.3.0"

    const val lint_gradle: String = "26.4.0-beta02" 

    const val material: String = "1.1.0-alpha03"

    const val picasso: String = "2.71828" 

    const val retrofit: String = "2.5.0" 

    const val com_tickaroo_tikxml: String = "0.8.13" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val junit: String = "4.12" 

    const val org_jetbrains_kotlin: String = "1.3.20" 

    const val org_jetbrains_kotlinx: String = "1.1.1" 

    const val mockito_core: String = "2.24.0"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.1.1"

        const val currentVersion: String = "5.2"

        const val nightlyVersion: String = "5.3-20190205001603+0000"

        const val releaseCandidate: String = ""
    }
}
