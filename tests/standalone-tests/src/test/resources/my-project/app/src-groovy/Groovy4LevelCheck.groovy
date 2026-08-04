package com.company

// Groovy 4 level lock: groovy.transform.RecordType only exists since Groovy 4.0, so this
// class fails to compile if the build-time Groovy silently reverts to a 3.x level.
// A class-literal reference is used on purpose: it is a pure resolution check, immune to
// parser differences between plain Groovy and the groovy-eclipse patched compiler (native
// 'record' declarations are rejected by the greclipse groovy40 parser).
class Groovy4LevelCheck {

    static final Class<?> GROOVY_4_ONLY_TYPE = groovy.transform.RecordType
}
