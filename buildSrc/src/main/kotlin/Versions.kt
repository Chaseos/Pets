import kotlin.String
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val org_jetbrains_kotlinx_kotlinx_serialization: String = "0.20.0"
             // available: "0.20.0-1.4-M1-release-99"

    const val org_jetbrains_kotlinx_kotlinx_coroutines: String = "1.3.5"
             // available: "1.3.5-1.4-M1-release-99"

    const val com_github_bumptech_glide: String = "4.11.0"

    const val androidx_databinding: String = "4.1.0-alpha08"

    const val org_jetbrains_kotlin: String = "1.3.72"

    const val androidx_navigation: String = "2.2.2"

    const val androidx_lifecycle: String = "2.2.0"

    const val com_airbnb_android: String = "3.9.0"

    const val org_koin: String = "2.1.5"

    const val android_arch_lifecycle_extensions: String = "1.1.1"

    const val com_android_databinding_compiler: String = "+"

    const val com_android_tools_build_gradle: String = "4.1.0-alpha08"

    const val androidx_test_core: String = "1.2.0"

    const val retrofit2_kotlinx_serialization_converter: String = "0.5.0"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.7.0"

    const val retrofit2_kotlin_coroutines_adapter: String = "0.9.2"

    const val play_services_location: String = "17.0.0"

    const val paging_runtime_ktx: String = "2.1.2"

    const val swiperefreshlayout: String = "1.0.0"

    const val constraintlayout: String = "2.0.0-beta4"

    const val collection_ktx: String = "1.1.0"

    const val fragment_ktx: String = "1.2.4"

    const val mockito_core: String = "3.3.3"

    const val recyclerview: String = "1.1.0"

    const val lint_gradle: String = "27.1.0-alpha08"

    const val palette_ktx: String = "1.0.0"

    const val appcompat: String = "1.1.0"

    const val liveevent: String = "1.2.0"

    const val core_ktx: String = "1.2.0"

    const val material: String = "1.2.0-alpha01"

    const val retrofit: String = "2.8.1"

    const val picasso: String = "2.71828"

    const val timber: String = "4.7.1"

    const val aapt2: String = "4.1.0-alpha08-6376216"

    const val junit: String = "4.13"

    /**
     * Current version: "6.3"
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "6.3"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
    inline get() =
            id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
