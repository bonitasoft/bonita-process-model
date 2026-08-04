package com.company

// Pins the FIXTURE COMPILE CLASSPATH to the Groovy 4 line: groovy.transform.RecordType only
// exists since Groovy 4.0, so this class fails to compile if the groovy-all dependency of this
// fixture pom reverts to a 3.x line. It does NOT lock the build-time compiler level - the class
// literal resolves from the compile classpath, not from the compiler runtime; the compiler level
// is asserted directly in the tests via GroovySystem.getVersion().
// Also the only fixture in a non-default package, exercising the directory-creation branch of
// the OSGi output-phase workaround in CustomGroovyArtifactProvider.
class Groovy4LevelCheck {

    static final Class<?> GROOVY_4_ONLY_TYPE = groovy.transform.RecordType
}
