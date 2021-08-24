import com.ideallim.demoapp.build.dependencies.addComposeOfficialDependencies
import com.ideallim.demoapp.build.dependencies.addCoreAndroidDependencies


plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}


dependencies {
    addComposeOfficialDependencies()
    addCoreAndroidDependencies()

}
