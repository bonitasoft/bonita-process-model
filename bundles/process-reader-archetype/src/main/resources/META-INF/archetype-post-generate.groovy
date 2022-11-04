import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Logger

def logger = Logger.getLogger("Archetype post generate")

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

if(Boolean.valueOf(request.properties.get("wrapper"))) {
    installMavenWrapper(logger, projectPath)
}

def installMavenWrapper(Logger logger, Path projectPath) {
	def wrapperCommand = 'mvn -N io.takari:maven:0.7.7:wrapper'
    def cmd = System.properties['os.name'].toLowerCase().contains('windows') ? "cmd /c $wrapperCommand" : wrapperCommand
    logger.info("Installing maven wrapper... ($cmd)")
    println cmd.execute(null, projectPath.toFile()).text
}
