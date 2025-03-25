import groovy.transform.Field

@Field final versionProperties = ['java-version',
    'maven-clean-version', 'maven-resources-version', 'maven-compiler-version',
    'maven-surefire-version', 'maven-jar-version', 'maven-install-version', 'maven-deploy-version']

def src =  new File(properties['src.dir'])
println "Patching versions in ${src}"

def metadataFile = src.toPath().resolve('main').resolve('resources').resolve('META-INF').resolve('maven').resolve('archetype-metadata.xml').toFile()
patchArchetypeMetadataFile(metadataFile)

def testProjectsFolder = src.toPath().resolve('test').resolve('resources').resolve('projects').toFile()
println "${testProjectsFolder.listFiles()}"
testProjectsFolder.listFiles().each { projectFile -> patchArchetypeProperties(projectFile) }


def patchArchetypeMetadataFile(metadataFile) {
    println "Patch version default values in ${metadataFile}"
    def metadataLines = []
    var previousLine = ''
    
    // read and replace xml lines
    metadataFile.eachLine { line ->
        def propertyKey = versionProperties.find { key ->
            previousLine.contains("<requiredProperty key=\"${key}\">")
        }
        if(propertyKey!=null) {
            metadataLines << "      <defaultValue>${properties[propertyKey]}</defaultValue>"
        } else {
            metadataLines << line
        }
        previousLine = line
    }
    
    // then overwrite file content
    metadataFile.withWriter { out ->
        metadataLines.each { out.println it }
    }
}

def patchArchetypeProperties(projectFile) {
    def propertiesFile = projectFile.toPath().resolve('archetype.properties').toFile()
    println "Patch versions in ${propertiesFile}"
    def propertiesLines = []
    def foundPropertyKeys = []
    
    // read and replace properties
    propertiesFile.eachLine { line ->
        def propertyKey = versionProperties.find { key ->
            line.startsWith("${key}=")
        }
        if(propertyKey!=null) {
            propertiesLines << "${propertyKey}=${properties[propertyKey]}"
            foundPropertyKeys << propertyKey
        } else {
            propertiesLines << line
        }
    }
    
    versionProperties.each { key ->
        if(!foundPropertyKeys.contains(key)) {
            propertiesLines << "${key}=${properties[key]}"
        }
    }
    
    // then overwrite file content
    propertiesFile.withWriter { out ->
        propertiesLines.each { out.println it }
    }
}

