import net.minecraftforge.gradle.user.UserBaseExtension
import java.util.*

buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "Jitpack"
            setUrl("https://jitpack.io")
        }
        maven {
            name = "Forge"
            setUrl("https://maven.minecraftforge.net")
        }
    }
    dependencies {
        classpath("com.github.GregTechCE:ForgeGradle:FG_2.3-SNAPSHOT")
        classpath("org.eclipse.jgit:org.eclipse.jgit:5.8.0.202006091008-r")
        classpath("org.apache.commons:commons-lang3:3.12.0")
    }
}

apply {
    plugin("net.minecraftforge.gradle.forge")
}

val Project.minecraft: UserBaseExtension
    get() = extensions.getByName<UserBaseExtension>("minecraft")

val config: Properties = file("build.properties").inputStream().let {
    val prop = Properties()
    prop.load(it)
    return@let prop
}

val modVersion = config["tfc.version"] as String
val mcVersion = config["mc.version"] as String
val forgeVersion = "$mcVersion-${config["forge.version"]}"
val shortVersion = mcVersion.substring(0, mcVersion.lastIndexOf("."))
val strippedVersion = shortVersion.replace(".", "") + "0";

version = "$mcVersion-$modVersion"
group = "tfc"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

configure<BasePluginConvention> {
    archivesBaseName = "TerraFirmaCraft"
}

configure<UserBaseExtension> {
    version = forgeVersion
    mappings = config["mcp.version"] as String
    runDir = "run"
    replace("@VERSION@", modVersion)
    replaceIn("TerraFirmaCraft.java")
}

repositories {
    maven {
        name = "JEI"
        setUrl("http://dvs1.progwml6.com/files/maven/")
    }
    maven {
        name = "CraftTweaker"
        setUrl("https://maven.blamejared.com/")
    }
    maven {
        name = "CCL, CASM"
        setUrl("http://chickenbones.net/maven/")
    }
    maven {
        name = "CTM"
        setUrl("https://maven.tterrag.com/")
    }
    maven {
        setUrl("https://maven.cleanroommc.com")
    }
    maven {
        setUrl("https://cursemaven.com")
    }
    maven {
        setUrl("https://repo.spongepowered.org/maven")
    }
}

dependencies {

    "compile"("zone.rong:mixinbooter:7.0")

    "deobfCompile"("curse.maven:codechicken-lib-1-8-242818:2779848")
    "deobfCompile"("codechicken:ChickenASM:$shortVersion-${config["chickenasm.version"]}")
    "deobfCompile"("curse.maven:had-enough-items-557549:3862733")
    "deobfCompile"("curse.maven:the-one-probe-245211:2667280")
    "deobfCompile"("curse.maven:fluidloggedapi-485654:4128089")
    // "deobfCompile"("curse.maven:dramatictrees-637540:4059721")
    "deobfCompile"("CraftTweaker2:CraftTweaker2-MC$strippedVersion-Main:${config["crafttweaker.version"]}")

    "compileOnly" ("curse.maven:hwyla-253449:2568751")

    "provided"(files("libs/gregtech-1.12.2-2.4.3-beta.jar"))
}

val processResources: ProcessResources by tasks
val sourceSets: SourceSetContainer = the<JavaPluginConvention>().sourceSets

processResources.apply {
    inputs.property("version", modVersion)
    inputs.property("mcversion", forgeVersion)

    from(sourceSets["main"].resources.srcDirs) {
        include("mcmod.info")
        expand(mapOf("version" to modVersion, "mcversion" to forgeVersion))
    }

    from(sourceSets["main"].resources.srcDirs) {
        exclude("mcmod.info")
    }

    // Access Transformer jar manifest info
    rename("(.+_at.cfg)", "META-INF/$1")
}
